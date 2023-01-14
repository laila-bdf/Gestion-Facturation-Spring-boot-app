package com.cigi.facturation.repository;

import com.cigi.facturation.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> , JpaSpecificationExecutor<Client> {
    Page<Client> findClientByNomIgnoreCaseAndAndPrenomIgnoreCase(String nom, String prenom , Pageable page);



}
