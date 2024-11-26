package com.franquicias.franquicias_api.v2.DTOs.Response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record FranchiseResponseDto(
        Long id,
        String name,
        List<BranchResponseDto> branches
) implements Serializable {
}

