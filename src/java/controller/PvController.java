/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Departement;
import bean.Enseignant;
import bean.Filiere;
import bean.Semestre;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import org.primefaces.event.FileUploadEvent;
import service.EtudiantFacade;
import bean.Pv;
import controller.util.JsfUtil;
import controller.util.SessionUtil;
import service.PvFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import service.SemestreFacade;

/**
 *
 * @author Abed
 */
@Named("pvController")
@SessionScoped
public class PvController implements Serializable {

    @EJB
    private service.PvFacade ejbFacade;
    private List<Pv> items = null;
    private List<Semestre> semestres = null;
    private Pv selected;

    @EJB
    private EtudiantFacade etudiantFacade;
    @EJB
    private SemestreFacade semestreFacade;
    private Semestre semestre = null;
    private Filiere filiere = null;
    private Departement departement;

    public void upload(FileUploadEvent event) throws IOException, Exception {
        System.out.println("Success " + event.getFile().getFileName() + " is uploaded.");
        // Do what you want with the 
        System.out.println("**** upload **** || getSemestre() === " + semestre);
        if (semestre == null || semestre.getId() == null) {
            JsfUtil.addErrorMessage("Selectionner un semstre");

        } else {
            int res = ejbFacade.readXls(event, semestre);
            switch (res) {
                case -6:
                    System.out.println("hanii hnaa f errur modul nn trove contrlr w res hoa " + res);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez Choisir le semestre"));
                    break;
                case -5:
                    System.out.println("hanii hnaa f errur modul nn trove contrlr w res hoa " + res);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Le fichier " + event.getFile().getFileName() + " ne contient pas les module du Semestre " + getSemestre().getLibelle() + ". Veuillez verifiez les nom des modules dans votre fichier."));
                    break;
                default:
                    if (res < 0) {
                        if (res != -5 && res != -1 && res != -6) {
                            JsfUtil.addErrorMessage("Error occured!!!");
                        }
                    } else {
                        JsfUtil.addSuccessMessage("Upload success!!!");
                    }
            }
        }

    }

    public void uploadInscr(FileUploadEvent event) throws IOException, Exception {
        System.out.println("Success " + event.getFile().getFileName() + " is uploaded.");
        // Do what you want with the 
        int res = ejbFacade.inscription(event, getFiliere());
        switch (res) {
            case -6:
                System.out.println("hanii hnaa f errur modul nn trove contrlr w res hoa " + res);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez Choisir la Filiere"));
                break;
            case -5:
                System.out.println("hanii hnaa f errur modul nn trove contrlr w res hoa " + res);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Le fichier " + event.getFile().getFileName() + " ne contient pas les module du Semestre " + getSemestre().getLibelle() + ". Veuillez verifiez les nom des modules dans votre fichier."));
                break;
            default:
                if (res < 0) {
                    if (res != -5 && res != -1 && res != -6) {
                        JsfUtil.addErrorMessage("Error occured!!!");
                    }
                } else {
                    JsfUtil.addSuccessMessage("Upload success!!!");
                }
        }

    }

    public void uploadDeust(FileUploadEvent event) throws IOException, Exception {
        System.out.println("Success " + event.getFile().getFileName() + " is uploaded.");
        // Do what you want with the 
        int res = ejbFacade.readDeustResults(event, getFiliere());
        switch (res) {
            case -6:
                System.out.println("hanii hnaa f errur modul nn trove contrlr w res hoa " + res);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez Choisir la Filiere"));
                break;
            case -5:
                System.out.println("hanii hnaa f errur modul nn trove contrlr w res hoa " + res);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Le fichier " + event.getFile().getFileName() + " ne contient pas les module du Semestre " + getSemestre().getLibelle() + ". Veuillez verifiez les nom des modules dans votre fichier."));
                break;
            default:
                if (res < 0) {
                    if (res != -5 && res != -1 && res != -6) {
                        JsfUtil.addErrorMessage("Error occured!!!");
                    }
                } else {
                    JsfUtil.addSuccessMessage("Upload success!!!");
                }
        }

    }

    public void uploadFiliere(FileUploadEvent event) throws IOException, Exception {
        System.out.println("Success " + event.getFile().getFileName() + " is uploaded.");
        // Do what you want with the 
        int res = ejbFacade.FiliereSemestreModuleCreation(event, getDepartement());
        switch (res) {
            case -6:
                System.out.println("hanii hnaa f errur modul nn trove contrlr w res hoa " + res);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez Choisir le Departement"));
                break;
            case -5:
                System.out.println("hanii hnaa f errur modul nn trove contrlr w res hoa " + res);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Le fichier " + event.getFile().getFileName() + " ne contient pas les module du Semestre " + getSemestre().getLibelle() + ". Veuillez verifiez les nom des modules dans votre fichier."));
                break;
            default:
                if (res < 0) {
                    if (res != -5 && res != -1 && res != -6) {
                        JsfUtil.addErrorMessage("Error occured!!!");
                    }
                } else {
                    JsfUtil.addSuccessMessage("Upload success!!!");
                }
        }

    }

    public void findItemsByFiliere() {
        if (filiere != null) {
            System.out.println("filiere " + filiere);
            System.out.println("Semestres de ce Filiere === " + semestreFacade.findSemestreByFiliere(filiere));
            semestres = semestreFacade.findSemestreByFiliere(filiere);
        }
    }

    public EtudiantFacade getEtudiantFacade() {
        return etudiantFacade;
    }

    public void setEtudiantFacade(EtudiantFacade etudiantFacade) {
        this.etudiantFacade = etudiantFacade;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    /**
     * Creates a new instance of PvController
     */
    public PvController() {
    }

    public Pv getSelected() {
        return selected;
    }

    public List<Semestre> getSemestres() {
        if (semestres == null) {
            semestres = new ArrayList();
        }
        return semestres;
    }

    public void setSemestres(List<Semestre> semestres) {
        this.semestres = semestres;
    }

    public void setSelected(Pv selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public PvFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PvFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Pv> getItems() {
        return items;
    }

    public void setItems(List<Pv> items) {
        this.items = items;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Enseignant getFilliereConnectedEnseignant() {
        return (Enseignant) SessionUtil.getConnectedUser();
    }

    private Filiere findFiliere() {
        Enseignant enseignant = getFilliereConnectedEnseignant();
        if (enseignant.getFiliere() != null && enseignant.getFiliere().getId() != null) {
            return enseignant.getFiliere();
        }
        return null;
    }

    public boolean isConnectedEnseignantHasFilliere() {

        //System.out.println("=== isConnectedEnsHasFil == ");
        //System.out.println("findFiliere == "+findFiliere());
        return findFiliere() != null;
    }

    public Filiere getFiliere() {
        // System.out.println("getFiliere ====> isConnectedEnseignant == "+isConnectedEnseignantHasFilliere());
        // System.out.println("getFiliere ====> findFiliere() == "+findFiliere());
        if (filiere == null) {
            filiere = new Filiere();
        }
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

}
