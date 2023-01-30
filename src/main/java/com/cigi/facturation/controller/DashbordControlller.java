package com.cigi.facturation.controller;

import com.cigi.facturation.dto.ProduitDTO;
import com.cigi.facturation.service.FactureService;
import com.cigi.facturation.service.ProduitService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/dashbord")
public class DashbordControlller {

    private FactureService factureservice;
    @GetMapping
    public ResponseEntity<List<ProduitDTO>> findAll(Pageable page) {
        return new ResponseEntity<>(factureservice.ProduisEnruptureDeStock( page), HttpStatus.OK);
    }
}
