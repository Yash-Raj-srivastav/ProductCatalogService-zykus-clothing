package com.zykus.clothing.productcatalogservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import design.zykus.zykus.clothing.utils.ProductType;
import design.zykus.zykus.clothing.utils.ProductSize;
import design.zykus.zykus.clothing.utils.ProductStatus;

@Data
@RequiredArgsConstructor
public class ProductCatalogDTO {
    private ProductType productType;
    private String color;
    private String name;
    private ProductSize size;
    private float price;
    private String productDescription;
    private int rating;
    private ProductStatus productStatus;
    private int quantity;
}
