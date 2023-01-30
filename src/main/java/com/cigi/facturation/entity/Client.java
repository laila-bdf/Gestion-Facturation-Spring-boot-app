package com.cigi.facturation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String Ville;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Commande> commandes = new ArrayList<Commande>();
}
