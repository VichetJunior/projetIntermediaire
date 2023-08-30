package com.example.ProjetIntermediaire.controller;

import com.example.ProjetIntermediaire.dto.FavoriteItem;
import com.example.ProjetIntermediaire.dto.FavoriteListItem;
import com.example.ProjetIntermediaire.service.IFavoritesService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
class ControllerFavorites {

    private final IFavoritesService favoritesService;

    ControllerFavorites(IFavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @GetMapping(path = "/favorites")//DONE
    List<FavoriteListItem> findAll(){
        return favoritesService.findAll();
    }



    @DeleteMapping(path = "/favorites/{ids}")
    void deleteMultiple(@PathVariable String ids){
        favoritesService.deleteMultiple(
                Arrays.stream(ids.split("-")).map(Long::valueOf).toList()
        );
    }

   @GetMapping("/filter")
   List<FavoriteListItem> filterByCategory(@PathVariable long idCategory){
        return favoritesService.filterByCategory(idCategory);
    }
}
