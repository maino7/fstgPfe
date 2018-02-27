/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Annee;
import bean.Enseignant;
import bean.Etudiant;
import bean.Filiere;
import bean.NoteModulaire;
import bean.NoteSemestre;
import bean.Semestre;
import controller.util.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import service.EnseignantFacade;
import service.EtudiantFacade;
import service.NoteModulaireFacade;
import service.NoteSemestreFacade;
import service.SemestreFacade;

/**
 *
 * @author BENIHOUD Youssef
 */
@Named("searchEtudiantController")
@SessionScoped
public class SearchEtudiantController implements Serializable {

    private Enseignant enseignant;
    private Etudiant selectedEtud;
    private Annee annee;
    private List<Etudiant> elems;
    private List<NoteModulaire> items;
    private NoteSemestre noteSemestre;
    private Semestre semestreNumber;
    
    @EJB
    private EtudiantFacade etudiantFacade;
    @EJB
    private EnseignantFacade enseignantFacade;
    @EJB
    private SemestreFacade semestreFacade;
    @EJB
    private NoteModulaireFacade noteModulaireFacade;
    @EJB
    private NoteSemestreFacade noteSemestreFacade;

    /**
     * Creates a new instance of SearchEtudiantController
     */
    public SearchEtudiantController() {
    }

    public void initView() {
        selectedEtud = new Etudiant();
        elems = new ArrayList<>();
        items = new ArrayList<>();
    }

    //=================== METHODE ===================
    /*Search FORM FOR ETUDIANT BY FILIERE*/
    public void searchFormFiliere() {

        System.out.println("==== Search Form Filiere Controller ====");
        Etudiant clone = etudiantFacade.cloneEtud(selectedEtud);
        selectedEtud = null;
        System.out.println("clone === " + clone);
        System.out.println("==== Search Form Filiere Controller ====");

        elems = etudiantFacade.searchEtudFiliere(clone);
        items = null;

    }

    /**
     * Methode : find NoteModulaire by Etudiant and Semestre <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 23/05/2017 <br/>
     *
     * @param ed
     * @param sem
     */
    public void findByEtudiantAndSemestre(Etudiant ed, Semestre sem) {
        System.out.println("===== findByEtudiantAndSemestre =====");
        System.out.println("****** Etudiant === " + ed);
//        System.out.println("****** Semestre === " + sem.getLibelle());
        selectedEtud = ed;
//        System.out.println(" **** items === " + noteModulaireFacade.findByEtudiantAndSemestre(ed, sem));
        // items = ejbFacade.findByEtudiantAndSemestre(ed, sem);
        initTabPane(1);
//        items = noteModulaireFacade.findByEtudiantAndSemestre(ed, sem);
        System.out.println("===== end findByEtudiantAndSemestre =====");
    }

    public void initTabPane(int i) {
        if (selectedEtud != null) {
            if (selectedEtud.getCne() != null) {

                Semestre sem = new Semestre();
                sem.setLibelle(i);
                setItems(noteModulaireFacade.findByEtudiantAndSemestre(selectedEtud, sem));
                noteSemestre = noteSemestreFacade.findNSBySemestreLibelleAndEtudiant(selectedEtud, sem);
                System.out.println("ns " + noteSemestre);
            }
        }
    }

    public List<Semestre> findItemsByFiliere(Filiere filiere ) {
        System.out.println("filiere " + filiere);
        System.out.println("Semestres de ce Filiere === "+semestreFacade.findSemestreByFiliere(filiere));
        return semestreFacade.findSemestreByFiliere(filiere);
    }

    public boolean hasSemestre(int i) {
        List<NoteModulaire> tests = new ArrayList<>();
        boolean res = false;
        if (selectedEtud != null) {
            if (selectedEtud.getCne() != null) {
                Semestre sem = new Semestre();
                sem.setLibelle(i);
                tests = noteModulaireFacade.findByEtudiantAndSemestre(selectedEtud, sem);
            }
        }

        if (tests.isEmpty()) {
            res = true;
        }

        return res;
    }
    
    
    // added 09/06/2017 to Seach Semestre by Annee
    public List<Semestre> getFindSemestreByAnnee()
    {
        System.out.println("==== findSemestreByAnnee Controller =====");
        System.out.println("Annee Label :"+annee.getLibelle());
        System.out.println("Annee Id :"+annee.getId());
        if ( annee.getId() != null )
        return semestreFacade.findByAnnee(annee,getEnseignant().getFiliere());
        else 
            return semestreFacade.findSemestreByFiliere(getEnseignant().getFiliere());
    }
    
    
    
