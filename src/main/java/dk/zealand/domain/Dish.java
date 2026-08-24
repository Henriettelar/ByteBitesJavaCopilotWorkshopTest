package dk.zealand.domain;

public class Dish {
    private final String name;
    private final int price;

    public Dish(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String name() {
        return name;
    }

    public int price() {
        return price;
    }
}
