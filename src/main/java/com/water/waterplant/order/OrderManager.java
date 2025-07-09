package com.water.waterplant.order;

import com.water.waterplant.enums.ORDERSTATUS;
import com.water.waterplant.vo.Order;

import java.util.List;

public interface OrderManager {

    public boolean placeOrder(Order order);
    public boolean fulfillOrder(Order order);
    public boolean rejectOrder(Order order);
    public List<Order> ordersByStatus(ORDERSTATUS orderstatus);
}
