/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Device;
import bean.Enseignant;
import bean.Etudiant;
import bean.Secure;
import controller.util.DeviceUtil;
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
import service.DeviceFacade;
import service.EnseignantFacade;
import service.EtudiantFacade;
import service.SecureFacade;

/**
 *
 * @author BENIHOUD Youssef
 */
@Named("secureController")
@SessionScoped
public class SecureController implements Serializable {
    
    private Secure selected;
    private List<Secure> items;
    private List<Secure> elems;
    @EJB
    private SecureFacade ejbFacade;
    @EJB
    private EnseignantFacade enseignantFacade;
    @EJB
    private EtudiantFacade etudiantFacade;
    @EJB
    private DeviceFacade deviceFacade;

    /**
     * Creates a new instance of SecureController
     */
    public SecureController() {
    }

    // ==================== METHODE =======================
    //****************METHODE FOR ENSEIGNANTS***************//
    public void addSecureEns() {
        System.out.println("==== Start Create Secure ====");
        System.out.println("selected before cloning == " + selected);
        Secure secure = ejbFacade.clonning(selected);
        System.out.println("clone === " + secure);
        selected = new Secure();
       // secure.setId(ejbFacade.generateId("Secure", "id"));
        System.out.println("selected after cloning == " + selected);
        if ( !secure.getQuest().equals("") && !secure.getResp().equals("") )
            if ( !secure.getQuest().equals(""))
                if (!secure.getResp().equals(""))
        {
        items.add(secure);
        }
        System.out.println("==== END Create Secure ====");
    }
    
    public void QuestionsEns() {
        System.out.println("=== Question Ens ===");
        Enseignant test = (Enseignant) SessionUtil.getAttribute("deviceIssue");
        Enseignant enseignant = enseignantFacade.find(test.getCin());
        List<Secure> e = enseignant.getSecures();
        
        for (Secure secure : e) {
            secure.setResp("");
        }
        System.out.println("elems === " + e);
        elems = e;
        
        System.out.println("=== Question Ens ===");
        
    }
    
    public void helloEns() {
        System.out.println("=== hello Ens ===");
        Enseignant test = (Enseignant) SessionUtil.getConnectedUser();
        System.out.println("test Ens == " + test);
        Enseignant enseignant = enseignantFacade.find(test.getCin());
        List<Secure> e = enseignant.getSecures();
        
        if(!e.isEmpty())
        {
        for (Secure secure : e) {
            secure.setResp("**INVISIBLE**");
        }
        
        System.out.println("items ===" + e);
        items = e;
        }
        System.out.println("=== hello Etud ===");
        
    }
    
    
    public void checkAnswersEns(Secure item) {
        System.out.println("==== Controller CheckAnswers ====");
        System.out.println("selected.getResp() === " + item.getResp());
        String clonne = item.getResp();
        ejbFacade.checkAnswersEns(item.getQuest(), clonne);
        elems.remove(elems.indexOf(item));
        System.out.println("==== End Controller CheckAnswers ====");
    }
    
    public void validateEns() throws IOException {
        System.out.println("==== Validate Ens Controller ====");
        boolean res = ejbFacade.validateEns();
        if (res) {
            System.out.println("*** Congratulation ****");
            Device device = DeviceUtil.getDevice();
            Enseignant enseignant = (Enseignant) SessionUtil.getAttribute("deviceIssue");
            enseignant.setBlocked(false);
            deviceFacade.save(device, enseignant);
            SessionUtil.deconnectSelectedUser("deviceIssue");
            System.out.println("Enseignant === " + enseignant);
            SessionUtil.registerUser(enseignant);
            SessionUtil.redirect("../profilEnseignant/Home.xhtml");
            
        } else {
            System.out.println("**** Hard Luck ****");
            Enseignant enseignant = (Enseignant) SessionUtil.getAttribute("deviceIssue");
            enseignantFacade.sendEmailUrgent(enseignant); // added 16/05/2017 

        }
    }
    /*
    public void generatePdfEns() throws JRException, IOException
    {
        Enseignant enseignant = (Enseignant) SessionUtil.getConnectedUser();
        ejbFacade.generatePdf(items, enseignant.getNom());
        FacesContext.getCurrentInstance().responseComplete();
    }
    */
    public void okEns() throws IOException {
        System.out.println("=== okEns ===");
        List<Secure> results = items;
        if(!results.isEmpty())
        for (Secure item : results )
        {
            item.setId(ejbFacade.generateId("Secure", "id"));
            ejbFacade.createSecureEns(item);
        }
        setItems(null);
        SessionUtil.redirect("../profilEnseignant/Home.xhtml");
    }

