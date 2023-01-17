package com.cigi.facturation.controller;

import com.cigi.facturation.dto.CommandeDTO;
import com.cigi.facturation.dto.FactureDTO;
import com.cigi.facturation.entity.Facture;
import com.cigi.facturation.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/factures")
public class FactureConntroller {
    @Autowired
    public FactureService factureService;

    @PutMapping("/{id}")
    public ResponseEntity<Facture> update(@RequestBody Facture facture, @PathVariable Long id) {
        return ResponseEntity.accepted().body(factureService.update(facture, id));
    }

}
