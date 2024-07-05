package com.example.pet.controller;

import com.example.pet.dto.CategoryDto;
import com.example.pet.dto.ItemDto;
import com.example.pet.service.CategoryService;
import com.example.pet.service.ItemService;
import com.example.pet.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HtmlController {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final PageService pageService;

    @GetMapping("/")
    public String petForm(Model model){
        List<CategoryDto> categories = categoryService.cateList();
        model.addAttribute("categories", categories);

        List<ItemDto> items = itemService.indexItem();
        model.addAttribute("items",items);

        return "index";
    }

    @GetMapping("/login.html")
    public String loginForm(Model model) {
        List<CategoryDto> categories = categoryService.cateList();
        model.addAttribute("categories", categories);

        return "login";
    }

    @GetMapping("/index")
    public String mainForm() {
        return "index";
    }
}
