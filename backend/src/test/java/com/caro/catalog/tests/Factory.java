package com.caro.catalog.tests;

import com.caro.catalog.dto.ProductDTO;
import com.caro.catalog.entities.Category;
import com.caro.catalog.entities.Product;

import java.time.Instant;

public class Factory {
    public static Product createProduct() {
        Product product = new Product(1L, "Phone","Good phone", 800.0,"https://img.com/img.png", Instant.parse("2020" +
                "-10-20T03:00:00Z"));
        product.getCategories().add(new Category(2L,"Electronics"));
        return product;
    }
    public static ProductDTO createProductDTO(){
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }
}
