/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Filiere;
import bean.Module;
import bean.Semestre;
import controller.util.JsfUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import service.FiliereFacade;
import service.ModuleFacade;
import service.SemestreFacade;

/**
 *
 * @author BENIHOUD Youssef
 */
@Named( "tabViewController")
@SessionScoped
public class TabViewController implements Serializable {

    /**
     * Creates a new instance of TabViewController
     */
    public TabViewController() {
    }
    
    private Filiere selectedFiliere;
    private Semestre selectedSemestre;
    private Module selectedModule;
    
    @EJB
    private FiliereFacade filiereFacade;
    @EJB
    private SemestreFacade semestreFacade;
    @EJB
    private ModuleFacade moduleFacade;
    
    
    public void uploadFiliere(FileUploadEvent event) throws IOException, Exception {
        System.out.println("Success " + event.getFile().getFileName() + " is uploaded.");
        // Do what you want with the 
        int res = filiereFacade.readExcel(event, selectedFiliere);
        switch (res) {
            case -5:
                System.out.println("a Missing Elements , which means  res == " + res);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Le fichier " + event.getFile().getFileName() + " a un element qui se manque. Veuillez verifiez que rien est manquée dans votre fichier."));
                break;
            default:
                if (res < 0) {
                    if (res != -5 && res != -1) {
                        JsfUtil.addErrorMessage("Error occured!!!");
                    }
                } else {
                    JsfUtil.addSuccessMessage("Upload success!!!");
                }
        }

    }
    
    public void uploadModule(FileUploadEvent event) throws IOException, Exception {
        System.out.println("Success " + event.getFile().getFileName() + " is uploaded.");
        // Do what you want with the 
        int res = moduleFacade.readExcel(event, selectedModule);
        switch (res) {
            case -5:
                System.out.println("a Missing Elements , which means  res == " + res);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Le fichier " + event.getFile().getFileName() + " a un element qui se manque. Veuillez verifiez que rien est manquée dans votre fichier."));
                break;
            default:
                if (res < 0) {
                    if (res != -5 && res != -1) {
                        JsfUtil.addErrorMessage("Error occured!!!");
                    }
                } else {
                    JsfUtil.addSuccessMessage("Upload success!!!");
                }
        }

    }
    
    
    

    public Filiere getSelectedFiliere() {
        if ( selectedFiliere == null){
            selectedFiliere = new Filiere();
        }
    
        return selectedFiliere;
    }

    public void setSelectedFiliere(Filiere selectedFiliere) {
        this.selectedFiliere = selectedFiliere;
    }

    public Semestre getSelectedSemestre() {
        if(selectedSemestre == null ){
            selectedSemestre = new Semestre();
        }
        return selectedSemestre;
    }

    public void setSelectedSemestre(Semestre selectedSemestre) {
        this.selectedSemestre = selectedSemestre;
    }

    public Module getSelectedModule() {
        if ( selectedModule == null ){
            selectedModule = new Module();
        }
        return selectedModule;
    }

    public void setSelectedModule(Module selectedModule) {
        this.selectedModule = selectedModule;
    }
    
    
    
    
}
