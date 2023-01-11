package com.cigi.facturation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Fournisseur {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nom;
    private String prenom;
    private String ville;

    @OneToMany
    private List<Fournisseur_Produit> fournisseur_produits=new ArrayList<>();
}