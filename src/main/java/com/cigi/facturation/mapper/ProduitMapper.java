package com.cigi.facturation.mapper;


import com.cigi.facturation.dto.ProduitDTO;
import com.cigi.facturation.entity.Produit;
import com.cigi.facturation.exception.EnableToMapNullEntityException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class ProduitMapper {


    private  ModelMapper modelMapper=new ModelMapper();


    public Produit toEntity(ProduitDTO produitDTO){
        if(produitDTO==null){
            throw new EnableToMapNullEntityException("produitDto shouldn't be null");
        }
        return modelMapper.map(produitDTO ,Produit.class);
    }

    public  ProduitDTO toDTO(Produit produit) {
        if(produit==null){
            throw new EnableToMapNullEntityException("produit shouldn't be null");
        }
        return modelMapper.map(produit ,ProduitDTO.class);
    }

    public Page<ProduitDTO> toDTO(Page<Produit> produitPage) {
        return produitPage.map(produit -> toDTO(produit));
    }
}


