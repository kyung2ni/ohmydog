package com.example.pet.controller;

import com.example.pet.constant.Role;
import com.example.pet.dto.CategoryDto;
import com.example.pet.dto.CustomerDto;
import com.example.pet.form.CustomerForm;
import com.example.pet.service.CategoryService;
import com.example.pet.service.CustomerService;
import com.example.pet.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PetController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CategoryService categoryService;



    @GetMapping("/register.html")
    public String registerForm(Model model, HttpServletRequest request){

        List<CategoryDto> categorys = categoryService.cateList();
        model.addAttribute("categories", categorys);

        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        System.out.println(token.getHeaderName() + " = " + token.getToken());

        model.addAttribute("customer", new CustomerForm());
        return "register";
    }

    @PostMapping("/reg")
    public String registerSubmit(@Valid CustomerForm customerForm, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("customer", customerForm);
            return "register";
        }

        try {
            CustomerDto findId = customerService.overlapId(customerForm.getId());

            if (findId != null) {
                throw new IllegalStateException("중복된 아이디");
            }

            CustomerDto findTel = customerService.overlapTel(customerForm.getTel());

            if (findTel != null) {
                throw new IllegalStateException("전화번호를 다시 확인해주세요");
            }

            CustomerDto dto = new CustomerDto();
            dto.setCustId(customerForm.getId());
            
            //비밀번호 암호화
            String encodedPassword = passwordEncoder.encode(customerForm.getPassword());
            dto.setCustPw(encodedPassword);

            dto.setCustName(customerForm.getName());
            dto.setCustTel(customerForm.getTel());
            dto.setRole(Role.USER);

            customerService.insertCust(dto);


        } catch (IllegalStateException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("customer", customerForm);
            return "register";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 다시 입력하세요");

        return "/login";
    }

}
