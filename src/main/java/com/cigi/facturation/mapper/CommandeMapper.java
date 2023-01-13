package com.cigi.facturation.mapper;

import com.cigi.facturation.dto.CommandeDTO;
import com.cigi.facturation.entity.Commande;
import com.cigi.facturation.exception.EnableToMapNullEntityException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CommandeMapper {

    private ModelMapper modelMapper=new ModelMapper();
    public Commande toEntity(CommandeDTO cmdDTO){
        if(cmdDTO==null){
            throw new EnableToMapNullEntityException("cmdDTO shouldn't be null");
        }
        return modelMapper.map(cmdDTO ,Commande.class);
    }

    public  CommandeDTO toDTO(Commande commande) {
        if(commande==null){
            throw new EnableToMapNullEntityException("commande shouldn't be null");
        }
        return modelMapper.map(commande ,CommandeDTO.class);
    }

    public Page<CommandeDTO> toDTO(Page<Commande> commandePage) {
        return commandePage.map(cmd -> toDTO(cmd));
    }
}
