package com.example.pet.mapper;

import com.example.pet.dto.ItemDto;
import com.example.pet.notice.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PageMapper {

    // 페이징 처리된 아이템 목록 조회
    List<ItemDto> itemAllPaged(@Param("pageSize") int pageSize, @Param("offset") int offset);

    // 전체 아이템 수 조회
    int countItems();

    // 페이징 처리된 카테고리별 아이템 목록 조회
    List<ItemDto> itemsByCategoryPaged(@Param("categoryName") String categoryName, @Param("pageSize") int pageSize, @Param("offset") int offset);

    // 카테고리별 전체 아이템 수 조회
    int countItemsByCategory(@Param("categoryName") String categoryName);


    List<NoticeDto> noticePaged(int pageSize, int offset);

    int countNotice();

}
