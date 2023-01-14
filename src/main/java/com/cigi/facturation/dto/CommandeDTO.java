package com.cigi.facturation.dto;

import com.cigi.facturation.entity.Client;
import com.cigi.facturation.util.Enum.EtatCommande;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class CommandeDTO {

    private Long id;
    private String designation;
    private float solde;
    private Date date;
    private EtatCommande etat;
    private Client client;
}
