package com.example.ProjetIntermediaire.controller;

import com.example.ProjetIntermediaire.dto.CategoryListItem;
import com.example.ProjetIntermediaire.dto.FavoriteDefinition;
import com.example.ProjetIntermediaire.dto.FavoriteItem;
import com.example.ProjetIntermediaire.persistance.entity.Category;
import com.example.ProjetIntermediaire.service.ICategoryService;
import com.example.ProjetIntermediaire.service.IFavoritesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api") //DONE
public class ControllerCategory {
    private final ICategoryService categoryService;
    private final IFavoritesService favoritesService;
    public ControllerCategory(ICategoryService categoryService, IFavoritesService favoritesService) {
        this.categoryService = categoryService;
        this.favoritesService = favoritesService;
    }

    @GetMapping(path = "/categories") //DONE
    List<CategoryListItem> findAllCategory(){
        return categoryService.findAllCategory();
    }

    @PostMapping(path = "/category/{categoryId}/favorites")
    FavoriteItem save (@PathVariable long categoryId, @RequestBody FavoriteDefinition favoriteToAdd){
        return favoritesService.save(categoryId, favoriteToAdd);
    }
}