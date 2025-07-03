package com.water.waterplant.store;

import com.water.waterplant.vo.Order;
import com.water.waterplant.vo.Store;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class StoreManagerImpl implements StoreManager {

    public Store store;

    @PostConstruct
    public void init(){
        store = new Store();
        store.setBubbleCans(100);
        store.setCoolCans(100);
    }


    @Override
    public boolean canOrderBeFulfilled(Order order) {
        Store currentStock = getCurrentStock();
        return currentStock.getBubbleCans() > order.getBubbleCanQuantity() &&
                currentStock.getCoolCans() > order.getCoolCanQuantity();
    }

    @Override
    public Store getCurrentStock() {
        return store;
    }

    @Override
    public boolean updateStock(Order order) {
        int updatedCoolCanCount = store.getCoolCans() - order.getCoolCanQuantity();
        int updatedBubbleCanCount = store.getBubbleCans() - order.getBubbleCanQuantity();
        store.setCoolCans(updatedCoolCanCount);
        store.setBubbleCans(updatedBubbleCanCount);
        System.out.println("Updated stock is " + store);
        return false;
    }
}
