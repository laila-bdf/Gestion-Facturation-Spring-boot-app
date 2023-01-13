package com.cigi.facturation.service;

import com.cigi.facturation.dto.ClientDTO;
import com.cigi.facturation.entity.Client;
import com.cigi.facturation.exception.EntityNotFoundException;
import com.cigi.facturation.mapper.ClientMapper;
import com.cigi.facturation.repository.ClientRepository;
import com.cigi.facturation.repository.specification.ClientSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientrepository;

    @Autowired
    private ClientMapper clientMapper;

    public Page<ClientDTO> findAll(Pageable page) {

        return clientMapper.toDTO(clientrepository.findAll(page));
    }

    public Page<ClientDTO> findBy(String nom, String prenom, Pageable page) {
        return  clientMapper.toDTO(clientrepository.findAll(
                ClientSpecification.findByNom(nom)
                        .and(ClientSpecification.findByPrenom(prenom)),page));
    }

    public ClientDTO findById(Long id) {
        Optional<Client> oClient = clientrepository.findById(id);
        if (oClient.isPresent()) {
            return clientMapper.toDTO(oClient.get());
        } else {
            throw new EntityNotFoundException("client not found");
        }
    }

    public ClientDTO save(ClientDTO client) {
        return clientMapper.toDTO(clientrepository.save(clientMapper.toEntity(client)));
    }

    public ClientDTO update(ClientDTO client, Long id) {

        findById(id);
        client.setId(id);
        return clientMapper.toDTO(clientrepository.save(clientMapper.toEntity(client)));

    }

    public String deleteClientById(Long id) {
        findById(id);
        clientrepository.deleteById(id);
      return "Done";
    }
    public void deleteAll(){
        clientrepository.deleteAll();
    }
}
