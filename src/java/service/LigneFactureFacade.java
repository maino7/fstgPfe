/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Facture;
import bean.LigneFacture;
import bean.Produit;
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
public class LigneFactureFacade extends AbstractFacade<LigneFacture> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;
    @EJB
    private FactureFacade factureFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneFactureFacade() {
        super(LigneFacture.class);
    }
    public List<LigneFacture> findLignefctr(String id){
        return em.createQuery("SELECT l FROM LigneFacture l WHERE l.facture.id='"+id+"'").getResultList();
    }

    public int createLigneFacture(String idFacture, double quantite, Produit produit) {
        Facture facture = factureFacade.find(idFacture);
        if (facture == null) {
            return -1;
        }
        LigneFacture ligneFacture = new LigneFacture();
        ligneFacture.setFacture(facture);
        ligneFacture.setProduit(produit);
        ligneFacture.setQuantite(quantite);
        create(ligneFacture);
        return 1;
    }

    public LigneFacture clone(LigneFacture ligneFacture) {
        LigneFacture clone = new LigneFacture();
        Facture facture = new Facture();
        facture.setId("fact");
        clone.setFacture(facture);
        clone.setId(1l);
        clone.setProduit(ligneFacture.getProduit());
        clone.setQuantite(ligneFacture.getQuantite());
        return clone;
    }

    public int ajouterLigneFacture(Facture facture, List<LigneFacture> ligneFactures) {
        if (facture != null && ligneFactures != null) {
            for (int i = 0; i < ligneFactures.size(); i++) {
                LigneFacture ligne = ligneFactures.get(i);
                createLigneFacture(facture.getId(), ligne.getQuantite(), ligne.getProduit());
            }
            return 1;
        } else {
            return -1;
        }
    }

}
