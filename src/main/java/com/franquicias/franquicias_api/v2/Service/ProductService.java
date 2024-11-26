package com.franquicias.franquicias_api.v2.Service;

import com.franquicias.franquicias_api.v2.DTOs.Mapper.ProductMapper;
import com.franquicias.franquicias_api.v2.DTOs.Request.ProductNameRequest;
import com.franquicias.franquicias_api.v2.DTOs.Request.ProductRequest;
import com.franquicias.franquicias_api.v2.DTOs.Request.ProductStockRequest;
import com.franquicias.franquicias_api.v2.DTOs.Response.ProductResponseDto;
import com.franquicias.franquicias_api.v2.DTOs.Response.ProductWithBranchResponseDto;
import com.franquicias.franquicias_api.v2.Entity.Branch;
import com.franquicias.franquicias_api.v2.Entity.Product;
import com.franquicias.franquicias_api.v2.Repository.FranchiseRepository;
import com.franquicias.franquicias_api.v2.Repository.ProductRepository;
import com.franquicias.franquicias_api.v2.Validator.BranchValidator;
import com.franquicias.franquicias_api.v2.Validator.FranchiseValidator;
import com.franquicias.franquicias_api.v2.Validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductValidator productValidator;
    private final BranchValidator branchValidator;
    private final FranchiseValidator franchiseValidator;
    private final FranchiseRepository franchiseRepository;

    @Transactional
    public void deleteProductFromBranch(Long branchId, Long productId){
        branchValidator.validateBranchExists(branchId);
        productValidator.validateProductExists(productId);
        productValidator.validateProductBelongsToBranch(productId, branchId);
        productRepository.deleteProductFromBranch(branchId, productId);
    }

    @Transactional
    public void updateProductStock(Long productId, Long branchId, ProductStockRequest productStockRequest){
        branchValidator.validateBranchExists(branchId);
        productValidator.validateProductExists(productId);
        productValidator.validateProductBelongsToBranch(productId, branchId);
        productRepository.updateStockById(productId, productStockRequest.getStock());
    }

    @Transactional
    public void updateProductName(Long productId, Long branchId, ProductNameRequest productNameRequest){
        branchValidator.validateBranchExists(branchId);
        productValidator.validateProductExists(productId);
        productValidator.validateProductBelongsToBranch(productId, branchId);
        productRepository.updateNameById(productId, productNameRequest.getName());
    }

    @Transactional
    public List<ProductWithBranchResponseDto> getTopProductsByFranchise(Long franchiseId) {
        franchiseValidator.validateFranchiseExists(franchiseId);
        List<Long> branchIdsForFranchise = franchiseRepository.findBranchIdsByFranchiseId(franchiseId);
        List<Product> topProducts = new ArrayList<>();

        for (Long branchId : branchIdsForFranchise) {
            if (productRepository.existsProductsByBranch(branchId)) {
                List<Product> products = productRepository.findTopProductsByBranch(branchId);
                topProducts.addAll(products);
            }
        }

        return productMapper.toProductWithBranchResponseDtoList(topProducts);
    }


    public ProductResponseDto createProduct(ProductRequest productRequest, Long branchId){
        Branch branch = branchValidator.validateAndGetBranch(branchId);
        Product product = mapAndSetBranch(productRequest, branch);
        productRepository.save(product);
        return productMapper.toResponseDto(product);
    }

    @Transactional
    public List<Product> processAndSaveProducts(List<ProductRequest> productRequests, Branch branch) {
        return productRequests.stream()
                .map(productRequest -> mapAndSetBranch(productRequest, branch))
                .map(productRepository::save)
                .collect(Collectors.toList());
    }

    public Product mapAndSetBranch(ProductRequest productRequest, Branch branch) {
        Product product = productMapper.toEntity(productRequest);
        product.setBranch(branch);
        return product;
    }
}
