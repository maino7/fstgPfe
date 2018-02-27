/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Annee;
import bean.Etudiant;
import bean.Module;
import bean.NoteModulaire;
import bean.NoteSemestre;
import bean.Semestre;
import controller.util.DateUtil;
import controller.util.PdfUtil;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author xXx-BlackAngel-xXx
 */
@Stateless
public class NoteModulaireFacade extends AbstractFacade<NoteModulaire> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NoteModulaireFacade() {
        super(NoteModulaire.class);
    }

    /**
     * Methode : find NoteModulaire by Etudiant and Semestre <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 23/05/2017 <br/>
     *
     * @param ed
     * @param sem
     */
    public List<NoteModulaire> findByEtudiantAndSemestre(Etudiant etudiant, Semestre semestre) {
        System.out.println("===== Facade find By Etudiant And Semestre =====");
        System.out.println("*** etud facade === " + etudiant.getCne());
        System.out.println("*** semestre facade === " + semestre.getLibelle());
        List<NoteModulaire> items;
        List<NoteModulaire> res = new ArrayList<>();

        items = em.createQuery("SELECT nm FROM NoteModulaire nm WHERE nm.module.semestre.libelle ='" + semestre.getLibelle() + "' AND nm.etudiant.cne=" + etudiant.getCne() + "").getResultList();
        //    items = findNoteModulaireBySemestre(semestre);

        /* for (NoteModulaire item : items) {
            
            if(item.getEtudiant().getCne() == etudiant.getCne())
            {
                System.out.println(" *** Add ***");
                res.add(item);
            }
            
        }*/
        return items;

    }

    public List<NoteModulaire> findNoteModule(Etudiant etudiant, Module module) {
        System.out.println("SELECT nm from NoteModulaire nm WHERE");
        System.out.println("nm.module.id='" + module.getId() + "' AND nm.etudiant.cne='" + etudiant.getCne() + "' AND nm.etudiant.filiere.id ='" + etudiant.getFiliere().getId() + "'");
        return em.createQuery("SELECT nm from NoteModulaire nm where  nm.module.id='" + module.getId() + "' AND nm.etudiant.cne='" + etudiant.getCne() + "' AND nm.etudiant.filiere.id='" + etudiant.getFiliere().getId() + "'").getResultList();
    }

    public List<Module> modulsOfSemestreActuel(Etudiant etudiant) {
        return em.createQuery("SELECT md FROM Module md where md.filiere.id='" + etudiant.getFiliere().getId() + "' AND md.semestre.libelle='" + etudiant.getSemestreActuel().getLibelle() + "'").getResultList();
    }

    public List<Module> findModuls(Etudiant etudiant, Semestre semestreNonValide, int etat) {
        List<Module> s;
        if (etat == 0) {
            s = em.createQuery("SELECT md FROM Module md , NoteModulaire nm WHERE md.id=nm.module.id AND nm.etat='0'  AND nm.etudiant.cne='" + etudiant.getCne() + "' AND md.semestre.id='" + semestreNonValide.getId() + "'").getResultList();
            if (s == null) {
                s = new ArrayList<>();
            }
            return s;
        }
        if (etat == 1) {
            System.out.println("md.id=nm.module.id AND nm.etat='1'");

            return em.createQuery("SELECT md FROM Module md , NoteModulaire nm WHERE md.id=nm.module.id AND nm.etat='1'  AND nm.etudiant.cne='" + etudiant.getCne() + "' AND md.semestre.id='" + semestreNonValide.getId() + "'").getResultList();
        } else {
            System.out.println("AND nm.etudiant.cne=");

            return em.createQuery("SELECT md FROM Module md , NoteModulaire nm WHERE md.id=nm.module.id   AND nm.etudiant.cne='" + etudiant.getCne() + "' AND md.semestre.id='" + semestreNonValide.getId() + "'").getResultList();
        }
    }

    public List<NoteModulaire> findNoteModulaire(Etudiant etudiant, Semestre semestreNonValide, int etat) {
        List<NoteModulaire> s;
        if (etat == 0) {
            s = em.createQuery("SELECT nm FROM  NoteModulaire nm WHERE  nm.etat='0'  AND nm.etudiant.cne='" + etudiant.getCne() + "' nm.module.semestre.libelle='" + semestreNonValide.getLibelle() + "'").getResultList();
            if (s == null) {
                s = new ArrayList<>();
            }
            return s;
        }
        if (etat == 1) {
            System.out.println("md.id=nm.module.id AND nm.etat='1'");

            return em.createQuery("SELECT nm FROM  NoteModulaire nm WHERE   nm.etat='1'  AND nm.etudiant.cne='" + etudiant.getCne() + "' AND nm.module.semestre.libelle='" + semestreNonValide.getLibelle() + "'").getResultList();
        } else {
            System.out.println("AND nm.etudiant.cne=");

            return em.createQuery("SELECT nm FROM NoteModulaire nm WHERE   nm.etudiant.cne='" + etudiant.getCne() + "' AND nm.module.semestre.libelle='" + semestreNonValide.getLibelle() + "'").getResultList();
        }
    }

    public List<NoteModulaire> findNoteModulaire(Etudiant etudiant, int semestreNonValide) {

        System.out.println("AND nm.etudiant.cne=");

        return em.createQuery("SELECT nm FROM NoteModulaire nm WHERE   nm.etudiant.cne='" + etudiant.getCne() + "' AND nm.module.semestre.libelle='" + semestreNonValide + "'").getResultList();

    }

    //Abed's
    public NoteModulaire findNMByModuleAndEtudiant(NoteModulaire noteModulaire) {
        try {
            return (NoteModulaire) em.createQuery("SELECT nm FROM NoteModulaire nm WHERE  nm.etudiant.cne='" + noteModulaire.getEtudiant().getCne() + "' AND nm.module.id='" + noteModulaire.getModule().getId() + "'").getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void printPdf(Etudiant etudiant, Semestre semestre) throws JRException, IOException {
        Map<String, Object> params = new HashMap();
        params.put("cne", etudiant.getCne());
        params.put("nom", etudiant.getNom());
        params.put("prenom", etudiant.getPrenom());
        params.put("semestre", "Semestre "+semestre.getLibelle());
        params.put("libelle", etudiant.getFiliere().getLibelle());
        String annee = semestre.getAnnee().getLibelle()+"/"+Integer.toString(semestre.getAnnee().getLibelle() + 1);
        params.put("annee", annee );
        params.put("an", null);
        params.put("noteSemestre", findBySemestre(etudiant, semestre).getNote());
        params.put("observation", findBySemestre(etudiant, semestre).getMention());
        params.put("dateSystem", DateUtil.format(new Date()));
        System.out.println(params);
        String title = "RN" + etudiant.getCne() + "-" + semestre.toString() + LocalDate.now();
        PdfUtil.generatePdf(noteEtu(etudiant, semestre), params, title, "/jasper/noteEtudiant.jasper");
    }

    public List<NoteModulaire> notes(Etudiant etudiant) {
        return em.createQuery("SELECT n FROM NoteModulaire n Where n.etudiant.cne = " + etudiant.getCne()).getResultList();
    }

    public NoteSemestre findBySemestre(Etudiant etudiant, Semestre semestre) {
        return (NoteSemestre) em.createQuery("SELECT n FROM NoteSemestre n WHERE n.semestre.id ='" + semestre.getId() + "' AND n.etudiant.cne='" + etudiant.getCne() + "'").getSingleResult();
    }

    public List<NoteModulaire> noteEtu(Etudiant etudiant, Semestre semestre) {
        System.out.println("zhor === noteEtu ===");
        List<NoteModulaire> notes = notes(etudiant);
        List<NoteModulaire> noteEtudiant = new ArrayList<>();
        for (NoteModulaire note : notes) {
            if (note.getModule().getSemestre().getId() == semestre.getId()) {
                System.out.println("note.note");
                noteEtudiant.add(note);
            }
        }
        return noteEtudiant;
    }

    public List<NoteModulaire> nonValide(Etudiant etudiant, Semestre semestre) {
        List<NoteModulaire> notes = notes(etudiant);
        List<NoteModulaire> modules = new ArrayList();
        for (NoteModulaire note : notes) {
            if (note.getModule().getSemestre().getId() == semestre.getId() && note.getNote() < 10) {
                modules.add(note);
            }
        }
        return modules;
    }
    
    
    public List<NoteModulaire> findbyChart(Semestre semestre , Module module , Annee annee , int etat , boolean choice )
    {
        System.out.println("=== find Chart Facade === ");
        System.out.println("Choice in findByChart == "+choice);
        try {
            String req ="SELECT nm FROM NoteModulaire nm WHERE 1=1 ";

            if ( etat < 0 )
            req += " AND nm.etat ='"+etat+"' ";
            if ( module != null && module.getId() != null && !choice )
                req += " AND nm.module.id ='"+module.getId()+"'";
            if(semestre != null )
                req += "AND nm.module.semestre.libelle ='"+semestre.getLibelle()+"'";
            if ( annee != null )
                req += " AND nm.module.semestre.annee.id ='"+annee.getId()+"'";
            
            System.out.println("requette == "+req);
            return em.createQuery(req).getResultList();
        } catch (Exception e) {
            System.out.println("findByChart Error == "+e.getMessage());
            return new ArrayList<>();
        }
    }
    
    
    public List<NoteModulaire> findByModule(Module module)
    {
        List<NoteModulaire> res = new ArrayList<>();
        
        if ( module != null )
        {
            res = em.createQuery("SELECT nm FROM NoteModulaire nm WHERE nm.module.nom ='"+module.getNom()+"'").getResultList();
        }
        
        return res;
    }

}
