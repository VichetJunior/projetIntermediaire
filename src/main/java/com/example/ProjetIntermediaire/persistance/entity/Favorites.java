package com.example.ProjetIntermediaire.persistance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column (name = "name", nullable = false)
    private String name;
    @Lob
    @Column (name = "link", nullable = false)
    private String link;

    @Column (name = "updated_date", columnDefinition = "date")
    private Date updatedDate;

    //Relation Entité-Entité
        //Récupère automatiquement la catégorie automatiquement
        //optional = false : oblige à ce qu'un FAVORIS soit associé à une Catégorie.
        //fetch = FetchType.EAGER
        //cascade :  l'attribut cascade de l'annotation @OneToMany indique que les opérations de persistance effectuées sur l'entité Author doivent également être appliquées aux entités associées dans la liste books.
            //CascadeType.All : ensemble des autres valeurs
            //CascadeType.REMOVE : Supprimer un lien, supprime aussi la Catégorie associée
                // Le REMOVE ne supprimera que les catégories orphelines
                // C'est à dire si un autre FAVORIS est associé à une CATEGORY, alors la catégorie en question ne sera pas supprimée
            //CascadeType.PERSIST : créer / sauvegarde
            //CascadeType.MERGE mise à jour
            //CascadeType.REFRESH : opération de lecture
            //CascadeType.DETACH : lorsqu'on quitte la BDD, l'entité est détachée


    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    //@JoinColumn est utilisé pour spécifier les détails de la colonne de jointure à utiliser
    //dans les relations entre entités dans JPA
    //insertable : false Interdiction de faire insert lorsqu'on crée un lien si on indique une catégorie inexistante en BDD
        //referencedColumnName = "nomColonne" correspondant à ce qu'on a indiqué dans l'id de la classe Category
        //table = "category" il faut indiquer le nom de la table indiquée dans l'entity "Catégorie".
    @JoinColumn(name = "category_id",referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_CATEGORY_FAVORI"))// On peut nommer l'ID qui correspond, par défaut il le nommera "entityName-id"
    @Fetch(FetchMode.JOIN)
    private Category category;
}
