package com.example.admin.mapper;


import com.example.admin.dto.AdminOrderListDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminOrderMapper {

    List<AdminOrderListDto> orderListAll();

    List<AdminOrderListDto> searchOrders(@Param("searchType") String searchType,
                                         @Param("searchKeyword") String searchKeyword,
                                         @Param("startDate") String startDate,
                                         @Param("endDate") String endDate,
                                         @Param("orderStatus") String orderStatus,
                                         @Param("pageSize") Integer pageSize,
                                         @Param("offset") Integer offset);

    int countSearchOrders(@Param("searchType") String searchType,
                          @Param("searchKeyword") String searchKeyword,
                          @Param("startDate") String startDate,
                          @Param("endDate") String endDate,
                          @Param("orderStatus") String orderStatus);

    int updateStatus (Map map);
}
