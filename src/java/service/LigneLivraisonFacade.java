/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.LigneLivraison;
import bean.Livraison;
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
public class LigneLivraisonFacade extends AbstractFacade<LigneLivraison> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;
    @EJB
    private LivraisonFacade livraisonFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneLivraisonFacade() {
        super(LigneLivraison.class);
    }
    
        public LigneLivraison cloneLigneLivraison(LigneLivraison ligneLivraison) {

        System.out.println("raaaah dkhelt l hnaya ");
        LigneLivraison cloneLigne = new LigneLivraison();
        Livraison livraison  = new Livraison();
        livraison .setId(1L);
        cloneLigne.setProduit(ligneLivraison.getProduit());
        cloneLigne.setQuantite(ligneLivraison.getQuantite());
        cloneLigne.setLivraison(livraison );
        return cloneLigne;
    }
    
    public int createLigneLivraison(Double qteRecu ,Produit produit,Long idLivraison) {
            Livraison livraison = livraisonFacade.find(idLivraison);
            LigneLivraison ligneLivraison = new LigneLivraison();
            ligneLivraison.setProduit(produit);
            ligneLivraison.setQuantite(qteRecu);
            ligneLivraison.setLivraison(livraison);
            create(ligneLivraison);
            return 1;
        
    }
}
