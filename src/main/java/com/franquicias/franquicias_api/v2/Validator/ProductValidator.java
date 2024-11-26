package com.franquicias.franquicias_api.v2.Validator;

import com.franquicias.franquicias_api.v2.Exception.ProductExceptions.ProductNotBelongToBranch;
import com.franquicias.franquicias_api.v2.Exception.ProductExceptions.ProductNotFoundException;
import com.franquicias.franquicias_api.v2.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductValidator {

    public final ProductRepository productRepository;

    public void validateProductBelongsToBranch(Long productId, Long branchId){
        if (!productRepository.existsByIdAndBranchId(productId, branchId)) {
            throw new ProductNotBelongToBranch("El producto no pertenece a la sucursal indicada.");
        }
    }

    public void validateProductExists(Long productId){
        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("El producto con ID '" + productId + "' no existe"));
    }
}
