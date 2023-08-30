package com.example.ProjetIntermediaire.persistance.repository;

import com.example.ProjetIntermediaire.persistance.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
