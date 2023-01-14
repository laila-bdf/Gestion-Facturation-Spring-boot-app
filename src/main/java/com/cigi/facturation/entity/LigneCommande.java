package com.cigi.facturation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity@NoArgsConstructor
@Data
public class LigneCommande {
    @EmbeddedId
    private LigneCommandeID id = new LigneCommandeID();

    @ManyToOne
    @MapsId("commdId")
    private Commande commande;

    @ManyToOne
    @MapsId("prodId")
    private Produit produit;

    private int quantite;
}
