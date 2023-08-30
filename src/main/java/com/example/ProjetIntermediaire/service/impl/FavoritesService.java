package com.example.ProjetIntermediaire.service.impl;

import com.example.ProjetIntermediaire.dto.CategoryItem;
import com.example.ProjetIntermediaire.dto.FavoriteDefinition;
import com.example.ProjetIntermediaire.dto.FavoriteItem;
import com.example.ProjetIntermediaire.dto.FavoriteListItem;
import com.example.ProjetIntermediaire.exception.NotFoundException;
import com.example.ProjetIntermediaire.persistance.entity.Favorites;
import com.example.ProjetIntermediaire.persistance.repository.CategoryRepository;
import com.example.ProjetIntermediaire.persistance.repository.FavoritesRepository;
import com.example.ProjetIntermediaire.service.IFavoritesService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional //Toutes les méthodes qu'ils contiennent deviennent transactionnelles
class FavoritesService implements IFavoritesService {

    private final FavoritesRepository favoriteRepository;
    private final CategoryRepository categoryRepository;
    FavoritesService(FavoritesRepository favoriteRepository, CategoryRepository categoryRepository) {
        this.favoriteRepository = favoriteRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public FavoriteItem save(long categoryId, FavoriteDefinition item){
        Date now = new Date();
        Favorites favoriteToAdd = favoriteRepository
                .save(new Favorites(item.getId(), item.getName(), item.getLink(), now, categoryRepository
                .findById(categoryId).orElseThrow(() -> new NotFoundException("not found"))));;

                return new FavoriteItem(favoriteToAdd.getId(), favoriteToAdd.getName(), favoriteToAdd.getLink(), now,
                        new CategoryItem(favoriteToAdd.getCategory().getId(), favoriteToAdd.getCategory().getName()));
    }

    @Override
    public List<FavoriteListItem> findAll() {
        return favoriteRepository.findAll()
                .stream()
                .map(p->new FavoriteListItem(p.getId(),p.getName(),p.getLink(),p.getUpdatedDate(),new CategoryItem(p.getCategory().getId(),p.getCategory().getName())))
                .toList();
    }

    @Override
    public List<FavoriteListItem> filterByCategory(long idCategory) {
        return favoriteRepository.findAll()
                .stream()
                .filter(f-> f.getCategory().getId().equals(idCategory))
                        .map(m-> new FavoriteListItem(m.getId(), m.getName(), m.getLink(), m.getUpdatedDate(),new CategoryItem(m.getCategory().getId(), m.getCategory().getName())))
                .toList();
    }

    @Override
    public void delete(long id) {
        Favorites favorite = favoriteRepository.findById(id).orElseThrow(()-> new NotFoundException("message d'erreur"));
        favoriteRepository.delete(favorite);
    }

    @Override
    public void deleteMultiple(List<Long> ids) {
        for (Long id : ids){
            delete(id);
        }
    }

}
