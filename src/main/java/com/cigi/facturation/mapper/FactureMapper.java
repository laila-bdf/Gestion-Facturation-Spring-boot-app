package com.cigi.facturation.mapper;

import com.cigi.facturation.dto.ClientDTO;
import com.cigi.facturation.dto.FactureDTO;
import com.cigi.facturation.entity.Client;
import com.cigi.facturation.entity.Facture;
import com.cigi.facturation.exception.EnableToMapNullEntityException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class FactureMapper {

    private ModelMapper modelMapper=new ModelMapper();

    public Facture toEntity(FactureDTO factureDTO){
        if(factureDTO==null){
            throw new EnableToMapNullEntityException("factureDTO shouldn't be null");
        }
        return modelMapper.map(factureDTO ,Facture.class);
    }

    public  FactureDTO toDTO(Facture facture) {
        if(facture==null){
            throw new EnableToMapNullEntityException("client shouldn't be null");
        }
        return modelMapper.map(facture ,FactureDTO.class);
    }

    public Page<FactureDTO> toDTO(Page<Facture> factures) {
        return factures.map(facture -> toDTO(facture));
    }

}
