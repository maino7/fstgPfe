/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Candidat;
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
    public List<Candidat> findByNiveauAndSection(Niveau niveau,Section section,String cne){
        String qry = "SELECT DISTINCT p.condidature.candidat FROM PieceEtudiant p WHERE p.condidature.condidatureValide='0'";
        
        if(niveau != null){
                qry+=SearchUtil.addConstraint("p", "piecesParNiveau.niveau.id", "=", niveau.getId());
        }
        if(section != null){
            qry+=SearchUtil.addConstraint("p", "piecesParNiveau.niveau.filiere.section.id", "=", section.getId());
        }if(!"".equals(cne)){
            qry+=SearchUtil.addConstraint("p", "condidature.candidat.cne", "=", cne);
        }
            System.out.println("ha l qry==>"+qry);
       return em.createQuery(qry).getResultList();
    }
    
}
