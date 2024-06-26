package com.cwm.electronic.store.dtos;

import com.cwm.electronic.store.entities.Category;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {
    private String productId;

    private  String title;

    private String description;

    private int price;

    private  int discountedPrice;

    private int  quantity;

    private Date addedDate;

    private boolean isLive;

    private  boolean stock;
    private String productImageName;

    private CategoryDto category;
}
