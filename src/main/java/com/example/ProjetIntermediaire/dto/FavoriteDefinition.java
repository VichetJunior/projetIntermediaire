package com.example.ProjetIntermediaire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDefinition {
    long id;
    String name;
    String link;
}
