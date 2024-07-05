package com.example.admin.mapper;


import com.example.admin.dto.AdminCustomerDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminCustomerMapper {

    //유저 전체 조회
    List<AdminCustomerDto> custListAll();

    //유저 삭제
    void deleteCust(int custNum);

    //유저 검색
    List<AdminCustomerDto> searchCustomer(String searchField, String searchValue, String gradeName, Integer pageSize, Integer offset);

    //유저 검색
    int countSearchCustomer(String searchField, String searchValue, String gradeName);
}