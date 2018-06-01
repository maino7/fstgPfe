/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Candidat;
import bean.Niveau;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ouss
 */
@Stateless
public class CandidatFacade extends AbstractFacade<Candidat> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidatFacade() {
        super(Candidat.class);
    }
    
    public List<Candidat> findNonvalider(){
       return em.createQuery("SELECT c.candidat FROM Condidature c WHERE c.condidatureValide='0'").getResultList();
    }
    public List<Candidat> findByniveau(Niveau n){
        return em.createQuery("SELECT p.condidature.candidat FROM PieceEtudiant p WHERE p.piecesParNiveau.niveau="+n.getId()).getResultList();
    }
    public float calculeMoy(Candidat c){
        float moy = (c.getNoteS1() + c.getNoteS2() + c.getNoteS3() + c.getNoteS4())/3;
        return moy;
    }
    
}
