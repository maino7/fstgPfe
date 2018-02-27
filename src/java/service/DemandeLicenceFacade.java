/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.AnneUniversitaire;
import bean.Annee;
import bean.Demande;
import bean.DemandeLicence;
import bean.DemandeLicenceItem;
import bean.Etudiant;
import bean.Licence;
import controller.util.SearchUtil;
import controller.util.SessionUtil;
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
public class DemandeLicenceFacade extends AbstractFacade<DemandeLicence> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeLicenceFacade() {
        super(DemandeLicence.class);
    }

    public DemandeLicence prepareDemandeLicence() {
        Etudiant etudiant = (Etudiant) SessionUtil.getConnectedUser();
        DemandeLicence demandeLicence = new DemandeLicence();
        demandeLicence.setId(generateId("DemandeLicence", "id"));
        demandeLicence.setEtudiant(etudiant);
        System.out.println("***-** create demande facade== " + demandeLicence);
        return demandeLicence;
    }

    public DemandeLicence findByEtud(Etudiant e) {
        DemandeLicence dl = (DemandeLicence) em.createQuery("SELECT d FROM DemandeLicence d WHERE d.etudiant.cne=" + e.getCne()).getSingleResult();
        if (dl != null) {
            return dl;
        } else {
            return null;
        }
    }
}
