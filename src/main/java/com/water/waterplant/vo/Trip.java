package com.water.waterplant.vo;

import com.water.waterplant.enums.TRIPSTATUS;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Trip {

    private int driverId;
    private int vehicleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Order> orderList;
    private TRIPSTATUS status;
}
