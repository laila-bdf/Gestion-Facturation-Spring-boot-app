package com.cigi.facturation.controller;
import com.cigi.facturation.dto.ProduitDTO;
import com.cigi.facturation.entity.Produit;
import com.cigi.facturation.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitservice;

    @GetMapping
    public ResponseEntity<Page<ProduitDTO>> findAll(Pageable page) {
        return new ResponseEntity<>(produitservice.findAll(page), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ProduitDTO>> findBy(Pageable page,
                                                  @RequestParam(required = false,defaultValue = "",name = "nom") String nom
            ) {
        return new ResponseEntity<>(produitservice.findBy(nom,page),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProduitDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(produitservice.findById(id), HttpStatus.OK);
    }



    @PostMapping
    ResponseEntity<Produit> save(@RequestBody Produit produit) {
        return new ResponseEntity<>(produitservice.save(produit), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        produitservice.deleteProduitById(id);
        return new ResponseEntity<>("Done: Produit deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitDTO> update(@RequestBody ProduitDTO produit, @PathVariable Long id) {
        return ResponseEntity.accepted().body(produitservice.update(produit, id));
    }

}

