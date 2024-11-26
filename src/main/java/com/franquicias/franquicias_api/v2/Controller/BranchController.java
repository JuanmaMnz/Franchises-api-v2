package com.franquicias.franquicias_api.v2.Controller;

import com.franquicias.franquicias_api.v2.DTOs.Request.BranchNameRequest;
import com.franquicias.franquicias_api.v2.DTOs.Response.BranchResponseDto;
import com.franquicias.franquicias_api.v2.Service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @Operation(
            summary = "Agregar sucursal a franquicia",
            description = "Permite agregar una nueva sucursal a una franquicia existente.",
            tags = {"Sucursal"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente",
                    content = @Content(schema = @Schema(implementation = BranchResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Franquicia no encontrada"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping("/{franchiseId}")
    public ResponseEntity<BranchResponseDto> addBranchToFranchise(
            @Parameter(description = "ID de la franquicia", required = true) @Valid @PathVariable Long franchiseId,
            @Parameter(description = "Nombre de la sucursal", required = true) @Valid @RequestBody BranchNameRequest branchNameRequest) {
        BranchResponseDto createdBranch = branchService.addBranchToFranchise(branchNameRequest, franchiseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBranch);
    }



    @Operation(
            summary = "Actualizar nombre de la sucursal",
            description = "Actualiza el nombre de una sucursal existente",
            tags = {"Sucursal"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nombre de la sucursal actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PatchMapping("/{branchId}")
    public ResponseEntity<BranchResponseDto> updateBranchName(
            @Parameter(description = "ID de la sucursal", required = true) @Valid @PathVariable Long branchId,
            @Parameter(description = "Nuevo nombre de la sucursal", required = true) @Valid @RequestBody BranchNameRequest branchNameRequest) {
        BranchResponseDto updatedBranch = branchService.updateBranchName(branchId, branchNameRequest);
        return ResponseEntity.ok(updatedBranch);
    }
}
