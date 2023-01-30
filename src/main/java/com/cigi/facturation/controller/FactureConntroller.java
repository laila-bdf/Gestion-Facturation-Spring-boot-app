package com.cigi.facturation.controller;

import com.cigi.facturation.dto.ClientDTO;
import com.cigi.facturation.dto.CommandeDTO;
import com.cigi.facturation.dto.FactureDTO;
import com.cigi.facturation.entity.Facture;
import com.cigi.facturation.repository.FactureRepository;
import com.cigi.facturation.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/factures")
public class FactureConntroller {
    @Autowired
    public FactureService factureService;
    @Autowired
    FactureRepository factureRepository;


    @GetMapping(
            value = "/pdf/{id}",
            produces = MediaType.APPLICATION_PDF_VALUE
    )

    public  void factureDetailsReport(HttpServletResponse response, @PathVariable Long id) throws IOException {

        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String fileType = "attachment; filename=facture_details_" + dateFormat.format(new Date()) + ".pdf";
        response.setHeader("Content-Disposition", fileType);

       factureService.factureDetailReport(response,id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facture> update(@RequestBody Facture facture, @PathVariable Long id) {
        return ResponseEntity.accepted().body(factureService.update(facture, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facture> findById(@PathVariable Long id) {
        return new ResponseEntity<>(factureService.findById(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<FactureDTO>> findAll(Pageable page) {
        return new ResponseEntity<>(factureService.findAll(page), HttpStatus.OK);
    }

}
