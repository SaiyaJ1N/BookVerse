package com.onlinebookshop.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderRequestDto {
    @NotBlank
    @Size(min = 1, max = 255)
    private String shippingAddress;
}
