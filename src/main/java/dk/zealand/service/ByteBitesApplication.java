package dk.zealand.service;

import dk.zealand.domain.Dish;
import dk.zealand.domain.Order;

import java.util.Scanner;

public class ByteBitesApplication {

    private final Scanner scanner;
    private final MenuService menuService;
    private final OrderService orderService;

    public ByteBitesApplication(Scanner scanner, MenuService menuService, OrderService orderService) {
        this.scanner = scanner;
        this.menuService = menuService;
        this.orderService = orderService;
    }

    public void run() {
        boolean running = true;

        System.out.println("ByteBites – festivalens foodtruck");

        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> showDishes();
                case "2" -> createOrder();
                case "0" -> running = false;
                default -> System.out.println("Ugyldigt valg. Vælg 0, 1 eller 2.");
            }
        }

        System.out.println("Programmet er afsluttet.");
    }

    private void showMenu() {
        System.out.println();
        System.out.println("1. Vis retter");
        System.out.println("2. Opret bestilling");
        System.out.println("0. Afslut");
        System.out.print("Vælg: ");
    }

    private void showDishes() {
        System.out.println("Retter:");

        for (int i = 0; i < menuService.getDishes().size(); i++) {
            Dish dish = menuService.getDishes().get(i);
            System.out.printf("%d. %s - %d kr.%n", i + 1, dish.name(), dish.price());
        }
    }

    private void createOrder() {
        if (!orderService.canCreateOrder()) {
            System.out.println("Der kan højst gemmes ti bestillinger.");
            return;
        }

        Integer dishChoice = readInteger("Vælg ret: ", "Ugyldig ret. Vælg 1, 2 eller 3.");
        if (dishChoice == null) {
            return;
        }

        Dish dish;
        try {
            dish = menuService.getDishByChoice(dishChoice);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return;
        }

        Integer quantity = readInteger("Indtast antal: ", "Ugyldigt antal. Indtast et positivt heltal.");
        if (quantity == null) {
            return;
        }

        try {
            Order order = orderService.createOrder(dish, quantity);
            printOrder(order);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private Integer readInteger(String prompt, String errorMessage) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            System.out.println(errorMessage);
            return null;
        }
    }

    private void printOrder(Order order) {
        System.out.printf(
                "Bestilling oprettet: #%d %s x%d - status %s%n",
                order.id(),
                order.dish().name(),
                order.quantity(),
                order.status().name()
        );
    }
}
