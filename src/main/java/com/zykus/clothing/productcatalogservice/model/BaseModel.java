package com.zykus.clothing.productcatalogservice.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDate;

@Data
@MappedSuperclass
public class BaseModel {
    @Id
    private Long Id;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
