package com.cigi.facturation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
    @JsonIgnore
    private Commande commande;

    @ManyToOne
    @MapsId("prodId")
    @JsonIgnore
    private Produit produit;

    private int quantite;

    public LigneCommande(LigneCommandeID id) {
        this.id = id;
    }
}
