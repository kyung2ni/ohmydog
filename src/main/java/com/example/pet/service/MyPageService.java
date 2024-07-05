package com.example.pet.service;

import com.example.pet.dto.MyPageDto;
import com.example.pet.dto.OrderDetailDto;
import com.example.pet.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MyPageMapper mapper;


    public List<MyPageDto> selectMyPage(Long orderDetailNum){
        return mapper.selectMyPage(orderDetailNum);
    }

    public int deleteOrder(Long orderDetailNum){
        return mapper.deleteOrder(orderDetailNum);
    }

    public int changeStatus(Map map){
        return mapper.changeStatus(map);
    }

    public OrderDetailDto selectCancel(Map map){
        return mapper.selectCancel(map);
    }

}
