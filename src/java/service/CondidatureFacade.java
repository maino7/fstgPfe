/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Candidat;
import bean.Condidature;
import bean.Niveau;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.xbill.DNS.Update;

/**
 *
 * @author ouss
 */
@Stateless
public class CondidatureFacade extends AbstractFacade<Condidature> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CondidatureFacade() {
        super(Condidature.class);
    }
    public Condidature findByCandidat(Candidat candidat){
        return (Condidature) em.createQuery("SELECT c FROM Condidature c WHERE c.candidat.cne='"+candidat.getCne()+"'").getSingleResult();
    }
    public void validerCandidature(Candidat candidat){
        Condidature condidature = findByCandidat(candidat);
        condidature.setCondidatureValide(1);
        edit(condidature);
    }
    public int placeReste(Niveau niveau){
        List<Condidature> l = em.createQuery("SELECT p.condidature FROM PieceEtudiant p WHERE p.condidature.condidatureValide='1' AND p.piecesParNiveau.niveau.id="+niveau.getId()).getResultList();
        return  l.size();
    }
   public void validerPlusieurCand(List<Candidat> c){
       if(!c.isEmpty()){
           for (Candidat candidat : c) {
               validerCandidature(candidat);
           }
       }
   }
    
}
