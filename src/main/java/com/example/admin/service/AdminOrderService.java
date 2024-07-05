package com.example.admin.service;


import com.example.admin.dto.AdminOrderListDto;
import com.example.admin.mapper.AdminOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminOrderService {

    @Autowired
    private AdminOrderMapper orderMapper;

    public List<AdminOrderListDto> orderListAll() {
        return orderMapper.orderListAll();
    }

    public List<AdminOrderListDto> searchOrders(String searchType, String searchKeyword, String startDate, String endDate, String orderStatus, Integer pageSize, Integer offset) {
        return orderMapper.searchOrders(searchType, searchKeyword, startDate, endDate, orderStatus, pageSize, offset);
    }

    public int countSearchOrders(String searchType, String searchKeyword, String startDate, String endDate, String orderStatus) {
        Integer count = orderMapper.countSearchOrders(searchType, searchKeyword, startDate, endDate, orderStatus);
        return count == null ? 0 : count;
    }

    public int updateStatus(Long orderDetailNum, String orderStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderDetailNum", orderDetailNum);
        map.put("orderStatus", orderStatus);
        return orderMapper.updateStatus(map);
    }
}
