package com.cigi.facturation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity@Data@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Categorie",discriminatorType = DiscriminatorType.STRING,length = 2)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,property = "categorie")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "Ar" , value = Article.class),
        @JsonSubTypes.Type(name = "Se" , value = Service.class)
}
)
public  class Produit {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private float prixUnitaire ;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<LigneCommande> ligneCommande = new ArrayList<>();


    @OneToMany(mappedBy = "produit")
    private List<Fournisseur_Produit> fournisseur_produits=new ArrayList<>();

    public Produit(String nom, String description, float prix) {
        this.nom = nom;
        this.description = description;
        this.prixUnitaire = prix;
    }
}
