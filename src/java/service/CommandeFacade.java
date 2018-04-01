/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.UserStock;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Stateless
public class CommandeFacade extends AbstractFacade<Commande> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @EJB
    private UserStockFacade userStockFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeFacade() {
        super(Commande.class);
    }

    public Commande addCommande() {
        int nbrCmd = nombreCommande() + 1;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        UserStock userStock = userStockFacade.find("SH184344");
        Commande commande = new Commande();
        commande.setUserStock(userStock);
        commande.setId("CMD-" + year + "-" + nbrCmd);
        commande.setNombreCommande(nbrCmd);
        create(commande);
        return commande;
    }

    public int nombreCommande() {
        String query = "SELECT MAX(c.nombreCommande) FROM Commande c";
        return (int) em.createQuery(query).getSingleResult();
    }
}
