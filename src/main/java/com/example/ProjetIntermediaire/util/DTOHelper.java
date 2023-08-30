package com.example.ProjetIntermediaire.util;

import com.example.ProjetIntermediaire.dto.CategoryListItem;
import com.example.ProjetIntermediaire.persistance.entity.Category;
import com.example.ProjetIntermediaire.persistance.repository.CategoryRepository;
import com.example.ProjetIntermediaire.persistance.repository.FavoritesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DTOHelper {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FavoritesRepository favoritesRepository;

    private ModelMapper mapper = new ModelMapper();
    public CategoryListItem toCategoryListItem(Category entity){
        CategoryListItem dto = mapper.map(entity, CategoryListItem.class);
        dto.setReferences(
                favoritesRepository.findAll()
                        .stream()
                        .filter(f->f.getCategory().getId().equals(entity.getId()))
                        .count()
        );
        return dto;
    }

}
