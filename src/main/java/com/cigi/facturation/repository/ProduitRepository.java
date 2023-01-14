package com.cigi.facturation.repository;

import com.cigi.facturation.entity.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> , JpaSpecificationExecutor<Produit> {
    Page<Produit> findProduitByNomIgnoreCase(String nom,Pageable page);



}
