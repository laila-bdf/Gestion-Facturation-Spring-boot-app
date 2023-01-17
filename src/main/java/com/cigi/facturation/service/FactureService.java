package com.cigi.facturation.service;

import com.cigi.facturation.dto.ClientDTO;
import com.cigi.facturation.dto.FactureDTO;
import com.cigi.facturation.entity.Client;
import com.cigi.facturation.entity.Commande;
import com.cigi.facturation.entity.Facture;
import com.cigi.facturation.entity.LigneCommande;
import com.cigi.facturation.exception.EntityNotFoundException;
import com.cigi.facturation.exception.UnableToSaveEntityException;
import com.cigi.facturation.mapper.FactureMapper;
import com.cigi.facturation.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    @Lazy
    private CommandeService commandeService;
    @Autowired
    private FactureMapper factureMapper;

    @Autowired
    private LigneCommandeService ligneCommandeService;

    public Page<FactureDTO> findAll(Pageable page) {
        return factureMapper.toDTO(factureRepository.findAll(page));
    }


    public Facture save(Facture facture) {
        commandeService.findById(facture.getCommande().getId());
        try {
            facture.nonPayee();
            return factureRepository.save(facture);
        }catch (Exception e){
            throw new UnableToSaveEntityException("unable to save Facture");
        }

    }

    public Facture createFacture(Commande commande) {
        Facture newFacture= new Facture();
        newFacture.setCommande(commande);

        List<LigneCommande>  productQuantities =ligneCommandeService.ListProduit(commande);
        float totalPrice = 0;
         for (LigneCommande e : productQuantities) {
                totalPrice += e.getQuantite()*e.getProduit().getPrixUnitaire();
        }
        newFacture.setMontant_HTVA(totalPrice);
         newFacture.setMontant_TTC(totalPrice*(1+newFacture.getTVA()));
         newFacture.nonPayee();
        save(newFacture);
        return newFacture ;
    }


    public Facture update(Facture facture, Long id) {

        facture.setAncienneFacture(factureMapper.toEntity(findById(id)));
        save(facture);
        return  facture;
    }

    public FactureDTO findById(Long id) {
        Optional<Facture> facture = factureRepository.findById(id);
        if (facture.isPresent()) {
            return factureMapper.toDTO(facture.get());
        } else {
            throw new EntityNotFoundException("facture not found");
        }
    }
}
