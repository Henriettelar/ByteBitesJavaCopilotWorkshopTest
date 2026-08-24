package dk.zealand.service;

import dk.zealand.domain.Dish;
import dk.zealand.domain.Order;
import dk.zealand.domain.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private static final int MAX_ORDERS = 10;

    private final List<Order> orders = new ArrayList<>();
    private int nextOrderId = 1;

    public boolean canCreateOrder() {
        return orders.size() < MAX_ORDERS;
    }

    public Order createOrder(Dish dish, int quantity) {
        if (!canCreateOrder()) {
            throw new IllegalStateException("Der kan højst gemmes ti bestillinger.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Ugyldigt antal. Antallet skal være større end 0.");
        }

        Order order = new Order(nextOrderId++, dish, quantity, OrderStatus.MODTAGET);
        orders.add(order);
        return order;
    }

    public int getOrderCount() {
        return orders.size();
    }
}
