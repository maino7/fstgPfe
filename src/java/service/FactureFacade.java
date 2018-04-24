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
import static bean.LigneFacture_.facture;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    public Long nombreFacture() {
        String query = "SELECT COUNT(f.id) FROM Facture f ";
        return (Long) em.createQuery(query).getSingleResult();
    }
    
    
    public void imprimerFacture(Facture facture) {
        System.out.println("==============================");
        System.out.println("IMPRIMER =====> OUI");
        System.out.println("==============================");
        System.out.println("##### Le numero de la facture est ======> " + facture.getId());
        System.out.println("##### La date de la facture est ======> " + facture.getDateFacture());
        System.out.println("##### L'utilisateur qui l'a effectué est ======> " + facture.getFournisseur().getNom() + " " + facture.getPrixTotale());
        System.out.println("***** Les ligne de facture ***** ");
//        List<LigneCommande> lignes = ligneCommandeFacade.findByCommande(commande);
//        for (int i = 0; i < lignes.size(); i++) {
//            LigneCommande ligne = lignes.get(i);
//            System.out.println("-----------------------------------------------------------------------");
//            System.out.println("ID = " + ligne.getId() + " Produit = " + ligne.getProduit() + " Quantite = " + ligne.getQuantite());
//        }
    }
    

    public double calculTotalFacture(List<LigneFacture> ligneFactures) {
        double tt = 0;
        double prixTotale = 0;
        for (int i = 0; i < ligneFactures.size(); i++) {
            LigneFacture l = ligneFactures.get(i);
            tt = l.getProduit().getPrix()*l.getQuantite();
            prixTotale+=tt;
        }
        return prixTotale;
    }
   

}
