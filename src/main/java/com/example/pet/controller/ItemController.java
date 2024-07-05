package com.example.pet.controller;

import com.example.pet.config.PageHandler;
import com.example.pet.dto.CategoryDto;
import com.example.pet.dto.ItemDto;
import com.example.pet.service.CategoryService;
import com.example.pet.service.CustomerService;
import com.example.pet.service.FileService;
import com.example.pet.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {


    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemService itemService;
    private final FileService fileService;
    private final CustomerService customerService;
    private final CategoryService categoryService;

    @GetMapping("/item/{itemNum}")
    public String itemDetail(Model model, @PathVariable("itemNum") Long itemNum, Principal principal){

        List<CategoryDto> categorys = categoryService.cateList();
        model.addAttribute("categories", categorys);

        if (principal == null) {
            model.addAttribute("loginErrorMsg","로그인 후 사용가능합니다.");
            return "login";
        }

        ItemDto itemDto = itemService.selectItem(itemNum);
        model.addAttribute("item", itemDto);

        //로그인한 cust_id 확인
        System.out.println(principal.getName());

        return "item";
    }

    //검색어
    @RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchWord(@RequestParam("searchWord") String searchWord,
                             @RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "6") int pageSize,
                             Model model){

        List<CategoryDto> categorys = categoryService.cateList();
        model.addAttribute("categories", categorys);

        System.out.println(searchWord);
        System.out.println("상품 검색 컨트롤러 실행");

        int totalCnt = itemService.searchWordCount(searchWord);
        int offset = (page - 1) * pageSize;
        List<ItemDto> list = itemService.searchWord(searchWord, pageSize, offset);

        System.out.println( "totalCnt : " + totalCnt);
        System.out.println("offset : " + offset);
        PageHandler pageHandler = new PageHandler(totalCnt, pageSize, page);

        System.out.println(pageHandler.getBeginPage());
        System.out.println(pageHandler.getEndPage());
        model.addAttribute("pageHandler", pageHandler);
        model.addAttribute("searchItems", list);
        model.addAttribute("searchWord", searchWord);

        return "/search";
    }

}
