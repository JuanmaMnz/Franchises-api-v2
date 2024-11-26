package com.franquicias.franquicias_api.v2.Factory;

import com.franquicias.franquicias_api.v2.Entity.Franchise;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FranchiseFactory {

    public Franchise buildFranchise(String name) {
        return Franchise.builder()
                .name(name)
                .branches(List.of())
                .build();
    }

}
