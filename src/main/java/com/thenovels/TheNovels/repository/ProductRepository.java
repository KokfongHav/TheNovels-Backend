package com.thenovels.TheNovels.repository;

import com.thenovels.TheNovels.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String category);
    List<Product> findByName(String name);
    Long countByBrandAndName(String brand, String name);
    List<Product> findByBrand(String brand);
}
