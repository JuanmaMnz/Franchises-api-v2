package com.franquicias.franquicias_api.v2.Validator;

import com.franquicias.franquicias_api.v2.Entity.Branch;
import com.franquicias.franquicias_api.v2.Exception.BranchExceptions.BranchNotFoundException;
import com.franquicias.franquicias_api.v2.Exception.BranchExceptions.FranchiseListEmptyException;
import com.franquicias.franquicias_api.v2.Exception.DuplicateNameException;
import com.franquicias.franquicias_api.v2.Repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BranchValidator {

    private final BranchRepository branchRepository;

    public void validateBranchName(String name) {
        if (branchRepository.existsByName(name)) {
            throw new DuplicateNameException("Ya existe una sucursal con el nombre '" + name + "'.");
        }
    }

    public void validateBranchesNotEmpty(List<?> branches) {
        if (branches == null || branches.isEmpty()) {
            throw new FranchiseListEmptyException("La lista de Sucursales no puede estar vacía");
        }
    }

    public void validateBranchExists(Long branchId){
        branchRepository.findById(branchId)
                .orElseThrow(() -> new BranchNotFoundException("La sucursal con ID '" + branchId + "' no existe."));
    }

    public Branch validateAndGetBranch(Long branchId){
        return branchRepository.findById(branchId)
                .orElseThrow(() -> new BranchNotFoundException("La sucursal con ID '" + branchId + "' no existe."));
    }
}
