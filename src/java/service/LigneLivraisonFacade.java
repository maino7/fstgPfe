/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.LigneLivraison;
import bean.Livraison;
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
public class LigneLivraisonFacade extends AbstractFacade<LigneLivraison> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;
    @EJB
    private LivraisonFacade livraisonFacade;
    @EJB
    private LigneFacade ligneFacade;
    @EJB
    private ProduitFacade produitFacade;
    @EJB
    private CommandeFacade commandeFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneLivraisonFacade() {
        super(LigneLivraison.class);
    }

    public LigneLivraison cloneLigneLivraison(LigneLivraison ligneLivraison) {
        LigneLivraison cloneLigne = new LigneLivraison();
        Livraison livraison = new Livraison();
        livraison.setId("testId");
        cloneLigne.setProduit(ligneLivraison.getProduit());
        cloneLigne.setQuantite(ligneLivraison.getQuantite());
        cloneLigne.setLivraison(livraison);
        return cloneLigne;
    }

    public int createLigneLivraison(Double qteRecu, Produit produit, String idLivraison) {
        Livraison livraison = livraisonFacade.find(idLivraison);
        LigneLivraison ligneLivraison = new LigneLivraison();
        ligneLivraison.setLivraison(livraison);
        ligneFacade.createLigne(ligneLivraison, qteRecu, produit);
        produitFacade.addquantite(produit, qteRecu);
        return 1;
    }

    public int ajouterLigneLivtaison(Livraison livraison, List<LigneLivraison> ligneLivraisons) {
        if (livraison != null && ligneLivraisons != null) {
            for (int i = 0; i < ligneLivraisons.size(); i++) {
                LigneLivraison ligne = ligneLivraisons.get(i);
                createLigneLivraison(ligne.getQuantite(), ligne.getProduit(), livraison.getId());
            }
            
        double ttLivraison = livraisonFacade.calculTotalQuantiteLivraison(ligneLivraisons);
        double ttCommande = livraisonFacade.calculTotalQuantiteCommande(livraison.getCommande());
        
        if(ttCommande == ttLivraison){
            livraison.getCommande().setLivree(2);
            commandeFacade.edit(livraison.getCommande());
        } 
         if(ttCommande > ttLivraison){
           livraison.getCommande().setLivree(1);
           commandeFacade.edit(livraison.getCommande());
        }
            System.out.println("============== les quantité totales : =============");
            System.out.println(ttLivraison);
            System.out.println(ttCommande);
            
            return 1;
        } else {
            return -1;
        }
    }

    public List<LigneLivraison> findByLivraison(Livraison livraison) {
        return em.createQuery("SELECT l FROM LigneLivraison l WHERE "
                + "l.livraison.id = '" + livraison.getId() + "' ").getResultList();
    }

    
 
}
