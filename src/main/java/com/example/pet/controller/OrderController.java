package com.example.pet.controller;

import com.example.pet.dto.CartDetailDto;
import com.example.pet.dto.CategoryDto;
import com.example.pet.dto.OrderDetailDto;
import com.example.pet.dto.OrderDto;
import com.example.pet.form.OrderForm;
import com.example.pet.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final CartService cartService;
    private final ItemService itemService;
    private final CategoryService categoryService;

    @GetMapping("/pay.html")
    public String showPayPage(@RequestParam(value = "selectedItems", required = false) List<Long> selectedItems, Model model) {
        List<CategoryDto> categorys = categoryService.cateList();
        model.addAttribute("categories", categorys);

        if(selectedItems != null) {
            List<CartDetailDto> cartDetailDtos = new ArrayList<>();
            for(Long cartItemNum : selectedItems) {
                CartDetailDto cartDetailDto = cartService.selectItem(cartItemNum);
                cartDetailDtos.add(cartDetailDto);
            }
            model.addAttribute("list", cartDetailDtos);
        }
        model.addAttribute("orderForm", new OrderForm());
        return "pay";
    }

    @PostMapping("/order")
    public String pay(@Valid OrderForm orderForm, BindingResult bindingResult, Model model, Principal principal) {
        List<CategoryDto> categorys = categoryService.cateList();
        model.addAttribute("categories", categorys);

        System.out.println("order controller 실행");
        System.out.println(orderForm);
        System.out.println(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "정보를 입력해주세요!");
            return "pay";
        }


        try {

            OrderDto orderDto = orderService.insertOrder(orderForm, principal.getName());

            List<OrderDetailDto> list = orderService.insertDetail(orderForm, orderDto);

            model.addAttribute("successMessage", "주문 완료되었습니다!");

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "pay";
        }

        return "pay";
    }
}
