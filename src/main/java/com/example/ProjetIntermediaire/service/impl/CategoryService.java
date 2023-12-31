package com.example.ProjetIntermediaire.service.impl;

import com.example.ProjetIntermediaire.dto.CategoryDefinition;
import com.example.ProjetIntermediaire.dto.CategoryItem;
import com.example.ProjetIntermediaire.dto.CategoryListItem;
import com.example.ProjetIntermediaire.persistance.entity.Category;
import com.example.ProjetIntermediaire.persistance.repository.CategoryRepository;
import com.example.ProjetIntermediaire.persistance.repository.FavoritesRepository;
import com.example.ProjetIntermediaire.service.ICategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final FavoritesRepository favoritesRepository;
    public CategoryService(CategoryRepository categoryRepository, FavoritesRepository favoritesRepository) {
        this.categoryRepository = categoryRepository;
        this.favoritesRepository = favoritesRepository;
    }

    @Override
    public List<CategoryListItem> findAllCategory(){
        return categoryRepository.findAll()
                .stream()
                .map(p -> {
                    long nb = favoritesRepository.findAll().stream().filter(f -> f.getCategory().getId().equals(p.getId())).count();
                    return new CategoryListItem(p.getId(),p.getName(),nb);
                })
                .sorted(Comparator.comparing(CategoryListItem::getName))
                .toList();
    }

    @Override
    public CategoryItem createCategory(CategoryDefinition item) {
        Category categoryToAdd = categoryRepository.save(new Category(item.getId(),item.getName()));
        return new CategoryItem(categoryToAdd.getId(), categoryToAdd.getName());
    }
}
