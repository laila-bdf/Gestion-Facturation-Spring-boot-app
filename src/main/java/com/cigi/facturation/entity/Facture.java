package com.cigi.facturation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity@Data@NoArgsConstructor
public class Facture {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private float montant_HTVA;
    private Date date;
    private float montant_TTC;
    private Date echeance;

    @OneToOne
    private Commande commande;
}
