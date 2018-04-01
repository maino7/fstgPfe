/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.ExpressionBesoin;
import bean.LigneExpressionBesoin;
import bean.Produit;
import bean.UserStock;
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

    public int createLigneExp(Long idExpression, double quantite, Produit produit) {
        ExpressionBesoin expressionBesoin = expressionBesoinFacade.find(idExpression);
        if (expressionBesoin == null) {
            System.out.println("l expression de besoin  ma kaynach asat !!!");
            return -1;
        }
        LigneExpressionBesoin ligneExpressionBesoin = new LigneExpressionBesoin();
        ligneExpressionBesoin.setExpressionBesoin(expressionBesoin);
        ligneExpressionBesoin.setProduit(produit);
        ligneExpressionBesoin.setQuantite(quantite);
        create(ligneExpressionBesoin);
        System.out.println("ligneExpressionBesoin tcreeat !");
        return 1;
    }

}
