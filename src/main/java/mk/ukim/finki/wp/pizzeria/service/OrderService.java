package mk.ukim.finki.wp.pizzeria.service;

import mk.ukim.finki.wp.pizzeria.model.Order;

public interface OrderService {

    Order placeOrder(String pizzaType, String clientName, String address);
}
