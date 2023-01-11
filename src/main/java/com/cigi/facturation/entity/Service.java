package com.cigi.facturation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue(value = "Se")
public class Service extends Produit{

    private String type;

    public Service(String type,String nom,String description,  float prix) {
        super(nom,description,prix);
        this.type = type;
    }
}
