/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.Livraison;
import bean.UserStock;
import java.util.Date;
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LivraisonFacade() {
        super(Livraison.class);
    }
    
        public Livraison addLivraison(String idUser,Commande commande , Date daterecp) {
        
            UserStock userStock = userStockFacade.find(idUser);
           
            Livraison livraison = new Livraison();
            livraison.setUserStock(userStock);
            livraison.setDateLivraison(daterecp);
            livraison.setCommande(commande);
            create(livraison);
            
            return livraison;
        
    }
    
}
