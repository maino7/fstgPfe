/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.ConcourExamMatiere;
import bean.Condidature;
import bean.ExamCandidat;
import bean.MatiereConcour;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ouss
 */
@Stateless
public class ExamCandidatFacade extends AbstractFacade<ExamCandidat> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamCandidatFacade() {
        super(ExamCandidat.class);
    }

    public String findByCandidature(Condidature condidature) {
        String examCand = "";
        List<ExamCandidat> exams = em.createQuery("SELECT e FROM ExamCandidat e WHERE e.condidature.id=" + condidature.getId()).getResultList();
        examCand = exams.stream().map(Object::toString).collect(Collectors.joining(", "));
        System.out.println("ha la list li 3ta service==>" + examCand);
        return examCand;
    }

    public int findByCondidatureMatiere(Condidature c, ConcourExamMatiere cm) {
      
        if (c == null || cm == null) {
            return -1;
        } else {
            String qry = "SELECT e FROM ExamCandidat e WHERE e.condidature.id=" + c.getId() + " AND  e.matiereConcour.id=" + cm.getMatiereConcour().getId();
            List<ExamCandidat> ex = em.createQuery(qry).getResultList();
            if (ex.size() == 0) {
               
                return -1;
            } else {
              
             return 1;
            }
        }
    }

    public int createExam(Condidature condidature, ConcourExamMatiere cem, float note) {
        if(condidature == null || cem == null ){
            System.out.println("-1");
            return -1;
        }else if(note >20){
              System.out.println("-2");
            return -2;
        } else {
              System.out.println("dazet");
            ExamCandidat ex = new ExamCandidat();
            ex.setCondidature(condidature);
            ex.setMatiereConcour(cem.getMatiereConcour());
            ex.setNoteCalc(note);
            create(ex);
            return 1;
        }
        
    }
    public List<ExamCandidat> finbByCandidature(Condidature c){
        return em.createQuery("SELECT e FROM ExamCandidat e WHERE e.condidature.id="+c.getId()).getResultList();
    }
    
    public void editTa3i(ExamCandidat examCandidat){
        em.merge(examCandidat);
    }
}
