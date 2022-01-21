package com.caro.catalog.repositories;

import com.caro.catalog.entities.Category;
import com.caro.catalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
