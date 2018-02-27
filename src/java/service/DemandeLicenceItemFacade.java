/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeLicence;
import bean.DemandeLicenceItem;
import bean.Etudiant;
import bean.Licence;
import controller.util.SearchUtil;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class DemandeLicenceItemFacade extends AbstractFacade<DemandeLicenceItem> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeLicenceItemFacade() {
        super(DemandeLicenceItem.class);
    }

    public DemandeLicenceItem prepareDemandeItem(Licence licence) {
        DemandeLicenceItem d = new DemandeLicenceItem();
        d.setLicence(licence);
        d.setId(generateId("DemandeLicenceItem", "id"));
        System.out.println("**** facade item d " + d);
        return d;
    }

    public List<DemandeLicenceItem> findByEtu(Etudiant e) {
        int x=new Date().getYear()+1900;
        String rqt = "select d from DemandeLicenceItem d where 1=1";
        if (e != null && e.getCne() != null) {
            rqt += SearchUtil.addConstraint("d.demandeLicence", "etudiant.cne", "=", e.getCne());
            rqt += SearchUtil.addConstraint("d.demandeLicence", "anneUniversitaire.anneeMin.libelle", "=", x);
            System.out.println("------- facade search item by demnd "+rqt);
            return em.createQuery(rqt).getResultList();
        } 
        else {
            return null;
        }
    }
    public List<DemandeLicenceItem> findDLIByDemandeLicenceOrderByPriority(DemandeLicence demandeLicence){
        return em.createQuery("SELECT dm FROM DemandeLicenceItem dm WHERE dm.demandeLicence.id="+demandeLicence.getId()+" order by dm.priorite ").getResultList();
    }

}
