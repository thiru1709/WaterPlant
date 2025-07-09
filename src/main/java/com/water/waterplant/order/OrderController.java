package com.water.waterplant.order;

import com.water.waterplant.enums.ORDERSTATUS;
import com.water.waterplant.store.StoreManager;
import com.water.waterplant.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;


@RestController
public class OrderController {


    @Autowired
    StoreManager storeManager;

    @Autowired
    OrderManager orderManager;

    @PostMapping("/placeOrder")
    public ResponseEntity placeOrder(@RequestBody Order order){
        System.out.println("Order is placed " + order.toString());
        //check if order can be fulfilled
        generateOrderId(order);
        if (storeManager.canOrderBeFulfilled(order)){
            order.setOrderStatus(ORDERSTATUS.SUBMITTED);
            System.out.println("Order can be fulfilled and you are good to go");
            //update store
            storeManager.updateStock(order);
            //place the order
            orderManager.placeOrder(order);
            System.out.println("Pending orders " + orderManager.ordersByStatus(ORDERSTATUS.ACCEPTED));

        }else{
            orderManager.rejectOrder(order);
            System.out.println("Order cannot be fulfilled");
            return new ResponseEntity<>("We are unable to process your order at this time, Please try after some time"
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/ordersByStatus")
    public ResponseEntity<List<Order>> ordersByStatus(@RequestParam String orderStatus){
        ORDERSTATUS orderstatus = ORDERSTATUS.valueOf(orderStatus);
        return new ResponseEntity<>(orderManager.ordersByStatus(orderstatus),HttpStatus.OK);
    }

    private static void generateOrderId(Order order) {
        SecureRandom secureRandom = new SecureRandom();
        order.setOrderId(secureRandom.nextInt(1,232425252));
    }
}
