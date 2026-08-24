package dk.zealand.domain;

public class Order {
    private final int id;
    private final Dish dish;
    private final int quantity;
    private final OrderStatus status;

    public Order(int id, Dish dish, int quantity, OrderStatus status) {
        this.id = id;
        this.dish = dish;
        this.quantity = quantity;
        this.status = status;
    }

    public int id() {
        return id;
    }

    public Dish dish() {
        return dish;
    }

    public int quantity() {
        return quantity;
    }

    public OrderStatus status() {
        return status;
    }
}
