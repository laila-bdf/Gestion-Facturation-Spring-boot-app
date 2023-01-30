package com.cigi.facturation.controller;

import com.cigi.facturation.entity.LigneCommande;
import com.cigi.facturation.service.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ligneCommande")
public class LigneCommandeController {
    @Autowired
    private LigneCommandeService ligneCommandeService;

    @GetMapping
    public ResponseEntity<Page<LigneCommande>> findAll(Pageable page) {
        return new ResponseEntity<>(ligneCommandeService.findAll(page), HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<LigneCommande> save(@RequestBody LigneCommande ligneCommande) {
        return new ResponseEntity<>(ligneCommandeService.save(ligneCommande), HttpStatus.CREATED);
    }
}
