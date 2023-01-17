package com.cigi.facturation.entity;

import com.cigi.facturation.util.Enum.EtatCommande;
import com.cigi.facturation.util.interfaces.GestionEtatCommande;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity@NoArgsConstructor
@Data
public class Commande implements GestionEtatCommande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String designation;
    private Date date;
    private EtatCommande etat;

    @ManyToOne
    private Client client;

    @OneToOne(mappedBy = "commande")
    private Facture facture;

    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommande = new ArrayList<>();


    public void confirmer(){
        this.etat = EtatCommande.CONFIRMEE;
    }

    public void mettreEnAttente(){
        this.etat = EtatCommande.EN_ATTENTE;
    }
}
