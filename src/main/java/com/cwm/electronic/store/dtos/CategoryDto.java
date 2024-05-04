package com.cwm.electronic.store.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryDto {

    private String categoryId;

    @NotBlank(message = "title is req !!")
    @Size(min = 4,message = "title must be of minimum 4 characters")
    private String title;
    @NotBlank(message = "description required")
    private String description  ;
    @NotBlank
    private String coverImage;
}
