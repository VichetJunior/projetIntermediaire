package com.example.ProjetIntermediaire.service;

import com.example.ProjetIntermediaire.dto.FavoriteDefinition;
import com.example.ProjetIntermediaire.dto.FavoriteItem;
import com.example.ProjetIntermediaire.dto.FavoriteListItem;

import java.util.List;

public interface IFavoritesService {

    FavoriteItem save(long favorite, FavoriteDefinition categoryId);

    // US1
    List<FavoriteListItem> findAll();

    // US3
    List<FavoriteListItem> filterByCategory(long idCategory);

    // US4 Sélectionner et Supprimer les favoris
    void delete(long id);

    void deleteMultiple(List<Long> ids);

}