    public void generateReleveePdf(Etudiant etudItem) throws JRException, IOException
    {
        
        List<Semestre> semestres = new ArrayList<>();
        semestres.add(semestreNumber);
        
        System.out.println("====== generatePdf Controller ======");
        System.out.println("selectedEtud === "+etudItem);
        System.out.println("selectedSemestres === "+semestres);
        //List<AttestationPdf> aps = semestreFacade.getAllYouWant(etudItem, semestres);
        //semestreFacade.generatePdf(etudItem, semestres, aps);
        noteModulaireFacade.printPdf(etudItem, semestreNumber);
        FacesContext.getCurrentInstance().responseComplete();
        semestreNumber = new Semestre();
    }
    
    public void generateCSPdf(Etudiant etud) throws JRException, IOException
     {
         
         etudiantFacade.generateCertificatScolairePdf(etud);
         FacesContext.getCurrentInstance().responseComplete();
         
     }
    
    
    public void generateAttestationPdf(Etudiant etud) throws JRException, IOException
     {
         
         etudiantFacade.printAttestationPdf(etud,null);
         FacesContext.getCurrentInstance().responseComplete();
         
     }
    
    public List<Semestre> getEtudSemetres(Etudiant etudItem)
    {
        if( etudItem != null )
        {
         
         List<Semestre> semestres = semestreFacade.findByFiliere(etudItem.getFiliere());
         
        
        return semestres;
        }
        else 
            return new ArrayList<>();
    }

    //=================== METHODE ===================
    public Etudiant getSelectedEtud() {
        if (selectedEtud == null) {
            selectedEtud = new Etudiant();
        }
        return selectedEtud;
    }

    public void setSelectedEtud(Etudiant selectedEtud) {
        this.selectedEtud = selectedEtud;
    }

    public Annee getAnnee() {
        if ( annee == null )
        {
            annee = new Annee();
            annee.setId(etudiantFacade.generateId("Annee", "id")-1);
        }
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }
    
    

    public List<Etudiant> getElems() {
        if (elems == null) {
            elems = new ArrayList<>();
        }
        return elems;
    }

    public void setElems(List<Etudiant> elems) {
        this.elems = elems;
    }
    
    public List<NoteModulaire> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public Semestre getSemestreNumber() {
        if ( semestreNumber == null )
        {
            semestreNumber = new Semestre();
        }
        return semestreNumber;
    }

    public void setSemestreNumber(Semestre semestreNumber) {
        this.semestreNumber = semestreNumber;
    }

    public void setItems(List<NoteModulaire> items) {
        this.items = items;
    }

    public Enseignant getEnseignant() {
        Object o = SessionUtil.getConnectedUser();
        if (o instanceof Enseignant) {
            enseignant = (Enseignant) o;
            enseignant = enseignantFacade.find(enseignant.getCin());
        }
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public EtudiantFacade getEtudiantFacade() {
        return etudiantFacade;
    }

    public void setEtudiantFacade(EtudiantFacade etudiantFacade) {
        this.etudiantFacade = etudiantFacade;
    }

    public SemestreFacade getSemestreFacade() {
        return semestreFacade;
    }

    public void setSemestreFacade(SemestreFacade semestreFacade) {
        this.semestreFacade = semestreFacade;
    }

    public NoteModulaireFacade getNoteModulaireFacade() {
        return noteModulaireFacade;
    }

    public void setNoteModulaireFacade(NoteModulaireFacade noteModulaireFacade) {
        this.noteModulaireFacade = noteModulaireFacade;
    }

    public NoteSemestre getNoteSemestre() {
        if (noteSemestre == null) {
            noteSemestre = new NoteSemestre();
        }
        return noteSemestre;
    }

    public void setNoteSemestre(NoteSemestre noteSemestre) {
        this.noteSemestre = noteSemestre;
    }

    public EnseignantFacade getEnseignantFacade() {
        return enseignantFacade;
    }

    public void setEnseignantFacade(EnseignantFacade enseignantFacade) {
        this.enseignantFacade = enseignantFacade;
    }

}
