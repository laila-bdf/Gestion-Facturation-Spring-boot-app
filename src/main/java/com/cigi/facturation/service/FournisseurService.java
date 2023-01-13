package com.cigi.facturation.service;

import com.cigi.facturation.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FournisseurService {
    @Autowired
    private FournisseurRepository fournisseurRepository;



}
