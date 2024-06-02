package com.zykus.clothing.productcatalogservice.service;

import com.zykus.clothing.productcatalogservice.dto.ProductCatalogDTO;
import com.zykus.clothing.productcatalogservice.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductCatalogService {
    Iterable<Product> getAllProducts();
    Optional<Product> getSingleProduct(Long id);
    Product addNewProduct(ProductCatalogDTO product);
    List<Product> addMultipleNewProducts(Iterable<ProductCatalogDTO> products);
    Optional<Product> updateExistingProduct(ProductCatalogDTO product, Long orderId);
    List<Product> filter(String type, String size, Float minPrice, Float maxPrice);
    List<Product> sortByPrice();
    List<Product> sortByRating();
    Product deleteProduct(Long id);
}
