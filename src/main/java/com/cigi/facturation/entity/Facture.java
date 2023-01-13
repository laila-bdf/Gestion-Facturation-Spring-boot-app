package com.cigi.facturation.entity;

import com.cigi.facturation.util.Enum.EtatFacture;
import com.cigi.facturation.util.interfaces.GestionEtatFacture;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity@Data@NoArgsConstructor
public class Facture implements GestionEtatFacture {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float montant_HTVA;
    private Date date;
    private float montant_TTC;
    private Date echeance;
    private float montantPayer = 0;
    private EtatFacture etat;

    @OneToOne
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
    public boolean estPayee(){
        return this.etat == EtatFacture.PAYEE;
    }
}
