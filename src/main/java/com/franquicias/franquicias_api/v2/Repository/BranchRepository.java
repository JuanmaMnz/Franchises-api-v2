package com.franquicias.franquicias_api.v2.Repository;

import com.franquicias.franquicias_api.v2.Entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    boolean existsByName(String name);
}
