package com.franquicias.franquicias_api.v2.Service;

import com.franquicias.franquicias_api.v2.DTOs.Mapper.BranchMapper;
import com.franquicias.franquicias_api.v2.DTOs.Request.BranchNameRequest;
import com.franquicias.franquicias_api.v2.DTOs.Request.BranchRequest;
import com.franquicias.franquicias_api.v2.DTOs.Response.BranchResponseDto;
import com.franquicias.franquicias_api.v2.Entity.Branch;
import com.franquicias.franquicias_api.v2.Entity.Franchise;
import com.franquicias.franquicias_api.v2.Entity.Product;
import com.franquicias.franquicias_api.v2.Factory.BranchFactory;
import com.franquicias.franquicias_api.v2.Repository.BranchRepository;
import com.franquicias.franquicias_api.v2.Validator.BranchValidator;
import com.franquicias.franquicias_api.v2.Validator.FranchiseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;
    private final BranchFactory branchFactory;
    private final BranchValidator branchValidator;

    private final FranchiseValidator franchiseValidator;
    private final ProductService productService;

    public Branch createBranch(String name, Long franchiseId){
        Franchise franchise = franchiseValidator.validateAndGetFranchise(franchiseId);
        branchValidator.validateBranchName(name);
        Branch branch = branchFactory.buildBranch(name, franchise);
        branchRepository.save(branch);
        return branch;
    }

    public BranchResponseDto addBranchToFranchise(BranchNameRequest branchNameRequest, Long franchiseId){
        Branch branch = createBranch(branchNameRequest.getName(), franchiseId);
        return branchMapper.toResponseDto(branch);
    }

    @Transactional
    public List<Branch> createBranchesWithProducts(List<BranchRequest> branchRequests, Franchise franchise) {
        branchValidator.validateBranchesNotEmpty(branchRequests);
        return branchRequests.stream()
                .map(branchRequest -> {
                    Branch branch = createBranch(branchRequest.getName(), franchise.getId());
                    List<Product> savedProducts = productService.processAndSaveProducts(branchRequest.getProducts(), branch);
                    branch.setProducts(savedProducts);
                    return branch;
                })
                .toList();
    }

    public BranchResponseDto updateBranchName(Long branchId, BranchNameRequest branchNameRequest){
        Branch branch = branchValidator.validateAndGetBranch(branchId);
        branch.setName(branchNameRequest.getName());
        branchRepository.save(branch);
        return branchMapper.toResponseDto(branch);
    }



}
