/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.CertificatScolaire;
import bean.Device;
import bean.Enseignant;
import bean.Etudiant;
import bean.NoteSemestre;
import bean.Semestre;
import controller.util.DeviceUtil;
import controller.util.EmailUtil;
import controller.util.HashageUtil;
import controller.util.PdfUtil;
import controller.util.SearchUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import controller.util.SessionUtil;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Abed
 */
@Stateless
public class EtudiantFacade extends AbstractFacade<Etudiant> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @EJB
    private DeviceFacade deviceFacade;
    @EJB
    private EnseignantFacade enseignantFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtudiantFacade() {
        super(Etudiant.class);
    }

    /**
     * Methode : find Etudiant ( Without Exception ) <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 10/05/2017 <br/>
     *
     * @param id
     * @return etudiant, if it's found in DataBase <br/>
     * @return null, it it's not found in DataBase <br/>
     */
    @Override
    public Etudiant find(Object id) {
        try {
            Etudiant etudiant = (Etudiant) em.createQuery("select e from Etudiant e where e.cne = '" + id + "'").getSingleResult();
            if (etudiant != null) {
                return etudiant;
            }
        } catch (Exception e) {
            System.out.println("Makaynch had Enseignant");

        }
        return null;
    }

    public Enseignant findEns(Etudiant etudiant) {
        try {
            System.out.println("flier" + etudiant.getFiliere().getLibelle());
            System.out.println("Ensei" + etudiant.getFiliere().getResponsableFiliere());
            return (Enseignant) em.createQuery("SELECT e from Enseignant e WHERE e.cin='" + etudiant.getFiliere().getResponsableFiliere().getCin() + "'").getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Methode Sign in Etudiant <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 9/5/2017 <br/>
     *
     * @param etudiant
     * @return -5 : The Param is Null <br/>
     * @return -4 : The Param is not found in DataBase <br/>
     * @return -3 : The Password is wrong <br/>
     * @return -2 : The Param is blocked <br/>
     * @return -6 : The Device's Param has Error <br/>
     * @return 1 : Connected With Success <br/>
     */
    public int signIn(Etudiant etudiant) {
        System.out.println("========= Sign In ===========");

        if (etudiant == null || etudiant.getCne() == null) {
            return -5; // You must type your login
        } else {

            Etudiant loadEtud = find(etudiant.getCne());
            System.out.println("loadEtud === " + loadEtud);

            if (loadEtud == null) {
                return -4; // there is no User here
            } else if (!loadEtud.getPassword().equals(HashageUtil.sha256(etudiant.getPassword()))) {
                int nbrCnx = loadEtud.getNbrCnx();

                if (nbrCnx < 3) {
                    System.out.println("loadEtud.getNbrCnx == " + loadEtud.getNbrCnx());
                    System.out.println(" This is Your Attempt number : " + nbrCnx);
                    loadEtud.setNbrCnx(nbrCnx + 1);

                } else if (nbrCnx == 3) {  // If the User try more than 3 attempts

                    System.out.println(" This is Your Attempt number == " + nbrCnx);
                    
                    if(!firstConnection(loadEtud))
                    {
                    loadEtud.setBlocked(true); // Blocked User
                    }
                }
                edit(loadEtud);
                return -3; // Wrong Password
            } else if (loadEtud.isBlocked()) {
                SessionUtil.setAttribute("deviceIssue", loadEtud);
                return -2; // Etudiant blocked
            } else {
                loadEtud.setNbrCnx(0);

                System.out.println("============= START Create Device ==============");
                Device device = DeviceUtil.getDevice(); // Create Device
                int res = deviceFacade.checkDevice(loadEtud, device); // Check if Device is already Exists
                System.out.println("res for Device === " + res);
                if (res < 0) {
                    // sendEmailRed(user, device);
                    System.out.println("==== res = -6 === ");
                    System.out.println("Device === " + device);
                    System.out.println("Device Browser === " + device.getBrowser());
                    System.out.println("Device Category === " + device.getDeviceCategorie());
                    System.out.println("Device Operating System === " + device.getOperatingSystem());
                    //sendEmailUrgent(loadEtud); // added 16/05/2017
                    SessionUtil.setAttribute("deviceIssue", loadEtud);
                    return -6; // this device is not included
                }
                System.out.println("=============  END Create Device ==============");
                SessionUtil.registerUser(loadEtud);
                edit(loadEtud);
                return 1; // Succes Connection

            }
        }
    }

    /*
    Methode Sign in
    Author : Youssef 
    Date : 09/05/2017
     */
    /**
     *
     * Methode : Clonning <br/> Author : Youssef Benihoud <br/> Date :
     * 10/05/2017 <br/>
     * modified 20/05/2017 <br/>
     *
     * @param etudiant
     * @return cloned Etudiant
     */
    public Etudiant cloneEtud(Etudiant etudiant) {
        Etudiant clone = new Etudiant();
        clone.setBlocked(etudiant.isBlocked());
        clone.setCne(etudiant.getCne());
        clone.setDateNaissance(etudiant.getDateNaissance());
        clone.setDevices(etudiant.getDevices());
        clone.setEmail(etudiant.getEmail());
        clone.setSemestreActuel(etudiant.getSemestreActuel()); // added 20/05/2017
        clone.setFiliere(etudiant.getFiliere());
        clone.setGender(etudiant.getGender());
        clone.setMdpChanged(etudiant.isMdpChanged());
        clone.setNbrCnx(etudiant.getNbrCnx());
        clone.setNom(etudiant.getNom());
        clone.setPassword(etudiant.getPassword());
        clone.setPrenom(etudiant.getPrenom());
        return clone;
    }

    /*
    Methode : Clonning 
    Author : Youssef Benihoud
    Date : 10/05/2017
     */
 /*TEST*/
    public void editPass(Long etudiant) {
        Etudiant test = find(etudiant);

        if (test != null) {
            test.setPassword(HashageUtil.sha256("123456"));
            edit(test);
        }
    }

    /*TEST*/
    /**
     * Methode : Email sent for Urgent <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 14/05/2017 <br/>
     * works
     *
     * @param etudiant
     * @return Boolean
     */
    public boolean sendEmailUrgent(Etudiant etudiant) {

        System.out.println("==== Urgent Email ====");
        String subject = "";
        String message = "";
        Etudiant load = find(etudiant.getCne());

        if (load != null) {
            subject = "Someone else is Connected from Your Account";
            message = "Hello Mr/Ms " + load.getNom() + "<br/>";
            message += "Be Aware that someone else is connected from your account <br/> ";
            message += " You have to change your password AS SOON AS POSSIBLE <br/>";
            message += " Have a Good Day, FST Team <br/>";
            try {
                boolean res = EmailUtil.sendMail(message, load.getEmail(), subject);
            } catch (MessagingException ex) {
                Logger.getLogger(EtudiantFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * Methode : Send Email After Changing Password <br/> Author : Youssef
     * Benihoud <br/>
     * <br/>
     * Date : 14/05/2017 <br/>
     * works
     *
     * @param key <br/>
     * @return Boolean
     */
    public boolean sendEmailPassword(String key) {

        System.out.println("=== SendEmail Password Methode ===");
        Etudiant load = findByCneOrEmail(key);
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
     * Methode : Find Etudiant By Email or CNE  <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 15/05/2017 <br/>
     * works
     *
     * @param key
     * @return
     */
    public Etudiant findByCneOrEmail(String key) {
        try {
            // Etudiant res = new Etudiant();
            String req = "SELECT e FROM Etudiant e WHERE 1=0";
            if (key.matches("\\d+")) {
                req += SearchUtil.orConstraint("e", "cne", "=", key);
            } else {

                req += SearchUtil.orConstraintContains("e", "email", " LIKE ", key);
            }
            Etudiant etudiant = (Etudiant) em.createQuery(req).getSingleResult();

            if (etudiant != null) {

                return etudiant;
            }

        } catch (Exception e) {
            System.out.println("Makaynch had Etudiant");
            System.out.println(e.getMessage());

        }
        return null;
    }

    /**
     * Methode : Search Etudiant <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 14/05/2017 <br/>
     * works
     *
     * @param etudiant <br/>
     * @return List<Etudiant> <br/>
     */
    public List<Etudiant> searchEtud(Etudiant etudiant) {
        String req = "SELECT ed FROM Etudiant ed WHERE 1=1 ";
        if (etudiant != null) {
            if (etudiant.getCne() != null) {
                System.out.println("etudiant.getCne() == '" + etudiant.getCne() + "' ");
                req += "AND ed.cne ='" + etudiant.getCne() + "' ";
            }
            if (!etudiant.getNom().equals("")) {
                req += "AND ed.nom LIKE '%" + etudiant.getNom() + "%' ";
            }
            if (!etudiant.getPrenom().equals("")) {
                req += "AND ed.nom LIKE '%" + etudiant.getPrenom() + "%' ";
            }
            if (!etudiant.getEmail().equals("")) {
                req += "AND ed.email LIKE '%" + etudiant.getEmail() + "%' ";
            }
            if (etudiant.getGender() != null) {
                req += "AND ed.email LIKE '%" + etudiant.getGender() + "%' ";
            }
            if (etudiant.getDateNaissance() != null) {
                req += SearchUtil.addConstraintDate("ed", "dateNaissance", "LIKE", etudiant.getDateNaissance());
            }
        }
        return em.createQuery(req).getResultList();
    }

    /**
     * Methode : Search Etudiant By Filiere <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 19/05/2017 <br/>
     * doesn't work
     *
     * @param etudiant <br/>
     * @return List<Etudiant> <br/>
     */
    public List<Etudiant> searchEtudFiliere(Etudiant etudiant) {
        System.out.println("==== Start Search Etud Filiere ====");
        Enseignant test = (Enseignant) SessionUtil.getConnectedUser();
        System.out.println("test Ens === " + test);
        Enseignant enseignant = enseignantFacade.find(test.getCin());
        System.out.println("Enseignant === " + enseignant);

        String req = "SELECT ed FROM Etudiant ed WHERE 1=1 ";

        if (etudiant.getFiliere() != null) {
            System.out.println("*** getFiliere != null ");
            req += "AND ed.filiere.id ='" + etudiant.getFiliere().getId() + "' ";
        }
        if (etudiant.getFiliere() == null) {
            System.out.println("**** getFiliere == null");
            req += "AND ed.filiere.id ='" + enseignant.getFiliere().getId() + "' ";
        }
        if (etudiant.getCne() != null) {
            System.out.println("*** etudiant.getCne() == '" + etudiant.getCne() + "' ");
            req += "AND ed.cne ='" + etudiant.getCne() + "' ";
        }
        if (!etudiant.getNom().equals("")) {
            System.out.println("*** getNom() === " + etudiant.getNom());
            req += "AND ed.nom LIKE '%" + etudiant.getNom() + "%' ";
        }
        if (!etudiant.getPrenom().equals("")) {
            System.out.println("*** getPrenom() === " + etudiant.getPrenom());
            req += "AND ed.prenom LIKE '%" + etudiant.getPrenom() + "%' ";
        }
        if (!etudiant.getEmail().equals("")) {
            System.out.println("*** getEmail() === " + etudiant.getEmail());
            req += "AND ed.email LIKE '%" + etudiant.getEmail() + "%' ";
        }
        if (etudiant.getGender() != null) {
            System.out.println("*** Gender === " + etudiant.getGender());
            req += "AND ed.gender LIKE '%" + etudiant.getGender() + "%' ";
        }
        if (etudiant.getSemestreActuel() != null) {
            System.out.println("*** Etudiant Semestre Actuel === " + etudiant.getSemestreActuel().getId());
            req += " AND ed.semestreActuel.id ='" + etudiant.getSemestreActuel().getId() + "' ";
        }
//        if (etudiant.getSemestreActuel() == null) {
//            System.out.println("Semestre is Null !!");
//            req += " AND ed.semestreActuel.id ='" + 1L + "' ";
//        }
        if (etudiant.getDateNaissance() != null) {
            req += SearchUtil.addConstraintDate("ed", "dateNaissance", "LIKE", etudiant.getDateNaissance());
        }

        System.out.println("res search ==" + em.createQuery(req).getResultList());
        return em.createQuery(req).getResultList();
    }

    /**
     * Methode : to Change Password into the Wanted Password for User <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 15/05/2017 <br/>
     * works
     *
     * @param etudiant
     * @param changedPass
     * @return 1 : success <br/>
     * @return -1 : User is null <br/>
     * @return -2 : String changedPass is null <br/>
     */
    public int changePassword(Etudiant etudiant, String changedPass) {
        System.out.println("=== Change Password ===");
        Etudiant load = find(etudiant.getCne());

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
     * @param etudiant <br/>
     * @return Boolean <br/>
     */
    public boolean firstConnection(Etudiant etudiant) {
        Etudiant test = find(etudiant.getCne());
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
     * Works modification : Check Email if it is already exist in DataBase
     *
     * @param cne
     * @return True Or False
     */
    public boolean hasEmail(Long cne) {
        System.out.println("=== Has Email Facade Etudiant ===");
        Etudiant etudiant = find(cne);
        System.out.println("etudiant === " + etudiant);
        if (etudiant != null) {
            if (etudiant.getEmail() != null) {
                if (!etudiant.getEmail().equals("")) {
                    System.out.println("getEmail === " + etudiant.getEmail());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Methode : Check if Enseignant has already an Questions or not <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 16/05/2017 <br/>
     * Works modification : Check Email if it is already exist in DataBase
     *
     * @param cne
     * @return True Or False
     */
    public boolean hasSecure(Long cne) {
        System.out.println("=== Has Secure Facade Etudiant ===");
        Etudiant etudiant = find(cne);
        System.out.println("etudiant === " + etudiant);
        if (etudiant != null) {
            if (etudiant.getSecures() != null) {
                if (!etudiant.getSecures().isEmpty()) {
                    System.out.println("getSecures === " + etudiant.getSecures());
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isNotPass()
    {
        Etudiant etudiant = (Etudiant) SessionUtil.getConnectedUser();
        if ( etudiant != null )
        {
            return etudiant.isMdpChanged();
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
    public void changeData(Etudiant connected, Etudiant selected) {
        System.out.println("=== ChangeData ===");
        Etudiant test = find(connected.getCne());
        System.out.println("test == " + test);
        if (test != null) {
            test.setPassword(HashageUtil.sha256(selected.getPassword()));
            test.setMdpChanged(false);
            if (selected.getEmail() != null) {
                if (!dejaEmail(selected.getEmail())) {
                    if (!selected.getEmail().equals("")) {
                        test.setEmail(selected.getEmail());
                    } else {
                        test.setMdpChanged(true);
                    }
                } else {
                    test.setMdpChanged(true);
                }
            }
            edit(test);
        }

        System.out.println("=== ChangeData ===");

    }

    /**
     * Methode : find Etudiant by Filiere <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 18/05/2017 <br/>
     * 0% <br/>
     *
     * @param id of Filiere <br/>
     * @return List of Etudiants <br/>
     */
    public List<Etudiant> findByFiliere(Long id) {
        System.out.println("==== Start find Etudiant By Filiere ====");
        try {
            System.out.println("==== End find Etudiant By Filiere ====");
            return em.createQuery("SELECT e FROM Etudiant e WHERE e.filiere.id ='" + id + "'").getResultList();
        } catch (Exception e) {
            System.out.println("Exception on find By FIlier === " + e.getMessage());
            return new ArrayList<>();
        }

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
        System.out.println("**** CheckEmail Etudiant ****");
        Etudiant etudiant = findByCneOrEmail(email);
        if (etudiant != null) {
            return true;
        }
        return false;
    }

    public Double noteDeug(Etudiant etudiant)
    {
        if ( etudiant != null )
        {
            List<NoteSemestre> noteSemestres = etudiant.getNoteSemestre();
            if ( noteSemestres.size() <= 4 )
                if(!noteSemestres.isEmpty())
            {
                Double note = 0.0;
                for (NoteSemestre itemNote : noteSemestres)
                {
                    note += itemNote.getNote();
                }
                note = (note)/noteSemestres.size();
                return note;
            }
        }
        return 0.0;
    }
    
    
    public void generateCertificatScolairePdf(Etudiant etudiant) throws JRException, IOException
    {
        Map<String,Object> params = new HashMap();

        if ( etudiant != null )
        {
            CertificatScolaire cs = new CertificatScolaire();
            cs.setCne(etudiant.getCne()+"");
            cs.setBirthDate(etudiant.getDateNaissance());
            cs.setNom(etudiant.getNom()+" "+etudiant.getPrenom());
            
            params.put("cne", cs.getCne());
            params.put("id", cs.getId(etudiant.getFiliere()));
            params.put("birthDate", cs.getBirthDate());
            params.put("birthPlace", cs.getBirthPlace());
            params.put("filiere", cs.getFiliere(etudiant.getFiliere()));
            params.put("anneeUni", cs.getAnneeUni());
            params.put("nowDate", cs.getNowDate());
            params.put("nom", cs.getNom());
            params.put("leNum",cs.getLeNum(etudiant.getSemestreActuel()));
            
            if(false)
            {
               // http://imgur.com/download/HeKBBN1
               URL url = new URL("http://imgur.com/download/HeKBBN1");
             BufferedImage image = ImageIO.read(url);
                //System.out.println("image Url : "+image.getSource().toString());
               params.put("cache",image);
                
               // ?faces-redirect=true
              
               
            }
            
            System.out.println(params);
        }
        PdfUtil.generatePdf(new ArrayList<>(), params, "certificatScolaire_"+etudiant.getNom(), "/jasper/certificatScolaire.jasper");
    }
    
    
    
    public void printAttestationPdf(Etudiant etudiant, Semestre semestre) throws JRException, IOException {
        Map<String, Object> params = new HashMap();
        if ( etudiant != null )
        {
            CertificatScolaire cs = new CertificatScolaire();
            cs.setCne(etudiant.getCne()+"");
            cs.setBirthDate(etudiant.getDateNaissance());
            cs.setNom(etudiant.getNom()+" "+etudiant.getPrenom());
            
            params.put("cne", "CNE/CNIE : "+cs.getCne());
            params.put("cin", "CIN : "+etudiant.getCne());
            params.put("nom", cs.getNom());
            params.put("attestation", "Attestation N° : "+cs.getId(etudiant.getFiliere()));
            params.put("naissance", "Né le : "+cs.getBirthDate()+"      a       "+cs.getBirthPlace());
            params.put("specialite", "Spécialite : "+etudiant.getFiliere().getLibelle()+" ( "+etudiant.getFiliere().getAbreviation()+" )" );
            params.put("diplome", "Diplome d'Etudes Universitaires Sciences et Techniques ( DEUST )" );
            params.put("anneeUniversitaire", "Annee Universitaire : "+cs.getAnneeUni());
            params.put("faite", "faite a Marrakech, le "+cs.getNowDate());
            
            
            //TODO
            params.put("jury","devant la jury d'examen réuni le : ");
            params.put("moyenne", "Moyenne :  ");
            params.put("mention", "Mention :  ");
            params.put("duree", "Durée de la Validation : ");
        }
        System.out.println(params);
        String title = "ATTEST" + etudiant.getCne() + "-" + LocalDate.now();
        PdfUtil.generatePdf(null, params, title, "/jasper/attestationDeust.jasper");
    }
    
}