    //****************METHODE FOR ENSEIGNANTS***************//
//****************METHODE FOR ETUDIANTS***************//
    public void addSecureEtud() {
        System.out.println("==== Start Create Secure ====");
        System.out.println("selected before cloning == " + selected);
        Secure secure = ejbFacade.clonning(selected);
        System.out.println("clone === " + secure);
        selected = new Secure();
        secure.setId(ejbFacade.generateId("Secure", "id"));
        System.out.println("selected after cloning == " + selected);
        if ( !secure.getQuest().equals("") && !secure.getResp().equals("") )
            if ( !secure.getQuest().equals(""))
                if (!secure.getResp().equals(""))
        {
        items.add(secure);
        }
        System.out.println("==== END Create Secure ====");
    }
    
    public void QuestionsEtud() {
        System.out.println("=== Question Etud ===");
        Etudiant test = (Etudiant) SessionUtil.getAttribute("deviceIssue");
        System.out.println("test Etud == " + test);
        Etudiant etudiant = etudiantFacade.find(test.getCne());
        List<Secure> e = etudiant.getSecures();
        
        for (Secure secure : e) {
            secure.setResp("");
        }
        
        System.out.println("elems ===" + e);
        elems = e;
        
        System.out.println("=== Question Etud ===");
        
    }
    public void helloEtud() {
        System.out.println("=== hello Etud ===");
        Etudiant test = (Etudiant) SessionUtil.getConnectedUser();
        System.out.println("test Etud == " + test);
        Etudiant etudiant = etudiantFacade.find(test.getCne());
        List<Secure> e = etudiant.getSecures();
        
        if(!e.isEmpty())
        {
        for (Secure secure : e) {
            secure.setResp("**INVISIBLE**");
        }
        
        System.out.println("items ===" + e);
        items = e;
        }
        System.out.println("=== hello Etud ===");
        
    }
    
    
    public void checkAnswersEtud(Secure item) {
        System.out.println("==== Controller CheckAnswers ====");
        System.out.println("selected.getResp() === " + item.getResp());
        String clonne = item.getResp();
        ejbFacade.checkAnswersEtud(item.getQuest(), clonne);
        elems.remove(elems.indexOf(item));
        System.out.println("==== End Controller CheckAnswers ====");
    }
    
    public void validateEtud() throws IOException {
        System.out.println("==== Validate Ens Controller ====");
        boolean res = ejbFacade.validateEtud();
        if (res) {
            System.out.println("*** Congratulation ****");
            Device device = DeviceUtil.getDevice();
            Etudiant etudiant = (Etudiant) SessionUtil.getAttribute("deviceIssue");
            etudiant.setBlocked(false);
            deviceFacade.save(device, etudiant);
            SessionUtil.deconnectSelectedUser("deviceIssue");
            System.out.println("Etudiant === " + etudiant);
            SessionUtil.registerUser(etudiant);
            SessionUtil.redirect("../home/Home.xhtml");
        } else {
            System.out.println("**** Hard Luck ****");
            Etudiant etudiant = (Etudiant) SessionUtil.getAttribute("deviceIssue");
            etudiantFacade.sendEmailUrgent(etudiant);
        }
    }
    
   /* public void generatePdfEtud() throws JRException, IOException
    {
        Etudiant etudiant = (Etudiant) SessionUtil.getConnectedUser();
        ejbFacade.generatePdf(items, etudiant.getNom());
        FacesContext.getCurrentInstance().responseComplete();
    }*/
    
    public void okEtud() throws IOException {
        System.out.println("=== okEtud === ");
        List<Secure> results = items;
        if(!results.isEmpty())
        for (Secure item : results )
        {
            item.setId(ejbFacade.generateId("Secure", "id"));
            ejbFacade.createSecureEtud(item);
        }
        setItems(null);
        SessionUtil.redirect("../home/Home.xhtml");
    }

//****************METHODE FOR ETUDIANTS***************//
    public void minusSecure(Secure secure) {
        System.out.println("=== minus Secure ===");
        System.out.println("Secure ::== " + secure);
       // ejbFacade.remove(secure);
        items.remove(items.indexOf(secure));
        System.out.println("=== end minus Secure ===");
    }
    
    public boolean isArrayList(List<Secure> test) {
        if (test != null) {
            return false;
        }
        
        return true;
    }
    
    public void createSecure() {
        items = new ArrayList<>();
        // To Do Path
    }
    
    
    
    public void soutest()
    {
        System.out.println("*** it works ***");
    }

// ==================== METHODE =======================
    public Secure getSelected() {
        if (selected == null) {
            selected = new Secure();
        }
        return selected;
    }
    
    public void setSelected(Secure selected) {
        this.selected = selected;
    }
    
    public List<Secure> getElems() {
        if (elems == null) {
            
            elems = new ArrayList<>();
        }
        return elems;
    }
    
    public void setElems(List<Secure> elems) {
        this.elems = elems;
    }
    
    public List<Secure> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }
    
    public void setItems(List<Secure> items) {
        this.items = items;
    }
    
    public boolean getItemsMoreThanThree()
    {
        return ejbFacade.itemsMoreThanThree(items);
    }
    
}
