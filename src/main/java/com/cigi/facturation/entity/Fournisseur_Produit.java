package com.cigi.facturation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.awt.print.Book;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fournisseur_Produit {
    @EmbeddedId
    private FournProId id = new FournProId();

    @ManyToOne
    @MapsId("fournId")
    private Fournisseur fournisseur;

    @ManyToOne
    @MapsId("prodId")
    private Produit produit;

    private int quantite;
}
