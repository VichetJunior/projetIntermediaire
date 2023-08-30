package com.example.ProjetIntermediaire.dto;

import com.example.ProjetIntermediaire.persistance.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class FavoriteListItem {
    private Long id;
    private String name;
    private String link;
    private Date updatedDate;
    private CategoryItem category;
}
