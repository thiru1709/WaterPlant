package com.water.waterplant.vo;

import com.water.waterplant.enums.DRIVERSTATUS;
import lombok.Data;

@Data
public class Driver {

    private int driverId;
    private String driverName;
    private DRIVERSTATUS status;
}
