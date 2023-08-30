package com.example.ProjetIntermediaire.service;

import com.example.ProjetIntermediaire.dto.CategoryListItem;

import java.util.List;

public interface ICategoryService {

    List<CategoryListItem> findAllCategory();
}
