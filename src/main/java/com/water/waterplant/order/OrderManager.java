package com.water.waterplant.order;

import com.water.waterplant.vo.Order;

import java.util.List;

public interface OrderManager {

    public boolean placeOrder(Order order);
    public List<Order> pendingOrders();
    public List<Order> fulfilledOrders();
    public boolean fulfillOrder(Order order);
    public boolean rejectOrder(Order order);
    public List<Order> rejectedOrders();
}
