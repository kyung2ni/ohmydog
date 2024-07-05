package com.example.pet.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CustomerForm {

    @NotBlank()
    private String id;

    @NotBlank()
    private String password;

    @NotBlank()
    private String name;

    @NotBlank()
    private String tel;
}
