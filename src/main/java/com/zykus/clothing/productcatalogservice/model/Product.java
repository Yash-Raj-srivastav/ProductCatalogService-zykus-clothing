package com.zykus.clothing.productcatalogservice.model;

import design.zykus.zykus.clothing.utils.ProductType;
import design.zykus.zykus.clothing.utils.ProductSize;
import design.zykus.zykus.clothing.utils.ProductStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "products")
@RequiredArgsConstructor
public class Product extends BaseModel {
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private String color;
    private String name;
    @Enumerated(EnumType.STRING)
    private ProductSize size;
    private float price;
    private String productDescription;
    private int rating;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    private int quantity;
}
