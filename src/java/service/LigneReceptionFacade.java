/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.LigneReception;
import bean.Produit;
import bean.Reception;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Stateless
public class LigneReceptionFacade extends AbstractFacade<LigneReception> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;
    @EJB
    private ReceptionFacade receptionFacade;
    @EJB
    private LigneFacade ligneFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneReceptionFacade() {
        super(LigneReception.class);
    }

    public LigneReception cloneLigneReception(LigneReception ligneReception) {
        LigneReception cloneLigne = new LigneReception();
        Reception reception = new Reception();
        reception.setId("testId");
        cloneLigne.setProduit(ligneReception.getProduit());
        cloneLigne.setQuantite(ligneReception.getQuantite());
        cloneLigne.setReception(reception);
        return cloneLigne;
    }

   public int createLigneReception(Double qteRecu, Produit produit, String idReception) {
        Reception reception = receptionFacade.find(idReception);
        LigneReception ligneReception = new LigneReception();
        ligneReception.setReception(reception);
        ligneFacade.createLigne(ligneReception, qteRecu, produit);
        return 1;
    }

}
