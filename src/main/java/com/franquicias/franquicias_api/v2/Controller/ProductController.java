package com.franquicias.franquicias_api.v2.Controller;

import com.franquicias.franquicias_api.v2.DTOs.Request.ProductNameRequest;
import com.franquicias.franquicias_api.v2.DTOs.Request.ProductRequest;
import com.franquicias.franquicias_api.v2.DTOs.Request.ProductStockRequest;
import com.franquicias.franquicias_api.v2.DTOs.Response.ProductResponseDto;
import com.franquicias.franquicias_api.v2.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;



    @Operation(
            summary = "Agregar producto a sucursal",
            description = "Permite añadir un nuevo producto a una sucursal existente.",
            tags = {"Producto"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping("/branches/{branchId}")
    public ResponseEntity<ProductResponseDto> addProductToBranch(
            @Parameter(description = "ID de la sucursal", required = true) @Valid @PathVariable Long branchId,
            @Parameter(description = "Detalles del producto a agregar", required = true) @Valid @RequestBody ProductRequest productRequest) {

        ProductResponseDto createdProduct = productService.createProduct(productRequest, branchId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }



    @Operation(
            summary = "Eliminar producto de sucursal",
            description = "Elimina un producto de una sucursal existente.",
            tags = {"Producto"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal o producto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @DeleteMapping("{productId}/branches/{branchId}")
    public ResponseEntity<String> deleteProductFromBranch(
            @Parameter(description = "ID de la sucursal a la que pertenece el producto", required = true) @PathVariable Long branchId,
            @Parameter(description = "ID del producto a eliminar", required = true) @PathVariable Long productId) {
        productService.deleteProductFromBranch(branchId, productId);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }



    @Operation(
            summary = "Actualizar stock de producto",
            description = "Actualiza el stock de un producto existente por su ID",
            tags = {"Producto"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock del producto actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Sucursal o producto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Entrada inválida")
    })
    @PatchMapping("/update-stock/{productId}/branches/{branchId}")
    public ResponseEntity<String> updateProductStock(
            @Parameter(description = "ID del producto a actualizar") @Valid @PathVariable Long productId,
            @Parameter(description = "ID de la sucursal a la que pertenece el producto", required = true) @Valid @PathVariable Long branchId,
            @Parameter(description = "Nuevo stock del producto") @Valid @RequestBody ProductStockRequest productStockRequest) {
        productService.updateProductStock(productId, branchId, productStockRequest);
        return ResponseEntity.ok("Producto actualizado correctamente");
    }



    @Operation(
            summary = "Actualizar nombre de producto",
            description = "Actualiza el nombre de un producto existente por su ID.",
            tags = {"Producto"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nombre del producto actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Sucursal o producto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Entrada inválida")
    })
    @PatchMapping("/update-name/{productId}/branches/{branchId}")
    public ResponseEntity<String> updateProductName(
            @Parameter(description = "ID del producto a actualizar") @Valid @PathVariable Long productId,
            @Parameter(description = "ID de la sucursal a la que pertenece el producto", required = true) @Valid @PathVariable Long branchId,
            @Parameter(description = "Nuevo nombre del producto") @Valid @RequestBody ProductNameRequest productNameRequest) {
        productService.updateProductName(productId, branchId, productNameRequest);
        return ResponseEntity.ok("Producto actualizado correctamente");
    }

}
