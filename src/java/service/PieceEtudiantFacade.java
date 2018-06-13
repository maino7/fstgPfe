/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Candidat;
import bean.Condidature;
import bean.Niveau;
import bean.PieceEtudiant;
import bean.Section;
import controller.util.SearchUtil;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xXx-BlackAngel-xXx
 */
@Stateless
public class PieceEtudiantFacade extends AbstractFacade<PieceEtudiant> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PieceEtudiantFacade() {
        super(PieceEtudiant.class);
    }

    public List<Candidat> findByNiveauAndSection(Niveau niveau, Section section, String cne, int type) {
        String qry = "SELECT DISTINCT p.condidature.candidat FROM PieceEtudiant p WHERE 1=1";

            qry += SearchUtil.addConstraint("p", "condidature.condidatureValide", "=", type);
        if (niveau != null) {
            qry += SearchUtil.addConstraint("p", "piecesParNiveau.niveau.id", "=", niveau.getId());
        }
        if (section != null) {
            qry += SearchUtil.addConstraint("p", "piecesParNiveau.niveau.filiere.section.id", "=", section.getId());
        }
        if (!"".equals(cne)) {
            qry += SearchUtil.addConstraint("p", "condidature.candidat.cne", "=", cne);
        }
        System.out.println("ha l qry==>" + qry);
        return em.createQuery(qry).getResultList();
    }

    public List<Condidature> findByNiveauAndSection2(Niveau niveau, Section section, String cne) {
        String qry = "SELECT DISTINCT p.condidature FROM PieceEtudiant p WHERE p.condidature.condidatureValide='1'";

        if (niveau != null) {
            qry += SearchUtil.addConstraint("p", "piecesParNiveau.niveau.id", "=", niveau.getId());
        }
        if (section != null) {
            qry += SearchUtil.addConstraint("p", "piecesParNiveau.niveau.filiere.section.id", "=", section.getId());
        }
        if (!"".equals(cne)) {
            qry += SearchUtil.addConstraint("p", "condidature.candidat.cne", "=", cne);
        }
        System.out.println("ha l qry==>" + qry);
        return em.createQuery(qry).getResultList();
    }

    public List<Candidat> findnonValiderniveau(Niveau n) {
        return em.createQuery("SELECT DISTINCT p.condidature.candidat FROM PieceEtudiant p WHERE p.condidature.condidatureValide='2' AND p.piecesParNiveau.niveau.id=" + n.getId()).getResultList();
    }

    public List<Candidat> findValiderniveau(Niveau n) {
        return em.createQuery("SELECT DISTINCT p.condidature.candidat FROM PieceEtudiant p WHERE p.condidature.condidatureValide='1' AND p.piecesParNiveau.niveau.id=" + n.getId()).getResultList();
    }

    public Niveau findNiveau(Condidature c) {
        Niveau n = (Niveau) em.createQuery("SELECT p.piecesParNiveau.niveau FROM PieceEtudiant p WHERE p.condidature.id=" + c.getId()).getResultList().get(0);
        System.out.println("ha niveau li ja ==>" + n);
        return n;
    }

}
