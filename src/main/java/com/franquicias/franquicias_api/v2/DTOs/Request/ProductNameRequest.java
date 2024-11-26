package com.franquicias.franquicias_api.v2.DTOs.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductNameRequest {

    @NotBlank(message = "El nombre no puede estar vacío ni ser nulo")
    private String name;
}
