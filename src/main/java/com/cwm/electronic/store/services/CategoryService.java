package com.cwm.electronic.store.services;

import com.cwm.electronic.store.dtos.CategoryDto;
import com.cwm.electronic.store.dtos.PageableResponse;
import lombok.Setter;
import org.springframework.stereotype.Service;


public interface CategoryService {
    //create
    CategoryDto create(CategoryDto categoryDto);
    //update
    CategoryDto update(CategoryDto categoryDto,String categoryId);
    //delete
    void delete(String categoryID);
    //get all
    PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir);
    //get single cat
    CategoryDto get(String categoryId);
}
