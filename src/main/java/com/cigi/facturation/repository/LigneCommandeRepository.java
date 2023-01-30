package com.cigi.facturation.repository;

import com.cigi.facturation.entity.Commande;
import com.cigi.facturation.entity.LigneCommande;
import com.cigi.facturation.entity.LigneCommandeID;
import com.cigi.facturation.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, LigneCommandeID> {
    ArrayList<LigneCommande> findByCommande(Commande commande);
    ArrayList<LigneCommandeID> findByid(Long id);

    @Query(value = "SELECT count(produit_id) as nbr FROM ligne_commande WHERE nbr >=7 group by nbr", nativeQuery = true)
    public Iterable<LigneCommande> findByCountId();
}
