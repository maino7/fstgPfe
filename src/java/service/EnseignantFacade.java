/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Departement;
import bean.Device;
import bean.Enseignant;
import controller.util.DeviceUtil;
import controller.util.EmailUtil;
import controller.util.HashageUtil;
import controller.util.SearchUtil;
import controller.util.SessionUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abed
 */
@Stateless
public class EnseignantFacade extends AbstractFacade<Enseignant> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @EJB
    private DeviceFacade deviceFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnseignantFacade() {
        super(Enseignant.class);
    }

    public List<Enseignant> findByDepartm(Departement d) {
        String requette = "SELECT ens FROM Enseignant ens WHERE 1=1";
        if (d.getId() != null && d != null) {
            requette += SearchUtil.addConstraint("ens.departement", "id", "=", d.getId());
            requette += " ORDER BY ens.nom";
        }
        return em.createQuery(requette).getResultList();
    }

    /**
     * Methode : find ( Without Exception ) <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 10/05/2017 <br/>
     *
     * @param id
     * @return Enseignant, if it's found in DataBase <br/>
     * @return null, it it's not found in DataBase <br/>
     */
    @Override
    public Enseignant find(Object id) {
        try {
            Enseignant enseignant = (Enseignant) em.createQuery("select e from Enseignant e where e.cin ='" + id + "'").getSingleResult();
            if (enseignant != null) {
                return enseignant;
            }
        } catch (Exception e) {
            System.out.println("Makaynch had Enseignant");

        }
        return null;
    }

    /**
     * Methode : find ( Without Exception ) Author : Youssef Benihoud Date :
     * 10/05/2017
     */
    /**
     * Methode Sign in Enseignant <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 9/5/2017 <br/>
     *
     * @param enseignant
     * @return -5 : The Param is Null <br/>
     * @return -4 : The Param is not found in DataBase <br/>
     * @return -3 : The Password is wrong <br/>
     * @return -2 : The Param is blocked <br/>
     * @return -6 : The Device's Param has Error <br/>
     * @return 1 : Connected With Success <br/>
     */
    public int signIn(Enseignant enseignant) {
        System.out.println("========= Sign In ===========");
        if (enseignant == null || enseignant.getCin() == null) {
            return -5; // You must type your login
        } else {
            Enseignant loadEns = find(enseignant.getCin());
            System.out.println("loadEns === " + loadEns);

            if (loadEns == null) {
                return -4; // there is no User here
            } else if (!loadEns.getPassword().equals(HashageUtil.sha256(enseignant.getPassword()))) {
                int nbrCnx = loadEns.getNbrCnx();

                if (nbrCnx < 3) {
                    System.out.println("loadEns.getNbrCnx == " + loadEns.getNbrCnx());
                    System.out.println(" This is Your Attempt number : " + nbrCnx);
                    loadEns.setNbrCnx(nbrCnx + 1);

                } else if (nbrCnx == 3) {  // If the User try more than 3 attempts

                    System.out.println(" This is Your Attempt number == " + nbrCnx);
                    loadEns.setBlocked(true); // Blocked User
                }

                edit(loadEns);
                return -3; // Wrong Password
            } else if (loadEns.isBlocked() == true) {
                SessionUtil.setAttribute("deviceIssue", loadEns);
                return -2; // Enseignant blocked
            } else {
                loadEns.setNbrCnx(0);

                System.out.println("============= START Create Device ==============");
                Device device = DeviceUtil.getDevice(); // Create Device
                int res = deviceFacade.checkDevice(loadEns, device); // Check if Device is already Exists
                System.out.println("res for Device === " + res);
                if (res < 0) {
                    // sendEmailRed(user, device);
                    System.out.println("==== res = -6 === ");
                    System.out.println("Device === " + device);
                    System.out.println("Device Browser === " + device.getBrowser());
                    System.out.println("Device Category === " + device.getDeviceCategorie());
                    System.out.println("Device Operating System === " + device.getOperatingSystem());
                    //sendEmailUrgent(loadEns); // added 16/05/2017 
                    SessionUtil.setAttribute("deviceIssue", loadEns);
                    return -6; // this device is not included
                }
                System.out.println("=============  END Create Device ==============");
                SessionUtil.registerUser(loadEns);
                edit(loadEns);

                return 1;
            }
        }
    }
    
    public boolean isNotPass()
    {
        Enseignant enseignant = (Enseignant) SessionUtil.getConnectedUser();
        if ( enseignant != null )
        {
            return enseignant.isMdpChanged();
        }
        return false;
    }

    /*
    Methode Sign in
    Author : Youssef Benihoud
    Date : 9/5/2017
     */
    /**
     * Methode : Clonning Enseignant <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 10/05/2017 <br/>
     *
     * @param enseignant <br/>
     * @return Cloned Enseignant <br/>
     */
    public Enseignant cloneEns(Enseignant enseignant) {
        Enseignant clone = new Enseignant();
        clone.setAdmine(enseignant.isAdmine());
        clone.setBlocked(enseignant.isBlocked());
        clone.setCin(enseignant.getCin());
        clone.setDateNaissance(enseignant.getDateNaissance());
        clone.setDevices(enseignant.getDevices());
        clone.setEmail(enseignant.getEmail());
        clone.setFiliere(enseignant.getFiliere());
        clone.setGender(enseignant.getGender());
        clone.setMdpChanged(enseignant.isMdpChanged());
        clone.setModules(enseignant.getModules());
        clone.setNbrCnx(enseignant.getNbrCnx());
        clone.setNom(enseignant.getNom());
        clone.setPassword(enseignant.getPassword());
        clone.setPrenom(enseignant.getPrenom());
        clone.setTelephone(enseignant.getTelephone());
        return clone;
    }

    /*
    Methode : Clonning Enseignant
    Author : Youssef Benihoud
    Date : 10/05/2017
     */
 /*
    TesT : Edit
     */
    public void editPass(String enseignant) {
        Enseignant test = find(enseignant);

        if (test != null) {
            test.setPassword(HashageUtil.sha256("123456"));
            edit(test);
        }
    }

    /*
    TesT : Edit
     */
    /**
     * Methode : Find Enseignant By CIN or Email <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 15/05/2017 <br/>
     * works
     *
     * @param key <br/>
     * @return Enseignant
     */
    public Enseignant findByCneOrEmail(String key) {
        try {
            // Etudiant res = new Etudiant();
            String req = "SELECT e FROM Enseignant e WHERE 1=0";
            if (!key.matches("\\d+")) {
                req += SearchUtil.orConstraint("e", "cin", "=", key);
            }

            req += SearchUtil.orConstraintContains("e", "email", " LIKE ", key);

            Enseignant enseignant = (Enseignant) em.createQuery(req).getSingleResult();

            if (enseignant != null) {

                return enseignant;
            }

        } catch (Exception e) {
            System.out.println("Makaynch had Enseignant");
            System.out.println(e.getMessage());

        }
        return null;
    }

    /**
     * Methode : Email sent for Urgent <br/>
     * Author : Youssef Benihoud  <br/>
     * Date : 14/05/2017 <br/>
     * works
     *
     * @param enseignant <br/>
     * @return Boolean <br/>
     */
    public boolean sendEmailUrgent(Enseignant enseignant) {

        System.out.println("==== Urgent Email ====");
        String subject = "";
        String message = "";
        Enseignant load = find(enseignant.getCin());

        if (load != null) {
            subject = "Someone else is Connected from Your Account";
            message = "Hello Mr/Ms " + load.getNom() + "<br/>";
            message += "Be Aware that someone else is connected from your account <br/> ";
            message += " You have to change your password AS SOON AS POSSIBLE <br/>";
            message += " Have a Good Day, FST Team <br/>";
            try {
                boolean res = EmailUtil.sendMail(message, load.getEmail(), subject);
            } catch (MessagingException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return false;
    }

    /**
     * Methode : Send Email After Changing Password <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 14/05/2017 <br/>
     * works
     *
     * @param key
     * @return
     */
    public boolean sendEmailPassword(String key) {

        System.out.println("=== SendEmail Password Methode ===");
        Enseignant load = findByCneOrEmail(key);
        String pass = getGeneratePass();
        boolean res = false;
        if (load != null) {
            load.setPassword(HashageUtil.sha256(pass));
            load.setMdpChanged(true);
            edit(load);
            String subject = "Your Password is Changed ";
            String message = "Hello Mr/Ms " + load.getNom() + "<br/>";
            message += " You forgot your password, then we generate a new Password for you <br/> ";
            message += " Your New Password : " + pass + " <br/> ";
            message += " You have to change your password AS SOON AS POSSIBLE <br/>";
            message += " Have a Good Day, FST Team <br/>";
            try {
                res = EmailUtil.sendMail(message, load.getEmail(), subject);
            } catch (MessagingException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return res;
    }

    /**
     * Methode : Search Enseignant <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 14/05/2017 <br/>
     * works
     *
     * @param enseignant <br/>
     * @return List<Enseignant> <br/>
     */
    public List<Enseignant> searchEns(Enseignant enseignant) {
        String req = "SELECT ed FROM Enseignant ed WHERE 1=1 ";
        if (enseignant != null) {
            if (enseignant.getCin() != null) {
                System.out.println("enseignant.getCne() == '" + enseignant.getCin() + "' ");
                req += "AND ed.cin ='" + enseignant.getCin() + "' ";
            }
            if (!enseignant.getNom().equals("")) {
                req += "AND ed.nom LIKE '%" + enseignant.getNom() + "%' ";
            }
            if (!enseignant.getPrenom().equals("")) {
                req += "AND ed.nom LIKE '%" + enseignant.getPrenom() + "%' ";
            }
            if (!enseignant.getEmail().equals("")) {
                req += "AND ed.email LIKE '%" + enseignant.getEmail() + "%' ";
            }
            if (enseignant.getGender() != null) {
                req += "AND ed.email LIKE '%" + enseignant.getGender() + "%' ";
            }
            if (enseignant.getDateNaissance() != null) {
                req += SearchUtil.addConstraintDate("ed", "dateNaissance", "=", enseignant.getDateNaissance());
            }
        }
        return em.createQuery(req).getResultList();
    }

    /**
     * Methode : to Change Password into the Wanted Password for User <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 15/05/2017 <br/>
     * works
     *
     * @param enseignant
     * @param changedPass
     * @return 1 : success <br/>
     * @return -1 : User is null <br/>
     * @return -2 : String changedPass is null <br/>
     */
    public int changePassword(Enseignant enseignant, String changedPass) {
        System.out.println("=== Start Change Password ===");
        Enseignant load = find(enseignant.getCin());

        if (load != null) {
            if (!changedPass.equals("")) {
                load.setPassword(HashageUtil.sha256(changedPass));
                edit(load);
                return 1;
            }
            return -2;
        }
        System.out.println("=== End Change Password ===");

        return -1;
    }

    /**
     * Methode : verify if the connection was the first connection or not By
     * Checking its Password <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 16/05/2017 <br/>
     * works
     *
     * @param enseignant <br/>
     * @return Boolean <br/>
     */
    public boolean firstConnection(Enseignant enseignant) {
        Enseignant test = find(enseignant.getCin());
        String dateInscription = SearchUtil.convertForPw(test.getDateNaissance());
        System.out.println("Date To String === " + dateInscription);
        if (test.getPassword().equals(HashageUtil.sha256(dateInscription))) {
            return true;
        }

        return false;
    }

    /**
     * Methode : Check if Enseignant has already an Email or not <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 16/05/2017 <br/>
     * Works
     *
     * @param cne
     * @return True Or False
     */
    public boolean hasEmail(String cne) {
        System.out.println("=== Has Email Facade Enseignnat ===");
        Enseignant enseignant = find(cne);
        System.out.println("enseignant === " + enseignant);
        if (enseignant != null) {
            if (enseignant.getEmail() != null) {
                if (!enseignant.getEmail().equals("")) {
                    System.out.println("getEmail === " + enseignant.getEmail());
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Methode : Check if Enseignant has already a List<\Secures\> or not <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 16/05/2017 <br/>
     * Works
     *
     * @param cin
     * @return True Or False
     */
    public boolean hasSecure(String cin) {
        System.out.println("=== Has Secure Facade Enseignnat ===");
        Enseignant enseignant = find(cin);
        System.out.println("enseignant === " + enseignant);
        if (enseignant != null) {
            if (enseignant.getSecures() != null) {
                if (!enseignant.getSecures().isEmpty()) {
                    System.out.println("getSecures === " + enseignant.getSecures());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Methode : changeData : the fact that User change its own data <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 16/05/2017 <br/>
     *
     * Works
     *
     * @param connected
     * @param selected
     */
    public void changeData(Enseignant connected, Enseignant selected) {
        System.out.println("=== ChangeData ===");
        Enseignant test = find(connected.getCin());
        System.out.println("test == " + test);
        if (test != null) {
            System.out.println("selected.getPassword() == " + selected.getPassword());
            test.setPassword(HashageUtil.sha256(selected.getPassword()));
            test.setMdpChanged(false);
            if (selected.getEmail() != null) {
                if (!dejaEmail(selected.getEmail())) {
                    test.setEmail(selected.getEmail());
                } else {
                    test.setMdpChanged(true);
                }
            }
            edit(test);
        }

        System.out.println("=== ChangeData ===");

    }

    /**
     * Methode : Check if Email is already exist in DataBase <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 20/05/2017 <br/>
     *
     * @param email <br/>
     * @return Boolean
     */
    public boolean dejaEmail(String email) {
        System.out.println("**** CheckEmail Enseignant ****");
        Enseignant enseignant = findByCneOrEmail(email);
        if (enseignant != null) {
            return true;
        }
        return false;
    }
    
    
    public Enseignant findSingleEnsByFullName(Enseignant enseignant)
    {
        return (Enseignant) em.createQuery("SELECT e FROM Enseignant e WHERE e.nom ='"+enseignant.getNom()+"'").getSingleResult();
    }

    
    
     /*Booleans Methods || Permissions */
    
    public boolean isEnsAdmin()
    {
        Enseignant enseignant = (Enseignant) SessionUtil.getConnectedUser();
        return enseignant.isAdmine();
    }
    
    /*Booleans Methods || Permissions */
}
