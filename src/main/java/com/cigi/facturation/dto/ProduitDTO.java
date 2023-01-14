package com.cigi.facturation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class ProduitDTO {

    private long id;
    private String nom;
    private String description;
    private float prix ;
}
