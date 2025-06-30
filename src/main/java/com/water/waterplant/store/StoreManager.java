package com.water.waterplant.store;

import com.water.waterplant.vo.Order;
import com.water.waterplant.vo.Store;

public interface StoreManager {
    public boolean canOrderBeFulfilled(Order order);
    public Store getCurrentStock();
    public boolean updateStock(Order order);

}
