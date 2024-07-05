package com.example.pet.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long orderNum;
    private Long custNum;
    private LocalDateTime orderDate;
    private String address;
    private String orderReq;
}
