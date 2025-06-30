package com.water.waterplant.order;

import com.water.waterplant.store.StoreManager;
import com.water.waterplant.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {


    @Autowired
    StoreManager storeManager;

    @PostMapping("/placeOrder")
    public String placeOrder(@RequestBody Order order){
        System.out.println("Order is placed " + order.toString());
        //check if order can be fulfilled
        if (storeManager.canOrderBeFulfilled(order)){
            System.out.println("Order can be fulfilled and you are good to go");
            //update store
            storeManager.updateStock(order);
            //place the order
        }else{
            System.out.println("Order cannot be fulfilled");
        }

        return "order is placed" + order.toString();

    }
}
