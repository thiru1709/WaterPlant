package com.water.waterplant.vo;

import lombok.Data;

import java.util.List;

@Data
public class Trip {

    private int driverId;
    private int vehicleId;
    private String startTime;
    private String endTime;
    private List<Order> orderList;
    private String status;
    private String amountCollected;
    private String totalAmountTobeCollected;
}
