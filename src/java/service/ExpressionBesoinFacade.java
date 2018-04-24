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
import java.util.ArrayList;
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

    public List<ExpressionBesoin> findBycriteria(String idUser, Date dateMin, Date dateMax, String idProduit) {
        String query = "SELECT e FROM ExpressionBesoin e WHERE 1=1";
        query += SearchUtil.addConstraintMinMaxDate("e", "dateExpressionBesoin", dateMin, dateMax);
        query += SearchUtil.addConstraint("e", "userStock.id", "=", idUser);
        List exps = getEntityManager().createQuery(query).getResultList();
        List<ExpressionBesoin> listResult = new ArrayList<>();
        if (idProduit != null) {
            for (int i = 0; i < exps.size(); i++) {
                ExpressionBesoin expressionBesoin = (ExpressionBesoin) exps.get(i);
                List<LigneExpressionBesoin> lignes = expressionBesoin.getLigneExpressionBesoins();
                for (int j = 0; j < lignes.size(); j++) {
                    LigneExpressionBesoin ligneExpressionBesoin = lignes.get(j);
                    if (ligneExpressionBesoin.getProduit().getId().equals(idProduit)) {
                        listResult.add(expressionBesoin);
                    }
                }
            }
            return listResult;
        }

        return exps;

    }

    public List<LigneExpressionBesoin> findByProduit(Produit produit) {
        String query = "SELECT le FROM LigneExpressionBesoin le WHERE 1=1";
        query += SearchUtil.addConstraint("le", "produit.id", "=", produit.getId());
        System.out.println(query);
        return em.createQuery(query).getResultList();

    }

//    public List<ExpressionBesoin> findBycriteria(UserStock user, Date dateMin, Date dateMax) {
//        String query = "SELECT e FROM ExpressionBesoin e WHERE 1=1";
//        query += SearchUtil.addConstraintMinMaxDate("e", "dateExpressionBesoin", dateMin, dateMax);
//        query += SearchUtil.addConstraint("e", "userStock.id", "=", user.getId());
//        System.out.println(query);
//        return em.createQuery(query).getResultList();
//
//    }
    public List<ExpressionBesoin> findByDate() {
        String query = "SELECT e FROM ExpressionBesoin e WHERE 1=1";
        query += SearchUtil.addConstraintDate("e", "dateExpressionBesoin", "=", new Date());
        System.out.println(query);

        return em.createQuery(query).getResultList();
    }

    public int creer(List<LigneExpressionBesoin> lignes) {
        ExpressionBesoin expressionBesoin = new ExpressionBesoin();
        expressionBesoin.setLigneExpressionBesoins(lignes);
        create(expressionBesoin);
        return 1;
    }

    public ExpressionBesoin addExpressionDeBesoin(String idUser) {
        long nbrExp = nombreExpression() + 1L;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        UserStock userStock = userStockFacade.find(idUser);
        ExpressionBesoin expressionBesoin1 = new ExpressionBesoin();
        expressionBesoin1.setId("EXP-" + year + "-" + nbrExp);
        expressionBesoin1.setUserStock(userStock);
        expressionBesoin1.setDateExpressionBesoin(new Date());
        create(expressionBesoin1);
        return expressionBesoin1;
    }

    public Long nombreExpression() {
        String query = "SELECT COUNT(e) FROM ExpressionBesoin e";
        return (Long) em.createQuery(query).getSingleResult();
    }

    public void imprimerCommande(ExpressionBesoin expressionBesoin) {
        System.out.println("==============================");
        System.out.println("IMPRIMER =====> OUI");
        System.out.println("==============================");
        System.out.println("##### Le numero de la commande est ======> " + expressionBesoin.getId());
        System.out.println("##### La date de la commande est ======> " + expressionBesoin.getDateExpressionBesoin());
        System.out.println("##### L'utilisateur qui l'a effectué est ======> " + expressionBesoin.getUserStock().getNom() + " " + expressionBesoin.getUserStock().getPrenom());
        System.out.println("***** Les ligne de Commande ***** ");
//        List<LigneExpressionBesoin> lignes = LigneExpressionBesoinFacade.findByCommande(commande);
//        for (int i = 0; i < lignes.size(); i++) {
//            LigneCommande ligne = lignes.get(i);
//            System.out.println("-----------------------------------------------------------------------");
//            System.out.println("ID = " + ligne.getId() + " Produit = " + ligne.getProduit() + " Quantite = " + ligne.getQuantite());
//        }
    }

}
