package com.water.waterplant.order;

import com.water.waterplant.vo.Customer;
import com.water.waterplant.enums.ORDERSTATUS;
import com.water.waterplant.vo.Order;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.random.RandomGenerator;

@Service
public class OrderManagerImpl implements OrderManager{

    private final List<Order> orderList = new ArrayList<>();
    private final List<Order> fulfilledOrderList = new ArrayList<>();
    private final List<Order> rejectedOrderList = new ArrayList<>();

    @PostConstruct
    public void init(){
        Order order1 = new Order();
        order1.setOrderId(1);
        order1.setOrderStatus(ORDERSTATUS.ACCEPTED);
        order1.setBubbleCanQuantity(10);
        order1.setCoolCanQuantity(10);
        String dateTimeStr = "04-07-2025 10:30:10";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        order1.setTimeDeliveryRequired(localDateTime.toString());
        Customer customer = new Customer();
        customer.setName("Raghu");
        customer.setMobileNumber("988935325");
        customer.setAddress(new String[]{"15/1597","vijayanagar colony"});
        order1.setCustomer(customer);
        orderList.add(order1);
    }
    @Override
    public boolean placeOrder(Order order) {
        //add conditions to check the order
        order.setOrderStatus(ORDERSTATUS.ACCEPTED);
        orderList.add(order);
        return true;
    }



    @Override
    public List<Order> pendingOrders() {
        return orderList;
    }

    @Override
    public List<Order> fulfilledOrders() {
        return fulfilledOrderList;
    }

    @Override
    public boolean fulfillOrder(Order order) {
        orderList.remove(order);
        fulfilledOrderList.add(order);
        return false;
    }

    @Override
    public boolean rejectOrder(Order order) {
        rejectedOrderList.add(order);
        return true;
    }

    @Override
    public List<Order> rejectedOrders() {
        return rejectedOrderList;
    }
}
