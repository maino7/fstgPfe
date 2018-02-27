/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Annee;
import bean.DemandeReleveNote;
import bean.DemandeReleveNoteItem;
import bean.Etudiant;
import bean.Module;
import bean.NoteModulaire;
import bean.Semestre;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import service.DemandeReleveNoteFacade;
import service.NoteModulaireFacade;

/**
 *
 * @author Abed
 */
@Named("releveDeNoteController")
@SessionScoped
public class ReleveDeNoteController implements Serializable {

    @EJB
    private service.NoteModulaireFacade ejbFacade;
    @EJB
    private service.DemandeReleveNoteFacade releveNoteFacade;
    @EJB
    private service.DemandeReleveNoteItemFacade releveNoteItemFacade;
    private List<DemandeReleveNote> releveNotes;
    private List<DemandeReleveNoteItem> demandeReleveNoteItems;
    private Etudiant etudiant;
    private List<NoteModulaire> items = null;
    private Semestre semestre;
    private Annee annee;
    private Module module;

    //jasper
    public void generatPdf() throws JRException, IOException {
        System.out.println("etud :: " + etudiant);
        ejbFacade.printPdf(etudiant, semestre);
        FacesContext.getCurrentInstance().responseComplete();
    }

    //jasper
    public void generatPdf2(DemandeReleveNote drn, Etudiant e, Semestre s) throws JRException, IOException {

        System.out.println("etud :: " + e);
        ejbFacade.printPdf(e, s);
        List<DemandeReleveNoteItem> drnis = releveNoteItemFacade.findItemByReleveNote(drn);
        if (drnis.isEmpty()) {
            drn.setEtat(1);
            releveNoteFacade.edit(drn);
            releveNotes.remove(drn);
        }
        FacesContext.getCurrentInstance().responseComplete();
    }
//    

    public DemandeReleveNoteFacade getReleveNoteFacade() {
        return releveNoteFacade;
    }

    public void setReleveNoteFacade(DemandeReleveNoteFacade releveNoteFacade) {
        this.releveNoteFacade = releveNoteFacade;
    }

    public List<DemandeReleveNote> getReleveNotes() {
        releveNotes = releveNoteFacade.findReleveNoteDemandee();
        return releveNotes;
    }

    public void setReleveNotes(List<DemandeReleveNote> releveNotes) {
        this.releveNotes = releveNotes;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public void noteEtu() {
        items = ejbFacade.noteEtu(etudiant, semestre);
    }

    public void recherche() {
        items = ejbFacade.nonValide(etudiant, semestre);
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public NoteModulaireFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(NoteModulaireFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<DemandeReleveNoteItem> items(DemandeReleveNote drn) {
        demandeReleveNoteItems = releveNoteItemFacade.findItemByReleveNote(drn);
        return demandeReleveNoteItems;
    }

    public List<DemandeReleveNoteItem> getDemandeReleveNoteItems() {
        return demandeReleveNoteItems;
    }

    public void setDemandeReleveNoteItems(List<DemandeReleveNoteItem> demandeReleveNoteItems) {
        this.demandeReleveNoteItems = demandeReleveNoteItems;
    }

    public List<NoteModulaire> getItems() {
        if (items == null) {
            items = new ArrayList();
        }
        return items;
    }

    public void setItems(List<NoteModulaire> items) {
        this.items = items;
    }

    /**
     * Creates a new instance of ReleveDeNoteContoller
     */
    public ReleveDeNoteController() {
    }

}
