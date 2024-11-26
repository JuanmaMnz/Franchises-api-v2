package com.franquicias.franquicias_api.v2.Validator;

import com.franquicias.franquicias_api.v2.Entity.Franchise;
import com.franquicias.franquicias_api.v2.Exception.DuplicateNameException;
import com.franquicias.franquicias_api.v2.Exception.FranchiseExceptions.FranchiseNotFoundException;
import com.franquicias.franquicias_api.v2.Repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FranchiseValidator {

    private final FranchiseRepository franchiseRepository;

    public void validateFranchiseName(String name) {
        if (franchiseRepository.existsByName(name)) {
            throw new DuplicateNameException("Ya existe una franquicia con el nombre '" + name + "'.");
        }
    }

    public void validateFranchiseExists(Long franchiseId){
        franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new FranchiseNotFoundException("La franquicia con ID '" + franchiseId + "' no existe."));
    }

    public Franchise validateAndGetFranchise(Long franchiseId) {
        return franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new FranchiseNotFoundException("La franquicia con ID '" + franchiseId + "' no existe."));
    }
}
