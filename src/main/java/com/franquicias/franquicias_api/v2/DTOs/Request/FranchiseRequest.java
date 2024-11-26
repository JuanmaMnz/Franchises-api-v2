package com.franquicias.franquicias_api.v2.DTOs.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class FranchiseRequest {

    @NotBlank(message = "El nombre no puede estar vacío ni ser nulo")
    private String name;

    @NotEmpty(message = "La lista de sucursales no puede estar vacía")
    private List<BranchRequest> branches;
}
