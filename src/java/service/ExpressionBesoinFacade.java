/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Categorie;
import bean.ExpressionBesoin;
import bean.LigneExpressionBesoin;
import bean.Produit;
import bean.UserStock;
import controller.util.SearchUtil;
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
public class ExpressionBesoinFacade extends AbstractFacade<ExpressionBesoin> {

    @EJB
    private UserStockFacade userStockFacade;

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExpressionBesoinFacade() {
        super(ExpressionBesoin.class);
    }

    public List<ExpressionBesoin> findByDate(String idUser, Date dateMin, Date dateMax) {
        String query = "SELECT e FROM Expressionbesoin e WHERE 1=1";
        query += SearchUtil.addConstraintMinMaxDate("e", "dateExpressionBesoin", dateMin, dateMax);
        query += SearchUtil.addConstraint("e", "userStock.id", "=", idUser);
        List exps = getEntityManager().createQuery(query).getResultList();
        System.out.println("list found");
        return exps;

    }

    public int creer(List<LigneExpressionBesoin> lignes) {
        ExpressionBesoin expressionBesoin = new ExpressionBesoin();
        expressionBesoin.setLigneExpressionBesoins(lignes);
        create(expressionBesoin);
        return 1;
    }

    public ExpressionBesoin addExpressionDeBesoin(String idUser) {
        UserStock userStock = userStockFacade.find(idUser);
        ExpressionBesoin expressionBesoin1 = new ExpressionBesoin();
        expressionBesoin1.setUserStock(userStock);
        expressionBesoin1.setDateExpressionBesoin(new Date());
        create(expressionBesoin1);
        return expressionBesoin1;
    }

}
