/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Actualite;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.faces.context.FacesContext;
import service.ActualiteFacade;
import service.EnseignantFacade;

/**
 *
 * @author larbi
 */
@Named( "myActualiteController")
@SessionScoped
public class MyActualiteController implements Serializable {

    private Actualite selected;
    private Actualite sselected;
    private List<Actualite> items;
    private List<Actualite> iitems;
    private List<Actualite> iiitems;
    private List<Actualite> utems;
    private List<Actualite> uutems;
    private List<Actualite> uuutems;

    @EJB
    private ActualiteFacade actualiteFacade;
    @EJB
    private EnseignantFacade enseignantFacade;

    public EnseignantFacade getEnseignantFacade() {
        return enseignantFacade;
    }

    public void setEnseignantFacade(EnseignantFacade enseignantFacade) {
        this.enseignantFacade = enseignantFacade;
    }

    public Actualite getSselected() {
        return sselected;
    }

    public void setSselected(Actualite sselected) {
        this.sselected = sselected;
    }

    public Actualite getSelected() {
        if (selected == null) {
            selected = new Actualite();
        }

        return selected;
    }

    public void setSelected(Actualite selected) {

        this.selected = selected;
    }

    public List<Actualite> getIitems() {
        RefreshActualite();
        iitems = actualiteFacade.findActualite(2,1);
        return iitems;
    }

    public List<Actualite> getIiitems() {
          RefreshActualite();
        iiitems = actualiteFacade.findActualite(3,1);
        return iiitems;
    }

    public void setIiitems(List<Actualite> iiitems) {
        this.iiitems = iiitems;
    }

    public void setIitems(List<Actualite> iitems) {

        this.iitems = iitems;
    }

    public List<Actualite> getItems() {
        RefreshActualite();
        items = actualiteFacade.findActualite(1,1);
        return items;
    }

    public void myActualite(Actualite item) throws IOException {
        selected = item;
        FacesContext.getCurrentInstance().getExternalContext().redirect("../actualite/Actualite.xhtml");

    }

    public void myyActualite(Actualite item) throws IOException {
        sselected = item;
        FacesContext.getCurrentInstance().getExternalContext().redirect("../actualite/Actualite1.xhtml");

    }



    public void setItems(List<Actualite> items) {
        this.items = items;
    }

    public ActualiteFacade getActualiteFacade() {
        return actualiteFacade;
    }

    public void setActualiteFacade(ActualiteFacade actualiteFacade) {
        this.actualiteFacade = actualiteFacade;
    }
    //update:Refresh the actualite:
   // @Schedule(second = "0", minute = "29", hour = "1", dayOfWeek = "*", persistent = false)
    public void RefreshActualite(){
    actualiteFacade.updateActualite();
    }

    /**
     * Creates a new instance of MyActualiteController
     */
    public MyActualiteController() {
    }

    public List<Actualite> getUtems() {
          
        utems=actualiteFacade.findActualitee(1, 2,1);
        return utems;
    }

    public void setUtems(List<Actualite> utems) {
        this.utems = utems;
    }

    public List<Actualite> getUutems() {
            
         uutems=actualiteFacade.findActualitee(2, 2,1);
        return uutems;
    }

    public void setUutems(List<Actualite> uutems) {
        this.uutems = uutems;
    }

    public List<Actualite> getUuutems() {
           
         uuutems= actualiteFacade.findActualitee(3,2,1);
        return uuutems;
    }

    public void setUuutems(List<Actualite> uuutems) {
        this.uuutems = uuutems;
    }
    

}
