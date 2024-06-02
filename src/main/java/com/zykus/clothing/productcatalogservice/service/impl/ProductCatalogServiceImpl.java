package com.zykus.clothing.productcatalogservice.service.impl;

import com.zykus.clothing.productcatalogservice.dto.ProductCatalogDTO;
import com.zykus.clothing.productcatalogservice.model.Product;
import com.zykus.clothing.productcatalogservice.repository.ProductCatalogRepository;
import com.zykus.clothing.productcatalogservice.service.ProductCatalogService;
import design.zykus.zykus.clothing.utils.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import design.zykus.zykus.clothing.utils.ProductType;
import design.zykus.zykus.clothing.utils.ProductSize;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCatalogServiceImpl implements ProductCatalogService {

    @Autowired
    private ProductCatalogRepository productRepository;
    HashMap<Integer, ProductType> productTypeMap = new HashMap<>();

    public Iterable<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    public Optional<Product> getSingleProduct(Long id){
        return this.productRepository.findById(id);
    }

    public Product addNewProduct(ProductCatalogDTO product){
        Product newProduct = convertProductDTOToProduct(product);
        return productRepository.save(newProduct);
    }

    public List<Product> addMultipleNewProducts(Iterable<ProductCatalogDTO> products){
        List<Product> savedProducts = null;
        for(ProductCatalogDTO productCatalogDTO: products){
            Product newProduct = convertProductDTOToProduct(productCatalogDTO);
            savedProducts.add(newProduct);
        }
        return savedProducts;
    }

    public Optional<Product> updateExistingProduct(ProductCatalogDTO product, Long orderId){
        return productRepository.findById(orderId)
                .map(existingProduct -> {
                    // Update only the non-null fields from the updatedUser
                    if (product.getProductType() != null) {
                        existingProduct.setProductType(product.getProductType());
                    }
                    if (product.getName() != null) {
                        existingProduct.setName(product.getName());
                    }
                    if(product.getColor() != null){
                        existingProduct.setColor(product.getColor());
                    }
                    if(product.getSize() == ProductSize.SMALL || product.getSize() == ProductSize.MEDIUM || product.getSize() == ProductSize.LARGE || product.getSize() == ProductSize.EXTRA_LARGE){
                        existingProduct.setSize(product.getSize());
                    }
                    if(product.getPrice() >= 100){
                        existingProduct.setPrice(product.getPrice());
                    }
                    if(product.getProductDescription() != null){
                        existingProduct.setProductDescription(product.getProductDescription());
                    }
                    if(product.getRating() > 0 && product.getRating() <= 5){
                        int calcRating = (existingProduct.getRating() + product.getRating()) / 5;
                        existingProduct.setRating(calcRating);
                    }
                    if(product.getProductStatus() != null){
                        existingProduct.setProductStatus(product.getProductStatus());
                    }
                    if(product.getQuantity() >= 0){
                        existingProduct.setQuantity(product.getQuantity());
                    }
                    existingProduct.setUpdatedDate(LocalDate.now());
                    Product savedProduct = productRepository.save(existingProduct);
                    return Optional.of(savedProduct);
                })
                .orElse(null);
    }

    public Product deleteExistingProduct(Long productId){
        return productRepository.findById(productId)
                .map(existingProduct -> {
                    Product deletedProduct = null;
                    if(existingProduct.getId() == productId){
                        deletedProduct = existingProduct;
                        productRepository.deleteById(productId);
                    }
                    return deletedProduct;
                })
                .orElse(null);
    }

    @Override
    public List<Product> filter(String type, String size, Float minPrice, Float maxPrice) {
        List<Product> list = productRepository.filter(type, size, minPrice, maxPrice);
        return list;
    }

    @Override
    public List<Product> sortByPrice() {
        return productRepository.sortByPrice();
    }

    @Override
    public List<Product> sortByRating() {
        return productRepository.sortByRating();
    }

    @Override
    public Product deleteProduct(Long id){
        Optional<Product> deletedProduct = productRepository.findById(id);
        if(deletedProduct.isEmpty()){
            return null;
        }
        productRepository.deleteById(id);
        return deletedProduct.get();
    }

    public Product convertProductDTOToProduct(ProductCatalogDTO product){
        Product newProduct = new Product();
        newProduct.setProductType(product.getProductType());
        newProduct.setColor(product.getColor());
        newProduct.setName(product.getName());
        newProduct.setSize(product.getSize());
        newProduct.setPrice(product.getPrice());
        newProduct.setProductDescription(product.getProductDescription());
        newProduct.setRating(product.getRating());
        newProduct.setProductStatus(ProductStatus.IN_STOCK);
        newProduct.setUpdatedDate(LocalDate.now());
        newProduct.setCreatedDate(LocalDate.now());

        return newProduct;
    }
}
