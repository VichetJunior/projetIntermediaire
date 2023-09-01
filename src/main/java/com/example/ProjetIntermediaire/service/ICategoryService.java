package com.example.ProjetIntermediaire.service;

import com.example.ProjetIntermediaire.dto.CategoryDefinition;
import com.example.ProjetIntermediaire.dto.CategoryItem;
import com.example.ProjetIntermediaire.dto.CategoryListItem;

import java.util.List;

public interface ICategoryService {

    List<CategoryListItem> findAllCategory();

    CategoryItem createCategory(CategoryDefinition categoryToAdd);
}
