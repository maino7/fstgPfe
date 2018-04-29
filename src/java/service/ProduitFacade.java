/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Categorie;
import bean.LigneMagasin;
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
public class ProduitFacade extends AbstractFacade<Produit> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;
    @EJB
    private LigneMagasinFacade ligneMagasinFacade;

    public List<Produit> findByCategorie(Categorie categorie) {
        return em.createQuery("SELECT p FROM Produit p WHERE"
                + " p.categorie.id = '" + categorie.getId() + "' ").getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProduitFacade() {
        super(Produit.class);
    }
public void addquantite (Produit produit , double qtn){
      List<LigneMagasin> lignemagasins = ligneMagasinFacade.findbyProduit(produit);
        for (int i = 0; i < lignemagasins.size(); i++) {
            LigneMagasin get = lignemagasins.get(i);
            if (i==0) {
            get.setQuantite(get.getQuantite()+qtn);
            ligneMagasinFacade.edit(get);   
            }
        }
  
    }
public void deletequantite (Produit produit , double qtn){
        
      List<LigneMagasin> lignemagasins = ligneMagasinFacade.findbyProduit(produit);
      
        for (int i = 0; i < lignemagasins.size(); i++) {
            LigneMagasin get = lignemagasins.get(i);
            if (i==0) {
               get.setQuantite(get.getQuantite()-qtn);
            ligneMagasinFacade.edit(get); 
            }

           
        }
        System.out.println("it is done I guess check the DB");
     
  
    }
}
