package com.franquicias.franquicias_api.v2.DTOs.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductStockRequest {

    @NotNull(message = "El stock no puede ser nulo.")
    @Positive(message = "El stock debe ser un número positivo mayor a 0.")
    private int stock;
}
