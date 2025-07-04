package com.water.waterplant.vo;

import com.water.waterplant.enums.VEHICLESTATUS;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Vehicle {

    private int vehicleId;
    private int bubbleCans;
    private int coolCans;
    private int capacity;
    private VEHICLESTATUS status;
}
