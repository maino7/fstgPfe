/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author BENIHOUD Youssef
 */
public class CertificatScolaire {
    
    private static Long cmp = 1L;
    private String id;
    private String cne;
    private String nom;
    private Date birthDate;
    private Date nowDate;
    private String anneeUni;
    private String birthPlace;
    private String filiere;
    private String leNum;

    public CertificatScolaire() {
        
    }

    public CertificatScolaire(String id, String cne, String leNum, String nom, Date birthDate, Date nowDate, String anneeUni, String birthPlace, String filiere) {
        this.id = id;
        this.cne = cne;
        this.leNum = leNum;
        this.nom = nom;
        this.birthDate = birthDate;
        this.nowDate = nowDate;
        this.anneeUni = anneeUni;
        this.birthPlace = birthPlace;
        this.filiere = filiere;
    }
    
    public String getHadik()
    {
        Date anneeUniDate = new Date();
        System.out.println(anneeUniDate);
        
        String anneeUniString ="";
        if ( anneeUniDate.getMonth() >= 0 && anneeUniDate.getMonth() <= 5)
        {
            anneeUniString = (anneeUniDate.getYear()-100-1)+"/"+(anneeUniDate.getYear()-100);
        }
        else {
            anneeUniString = (anneeUniDate.getYear()-100)+"/"+(anneeUniDate.getYear()-100+1);
        }
        System.out.println("Hadik : "+anneeUniString);
        
        return anneeUniString;
    }
    
    
    

    public String getLeNum(Semestre sem) {
        if (sem.getLibelle() == 1)
        {
            leNum = "le 1er ";
        }
        else
        {
            leNum = "le "+sem.getLibelle()+"eme";
        }
        return leNum;
    }

    public void setLeNum(String leNum) {
        this.leNum = leNum;
    }
    
    
    
    
   

    public String getId(Filiere fil) {
        cmp = cmp + 1L;
        id = cmp+"/"+fil.getAbreviation()+"/"+getHadik().replace("/", "-");
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    

    public static Long getCmp() {
        return cmp;
    }

    public static void setCmp(Long cmp) {
        CertificatScolaire.cmp = cmp;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBirthDate() {
        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        String birthDateString = df.format(birthDate);
        return birthDateString;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNowDate() {
        DateFormat df = new SimpleDateFormat("dd/MM/yy");


        String nowDateString = df.format(new Date());
        return nowDateString;
    }

    public void setNowDate(Date nowDate) {
        this.nowDate = nowDate;
    }

    public String getAnneeUni() {
        
        Date anneeUniDate = new Date();
        System.out.println(anneeUniDate);
        
        String anneeUniString ="";
        if ( anneeUniDate.getMonth() >= 0 && anneeUniDate.getMonth() <= 5)
        {
            anneeUniString = (anneeUniDate.getYear()+1899)+"/"+(anneeUniDate.getYear()+1900);
        }
        else {
            anneeUniString = (anneeUniDate.getYear()+1900)+"/"+(anneeUniDate.getYear()+1901);
        }
        System.out.println("Annee Universitaire : "+anneeUniString);
        
        
        return anneeUniString;
    }

    public void setAnneeUni(String anneeUni) {
        this.anneeUni = anneeUni;
    }

    public String getBirthPlace() {
        if ( birthPlace == null )
        {
            birthPlace = "Marrakech";
        }
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getFiliere(Filiere fil) {
        
        if(fil.getAbreviation().equals("MIPC") || fil.getAbreviation().equals("MIP") || fil.getAbreviation().equals("BCG"))
        {
            filiere = "Tronc Commun du parcours "+fil.getLibelle();
        }
        else {
            filiere = "Liscence du parcours "+fil.getLibelle();
        }
        
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }
    
    
    
}
