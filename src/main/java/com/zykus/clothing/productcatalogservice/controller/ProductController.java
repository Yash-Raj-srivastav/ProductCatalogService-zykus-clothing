package com.zykus.clothing.productcatalogservice.controller;

import com.zykus.clothing.productcatalogservice.dto.ProductCatalogDTO;
import com.zykus.clothing.productcatalogservice.model.Product;
import com.zykus.clothing.productcatalogservice.service.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import design.zykus.zykus.clothing.utils.ProductType;
import design.zykus.zykus.clothing.utils.ProductSize;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/products")
public class ProductController {

    @Autowired
    private ProductCatalogService productService;

    @GetMapping("/")
    public Iterable<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getSingleProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.productService.getSingleProduct(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filter(
            @RequestParam(required = false) ProductType type,
            @RequestParam(required = false) ProductSize size,
            @RequestParam(required = false) Float minPrice,
            @RequestParam(required = false) Float maxPrice
    ){
        return ResponseEntity.ok(productService.filter(type.toString(), size.toString(), minPrice, maxPrice));
    }

    @GetMapping("/sort_by_price")
    public List<Product> sortByPrice(){
        return productService.sortByPrice();
    }

    @GetMapping("/sort_by_rating")
    public List<Product> sortByRating(){
        return productService.sortByRating();
    }

    @PostMapping("/")
    public Product addNewProduct(@RequestBody ProductCatalogDTO product){
        return this.productService.addNewProduct(product);
    }

    @PostMapping("/add_multiple")
    public Iterable<Product> addMultipleNewUsers(@RequestBody Iterable<ProductCatalogDTO> products){
        return this.productService.addMultipleNewProducts(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Product>> updateExistingProduct(@RequestBody ProductCatalogDTO product, @PathVariable("id") Long id){
        return ResponseEntity.ok(this.productService.updateExistingProduct(product, id));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        this.productService.deleteProduct(id);
    }
}
