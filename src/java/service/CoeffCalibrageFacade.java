/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.CoeffCalibrage;
import bean.EtablissementType;
import controller.util.DateUtil;
import controller.util.SearchUtil;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ouss
 */
@Stateless
public class CoeffCalibrageFacade extends AbstractFacade<CoeffCalibrage> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CoeffCalibrageFacade() {
        super(CoeffCalibrage.class);
    }

    public int creerCoeff(CoeffCalibrage c) {
        String d = DateUtil.format(new Date());
        if (c == null) {
            return -1;
        } else if (!c.getAnnee().equals(d)) {
            return -2;
        } else if (c.getNbrMin() > c.getNbrMax()) {
            return -3;
        } else {
            create(c);
            return 1;
        }
    }

    public List<CoeffCalibrage> findByCretaria(EtablissementType et, float coeffi, int nbrMax, int nbrMin, float noteMin, String annee) {
        String qry = "SELECT c FROM CoeffCalibrage c WHERE 1=1";
        if (et != null) {
            qry += SearchUtil.addConstraint("c", "etablissement.id", "=", et.getId());
        }
        if (noteMin != 0) {
            qry += SearchUtil.addConstraint("c", "noteMinimal", "=", noteMin);
        }
        if (nbrMax != 0) {
            qry += SearchUtil.addConstraint("c", "nbrMax", "=", nbrMax);
        }
        if (nbrMin != 0) {
            qry += SearchUtil.addConstraint("c", "nbrMin", "=", nbrMin);
        }
        if (!annee.equals("")) {
            qry += SearchUtil.addConstraint("c", "annee", "=", annee);
        }
        if (coeffi != 0) {
            qry += SearchUtil.addConstraint("c", "coeff", "=", coeffi);
        }
        System.out.println("ha l qry==>" + qry);
        return em.createQuery(qry).getResultList();

    }

    public CoeffCalibrage findByEtab(EtablissementType etablissementType) {
        if (etablissementType == null) {
            System.out.println("l etab rah null");
            return new CoeffCalibrage();
        } else {
            String d = DateUtil.format(new Date());
            return (CoeffCalibrage) em.createQuery("SELECT c FROM CoeffCalibrage c WHERE c.etablissement.id=" + etablissementType.getId() + " AND c.annee='" + d + "'").getResultList().get(0);
        }

    }
}
