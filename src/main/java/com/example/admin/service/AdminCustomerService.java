package com.example.admin.service;

import com.example.admin.dto.AdminCustomerDto;
import com.example.admin.mapper.AdminCustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCustomerService {

    @Autowired
    private AdminCustomerMapper customerMapper;

    //전체 유저 조회
    public List<AdminCustomerDto> custListAll() {
        return customerMapper.custListAll();
    }

    //유저 삭제
    public void deleteCust(int custNum) {
        customerMapper.deleteCust(custNum);
    }

    //유저 검색 (페이징 포함)
    public List<AdminCustomerDto> searchCustomer(String searchField, String searchValue, String gradeName, int pageSize, int offset) {
        return customerMapper.searchCustomer(searchField, searchValue, gradeName, pageSize, offset);
    }

    //검색된 유저 수
    public int countSearchCustomer(String searchField, String searchValue, String gradeName) {
        return customerMapper.countSearchCustomer(searchField, searchValue, gradeName);
    }

}
