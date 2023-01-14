package com.cigi.facturation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Fournisseur {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String ville;

    @OneToMany(mappedBy = "fournisseur")
    private List<Fournisseur_Produit> fournisseur_produits=new ArrayList<>();
}