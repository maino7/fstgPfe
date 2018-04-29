/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.Facture;
import bean.Fournisseur;
import bean.LigneFacture;
import controller.util.JsfUtil;
import controller.util.PdfUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Stateless
public class FactureFacade extends AbstractFacade<Facture> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;
    private double prixTotale;
    private double prix;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @EJB
    private UserStockFacade userStockFacade;
    @EJB
    private LigneFactureFacade ligneFactureFacade;

    public FactureFacade() {
        super(Facture.class);
    }

    public Facture addFacture(Date dateFacture, Commande commande, Fournisseur fournisseur, List<LigneFacture> ligneFactures) {
        Long nbrFct = nombreFacture() + 1L;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Facture facture = new Facture();
        facture.setCommande(commande);
        facture.setId("fact-" + year + "-" + nbrFct);
        facture.setDateFacture(dateFacture);
        facture.setFournisseur(fournisseur);
        facture.setPrixTotale(calculTotalFacture(ligneFactures));
        create(facture);
        return facture;
    }
    public void PrintMultiFacture() throws JRException, IOException{
        List<Facture> factures = new ArrayList<>();
        List<Facture> cm = new ArrayList<>();
        factures=findAll();
        List<JasperPrint> jasperPrints = new ArrayList();
        for (int i = 0; i < factures.size(); i++) {
            Facture c = factures.get(i);
            cm.add(c);
            jasperPrints.add(PdfUtil.createJasperPrint(cm, null,"/jasper/facture.jasper"));
            cm.clear();
        }
        if (jasperPrints != null && !jasperPrints.isEmpty()) {
            PdfUtil.generatePdfs(jasperPrints,"MultiFacture Report");
        } else {
            JsfUtil.addErrorMessage("Une erreur s'est produit lors de l'impression");
        }
        
    }

    public List<Facture> factureParAnnée(int année) {
        String requete = "SELECT f FROM Facture f WHERE f.dateFacture LIKE '" + année + "-%-%'";
        return em.createQuery(requete).getResultList();
    }

    public void printPdf(Facture facture) throws JRException, IOException {
        List<Facture> c = new ArrayList<>();
        c.add(find(facture.getId()));
        PdfUtil.generatePdf(c, null, "Facture Report", "/jasper/facture.jasper");
    }

    public Long nombreFacture() {
        String query = "SELECT COUNT(f.id) FROM Facture f ";
        return (Long) em.createQuery(query).getSingleResult();
    }

    public void imprimerFacture(Facture facture) throws JRException, IOException {
        List<LigneFacture> lfctr=ligneFactureFacade.findLignefctr(facture.getId());
        facture.setLigneFactures(lfctr);
        System.out.println("==============================");
        System.out.println("IMPRIMER =====> OUI");
        System.out.println("==============================");
        System.out.println("##### Le numero de la facture est ======> " + facture.getId());
        System.out.println("##### La date de la facture est ======> " + facture.getDateFacture());
        System.out.println("##### L'utilisateur qui l'a effectué est ======> " + facture.getFournisseur().getNom() + " " + facture.getPrixTotale());
        System.out.println("***** Les ligne de facture ***** ");
        List<Facture> c = new ArrayList<>();
        c.add(facture);
        PdfUtil.generatePdf(c, null, "Facture Report", "/jasper/facture.jasper");
    }

    public double calculTotalFacture(List<LigneFacture> ligneFactures) {
        double tt = 0;
        double prixTotale = 0;
        for (int i = 0; i < ligneFactures.size(); i++) {
            LigneFacture l = ligneFactures.get(i);
            tt = l.getProduit().getPrix() * l.getQuantite();
            prixTotale += tt;
        }
        return prixTotale;
    }

}
