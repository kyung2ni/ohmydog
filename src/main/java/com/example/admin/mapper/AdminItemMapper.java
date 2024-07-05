package com.example.admin.mapper;

import com.example.admin.dto.AdminItemDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminItemMapper {

    // 제품 등록
    int insertItem(AdminItemDto itemDto);

    // 제품 전체 조회
    List<AdminItemDto> itemListAll();

    // 제품 삭제
    void deleteItem(int itemNum);

    // 제품 검색
    List<AdminItemDto> searchItems(@Param("searchField") String searchField,
                                   @Param("searchValue") String searchValue,
                                   @Param("categoryName") String categoryName,
                                   @Param("minStock") Integer minStock,
                                   @Param("maxStock") Integer maxStock,
                                   @Param("minPrice") Integer minPrice,
                                   @Param("maxPrice") Integer maxPrice,
                                   @Param("pageSize") Integer pageSize,
                                   @Param("offset") Integer offset);

    // 검색 조건에 따른 전체 아이템 수 조회
    int countSearchItems(@Param("searchField") String searchField,
                         @Param("searchValue") String searchValue,
                         @Param("categoryName") String categoryName,
                         @Param("minStock") Integer minStock,
                         @Param("maxStock") Integer maxStock,
                         @Param("minPrice") Integer minPrice,
                         @Param("maxPrice") Integer maxPrice);
}
