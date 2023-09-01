package com.example.ProjetIntermediaire.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column (name = "name", unique = true)
    private String name;

//    @ManyToOne
//    @JoinColumn(name = "favorite_id")
//    private List<Favorites> FavoritesList;
}
