package com.franquicias.franquicias_api.v2.DTOs.Response;

import java.io.Serializable;

public record ProductWithBranchResponseDto(
        Long id,
        String name,
        int stock,
        Long branchId
) implements Serializable {
}
