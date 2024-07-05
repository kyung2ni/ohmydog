package com.example.pet.dto;

import lombok.Data;

@Data
public class CustomerTotalPriceDto {
    private Long custNum;
    private String custName;
    private String custId;
    private int totalPrice;
    private Long orderDetailNum;
    private Long orderNum;
    private Long itemNum;
    private int itemCnt;
    private int price;
}
