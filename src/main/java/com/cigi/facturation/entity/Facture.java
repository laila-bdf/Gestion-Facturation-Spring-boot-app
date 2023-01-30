package com.cigi.facturation.entity;

import com.cigi.facturation.util.Enum.EtatFacture;
import com.cigi.facturation.util.interfaces.GestionEtatFacture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity@Data@NoArgsConstructor
public class Facture implements GestionEtatFacture {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JsonIgnore
    private Facture ancienneFacture;
    private final float TVA =  0.2f;
    private float montant_HTVA;
    private Date date =new Date();
    private float montant_TTC;
    private float montantPayer = 0;
    private EtatFacture etat;

    @ManyToOne
    @JsonIgnore
    private Commande commande;
    @Override
    public void payer(){
        this.etat = EtatFacture.PAYEE;
    }
    @Override
    public void payerEnPartie(float montantPayer){
        this.etat = EtatFacture.PAYEE_EN_PARTIE;
        this.montantPayer +=montantPayer;
    }
    @Override
    public void nonPayee(){
         this.etat = EtatFacture.NON_PAYEE;
    }
}
