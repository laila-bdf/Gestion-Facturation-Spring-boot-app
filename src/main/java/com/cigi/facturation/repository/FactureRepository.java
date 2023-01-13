package com.cigi.facturation.repository;

import com.cigi.facturation.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepository extends JpaRepository <Facture,Long>{
}
