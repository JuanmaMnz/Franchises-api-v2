package com.franquicias.franquicias_api.v2.DTOs.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "El nombre no puede estar vacío ni ser nulo")
    private String name;

    @NotNull(message = "El stock no puede ser nulo.")
    @Positive(message = "El stock debe ser un número positivo mayor a 0.")
    private int stock;
}
