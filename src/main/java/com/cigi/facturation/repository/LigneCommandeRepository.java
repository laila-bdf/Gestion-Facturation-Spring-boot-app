package com.cigi.facturation.repository;

import com.cigi.facturation.entity.Commande;
import com.cigi.facturation.entity.LigneCommande;
import com.cigi.facturation.entity.LigneCommandeID;
import com.cigi.facturation.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, LigneCommandeID> {
    ArrayList<LigneCommande> findByCommande(Commande commande);
}
