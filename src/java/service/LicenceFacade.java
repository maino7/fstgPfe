/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Etudiant;
import bean.Licence;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class LicenceFacade extends AbstractFacade<Licence> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicenceFacade() {
        super(Licence.class);
    }

    public List<Licence> findLicence(Etudiant etudiant) {
        String requette = "select l from Licence l where l.filiere.id=" + etudiant.getFiliere().getId();
        return em.createQuery(requette).getResultList();
    }

}
