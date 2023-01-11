package com.cigi.facturation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity@NoArgsConstructor
@Data
public class Commande {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String designation;
    private float solde;
    private Date date;

    @ManyToOne
    private Client client;

    @OneToOne
    private Facture facture;

    @ManyToMany
    @JoinTable(
            name = "LigneCommande"
    )
    private List<Produit> produits ;

}
