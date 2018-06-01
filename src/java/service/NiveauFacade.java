/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Niveau;
import bean.Section;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ouss
 */
@Stateless
public class NiveauFacade extends AbstractFacade<Niveau> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NiveauFacade() {
        super(Niveau.class);
    }
    public List<Niveau> findBySection(Section section){
        return em.createQuery("SELECT n FROM Niveau n WHERE n.filiere.section.id="+section.getId()).getResultList();
    }
    
}
