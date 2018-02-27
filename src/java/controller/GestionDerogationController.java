/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.DemandeDerogation;
import bean.Enseignant;
import controller.util.SessionUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.DemandeDerogationFacade;
import service.EnseignantFacade;

/**
 *
 * @author Abed
 */
@Named("gestionDerogationController")
@SessionScoped
public class GestionDerogationController implements Serializable {

    @EJB
    private DemandeDerogationFacade demandeDerogationFacade;
    @EJB
    private EnseignantFacade enseignantFacade;
    private List<DemandeDerogation> derogations;
    private List<DemandeDerogation> allDerogations;
    private DemandeDerogation derogation;

    public void accept(DemandeDerogation d) {
        demandeDerogationFacade.accept(d);
        derogations.remove(d);
    }

    public void decline(DemandeDerogation d) {
        demandeDerogationFacade.decline(d);
        derogations.remove(d);
    }

    public DemandeDerogationFacade getDemandeDerogationFacade() {
        return demandeDerogationFacade;
    }

    public void setDemandeDerogationFacade(DemandeDerogationFacade demandeDerogationFacade) {
        this.demandeDerogationFacade = demandeDerogationFacade;
    }

    public List<DemandeDerogation> getDerogations() {
        Enseignant e = (Enseignant) SessionUtil.getConnectedUser();
        System.out.println("ha 9beel   " + e);
        e = enseignantFacade.find(e.getCin());
        System.out.println("ha bee3d   " + e);
        derogations = demandeDerogationFacade.findDerogationByFiliere(e.getFiliere());
        System.out.println("ha list   " + derogations);
        return derogations;
    }

    public void setDerogations(List<DemandeDerogation> derogations) {
        this.derogations = derogations;
    }

    public EnseignantFacade getEnseignantFacade() {
        return enseignantFacade;
    }

    public void setEnseignantFacade(EnseignantFacade enseignantFacade) {
        this.enseignantFacade = enseignantFacade;
    }

    public DemandeDerogation getDerogation() {
        return derogation;
    }

    public void setDerogation(DemandeDerogation derogation) {
        this.derogation = derogation;
    }

    public List<DemandeDerogation> getAllDerogations() {
        Enseignant e = (Enseignant) SessionUtil.getConnectedUser();
        e = enseignantFacade.find(e.getCin());
        allDerogations= demandeDerogationFacade.findAllDerogationByFiliere(e.getFiliere());
        return allDerogations;
    }

    public void setAllDerogations(List<DemandeDerogation> allDerogations) {
        this.allDerogations = allDerogations;
    }

    /**
     * Creates a new instance of DerogationController
     */
    public GestionDerogationController() {
    }

}
