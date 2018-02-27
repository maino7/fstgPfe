/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Enseignant;
import bean.Etudiant;
import controller.util.SessionUtil;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import service.EnseignantFacade;
import service.EtudiantFacade;

/**
 *
 * @author Abed
 */
@Named("templateController")
@SessionScoped
public class TemplateController implements Serializable {

    private Etudiant etudiant = null;
    private Enseignant enseignant = null;
    private @EJB
    EtudiantFacade etudiantFacade;
    private @EJB
    EnseignantFacade enseignantFacade;

    /**
     * Creates a new instance of TemplateController
     */
    public TemplateController() {
    }

    public void etudiantPrivileges() throws IOException {
        if (userConnected() < 0) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../template/Home.xhtml");
        }
    }

    public void enseignantPrivileges() throws IOException {
        if (userConnected() < 0) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../template/Home.xhtml");
        } else if (userConnected() == 1) {
            Enseignant e = (Enseignant) SessionUtil.getConnectedUser();
            enseignant = enseignantFacade.find(e.getCin());
            FacesContext.getCurrentInstance().getExternalContext().redirect("../template/Home.xhtml");
        } else if (userConnected() == 2) {
            Etudiant e = (Etudiant) SessionUtil.getConnectedUser();
            etudiant = etudiantFacade.find(e.getCne());
            FacesContext.getCurrentInstance().getExternalContext().redirect("../template/Home.xhtml");
        }
    }

    public void redirect() throws IOException {
        if (userConnected() > 0) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../template/Home.xhtml");
        }
    }

    public int userConnected() {
        Object o = SessionUtil.getConnectedUser();
        if (o instanceof Enseignant) {
            return 1;
        } else if (o instanceof Etudiant) {
            return 2;
        }
        return -1;
    }

    public Object getConnected(){
        Object o=SessionUtil.getConnectedUser();
        if(userConnected()==1){
            Enseignant e = (Enseignant) o;
            return enseignantFacade.find(e.getCin());
        }else if(userConnected()==2){
            Etudiant e = (Etudiant) o;
            System.out.println(" voilaa etudiant "+ etudiantFacade.find(e.getCne()));
            return  etudiantFacade.find(e.getCne());
        }
        return null;
    }
    
    public boolean isAnEtudiant() {
        if (userConnected() == 2) {
            return true;

        }
        return false;
    }

    public boolean isAnEnseignant() {
        if (userConnected() == 1) {
            return true;
        }
        return false;
    }

    public Etudiant getEtudiant() {
        etudiant= (Etudiant) getConnected();
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Enseignant getEnseignant() {
        enseignant= (Enseignant) getConnected();
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

    public EnseignantFacade getEnseignantFacade() {
        return enseignantFacade;
    }

    public void setEnseignantFacade(EnseignantFacade enseignantFacade) {
        this.enseignantFacade = enseignantFacade;
    }

}
