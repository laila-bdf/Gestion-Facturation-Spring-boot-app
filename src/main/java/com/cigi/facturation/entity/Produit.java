package com.cigi.facturation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity@Data@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Categorie",discriminatorType = DiscriminatorType.STRING,length = 2)
public class Produit {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private float prixUnitaire ;

    @OneToMany(mappedBy = "produit")
    private List<LigneCommande> ligneCommande = new ArrayList<>();


    @OneToMany(mappedBy = "produit")
    private List<Fournisseur_Produit> fournisseur_produits=new ArrayList<>();

    public Produit(String nom, String description, float prix) {
        this.nom = nom;
        this.description = description;
        this.prixUnitaire = prix;
    }
}
