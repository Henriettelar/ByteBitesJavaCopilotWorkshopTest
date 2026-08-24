package dk.zealand;

import dk.zealand.service.ByteBitesApplication;
import dk.zealand.service.MenuService;
import dk.zealand.service.OrderService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ByteBitesApplication application = new ByteBitesApplication(
                new Scanner(System.in),
                new MenuService(),
                new OrderService()
        );
        application.run();
    }
}
