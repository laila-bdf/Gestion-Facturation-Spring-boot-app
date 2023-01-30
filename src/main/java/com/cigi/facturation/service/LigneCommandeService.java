package com.cigi.facturation.service;

import com.cigi.facturation.entity.Commande;
import com.cigi.facturation.entity.LigneCommande;
import com.cigi.facturation.entity.LigneCommandeID;
import com.cigi.facturation.entity.Produit;
import com.cigi.facturation.repository.CommandeRepository;
import com.cigi.facturation.repository.LigneCommandeRepository;
import com.cigi.facturation.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigneCommandeService {
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    private CommandeRepository commandeRepository;



    @Autowired
    private ProduitRepository produitrepository;

    //liste dyal les produits en se basant sur l id commande et leur quantite
    public List<LigneCommande> ListProduit(Commande commande){
        List<LigneCommande> ligneCommandes = ligneCommandeRepository.findByCommande(commande);
        return ligneCommandes;
    }

    public List<LigneCommandeID> ListProduitbyID(Long id){
        List<LigneCommandeID> ligneCommandes = ligneCommandeRepository.findByid(id);
        return ligneCommandes;
    }

    public Page<LigneCommande> findAll(Pageable page) {
        return ligneCommandeRepository.findAll(page);
    }
    public LigneCommande save(LigneCommande ligneCommande) {
        try {
            Commande commande = commandeRepository.findById(ligneCommande.getId().getCommdId()).get();
            Produit produit = produitrepository.findById(ligneCommande.getId().getProdId()).get();
            ligneCommande.setProduit(produit);
            ligneCommande.setCommande(commande);
            return ligneCommandeRepository.save(ligneCommande);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
