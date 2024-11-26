package com.franquicias.franquicias_api.v2.DTOs.Mapper;

import com.franquicias.franquicias_api.v2.DTOs.Request.ProductRequest;
import com.franquicias.franquicias_api.v2.DTOs.Response.ProductResponseDto;
import com.franquicias.franquicias_api.v2.DTOs.Response.ProductWithBranchResponseDto;
import com.franquicias.franquicias_api.v2.Entity.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = BranchMapper.class)
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branch", ignore = true)
    Product toEntity(ProductRequest productRequest);

    ProductResponseDto toResponseDto(Product product);

    List<ProductResponseDto> toResponseDtoList(List<Product> products);

    @Mapping(target = "branchId", source = "branch.id")
    ProductWithBranchResponseDto toProductWithBranchResponseDto(Product product);

    List<ProductWithBranchResponseDto> toProductWithBranchResponseDtoList(List<Product> products);

}

