package com.campus.trade.dto;

import lombok.Data;

@Data
public class OrderCreateDTO {
    private Long productId;
    private Long buyerId;
}