package com.example.pet.service;

import com.example.pet.dto.ItemDto;
import com.example.pet.mapper.PageMapper;
import com.example.pet.notice.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PageService {

    private final PageMapper pageMapper;

    // 페이징 처리된 아이템 목록 조회
    public List<ItemDto> itemAllPaged(int pageSize, int offset) {
        return pageMapper.itemAllPaged(pageSize, offset);
    }

    // 전체 아이템 수 조회
    public int countItems() {
        return pageMapper.countItems();
    }

    // 페이징 처리된 카테고리별 아이템 목록 조회
    public List<ItemDto> itemsByCategoryPaged(String categoryName, int pageSize, int offset) {
        return pageMapper.itemsByCategoryPaged(categoryName, pageSize, offset);
    }

    // 카테고리별 전체 아이템 수 조회
    public int countItemsByCategory(String categoryName) {
        return pageMapper.countItemsByCategory(categoryName);
    }

    public List<NoticeDto> noticePaged(int pageSize, int offset) {
        return  pageMapper.noticePaged(pageSize, offset);
    }

    public int countNotice() {
        return pageMapper.countNotice();
    }

}
