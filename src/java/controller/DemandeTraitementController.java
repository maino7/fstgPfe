/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Demande;
import bean.DemandeItem;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import service.DemandeFacade;
import service.DemandeItemFacade;

/**
 *
 * @author BENIHOUD Youssef
 */
@Named("demandeTraitementController")
@SessionScoped
public class DemandeTraitementController implements Serializable {

    /**
     * Creates a new instance of DemandeTraitementController
     */
    public DemandeTraitementController() {
    }

    private Long cne;
    private List<Demande> doneDemandes;
    private List<Demande> allNewDemandes;
    private List<DemandeItem> elems;
    @EJB
    private DemandeFacade demandeFacade;
    @EJB
    private DemandeItemFacade demandeItemFacade;

//=============== Methode =====================
    public void findByEtud() {
        if (!cne.equals("")) {
            System.out.println("==== findByEtud Controller =====");
            allNewDemandes = demandeFacade.findByEtudiant(cne);
            elems = null;
        } else {
            allNewDemandes = demandeFacade.findNewDemandes();
        }
        System.out.println("==== End FindByEtud Controller =====");
    }

    public void findByDemande(Demande demande) {
        System.out.println("========= findByDemande Controller ======");
        elems = demandeItemFacade.findByDemande(demande.getId());
        System.out.println("======== End FindByDemande Controller ==========");
    }

    public void declineDem(Demande item) {
        System.out.println("***** Accepter ******");
        item.setEtat(-1);
        demandeFacade.edit(item);
        allNewDemandes = null;
        doneDemandes = null;
        elems = new ArrayList<>();
        // demandeItemFacade.remove(demandeItem);
    }

    public void accepterDem(Demande item) {
        // To Do What It has to be done
        item.setEtat(1);
        demandeFacade.edit(item);
        allNewDemandes = null;
        doneDemandes = null;
        elems = new ArrayList<>();
    }

    public List<DemandeItem> doneItems(Demande d) {
        if (d != null) {
            return demandeItemFacade.findByDemande(d.getId());
        }else{
            return new ArrayList();
        }
    }
        //=============== Methode =====================
    public Long getCne() {
        return cne;
    }

    public void setCne(Long cne) {
        this.cne = cne;
    }

    public List<Demande> getDoneDemandes() {
        doneDemandes = demandeFacade.findDoneDemandes();
        return doneDemandes;
    }

    public void setDoneDemandes(List<Demande> doneDemandes) {
        this.doneDemandes = doneDemandes;
    }

    public List<DemandeItem> getElems() {
        if (elems == null) {
            elems = new ArrayList<>();
        }
        return elems;
    }

    public void setElems(List<DemandeItem> elems) {
        this.elems = elems;
    }

        public List<Demande> getAllNewDemandes() {
        allNewDemandes = demandeFacade.findNewDemandes();
        return allNewDemandes;
    }

    public void setAllNewDemandes(List<Demande> allNewDemandes) {
        this.allNewDemandes = allNewDemandes;
    }

    public DemandeFacade getDemandeFacade() {
        return demandeFacade;
    }

    public void setDemandeFacade(DemandeFacade demandeFacade) {
        this.demandeFacade = demandeFacade;
    }

    public DemandeItemFacade getDemandeItemFacade() {
        return demandeItemFacade;
    }

    public void setDemandeItemFacade(DemandeItemFacade demandeItemFacade) {
        this.demandeItemFacade = demandeItemFacade;
    }

}
