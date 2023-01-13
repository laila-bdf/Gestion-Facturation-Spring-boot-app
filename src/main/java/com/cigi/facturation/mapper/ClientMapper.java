package com.cigi.facturation.mapper;

import com.cigi.facturation.dto.ClientDTO;
import com.cigi.facturation.entity.Client;
import com.cigi.facturation.exception.EnableToMapNullEntityException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class ClientMapper {


    private  ModelMapper modelMapper=new ModelMapper();

    public Client toEntity(ClientDTO clientDTO){
        if(clientDTO==null){
            throw new EnableToMapNullEntityException("clientDto shouldn't be null");
        }
        return modelMapper.map(clientDTO ,Client.class);
    }

    public  ClientDTO toDTO(Client client) {
        if(client==null){
            throw new EnableToMapNullEntityException("client shouldn't be null");
        }
        return modelMapper.map(client ,ClientDTO.class);
    }

    public Page<ClientDTO> toDTO(Page<Client> clientPage) {
        return clientPage.map(client -> toDTO(client));
    }
}
