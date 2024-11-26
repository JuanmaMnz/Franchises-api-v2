package com.franquicias.franquicias_api.v2.Controller;

import com.franquicias.franquicias_api.v2.DTOs.Request.FranchiseNameRequest;
import com.franquicias.franquicias_api.v2.DTOs.Request.FranchiseRequest;
import com.franquicias.franquicias_api.v2.DTOs.Response.FranchiseResponseDto;
import com.franquicias.franquicias_api.v2.DTOs.Response.ProductWithBranchResponseDto;
import com.franquicias.franquicias_api.v2.Entity.Product;
import com.franquicias.franquicias_api.v2.Service.FranchiseService;
import com.franquicias.franquicias_api.v2.Service.ProductService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;
    private final ProductService productService;



    @Operation(
            summary = "Agregar nueva franquicia",
            description = "Permite agregar una nueva franquicia",
            tags = {"Franquicia"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Franquicia creada exitosamente",
                    content = @Content(schema = @Schema(implementation = FranchiseResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping
    public ResponseEntity<FranchiseResponseDto> addFranchise(
            @Parameter(description = "Nombre de la franquicia", required = true) @Valid @RequestBody FranchiseNameRequest franchiseNameRequest) {
        FranchiseResponseDto savedFranchise = franchiseService.addFranchise(franchiseNameRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFranchise);
    }



    @Operation(
            summary = "Agregar nueva franquicia con sucursales",
            description = "Permite agregar una nueva franquicia con sucursales",
            tags = {"Franquicia"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Franquicia creada exitosamente",
                    content = @Content(schema = @Schema(implementation = FranchiseResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping("/franchise-with-branches")
    public ResponseEntity<FranchiseResponseDto> addFranchiseWithBranches(
            @Parameter(description = "Detalles de la franquicia a agregar", required = true) @Valid @RequestBody FranchiseRequest franchiseRequest){
        FranchiseResponseDto createdFranchise = franchiseService.addFranchiseWithBranches(franchiseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFranchise);
    }



    @Operation(
            summary = "Actualizar nombre franquicia",
            description = "Permite actualizar el nombre de una franquicia existente.",
            tags = {"Franquicia"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nombre de la franquicia actualizado correctamente",
                    content = @Content(schema = @Schema(implementation = FranchiseResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Franquicia no encontrada"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PatchMapping("/{franchiseId}/update-name")
    public ResponseEntity<FranchiseResponseDto> updateFranchiseName(
            @Parameter(description = "ID de la franquicia", required = true) @Valid @PathVariable Long franchiseId,
            @Parameter(description = "Nombre de la franquicia", required = true) @Valid @RequestBody  FranchiseNameRequest franchiseNameRequest) {
        FranchiseResponseDto updatedFranchise = franchiseService.updateFranchiseName(franchiseId, franchiseNameRequest);
        return ResponseEntity.ok(updatedFranchise);
    }



    @Operation(
            summary = "Obtener productos destacados por franquicia",
            description = "Devuelve una lista de cual es el producto que más stock tiene por sucursal para una franquicia puntual",
            tags = {"Franquicia"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos destacados obtenida correctamente",
                    content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "Franquicia no encontrada"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @GetMapping("/{franchiseId}/products/top")
    public ResponseEntity<List<ProductWithBranchResponseDto>> getTopProductsByFranchise(
            @Parameter(description = "Id de la franquicia", required = true) @PathVariable Long franchiseId) {
        List<ProductWithBranchResponseDto> topProducts = productService.getTopProductsByFranchise(franchiseId);
        return ResponseEntity.ok(topProducts);
    }

}
