/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Demande;
import bean.DemandeItem;
import bean.DemandeReleveNote;
import bean.DemandeReleveNoteItem;
import bean.Etudiant;
import bean.Module;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xXx-BlackAngel-xXx
 */
@Stateless
public class DemandeReleveNoteItemFacade extends AbstractFacade<DemandeReleveNoteItem> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeReleveNoteItemFacade() {
        super(DemandeReleveNoteItem.class);
    }
    /**
     * Abed's find releveNoteItems by releveNote
     * @param drn
     * @return 
     */
    public List<DemandeReleveNoteItem> findItemByReleveNote(DemandeReleveNote drn) {
        return em.createQuery("SELECT rni FROM DemandeReleveNoteItem rni WHERE rni.demandeReleveNote.id=" + drn.getId()).getResultList();
    }

}
