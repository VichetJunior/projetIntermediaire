package com.example.ProjetIntermediaire.persistance.repository;

import com.example.ProjetIntermediaire.persistance.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<Favorites, Long>{

}
