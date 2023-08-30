package com.example.ProjetIntermediaire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListItem {
            private long id;
            private String name;
            private long references;
}
