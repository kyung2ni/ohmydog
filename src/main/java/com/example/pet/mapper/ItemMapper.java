package com.example.pet.mapper;

import com.example.pet.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {

    List<ItemDto> indexItem();

    int insertItem(ItemDto itemDto);

    List<ItemDto> itemAll();

    ItemDto selectItem(Long num);

    int updateCnt(ItemDto itemDto);

    List<ItemDto> selectCate(String categoryName);

    //검색어
    List<ItemDto> searchItem(@Param("searchWord") String searchWord, @Param("pageSize") int pageSize, @Param("offset") int offset);
    int searchItemCount(String searchWord);

}
