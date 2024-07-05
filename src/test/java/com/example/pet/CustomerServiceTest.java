package com.example.pet;

import com.example.pet.dto.CustomerDto;
import com.example.pet.mapper.CustomerMapper;
import com.example.pet.service.CustomerSecurityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerServiceTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerSecurityService customerSecurityService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void insertCustTest(){

        for (int i=0; i<20; i++) {
            CustomerDto cust = new CustomerDto();
            cust.setCustId("test"+i);
            cust.setCustPw("1111111"+i);
            cust.setCustName("테스트"+i);
            cust.setCustTel("010-1234-5678");

            int result = customerMapper.insertCust(cust);
            assertThat(result).isEqualTo(1);
        }
    }

    @Test
    public void loginTest() throws Exception{
        String id="test0521";
        String password = "11111111";

        mockMvc.perform(formLogin().userParameter("id")
                        .loginProcessingUrl("/login_proc")
                        .user(id).password(password)).andExpect(SecurityMockMvcResultMatchers.authenticated());
    }
}
