package com.cigi.facturation.entity;

import com.cigi.facturation.util.Enum.EtatCommande;
import com.cigi.facturation.util.interfaces.GestionEtatCommande;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity@NoArgsConstructor
@Data
public class Commande implements GestionEtatCommande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String designation;
    private float solde;
    private Date date;
    private EtatCommande etat;

    @ManyToOne
    private Client client;

    @OneToOne(mappedBy = "commande")
    private Facture facture;

    @ManyToMany
    @JoinTable(
            name = "LigneCommande"
    )
    private List<Produit> produits ;


    public void confirmer(){
        this.etat = EtatCommande.CONFIRMEE;
    }

    public void mettreEnAttente(){
        this.etat = EtatCommande.EN_ATTENTE;
    }
}
