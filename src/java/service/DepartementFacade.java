/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Departement;
import controller.util.JsfUtil;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author HP
 */
@Stateless
public class DepartementFacade extends AbstractFacade<Departement> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartementFacade() {
        super(Departement.class);
    }

    public List<Departement> findAllDeprtm() {
        return em.createQuery("SELECT d FROM Departement d WHERE 1=1 ORDER BY d.intitule").getResultList();
    }

   
    public Departement clone(Departement departement) {
        Departement cloned = new Departement();
            cloned.setIntitule(departement.getIntitule());
            cloned.setChefDepartement(departement.getChefDepartement());
            cloned.setId(departement.getId());
            cloned.setDescription(departement.getDescription());
            cloned.setImg(departement.getImg());
        return cloned;
    }
}
