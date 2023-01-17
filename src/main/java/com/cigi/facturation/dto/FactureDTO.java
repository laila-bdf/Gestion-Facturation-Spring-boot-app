package com.cigi.facturation.dto;

import com.cigi.facturation.util.Enum.EtatFacture;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class FactureDTO {
    private Long id;
    private static double TVA =  0.2;
    private float montant_HTVA;
    private Date date;
    private float montant_TTC;
    private Date echeance;
    private float montantPayer = 0;
    private EtatFacture etat;
}
