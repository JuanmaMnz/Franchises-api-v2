package com.franquicias.franquicias_api.v2.Repository;

import com.franquicias.franquicias_api.v2.Entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {

    boolean existsByName(String name);

    @Query("SELECT b.id FROM Branch b WHERE b.franchise.id = :franchiseId")
    List<Long> findBranchIdsByFranchiseId(@Param("franchiseId") Long franchiseId);

}
