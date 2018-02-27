/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeReleveNote;
import bean.DemandeReleveNoteItem;
import bean.Etudiant;
import bean.NoteSemestre;
import bean.Semestre;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xXx-BlackAngel-xXx
 */
@Stateless
public class DemandeReleveNoteFacade extends AbstractFacade<DemandeReleveNote> {
    
    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;
    @EJB
    private service.SemestreFacade ejbFacade;
    @EJB
    private service.DemandeReleveNoteItemFacade demandeReleveNoteItemFacade;
    @EJB
    private service.NoteSemestreFacade noteSemestreFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public DemandeReleveNoteFacade() {
        super(DemandeReleveNote.class);
    }
    
    public void addDemande(List<DemandeReleveNoteItem> demandeItems, Etudiant etudiant) {
        DemandeReleveNote demande = new DemandeReleveNote();
        demande.setId(generateId("DemandeReleveNote", "id"));
        demande.setEtudiant(etudiant);
        demande.setEtat(0);// ze3ma la demande mazal ma tretainaha
        create(demande);
        for (DemandeReleveNoteItem dem : demandeItems) {
            System.out.println("hahowa l etudiant "+etudiant.getCne());
            System.out.println("dem ="+dem.getSemestre());
            System.out.println(" wach error"+noteSemestreFacade.changeAtributetatReleve(dem.getSemestre(), etudiant));
            NoteSemestre noteSemestre = noteSemestreFacade.changeAtributetatReleve(dem.getSemestre(), etudiant);
            dem.setDemandeReleveNote(demande);
            noteSemestre.setEtatReleve(1);
            demandeReleveNoteItemFacade.edit(dem);
        }
    }
    
    public void prepareDemande(List<String> m, Etudiant etudiant) {
        List<DemandeReleveNoteItem> demList = new ArrayList<>();
        int i;
        
        for (i = 0; i < m.size(); i++) {
            Semestre x = ejbFacade.findSemestre("" + m.get(i)).get(0);
            System.out.println("hahowa semestre "+x);
            DemandeReleveNoteItem di = new DemandeReleveNoteItem();
            di.setSemestre(x);
            di.setId(generateId("DemandeReleveNoteItem", "id"));
            demandeReleveNoteItemFacade.create(di);
            demList.add(di);
            System.out.println("list dyal les demande "+demList);
        }
        addDemande(demList, etudiant);
        
    }
    
    public List<DemandeReleveNote> findReleveNoteDemandee(){
        try {
            return em.createQuery("SELECT rn FROM DemandeReleveNote rn WHERE rn.etat=0").getResultList();
        } catch (Exception e) {
            return new ArrayList();
        }
    }
    
}
