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
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneReceptionFacade() {
        super(LigneReception.class);
    }

    public LigneReception cloneLigneReception(LigneReception ligneReception) {

        System.out.println("raaaah dkhelt l hnaya ");
        LigneReception cloneLigne = new LigneReception();
        Reception reception = new Reception();
        reception.setId(1L);
        cloneLigne.setProduit(ligneReception.getProduit());
        cloneLigne.setQuantiteRecu(ligneReception.getQuantiteRecu());
        cloneLigne.setReception(reception);
        return cloneLigne;
    }
    
    public int createLigneReception(Double qteRecu,Produit produit,Long idReception) {
            Reception reception=receptionFacade.find(idReception);
            LigneReception ligneReception = new LigneReception();
            ligneReception.setProduit(produit);
            ligneReception.setQuantiteRecu(qteRecu);
            ligneReception.setReception(reception);
            create(ligneReception);
            return 1;
        
    }

}
