package com.cigi.facturation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Data
public class ClientDTO {

    private long id;
    private String nom;
    private String prenom;
    private String Ville;

}