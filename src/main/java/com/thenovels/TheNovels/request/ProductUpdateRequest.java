package com.thenovels.TheNovels.request;

import com.thenovels.TheNovels.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int inventory;
    private Category category;
}
