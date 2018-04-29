/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.ExpressionBesoin;
import bean.LigneReception;
import bean.Reception;
import bean.UserStock;
import controller.util.PdfUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Stateless
public class ReceptionFacade extends AbstractFacade<Reception> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;
    @EJB
    private UserStockFacade userStockFacade;
    @EJB
    private LigneReceptionFacade receptionFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReceptionFacade() {
        super(Reception.class);
    }

    public Reception addReception(String idUser, ExpressionBesoin expressionBesoin, Date daterecp) {
        long nbrRec = nombreReception() + 1L;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        UserStock userStock = userStockFacade.find(idUser);
        Reception reception = new Reception();
        reception.setId("REC-" + year + "-" + nbrRec);
        reception.setUserStock(userStock);
        reception.setDateReception(daterecp);
        reception.setExpressionBesoin(expressionBesoin);
        create(reception);
        return reception;

    }

    public Long nombreReception() {
        String query = "SELECT COUNT(r) FROM Reception r";
        return (Long) em.createQuery(query).getSingleResult();
    }

    public void imprimerFacture(Reception reception) throws JRException, IOException {
         List<LigneReception> lr=receptionFacade.findligneexp(reception.getId());
         reception.setLigneReceptions(lr);
        System.out.println("==============================");
        System.out.println("IMPRIMER =====> OUI");
        System.out.println("==============================");
        System.out.println("##### Le numero de la reception est ======> " + reception.getId());
        System.out.println("##### La date de la reception est ======> " + reception.getDateReception());
        System.out.println("***** Les ligne de reception ***** ");
//        List<LigneCommande> lignes = ligneCommandeFacade.findByCommande(commande);
//        for (int i = 0; i < lignes.size(); i++) {
//            LigneCommande ligne = lignes.get(i);
//            System.out.println("-----------------------------------------------------------------------");
//            System.out.println("ID = " + ligne.getId() + " Produit = " + ligne.getProduit() + " Quantite = " + ligne.getQuantite());
//        }
            List<Reception> c = new ArrayList<>();
        c.add(reception);
        PdfUtil.generatePdf(c, null, "Reception Report", "/jasper/Reception.jasper");
    }

    public List<Reception> findReceptionByUser(UserStock userStock) {
        return em.createQuery("select r from Reception r where r.userStock.id='" + userStock.getId() + "'").getResultList();
    }
}
