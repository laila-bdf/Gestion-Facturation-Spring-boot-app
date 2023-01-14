package com.cigi.facturation.service;

import com.cigi.facturation.dto.ProduitDTO;
import com.cigi.facturation.entity.Produit;
import com.cigi.facturation.exception.EntityNotFoundException;
import com.cigi.facturation.mapper.ProduitMapper;
import com.cigi.facturation.repository.ProduitRepository;
import com.cigi.facturation.repository.specification.ProduitSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProduitService {

    @Autowired
    private ProduitRepository produitrepository;

    @Autowired
    private ProduitMapper produitMapper;

    public Page<ProduitDTO> findAll(Pageable page) {
        log.info("Getting page {} of {} produits ", page.getPageNumber(), page.getPageSize());
        return produitMapper.toDTO(produitrepository.findAll(page));
    }

    public Page<ProduitDTO> findBy(String nom, Pageable page) {
        log.info("Getting page {} of {} clients using filter : {} , {} ", page.getPageNumber(),page.getPageSize(),nom);
        //return clientMapper.toDTO(clientrepository.findClientByNomIgnoreCaseAndAndPrenomIgnoreCase(nom,prenom,page));
        return  produitMapper.toDTO(produitrepository.findAll(
                ProduitSpecification.findByNom(nom)
                        ,page));
    }

    public ProduitDTO findById(Long id) {
        log.info("Getting produit with id {}", id);
        Optional<Produit> oProduit = produitrepository.findById(id);
        if (oProduit.isPresent()) {
            return produitMapper.toDTO(oProduit.get());
        } else {
            log.error("Produit with id [{}] not found", id);
            throw new EntityNotFoundException("produit not found");
        }
    }

    public ProduitDTO save(ProduitDTO produit) {
        return produitMapper.toDTO(produitrepository.save(produitMapper.toEntity(produit)));
    }

    public ProduitDTO update(ProduitDTO produit, Long id) {

        findById(id);
        produit.setId(id);
        log.info("Produit with id [{}] updated",id);
        return produitMapper.toDTO(produitrepository.save(produitMapper.toEntity(produit)));

    }

    public String deleteProduitById(Long id) {
        findById(id);
        produitrepository.deleteById(id);
        log.info("Produit with id [{}] deleted",id);
        return "Done";
    }
    public void deleteAll(){
        produitrepository.deleteAll();
    }

}
