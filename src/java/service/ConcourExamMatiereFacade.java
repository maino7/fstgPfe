/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.ConcourExamMatiere;
import bean.Condidature;
import bean.Niveau;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ouss
 */
@Stateless
public class ConcourExamMatiereFacade extends AbstractFacade<ConcourExamMatiere> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @EJB
    private service.PieceEtudiantFacade etudiantFacade;

    public ConcourExamMatiereFacade() {
        super(ConcourExamMatiere.class);
    }
    public List<ConcourExamMatiere> findByCondidature(Condidature c){
        Niveau niveau = etudiantFacade.findNiveau(c);
        return em.createQuery("SELECT c FROM ConcourExamMatiere c WHERE  c.concourNiveau.niveau.id="+niveau.getId()).getResultList();
    }
    
    
    
}
