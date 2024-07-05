package com.example.admin.mapper;


import com.example.admin.dto.AdminCategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminCategoryMapper {

    //카테고리 생성
    int createCategory(String categoryName);

    //카테고리 조회
    List<AdminCategoryDto> allCategory();

    //카테고리 삭제
    void deleteCategory(String CategoryName);

//    //카테고리 수정
//    void updateCategory(String oldCategoryName,String newCategoryName);
}
