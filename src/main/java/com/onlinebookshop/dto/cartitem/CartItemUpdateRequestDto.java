package com.onlinebookshop.dto.cartitem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CartItemUpdateRequestDto {
    @NotBlank
    @Size(min = 1)
    private int quantity;
}
