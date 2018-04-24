/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.LigneCommande;
import bean.LigneLivraison;
import bean.Livraison;
import bean.UserStock;
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
public class LivraisonFacade extends AbstractFacade<Livraison> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @EJB
    private UserStockFacade userStockFacade;
    @EJB
    private LigneLivraisonFacade ligneLivraisonFacade;
    @EJB
    private CommandeFacade commandeFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LivraisonFacade() {
        super(Livraison.class);
    }

    public Livraison addLivraison(Commande commande, Date daterecp) {
        long nbrCmd = nombrelivraison() + 1L;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        UserStock userStock = userStockFacade.find("SH184344");
        Livraison livraison = new Livraison();
        livraison.setUserStock(userStock);
        livraison.setId("LVR-" + year + "-" + nbrCmd);
        livraison.setDateLivraison(daterecp);
        livraison.setCommande(commande);
        create(livraison);
        return livraison;
    }

    public Long nombrelivraison() {
        String query = "SELECT COUNT(l) FROM Livraison l";
        return (Long) em.createQuery(query).getSingleResult();
    }

    public void imprimerLivraison(Livraison livraison) {
        System.out.println("==============================");
        System.out.println("IMPRIMER =====> OUI");
        System.out.println("==============================");
        System.out.println("##### Le numero du bon de livraison est ======> " + livraison.getId());
        System.out.println("##### La date de la livraison est ======> " + livraison.getDateLivraison());
        System.out.println("##### L'utilisateur qui l'a effectué est ======> " + livraison.getUserStock().getNom() + " " + livraison.getUserStock().getPrenom());
        System.out.println("***** Les ligne de Livraison ***** ");
        List<LigneLivraison> lignes = ligneLivraisonFacade.findByLivraison(livraison);
        for (int i = 0; i < lignes.size(); i++) {
            LigneLivraison ligne = lignes.get(i);
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("ID = " + ligne.getId() + " Produit = " + ligne.getProduit() + " Quantite = " + ligne.getQuantite());
        }
        
    }
        public double calculTotalQuantiteCommande(Commande commande) {
        List<LigneCommande> lc = commande.getLigneCommandes();
        double tt = 0;
        for (int i = 0; i < lc.size(); i++) {
            LigneCommande l = lc.get(i);
            tt += l.getQuantite();
            System.out.println("====== I was here !! ======");
        }
        return tt;
        }
        
        public double calculTotalQuantiteLivraison(List<LigneLivraison> ligneLivraisons) {
 
        double tt = 0;
        for (int i = 0; i < ligneLivraisons.size(); i++) {
            LigneLivraison l = ligneLivraisons.get(i);
            tt += l.getQuantite();
        }
        return tt;
        }
}
