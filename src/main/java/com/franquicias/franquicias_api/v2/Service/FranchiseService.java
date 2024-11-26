package com.franquicias.franquicias_api.v2.Service;

import com.franquicias.franquicias_api.v2.DTOs.Mapper.FranchiseMapper;
import com.franquicias.franquicias_api.v2.DTOs.Request.FranchiseNameRequest;
import com.franquicias.franquicias_api.v2.DTOs.Request.FranchiseRequest;
import com.franquicias.franquicias_api.v2.DTOs.Response.FranchiseResponseDto;
import com.franquicias.franquicias_api.v2.Entity.Branch;
import com.franquicias.franquicias_api.v2.Entity.Franchise;
import com.franquicias.franquicias_api.v2.Factory.FranchiseFactory;
import com.franquicias.franquicias_api.v2.Repository.FranchiseRepository;
import com.franquicias.franquicias_api.v2.Validator.FranchiseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final FranchiseValidator franchiseValidator;
    private final FranchiseMapper franchiseMapper;
    private final FranchiseFactory franchiseFactory;
    private final BranchService branchService;

    public Franchise createFranchise(String name) {
        franchiseValidator.validateFranchiseName(name);
        Franchise franchise = franchiseFactory.buildFranchise(name);
        franchiseRepository.save(franchise);
        return franchise;
    }

    public FranchiseResponseDto addFranchise(FranchiseNameRequest franchiseNameRequest){
        Franchise franchise = createFranchise(franchiseNameRequest.getName());
        return franchiseMapper.toResponseDto(franchise);
    }

    @Transactional
    public FranchiseResponseDto addFranchiseWithBranches(FranchiseRequest franchiseRequest) {
        Franchise franchise = createFranchise(franchiseRequest.getName());
        List<Branch> branches = branchService.createBranchesWithProducts(franchiseRequest.getBranches(), franchise);
        franchise.setBranches( branches );
        return franchiseMapper.toResponseDto(franchise);
    }

    public FranchiseResponseDto updateFranchiseName(Long franchiseId, FranchiseNameRequest franchiseNameRequest){
        Franchise franchise = franchiseValidator.validateAndGetFranchise(franchiseId);
        franchise.setName(franchiseNameRequest.getName());
        franchiseRepository.save(franchise);
        return franchiseMapper.toResponseDto(franchise);
    }
}
