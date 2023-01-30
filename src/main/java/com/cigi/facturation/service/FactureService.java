package com.cigi.facturation.service;

import com.cigi.facturation.dto.ClientDTO;
import com.cigi.facturation.dto.FactureDTO;
import com.cigi.facturation.entity.*;
import com.cigi.facturation.exception.EntityNotFoundException;
import com.cigi.facturation.exception.UnableToSaveEntityException;
import com.cigi.facturation.mapper.FactureMapper;
import com.cigi.facturation.repository.CommandeRepository;
import com.cigi.facturation.repository.FactureRepository;
import com.cigi.facturation.repository.specification.ClientSpecification;
import com.cigi.facturation.util.Enum.EtatFacture;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    @Lazy
    private CommandeService commandeService;
    @Autowired
    private FactureMapper factureMapper;

    @Autowired
    private LigneCommandeService ligneCommandeService;

    public Page<FactureDTO> findAll(Pageable page) {
        return factureMapper.toDTO(factureRepository.findAll(page));
    }


    public Facture save(Facture facture) {
        commandeService.findById(facture.getCommande().getId());
        try {
            facture.nonPayee();
            return factureRepository.save(facture);
        }catch (Exception e){
            throw new UnableToSaveEntityException("unable to save Facture");
        }

    }

    public Facture createFacture(Commande commande) {
        Facture newFacture= new Facture();
        newFacture.setCommande(commande);

        List<LigneCommande>  productQuantities =ligneCommandeService.ListProduit(commande);
        float totalPrice = 0;
         for (LigneCommande e : productQuantities) {
                totalPrice += e.getQuantite()*e.getProduit().getPrixUnitaire();
        }
        newFacture.setMontant_HTVA(totalPrice);
         newFacture.setMontant_TTC(totalPrice*(1+newFacture.getTVA()));
         newFacture.nonPayee();
        save(newFacture);
        return newFacture ;
    }


    public Facture update(Facture facture, Long id) {
        facture.setCommande(findById(id).getCommande());
        facture.setAncienneFacture(findById(id));
        facture.setId(null);
        save(facture);
        return  facture;
    }

    public  Facture findById(Long id) {
        Optional<Facture> facture = factureRepository.findById(id);
        if (facture.isPresent()) {
            return facture.get();
        } else {
            throw new EntityNotFoundException("facture not found");
        }
    }

    public  void factureDetailReport(HttpServletResponse response, Long id) throws IOException {

        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDocument;
        pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);
        Facture facture = factureRepository.findById(id).get();
        try {

            document.add(new Paragraph("ML").setBold().setFontSize(70).setPaddingLeft(210f).setFontColor(Color.BLUE));
            document.add(new Paragraph("53 BD AI Qods Rue 259 Hay Moulay Abdellah Casablanca").setBold().setPaddingLeft(90f));
            document.add(new Paragraph("Téléphone : 0657984523   Fax : 0567432891").setBold().setPaddingLeft(120f));
            document.add(new Paragraph("Date :"+facture.getDate()).setBold().setPaddingLeft(360f));
            document.add(new Paragraph("Numéro de facture :"+id).setBold().setPaddingLeft(360f));
            document.add(new Paragraph("Code client :"+facture.getCommande().getClient().getId()).setBold().setPaddingLeft(360f));
            document.add(new Paragraph("Livré à :").setBold().setPaddingLeft(360f));
            document.add(new Paragraph("Nom du Client :"+facture.getCommande().getClient().getNom()).setBold().setPaddingLeft(360f));
            document.add(new Paragraph("Ville : "+facture.getCommande().getClient().getVille()).setBold().setPaddingLeft(360f));

            Table table = new Table(new float[]{30F, 40F, 30F,  30F});
            table.setWidthPercent(100)
                    .setPadding(0)
                    .setFontSize(9);

            Cell cell1 = new Cell(1,4);
            cell1.setTextAlignment(TextAlignment.CENTER);
            cell1.add("Facture Details").setBold();
            table.addCell(cell1);

            table.addCell(new Cell().add("Réference").setBold().setFontColor(Color.BLUE));
            table.addCell(new Cell().add("Déscription").setBold().setFontColor(Color.BLUE));
            table.addCell(new Cell().add("Prix Unitaire").setBold().setFontColor(Color.BLUE));
            table.addCell(new Cell().add("Quantité").setBold().setFontColor(Color.BLUE));


            //////////start///////////////
            Commande commande = commandeRepository.findById(id).orElse(null);
            if (commande == null) {
                throw new EntityNotFoundException("Commande not found");
            }

            List<LigneCommande>  productQuantities =ligneCommandeService.ListProduit(commande);
            for (LigneCommande e : productQuantities) {
                table.addCell(new Cell().add(String.valueOf(id)).setBold());
                table.addCell(new Cell().add(String.valueOf(e.getProduit().getDescription())).setBold());
                table.addCell(new Cell().add(String.valueOf(e.getProduit().getPrixUnitaire())).setBold());
                table.addCell(new Cell().add(String.valueOf(e.getQuantite())).setBold());

            }



            Table table1 = new Table(new float[]{20f, 50f, 30F});
            table.setWidthPercent(100)
                    .setPadding(10)
                    .setFontSize(9);
            table1.addCell(new Cell().add("Montant HTVA").setBold().setFontColor(Color.BLUE));
            table1.addCell(new Cell().add("Montant TTC").setBold().setFontColor(Color.BLUE));
            table1.addCell(new Cell().add("Montant A payer").setBold().setFontColor(Color.BLUE));
            table1.addCell(new Cell().add(String.valueOf(facture.getMontant_HTVA())).setBold());
            table1.addCell(new Cell().add(String.valueOf(facture.getMontant_TTC())).setBold());
            table1.addCell(new Cell().add(String.valueOf(facture.getMontantPayer())).setBold());





            document.add(table);
            document.add(new Paragraph("   \n ").setBold().setPaddingLeft(120f));
            document.add(table1);

            document.close();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
