package com.cigi.facturation.service;

import com.cigi.facturation.entity.Commande;
import com.cigi.facturation.entity.LigneCommande;
import com.cigi.facturation.entity.LigneCommandeID;
import com.cigi.facturation.repository.LigneCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigneCommandeService {
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;


    //liste dyal les produits en se basant sur l id commande et leur quantite
    public List<LigneCommande> ListProduit(Commande commande){
        List<LigneCommande> ligneCommandes = ligneCommandeRepository.findByCommande(commande);
        return ligneCommandes;
    }

    public List<LigneCommandeID> ListProduitbyID(Long id){
        List<LigneCommandeID> ligneCommandes = ligneCommandeRepository.findByid(id);
        return ligneCommandes;
    }
}
