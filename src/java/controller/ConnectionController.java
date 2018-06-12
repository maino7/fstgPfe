/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Enseignant;
import bean.Etudiant;
import bean.UserStock;
import controller.util.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import service.EnseignantFacade;
import service.EtudiantFacade;
import service.UserFacade;
import service.UserStockFacade;

/**
 *
 * @author BENIHOUD Youssef
 */
@Named("connectionController")
@SessionScoped
public class ConnectionController implements Serializable {

    private Enseignant selectedEns;
    private Etudiant selectedEtud;
    private UserStock selectedUserStock;
    private String messageConnection;
    private String key;
    @EJB
    private EnseignantFacade enseignantFacade;
    @EJB
    private EtudiantFacade etudiantFacade;
    @EJB
    private UserStockFacade userStockFacade;

    /**
     * Creates a new instance of ConnectionController
     */
    public ConnectionController() {
    }

    public boolean isConnected() {
        if (SessionUtil.getConnectedUser() != null) {
            return true;
        }
        return false;
    }

    /**
     * Methode Controller : Sign In Etudiant ; Sign In Enseignant Author :
     * Youssef Benihoud Date : 09/05/2017
     */
    public void signInEtud() throws IOException {

        System.out.println("====  Start Sign In Etudiant Controller  === ");

        Etudiant testEtud;
        Etudiant test = etudiantFacade.cloneEtud(selectedEtud);

        System.out.println(" testEtud  === " + test);
        selectedEtud = new Etudiant();
        int res = etudiantFacade.signIn(test);
        System.out.println(" Res  === " + res);

        boolean checkOut = etudiantFacade.firstConnection(test); // added 16/05/2017 Youssef Benihoud
        if (res == 1) {

            testEtud = etudiantFacade.find(test.getCne());

            if (checkOut || !etudiantFacade.hasSecure(testEtud.getCne())) {
                messageConnection = "";
                SessionUtil.redirect("../etudiant/etudfirstconnect.xhtml"); // added 16/05/2017 Youssef Benihoud
            }
            if (testEtud.isMdpChanged()) {
                // To Do Session
                // To Do Path To Change Password Interface For Etudiant	
                SessionUtil.redirect("../etudiant/etudfirstconnect.xhtml"); // added 16/05/2017 Youssef Benihoud
                messageConnection = "You have to Change Your Password";
            }

            // To Do Session
            SessionUtil.redirect("../home/Home.xhtml");
            messageConnection = "You are Etudiant";

        } else if (res == -5) {
            messageConnection = "You didn't write anything";
        } else if (res == -4) {
            messageConnection = " This User doesn't exist ";
        } else if (res == -3) {
            messageConnection = "Wrong Password";
        } else if (res == -6) {
            SessionUtil.redirect("../secure/SecureVEtud.xhtml"); // added 16/05/2017 Youssef Benihoud
            messageConnection = " Device Problem";
        } else if (res == -2) {
            SessionUtil.redirect("../secure/SecureVEtud.xhtml"); // added 16/05/2017 Youssef Benihoud
            messageConnection = " Blocked User";
        }

        // To Do Path : To Connection Page
        System.out.println("====  End Sign In Etudiant Controller  === ");
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void signInEns() throws IOException {
        System.out.println("====  Start Sign In Enseignant Controller  === ");

        Enseignant testEns;
        Enseignant test = enseignantFacade.cloneEns(selectedEns);
        selectedEns = new Enseignant();
        System.out.println(" test  === " + test);
        int res = enseignantFacade.signIn(test);

        if (res == 1) {
            testEns = enseignantFacade.find(test.getCin()); // Added 16/05/2017 Youssef Benihoud
            /*boolean checkOut = enseignantFacade.firstConnection(testEns); // added 16/05/2017 Youssef Benihoud

       /*     if (checkOut) {
                messageConnection = "";
                SessionUtil.redirect("../enseignant/ensfirstconnect.xhtml"); // added 16/05/2017 Youssef Benihoud
            }*/

            if (testEns.isMdpChanged() || !enseignantFacade.hasSecure(testEns.getCin())) {
                // To Do Session
                // To DO Path : Change Password Interface For Enseignant
                SessionUtil.redirect("../enseignant/ensfirstconnect.xhtml"); // added 16/05/2017 Youssef Benihoud
                messageConnection = " You have to change your password ";
            }

            if (testEns.isAdmine()) {
                // To Do Session
                // To Do Path : Interface Admin
                messageConnection = " You are Admin ";
            }

            // To Do Session
            // To Do Path : Interface Enseignant
            System.out.println("connected ens");
            SessionUtil.redirect("../profilEnseignant/Home.xhtml");
            messageConnection = " You are Enseignant";
        } else if (res == -5) {
            messageConnection = "You didn't write anything";
        } else if (res == -4) {
            messageConnection = " This User doesn't exist ";
        } else if (res == -3) {
            messageConnection = "Wrong Password";
        } else if (res == -6) {
            // TO DO PATH : To Question Verification
            SessionUtil.redirect("../secure/SecureVEns.xhtml"); // added 16/05/2017 Youssef Benihoud
            messageConnection = " Device Problem";
        } else if (res == -2) {
            SessionUtil.redirect("../secure/SecureVEns.xhtml"); // added 16/05/2017 Youssef Benihoud
            messageConnection = " Blocked User";
        }

        // To Do Path : To Connection Page
        System.out.println("====  Start Sign In Enseignant Controller  === ");
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void testConnection() {
        System.out.println("the test works");
    }

    //=========ADMIN CNX===========//
    public void signInAdmin() throws IOException {
        UserStock testUserStock;
       UserStock test = userStockFacade.cloneUserStock(selectedUserStock);
        selectedUserStock = new UserStock();
        int res = userStockFacade.adminSignUp(test);
        if (res == 1) {
              SessionUtil.redirect("../concours/preinscription.xhtml");
        } else if (res == -5) {
            messageConnection = "You didn't write anything";
        } else if (res == -4) {
            messageConnection = " This User doesn't exist ";
        } else if (res == -3) {
            messageConnection = "Wrong Password";
        } 
        System.out.println("====  Start Sign In UserStock Controller  === ");
        FacesContext.getCurrentInstance().responseComplete();

    }

    //============================//
    public void signInStock() throws IOException {
        System.out.println("====  Start Sign In Stock Controller  === ");

        UserStock testUserStock;
        UserStock test = userStockFacade.cloneUserStock(selectedUserStock);
        selectedUserStock = new UserStock();
        System.out.println(" test  === " + test);
        int res = userStockFacade.signIn(test);

        if (res == 1) {
            testUserStock = userStockFacade.find(test.getId()); // Added 16/05/2017 Youssef Benihoud
            /*boolean checkOut = enseignantFacade.firstConnection(testEns); // added 16/05/2017 Youssef Benihoud

       /*     if (checkOut) {
                messageConnection = "";
                SessionUtil.redirect("../enseignant/ensfirstconnect.xhtml"); // added 16/05/2017 Youssef Benihoud
            }*/
            if (testUserStock.getId().equals("SH184344")) {
                SessionUtil.redirect("../stock/DoyenLogin.xhtml");
            } else {
                SessionUtil.redirect("../stock/UserLogin.xhtml");
            }
//            if (testUserStock.isMdpChanged() || !enseignantFacade.hasSecure(testUserStock.getId())) {
//                // To Do Session
//                // To DO Path : Change Password Interface For Enseignant
//                SessionUtil.redirect("../stock/DoyenLogin.xhtml"); // added 16/05/2017 Youssef Benihoud
//                messageConnection = " You have to change your password ";
//            }

//            if (testUserStock.isAdmine()) {
//                // To Do Session
//                // To Do Path : Interface Admin
//                messageConnection = " You are Admin ";
//            }
            // To Do Session
            // To Do Path : Interface Enseignant
            System.out.println("connected user");
//            SessionUtil.redirect("../stock/DoyenLogin.xhtml");
//            messageConnection = " You are Enseignant";
        } else if (res == -5) {
            messageConnection = "You didn't write anything";
        } else if (res == -4) {
            messageConnection = " This User doesn't exist ";
        } else if (res == -3) {
            messageConnection = "Wrong Password";
        } else if (res == -6) {
            // TO DO PATH : To Question Verification
            SessionUtil.redirect("../secure/SecureVEns.xhtml"); // added 16/05/2017 Youssef Benihoud
            messageConnection = " Device Problem";
        } else if (res == -2) {
            SessionUtil.redirect("../secure/SecureVEns.xhtml"); // added 16/05/2017 Youssef Benihoud
            messageConnection = " Blocked User";
        }

        // To Do Path : To Connection Page
        System.out.println("====  Start Sign In UserStock Controller  === ");
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void logOut() throws IOException {
        System.out.println("=== Log Out ===");
        System.out.println("getConnected Before === " + SessionUtil.getConnectedUser());
        if (SessionUtil.getConnectedUser() != null) {
            SessionUtil.deconnectUser();
        }
        System.out.println("getConnected After === " + SessionUtil.getConnectedUser());
        SessionUtil.redirect("../template/Home.xhtml");
    }

    /*TEST*/
    public void forgetPassEns() {
        enseignantFacade.sendEmailPassword(key);
    }

    public void forgetPassEtud() {
        etudiantFacade.sendEmailPassword(key);
    }

    public void editPassWord() {
        enseignantFacade.changePassword(selectedEns, key);
    }

    public void redirect(String redirect) throws IOException {
        SessionUtil.redirect(redirect);
    }

    /*TEST*/
 /*
    Test Controller 
    Edit
    
    
    public void editEns()
    {
        //enseignantFacade.editPass(selectedEns.getCin());
        etudiantFacade.editPass(selectedEtud.getCne());
        messageConnection = "Password Changed from EditEns";
    }
    
    
    Test Controller 
    Edit
     */
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Enseignant getSelectedEns() {
        if (selectedEns == null) {
            return selectedEns = new Enseignant();
        }
        return selectedEns;
    }

    public void setSelectedEns(Enseignant selectedEns) {
        this.selectedEns = selectedEns;
    }

    public Etudiant getSelectedEtud() {
        if (selectedEtud == null) {
            return selectedEtud = new Etudiant();
        }
        return selectedEtud;
    }

    public void setSelectedEtud(Etudiant selectedEtud) {
        this.selectedEtud = selectedEtud;
    }

    public UserStock getSelectedUserStock() {
        if (selectedUserStock == null) {
            return selectedUserStock = new UserStock();
        }
        return selectedUserStock;
    }

    public void setSelectedUserStock(UserStock selectedUserStock) {
        this.selectedUserStock = selectedUserStock;
    }

    public String getMessageConnection() {
        if (messageConnection == null) {
            return messageConnection = "";
        }
        return messageConnection;
    }

    public void setMessageConnection(String messageConnection) {
        this.messageConnection = messageConnection;
    }

}
