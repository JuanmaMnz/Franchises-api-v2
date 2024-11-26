package com.franquicias.franquicias_api.v2.Factory;

import com.franquicias.franquicias_api.v2.Entity.Branch;
import com.franquicias.franquicias_api.v2.Entity.Franchise;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BranchFactory {

    public Branch buildBranch(String name, Franchise franchise){
        return Branch.builder()
                .name(name)
                .franchise(franchise)
                .products(List.of())
                .build();
    }
}
