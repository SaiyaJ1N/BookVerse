package com.onlinebookshop.dto.order;

import com.onlinebookshop.model.Order;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderStatusDto {
    @NotNull
    private Order.Status status;
}
