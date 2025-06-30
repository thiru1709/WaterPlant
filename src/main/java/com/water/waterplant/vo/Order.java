package com.water.waterplant.vo;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private int orderId;
    private Date timeDeliveryRequired;
    private int bubbleCanQuantity;
    private int coolCanQuantity;
    private String orderStatus;
    private Customer customer;
}
