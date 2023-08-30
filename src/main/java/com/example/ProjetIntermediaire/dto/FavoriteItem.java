package com.example.ProjetIntermediaire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteItem {
    private Long id;
    private String name;
    private String link;
    private Date updatedDate;
    private CategoryItem category;
}
