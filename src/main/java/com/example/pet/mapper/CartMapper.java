package com.example.pet.mapper;

import com.example.pet.dto.CartDetailDto;
import com.example.pet.dto.CartDto;
import com.example.pet.dto.CartItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {

    Long insertCart(Long custNum);

    int insertItem(CartItemDto cartItemDto);

    CartDto findCartNum(Long custNum);

    CartItemDto findCartItem(Long cartItemNum);

    CartItemDto cartList(Map map);

    List<CartDetailDto> listAll(Long cartNum);

    int updateCount(Map map);

    int deleteItem(Long cartItemNum);

    CartDetailDto selectItem(Long cartItemNum);

}
