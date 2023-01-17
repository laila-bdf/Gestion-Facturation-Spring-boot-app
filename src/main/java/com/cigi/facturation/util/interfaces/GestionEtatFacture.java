package com.cigi.facturation.util.interfaces;

public interface GestionEtatFacture {
    void payer();
    void payerEnPartie(float montantPayer);
    void nonPayee();
}
