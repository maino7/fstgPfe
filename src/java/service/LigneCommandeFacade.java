/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.LigneCommande;
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
public class LigneCommandeFacade extends AbstractFacade<LigneCommande> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;
    @EJB
    private ProduitFacade produitFacade;
    @EJB
    private CommandeFacade commandeFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneCommandeFacade() {
        super(LigneCommande.class);
    }

    public int createLigneCommande(String idCommande, double quantite, Produit produit) {
        Commande commande = commandeFacade.find(idCommande);
        if (commande == null) {
            System.out.println("la commande ma kaynach asat !!!");
            return -1;
        }
        LigneCommande ligneCommande = new LigneCommande();
        ligneCommande.setCommande(commande);
        ligneCommande.setProduit(produit);
        ligneCommande.setQuantite(quantite);
        create(ligneCommande);
        System.out.println("ligneCommande tcreeat !");
        return 1;
    }

    public LigneCommande clone(LigneCommande ligneCommande) {
        LigneCommande clone = new LigneCommande();
        Commande commande = new Commande();
        commande.setId("cmdTest");
        clone.setCommande(commande);
//        clone.setId(generateId("LigneCommande", "id"));
//        clone.setId(Long.MAX_VALUE);
        clone.setProduit(ligneCommande.getProduit());
        clone.setQuantite(ligneCommande.getQuantite());
        return clone;
    }

}
