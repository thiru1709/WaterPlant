package com.water.waterplant.vo;

import com.water.waterplant.enums.ORDERSTATUS;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private int orderId;
    private String timeDeliveryRequired;
    private int bubbleCanQuantity;
    private int coolCanQuantity;
    private ORDERSTATUS orderStatus;
    private Customer customer;
}


