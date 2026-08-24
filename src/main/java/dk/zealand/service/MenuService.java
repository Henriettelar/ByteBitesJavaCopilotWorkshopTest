package dk.zealand.service;

import dk.zealand.domain.Dish;

import java.util.List;

public class MenuService {

    private final List<Dish> dishes = List.of(
            new Dish("Festivalburger", 59),
            new Dish("Sprøde fritter", 35),
            new Dish("Vegansk bowl", 65)
    );

    public List<Dish> getDishes() {
        return dishes;
    }

    public Dish getDishByChoice(int choice) {
        if (choice < 1 || choice > dishes.size()) {
            throw new IllegalArgumentException("Ugyldig ret. Vælg 1, 2 eller 3.");
        }

        return dishes.get(choice - 1);
    }
}
