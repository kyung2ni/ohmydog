package com.example.pet.service;

import com.example.pet.dto.ItemDto;
import com.example.pet.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    //인텍스 상품
    public List<ItemDto> indexItem(){
        return itemMapper.indexItem();
    };

    private final ItemMapper itemMapper;

    public int insertItem(ItemDto itemDto) {
        return itemMapper.insertItem(itemDto);
    }

    public List<ItemDto> itemAll() {
        return itemMapper.itemAll();
    }

    public ItemDto selectItem(Long num) {
        return itemMapper.selectItem(num);
    }

    public int updateCnt(ItemDto itemDto) {
        return itemMapper.updateCnt(itemDto);
    }

    public List<ItemDto> selectCate(String categoryName) {
        return itemMapper.selectCate(categoryName);
    }

    //검색어
    public List<ItemDto> searchWord(String searchWord, int pageSize, int offset){
        System.out.println("상품 검색 서비스 실행");
        return itemMapper.searchItem(searchWord, pageSize, offset);
    };
    public int searchWordCount(String searchWord){
        return itemMapper.searchItemCount(searchWord);
    }

}
