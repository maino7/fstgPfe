/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Actualite;
import controller.util.SearchUtil;
import static java.lang.ProcessBuilder.Redirect.Type.READ;
import java.util.Date;
import java.util.List;
import javax.ejb.Lock;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author larbi
 */
@Stateless
public class ActualiteFacade extends AbstractFacade<Actualite> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    private Date MyDate;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ActualiteFacade() {
        super(Actualite.class);
    }
   
    public void updateActualite() {

        Date date = new Date();

        String req = "UPDATE Actualite ac SET ac.etat=1 WHERE 1=1";
        req += SearchUtil.addConstraintDate("ac", "dateDebut", "=", new Date());
        em.createQuery(req).executeUpdate();
        String reqq="UPDATE Actualite ac SET ac.etat=2 WHERE 1=1";
         reqq += SearchUtil.addConstraintDate("ac", "dateExpiration", "=", new Date());
          em.createQuery(reqq).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<Actualite> findActualite(int x, int y) {
        String req="SELECT ac FROM Actualite ac WHERE 1=1";
        req+=" And ac.position =" + x + " and ac.etat=" + y + " ORDER BY ac.priority";
        return em.createQuery(req).getResultList();
    }
 public List<Actualite> findActualitee(int x, int y, int z) {
        String req="SELECT ac FROM Actualite ac WHERE 1=1";
        req+=" And ac.position =" + x + " and ac.etat=" + y + " ORDER BY ac.priority";
        return em.createQuery("SELECT ac FROM Actualite ac WHERE ac.position ='" + x + "' and (ac.etat='" + y + "' or ac.etat='"+z+"') ORDER BY ac.priority ").getResultList();
    }
}