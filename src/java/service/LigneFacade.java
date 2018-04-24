/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Ligne;
import bean.Produit;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Stateless
public class LigneFacade extends AbstractFacade<Ligne> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneFacade() {
        super(Ligne.class);
    }
 
    public int createLigne(Ligne ligne, double quantite, Produit produit) {

        ligne.setQuantite(quantite);
        ligne.setProduit(produit);
        create(ligne);
        System.out.println("ligne tcreeat !");
        return 1;
    }
}
