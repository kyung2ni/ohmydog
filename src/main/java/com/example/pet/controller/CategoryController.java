package com.example.pet.controller;

import com.example.pet.config.PageHandler;
import com.example.pet.dto.CategoryDto;
import com.example.pet.dto.ItemDto;
import com.example.pet.service.CategoryService;
import com.example.pet.service.ItemService;
import com.example.pet.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ItemService itemService;
    private final PageService pageService;

    //카테고리 별 상품 목록
    @GetMapping("/categoryItem/{categoryName}")
    public String cateItems(@PathVariable("categoryName") String categoryName,
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "6") int pageSize,
                            Model model){

        int totalCnt;
        List<ItemDto> cateItems;
        if(categoryName.equals("ALL")){
            totalCnt = pageService.countItems();
            int offset = (page - 1) * pageSize;
            cateItems = pageService.itemAllPaged(pageSize, offset);
        } else {
            totalCnt = pageService.countItemsByCategory(categoryName);
            int offset = (page - 1) * pageSize;
            cateItems = pageService.itemsByCategoryPaged(categoryName, pageSize, offset);
        }

        PageHandler pageHandler = new PageHandler(totalCnt, pageSize, page);

        model.addAttribute("pageHandler", pageHandler);
        model.addAttribute("categoryItems", cateItems);
        model.addAttribute("categoryName", categoryName);

        List<CategoryDto> categories = categoryService.cateList();
        model.addAttribute("categories", categories);

        return "categoryItem";
    }
}
