package com.example.pet.mapper;

import com.example.pet.dto.MyPageDto;
import com.example.pet.dto.OrderDetailDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyPageMapper {

    List<MyPageDto> selectMyPage(Long orderDetailNum);

    int deleteOrder(Long orderDetailNum);

    int changeStatus(Map map);

    OrderDetailDto selectCancel(Map map);
}
