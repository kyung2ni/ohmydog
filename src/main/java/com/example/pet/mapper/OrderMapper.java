package com.example.pet.mapper;

import com.example.pet.dto.OrderDetailDto;
import com.example.pet.dto.OrderDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    int insertOrder(OrderDto orderDto);

    Long insertDetail(OrderDetailDto orderDetailDto);

    OrderDto selectOrder(Long custNum);

    List<OrderDetailDto> customerOrder(Long custNum);

    OrderDetailDto findOrderItem(Long orderDetailNum);


}
