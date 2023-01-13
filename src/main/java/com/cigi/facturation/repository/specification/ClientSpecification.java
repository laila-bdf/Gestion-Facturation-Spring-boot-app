package com.cigi.facturation.repository.specification;


import com.cigi.facturation.entity.Client;
import org.springframework.data.jpa.domain.Specification;

public class ClientSpecification {

    public static Specification<Client> findByNom(String nom){
        return (
                (root, query, cb) -> {
                    if (nom != null && !nom.isEmpty())
                        return cb.and(cb.like(root.get("nom"), "%" + nom + "%"));
                    return null;
                });
    }
    public static Specification<Client> findByPrenom(String prenom){
        return (
                (root, query, cb) -> {
                    if (prenom != null && !prenom.isEmpty())
                        return cb.and(cb.like(root.get("prenom"), "%" + prenom + "%"));
                    return null;
                });
    }
}
