package com.cigi.facturation.service;

import com.cigi.facturation.dto.CommandeDTO;
import com.cigi.facturation.entity.Commande;
import com.cigi.facturation.entity.Facture;
import com.cigi.facturation.exception.EntityNotFoundException;
import com.cigi.facturation.exception.UnableToSaveEntityException;
import com.cigi.facturation.mapper.CommandeMapper;
import com.cigi.facturation.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private CommandeMapper commandeMapper;

    @Autowired
    private ClientService clientService;

    @Autowired
    private FactureService factureService;

    public Page<CommandeDTO> findAll(Pageable page) {
        return commandeMapper.toDTO(commandeRepository.findAll(page));
    }

    public CommandeDTO findById(Long id) {

        Optional<Commande> oCommande = commandeRepository.findById(id);
        if (oCommande.isPresent()) {
            return commandeMapper.toDTO(oCommande.get());
        } else {

            throw new EntityNotFoundException("Commande not found");
        }
    }

    public CommandeDTO save(CommandeDTO commande) {
        clientService.findById(commande.getClient().getId());
        try {
            Commande newCommande = commandeMapper.toEntity(commande);
            newCommande.mettreEnAttente();
            return commandeMapper.toDTO(commandeRepository.save(newCommande));
        }catch (Exception e){
            throw new UnableToSaveEntityException("unable to save Commande");
        }

    }

    public CommandeDTO update(CommandeDTO commande, Long id) {
        findById(id);
        commande.setId(id);

        return commandeMapper.toDTO(commandeRepository.save(commandeMapper.toEntity(commande)));

    }

    public String deleteCommandeById(Long id) {
        findById(id);
        commandeRepository.deleteById(id);

        return "Done";
    }

    public Page<CommandeDTO> getCommandesByClientId(long id , Pageable page) {
        return commandeMapper.toDTO(commandeRepository.getCommandeByClientId(id , page));
    }

    public CommandeDTO confirmCommande(Long id) {
        Commande commande = commandeRepository.findById(id).orElse(null);
        if (commande == null) {
            throw new EntityNotFoundException("Commande not found");
        }
        commande.confirmer();
        Facture facture = factureService.createFacture(commande);
        commande.setFacture(facture);
        return  commandeMapper.toDTO(commandeRepository.save(commande));
    }
}
