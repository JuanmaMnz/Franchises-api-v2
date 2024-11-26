package com.franquicias.franquicias_api.v2.DTOs.Mapper;

import com.franquicias.franquicias_api.v2.DTOs.Request.BranchRequest;
import com.franquicias.franquicias_api.v2.DTOs.Response.BranchResponseDto;
import com.franquicias.franquicias_api.v2.Entity.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface BranchMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "franchise", ignore = true)
    Branch branchRequestToEntity(BranchRequest branchRequest);

    BranchResponseDto toResponseDto(Branch branch);

    List<BranchResponseDto> toResponseDtoList(List<Branch> branches);

}
