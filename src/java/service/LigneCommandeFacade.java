/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.LigneCommande;
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
public class LigneCommandeFacade extends AbstractFacade<LigneCommande> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;
    @EJB
    private ProduitFacade produitFacade;
    @EJB
    private CommandeFacade commandeFacade;
    @EJB
    private LigneFacade ligneFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneCommandeFacade() {
        super(LigneCommande.class);
    }
    public List<LigneCommande> findLignecmd(String id){
        return em.createQuery("SELECT l FROM LigneCommande l WHERE l.commande.id='"+id+"'").getResultList();
    }

    public void createLigneCommande(Commande commande, double quantite, Produit produit) {
        if (findByCommandeAndProduit(commande, produit) == null) {
            LigneCommande ligneCommande = new LigneCommande();
            ligneCommande.setCommande(commande);
            ligneFacade.createLigne(ligneCommande, quantite, produit);
            System.out.println("LigneCommande created successfully......");
        }else{
            LigneCommande ligneCommande = findByCommandeAndProduit(commande, produit);
            ligneCommande.setQuantite(ligneCommande.getQuantite()+quantite);
            edit(ligneCommande);
            System.out.println("LigneCommande modified successfully......");
        }
    }

    public int ajouterLigneCommande(Commande commande, List<LigneCommande> ligneCommandes) {
        if (commande != null && ligneCommandes != null) {
            for (int i = 0; i < ligneCommandes.size(); i++) {
                LigneCommande ligne = ligneCommandes.get(i);
                createLigneCommande(commande, ligne.getQuantite(), ligne.getProduit());
            }
            return 1;
        } else {
            return -1;
        }
    }

    public LigneCommande clone(LigneCommande ligneCommande) {
        LigneCommande clone = new LigneCommande();
        Commande commande = new Commande();
        commande.setId("cmdTest");
        clone.setCommande(commande);
        clone.setProduit(ligneCommande.getProduit());
        clone.setQuantite(ligneCommande.getQuantite());
        return clone;
    }

    public List<LigneCommande> findByCommande(Commande commande) {
        return em.createQuery("SELECT l FROM LigneCommande l WHERE "
                + "l.commande.id = '" + commande.getId() + "' ").getResultList();
    }

    public LigneCommande findByCommandeAndProduit(Commande commande, Produit produit) {
        List<LigneCommande> lignes = findAll();
        for (int i = 0; i < lignes.size(); i++) {
            LigneCommande ligne = lignes.get(i);
            if (ligne.getProduit().equals(produit) && ligne.getCommande().equals(commande)) {
                return lignes.get(i);
            }
        }
        return null;
    }
}
