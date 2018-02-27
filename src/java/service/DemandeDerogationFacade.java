/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeDerogation;
import bean.Etudiant;
import bean.Filiere;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xXx-BlackAngel-xXx
 */
@Stateless
public class DemandeDerogationFacade extends AbstractFacade<DemandeDerogation> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeDerogationFacade() {
        super(DemandeDerogation.class);
    }

    /**
     * Abed's find derogation non traiter par filiere
     *
     * @param filiere
     * @return
     */
    public List<DemandeDerogation> findDerogationByFiliere(Filiere filiere) {
        if (filiere != null) {
            return em.createQuery("SELECT dd FROM DemandeDerogation dd WHERE dd.filiere.id = " + filiere.getId() + " AND dd.etat =0").getResultList();
        }
        return new ArrayList();
    }

    public DemandeDerogation findDerogation(Etudiant etudiant) {
        try {
            return (DemandeDerogation) em.createQuery("SELECT dd FROM DemandeDerogation dd WHERE dd.etudiant.cne='" + etudiant.getCne() + "'").getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Abed's find all derogation par filiere
     *
     * @param filiere
     * @return
     */
    public List<DemandeDerogation> findAllDerogationByFiliere(Filiere filiere) {
        if (filiere != null) {
            return em.createQuery("SELECT dd FROM DemandeDerogation dd WHERE dd.filiere.id = " + filiere.getId() + " AND dd.etat <> 0").getResultList();
        }
        return new ArrayList();
    }

    /**
     * Abed's accepter derogation
     *
     * @param derogation
     */
    public void accept(DemandeDerogation derogation) {
        derogation.setEtat(1);
        edit(derogation);
    }

    /**
     * Abed's refuser derogation
     *
     * @param derogation
     */
    public void decline(DemandeDerogation derogation) {
        derogation.setEtat(-1);
        edit(derogation);
    }

}
