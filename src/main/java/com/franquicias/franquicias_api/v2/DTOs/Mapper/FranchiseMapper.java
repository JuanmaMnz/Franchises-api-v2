package com.franquicias.franquicias_api.v2.DTOs.Mapper;

import com.franquicias.franquicias_api.v2.DTOs.Request.FranchiseRequest;
import com.franquicias.franquicias_api.v2.DTOs.Response.FranchiseResponseDto;
import com.franquicias.franquicias_api.v2.Entity.Franchise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = BranchMapper.class)
public interface FranchiseMapper {

    @Mapping(target = "id", ignore = true)
    Franchise franchiseRequestToEntity(FranchiseRequest franchiseRequest);

    @Mapping(target = "branches", source = "branches")
    FranchiseResponseDto toResponseDto(Franchise franchise);

    List<FranchiseResponseDto> toResponseDtoList(List<Franchise> franchises);
}

