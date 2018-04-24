/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Facture;
import bean.LigneFacture;
import bean.Produit;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Stateless
public class LigneFactureFacade extends AbstractFacade<LigneFacture> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;
    @EJB
    private FactureFacade factureFacade;
    @EJB
    private ProduitFacade produitFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneFactureFacade() {
        super(LigneFacture.class);
    }
     public int createLigneFacture(String idFacture, double quantite, Produit produit) {
        Facture facture = factureFacade.find(idFacture);
        if (facture == null) {
            System.out.println("had l facture or tla !!!");
            return -1;
        }
         LigneFacture ligneFacture= new LigneFacture();
        ligneFacture.setFacture(facture);
        ligneFacture.setProduit(produit);
        ligneFacture.setQuantite(quantite);
        create(ligneFacture);
        System.out.println("ligneFacture tcreeat !");
        return 1;
    }

    public LigneFacture clone(LigneFacture ligneFacture) {
        System.out.println("raaaah dkhelt l hnaya ");
        LigneFacture clone = new LigneFacture();
        Facture facture = new Facture();
       facture.setId("fact");
        clone.setFacture(facture);
        clone.setId(1l);
        clone.setProduit(ligneFacture.getProduit());
        clone.setQuantite(ligneFacture.getQuantite());
        return clone;
    }
    
}
