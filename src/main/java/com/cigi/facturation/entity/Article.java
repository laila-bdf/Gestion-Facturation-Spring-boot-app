package com.cigi.facturation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Ar")
@Data
@NoArgsConstructor
public class Article extends Produit {

    private int stock;

    public Article(int stock,String nom,String description,  float prix) {
        super(nom,description,prix);
        this.stock = stock;
    }
}
