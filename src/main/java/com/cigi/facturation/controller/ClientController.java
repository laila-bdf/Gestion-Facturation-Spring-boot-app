package com.cigi.facturation.controller;

import com.cigi.facturation.dto.ClientDTO;
import com.cigi.facturation.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientservice;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable page) {
        return new ResponseEntity<>(clientservice.findAll(page), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ClientDTO>> findBy(Pageable page,
                                                  @RequestParam(required = false,defaultValue = "",name = "nom") String nom
            ,@RequestParam(required = false,defaultValue = "",name = "prenom") String prenom) {
        return new ResponseEntity<>(clientservice.findBy(nom, prenom,page),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(clientservice.findById(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<ClientDTO> save(@RequestBody ClientDTO client) {
        return new ResponseEntity<>(clientservice.save(client), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        clientservice.deleteClientById(id);
        return new ResponseEntity<>("Done: Client deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO client, @PathVariable Long id) {
        return ResponseEntity.accepted().body(clientservice.update(client, id));
    }

}
