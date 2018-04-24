/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.ExpressionBesoin;
import bean.LigneExpressionBesoin;
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
public class LigneExpressionBesoinFacade extends AbstractFacade<LigneExpressionBesoin> {

    @EJB
    private ExpressionBesoinFacade expressionBesoinFacade;
    @EJB
    private LigneFacade ligneFacade;

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneExpressionBesoinFacade() {
        super(LigneExpressionBesoin.class);
    }

    public LigneExpressionBesoin cloneLigneExpressionBesoin(LigneExpressionBesoin ligneExpressionDeBesoin) {
        LigneExpressionBesoin clone = new LigneExpressionBesoin();
//        ExpressionBesoin expressionDeBesoin = new ExpressionBesoin();
//        clone.setId(ligneExpressionDeBesoin.getId());
        clone.setProduit(ligneExpressionDeBesoin.getProduit());
        clone.setQuantite((int) ligneExpressionDeBesoin.getQuantite());
        clone.setExpressionBesoin(ligneExpressionDeBesoin.getExpressionBesoin());
        return clone;
    }

    public LigneExpressionBesoin creeLigne(Produit p, int quantite) {
        LigneExpressionBesoin ligneExpressionBesoin = new LigneExpressionBesoin();
        ExpressionBesoin ligneExpressionDeBesoin = new ExpressionBesoin();
        ligneExpressionBesoin.setProduit(p);
        ligneExpressionBesoin.setQuantite(quantite);
        ligneExpressionBesoin.setExpressionBesoin(ligneExpressionDeBesoin);
        return ligneExpressionBesoin;
    }

    public List<LigneExpressionBesoin> findByExp(String id) {
        String query = "select le from LigneExpressionBesoin le where le.expressionBesoin.id = '" + id + "'";
        return em.createQuery(query).getResultList();

    }

    public int createLigneExp(ExpressionBesoin expressionBesoin, List<LigneExpressionBesoin> ligneExpressionBesoins) {
        if (expressionBesoin != null && ligneExpressionBesoins != null) {
            for (int i = 0; i < ligneExpressionBesoins.size(); i++) {
                LigneExpressionBesoin ligneExpressionBesoin = ligneExpressionBesoins.get(i);
                ajouterLigneExp(expressionBesoin, ligneExpressionBesoin.getQuantite(), ligneExpressionBesoin.getProduit());
            }
            return 1;
        } else {
            return -1;
        }
    }

    public void ajouterLigneExp(ExpressionBesoin expressionBesoin, double quantite, Produit produit) {
        LigneExpressionBesoin ligneExpressionBesoin = new LigneExpressionBesoin();
        ligneExpressionBesoin.setExpressionBesoin(expressionBesoin);
        ligneFacade.createLigne(ligneExpressionBesoin, quantite, produit);
//        ligneExpressionBesoin.setProduit(produit);
//        ligneExpressionBesoin.setQuantite(quantite);
//        create(ligneExpressionBesoin);
    }

}
