/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Candidat;
import bean.ConcourNiveau;
import bean.Condidature;
import bean.Niveau;
import bean.PieceEtudiant;
import bean.PiecesParNiveau;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class CandidatFacade extends AbstractFacade<Candidat> {

    @EJB
    private CondidatureFacade condidatureFacade;
    @EJB
    private PieceEtudiantFacade etudiantFacade;

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidatFacade() {
        super(Candidat.class);
    }

    public List<Candidat> findNonvalider() {
        return em.createQuery("SELECT c.candidat FROM Condidature c WHERE c.condidatureValide='0'").getResultList();
    }

    public List<Candidat> findByniveau(Niveau n) {
        return em.createQuery("SELECT p.condidature.candidat FROM PieceEtudiant p WHERE p.piecesParNiveau.niveau=" + n.getId()).getResultList();
    }

    public float calculeMoy(Candidat c) {
        float moy = (c.getNoteS1() + c.getNoteS2() + c.getNoteS3() + c.getNoteS4()) / 3;
        return moy;
    }

    public int creerCycle(Candidat candidat, ConcourNiveau concourNiveau) {
        if (candidat == null) {
            return -1;
        } else {
            Condidature condidature = new Condidature();
            Niveau niveau = concourNiveau.getNiveau();
            PiecesParNiveau piecesParNiveau = (PiecesParNiveau) em.createQuery("SELECT p from PiecesParNiveau p where p.id=" + 1).getSingleResult();
            PieceEtudiant pieceEtudiant = new PieceEtudiant();
            pieceEtudiant.setPiecesParNiveau(piecesParNiveau);
            condidature.setId(generateId("Condidature", "id"));
            candidat.setCondidature(condidature);
            condidature.setCandidat(candidat);
            condidature.setTypeInscription(3);
            pieceEtudiant.setCondidature(condidature);
            condidatureFacade.create(condidature);
            etudiantFacade.create(pieceEtudiant);
            create(candidat);
            System.out.println("ha lcreation" + candidat);
            return 1;
        }
//        return 1;

    }

    public void creerMaster(Candidat candidat, Condidature condidature) {
        candidat.setCondidature(condidature);
        condidature.setCandidat(candidat);
        condidature.setTypeInscription(2);
        create(candidat);
        condidatureFacade.create(condidature);
        System.out.println("ha lcreation" + candidat);
//        return 1;

    }

}
