package com.franquicias.franquicias_api.v2.Repository;

import com.franquicias.franquicias_api.v2.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByIdAndBranchId(Long productId, Long branchId);

    @Modifying
    @Query("DELETE FROM Product p WHERE p.branch.id = :branchId AND p.id = :productId")
    void deleteProductFromBranch(@Param("branchId") Long branchId, @Param("productId") Long productId);

    @Modifying
    @Query("UPDATE Product p SET p.stock = :stock WHERE p.id = :id")
    void updateStockById(@Param("id") Long id, @Param("stock") int stock);

    @Modifying
    @Query("UPDATE Product p SET p.name = :name WHERE p.id = :id")
    void updateNameById(@Param("id") Long id, @Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.branch.id = :branchId AND p.stock = (SELECT MAX(p2.stock) FROM Product p2 WHERE p2.branch.id = :branchId)")
    List<Product> findTopProductsByBranch(@Param("branchId") Long branchId);

    @Query("SELECT COUNT(p) > 0 FROM Product p WHERE p.branch.id = :branchId")
    boolean existsProductsByBranch(@Param("branchId") Long branchId);
}
