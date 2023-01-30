package com.cigi.facturation.entity;

import com.cigi.facturation.util.Enum.EtatCommande;
import com.cigi.facturation.util.interfaces.GestionEtatCommande;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity@NoArgsConstructor
@Data
public class Commande implements GestionEtatCommande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String designation;
    private Date date =new Date(0);
    private EtatCommande etat;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.REMOVE)
    private List<Facture> facture;

    @OneToMany(mappedBy = "commande" , cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<LigneCommande> ligneCommande = new ArrayList<>();


    public void confirmer(){
        this.etat = EtatCommande.CONFIRMEE;
    }

    public void mettreEnAttente(){
        this.etat = EtatCommande.EN_ATTENTE;
    }
}
