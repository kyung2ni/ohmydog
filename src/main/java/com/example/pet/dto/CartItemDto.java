package com.example.pet.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private Long cartItemNum;
    private Long cartNum;
    private Long itemNum;
    private int count;
}
