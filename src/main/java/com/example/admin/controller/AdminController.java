package com.example.admin.controller;


import com.example.admin.config.AdminPageHandler;
import com.example.admin.dto.AdminCategoryDto;
import com.example.admin.dto.AdminCustomerDto;
import com.example.admin.dto.AdminOrderListDto;
import com.example.admin.dto.UpdateOrderStatusRequest;
import com.example.admin.service.AdminCategoryService;
import com.example.admin.service.AdminCustomerService;
import com.example.admin.service.AdminOrderService;
import com.example.pet.config.PageHandler;
import com.example.pet.dto.CustomerTotalPriceDto;
import com.example.pet.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private AdminCategoryService categoryService;

    @Autowired
    private AdminCustomerService customerService;

    @Autowired
    private AdminOrderService orderService;

    @Autowired
    private CustomerService custService;

    //메인 관리자페이지
    @GetMapping("/admin")
    public String adminAdminForm() {
        return "admin/admin";
    }

    //카테고리 전체 조회
    @GetMapping("/manageCategory")
    public String manageCategory(Model model) {
        List<AdminCategoryDto> category = categoryService.allCategory();
        model.addAttribute("category", category);

        return "admin/manageCategory";
    }

    //카테고리 추가하기
    @PostMapping("/manageCategory")
    public String addCategory(@RequestParam("categoryName") String categoryName, Model model) {
        try {
            categoryService.createCategory(categoryName);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "카테고리 등록 중에 에러 발생");
            return "admin/manageCategory";

        }
        return "redirect:/manageCategory";

    }

    // 카테고리 삭제
    @PostMapping("/deleteCategory")
    @ResponseBody
    public ResponseEntity<String> deleteCategory(@RequestParam("categoryName") String categoryName) {
        try {
            categoryService.deleteCategory(categoryName);
            return ResponseEntity.ok("삭제 성공");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
        }
    }

    //회원관리 검색 + 전체 조회 (페이징 포함)
    @GetMapping("/manageCustomer")
    public String searchAllCust(@RequestParam(value = "searchField", required = false) String searchField,
                                @RequestParam(value = "searchValue", required = false) String searchValue,
                                @RequestParam(value = "gradeName", required = false) String gradeName,
                                @RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                Model model) {
        int offset = (page - 1) * pageSize;
        List<AdminCustomerDto> cust = customerService.searchCustomer(searchField, searchValue, gradeName, pageSize, offset);

        if (cust.isEmpty()) {
           model.addAttribute("emptyResult", true);
        } else {
            model.addAttribute("emptyResult", false);
        }
        int totalRecords = customerService.countSearchCustomer(searchField, searchValue, gradeName);

        // AdminPageHandler 객체 생성
        AdminPageHandler pageHandler = new AdminPageHandler(totalRecords, pageSize, page);

        model.addAttribute("cust", cust);
        model.addAttribute("pageHandler", pageHandler);
        model.addAttribute("searchField", searchField); // 이전 검색 필드 유지를 위해 추가
        model.addAttribute("searchValue", searchValue); // 이전 검색 값 유지를 위해 추가
        model.addAttribute("gradeName", gradeName);     // 이전 등급명 유지를 위해 추가

        return "admin/manageCustomer";
    }

    // 유저 삭제
    @PostMapping("/deleteCustomer")
    @ResponseBody
    public ResponseEntity<String> deleteCustomer(@RequestParam("custNum") int custNum) {
        try {
            customerService.deleteCust(custNum);
            return ResponseEntity.ok("삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
        }
    }


//    //전체 주문 리스트
//    @GetMapping("/manageOrder")
//    public String orderListAllView(Model model) {
//
//        List<AdminOrderListDto> order = orderService.orderListAll();
//        model.addAttribute("orders", order);
//
//        return "admin/manageOrder";
//    }

    @GetMapping("/manageOrder")
    public String orderListAllView(
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "orderStatus", required = false) String orderStatus,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            Model model) {

        System.out.println("searchType : "+searchType);
        System.out.println("searchKeyword : "+searchKeyword);
        System.out.println("startDate : "+startDate);
        System.out.println("endDate : "+endDate);
        System.out.println("orderStatus : "+orderStatus);

        // 전체 주문 수 조회 (검색 조건 포함)
        int totalCnt = orderService.countSearchOrders(searchType, searchKeyword, startDate, endDate, orderStatus);
        PageHandler pageHandler = new PageHandler(totalCnt, pageSize, page);
        model.addAttribute("pageHandler", pageHandler);

        // 페이징 설정
        int offset = (page - 1) * pageSize;
        if (offset < 0) {
            offset = 0;
        }

        // 검색 조건에 따라 주문 리스트 조회 (페이징 포함)
        List<AdminOrderListDto> orders = orderService.searchOrders(searchType, searchKeyword, startDate, endDate, orderStatus, pageSize, offset);

        if (orders.isEmpty()) {
            model.addAttribute("emptyResult", true);
        } else {
            model.addAttribute("emptyResult", false);
        }

        model.addAttribute("orders", orders);

        // 검색 조건을 모델에 추가하여 뷰에서 사용
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("orderStatus", orderStatus);

        return "admin/manageOrder";
    }

    //주문상태 변경
    @PostMapping("/updateOrderStatus")
    public ResponseEntity<String> updateOrderStatus(@RequestBody UpdateOrderStatusRequest request) {
        try {
            request.getOrderDetailNums().forEach(orderDetailNum ->
                    orderService.updateStatus(orderDetailNum, request.getOrderStatus())
            );

            // 주문 상태가 'CANCEL'일 때 고객의 total_price 업데이트
            if ("CANCEL".equals(request.getOrderStatus())) {
                for (Long orderDetailNum : request.getOrderDetailNums()) {
                    CustomerTotalPriceDto customerTotalPriceDto = custService.custTotalPrice(orderDetailNum);
                    if (customerTotalPriceDto != null) {
                        int totalPriceToUpdate = customerTotalPriceDto.getTotalPrice() - (customerTotalPriceDto.getPrice() * customerTotalPriceDto.getItemCnt());

                        Map<String, Object> map = new HashMap<>();
                        map.put("custNum", customerTotalPriceDto.getCustNum());
                        map.put("totalPrice", totalPriceToUpdate);
                        custService.updateTP(map);
                    }
                }
            }
            return ResponseEntity.ok("주문 상태가 업데이트되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 상태 업데이트에 실패했습니다.");
        }
    }

}
