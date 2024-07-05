package com.example.pet.form;

import com.example.pet.dto.ItemDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemForm {
    private int id;

    @NotBlank
    private String itemName;

    @NotBlank
    private String categoryName;

    @NotNull
    private int count;

    @NotNull
    private int price;

    private MultipartFile itemImgFile;

    @NotBlank
    private String itemDetail;

}
