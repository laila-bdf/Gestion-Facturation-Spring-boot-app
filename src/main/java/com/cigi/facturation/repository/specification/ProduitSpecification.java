package com.cigi.facturation.repository.specification;



import com.cigi.facturation.entity.Produit;
import org.springframework.data.jpa.domain.Specification;

public class ProduitSpecification {

    public static Specification<Produit> findByNom(String nom){
        return (
                (root, query, cb) -> {
                    if (nom != null && !nom.isEmpty())
                        return cb.and(cb.like(root.get("nom"), "%" + nom + "%"));
                    return null;
                });
    }

}


