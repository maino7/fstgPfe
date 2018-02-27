/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Annee;
import bean.Etudiant;
import bean.Filiere;
import bean.Module;
import bean.NoteModulaire;
import bean.NoteSemestre;
import bean.Semestre;
import controller.util.PdfUtil;
import controller.util.SearchUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Abed
 */
@Stateless
public class SemestreFacade extends AbstractFacade<Semestre> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @EJB
    private service.EtudiantFacade etudiantFacade;
    @EJB
    private service.EnseignantFacade enseignantFacade;
    @EJB
    private service.NoteSemestreFacade noteSemestreFacade;
    @EJB
    private service.NoteModulaireFacade noteModulaireFacade;
    @EJB
    private service.ModuleFacade moduleFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SemestreFacade() {
        super(Semestre.class);
    }

    public List<Semestre> findSemestre(String a) {
        System.out.println("kat9alab 3la lmodul b String");
        return em.createQuery("SELECT s from Semestre s where s.libelle='" + a + "'").getResultList();
    }

    public List<Semestre> findSemestreByFiliere(Filiere filiere) {
        if (filiere != null) {
            return em.createQuery("SELECT s FROM Semestre s WHERE s.filiere.id=" + filiere.getId()).getResultList();

        }
        return new ArrayList();
    }

    /**
     * Abed's -
     *
     * @param etudiant
     * @return
     */
    public List<Semestre> findSemestreByNoteModulaireAndEtudiant(Etudiant etudiant) {
        List<Long> nms = em.createQuery("SELECT DISTINCT nm.module.semestre.id  FROM NoteModulaire nm WHERE NM.etudiant.cne=" + etudiant.getCne()).getResultList();
        List<Semestre> semestres = new ArrayList();
        nms.stream().forEach((nm) -> {
            semestres.add(find(nm));
        });
        return semestres;
    }

    /**
     * Abed's - Find the next semestre
     *
     * @param etudiant
     * @param semestre
     * @return
     */
    public Etudiant findNextSemestreForEtudiant(Etudiant etudiant, Semestre semestre) {
        Semestre s = etudiant.getSemestreActuel();
        if ((s.getLibelle() != 4 || s.getLibelle() != 6) && s.getLibelle() <= semestre.getLibelle()) {
            int nSem = s.getLibelle() + 1;
            Semestre nextSemestre = (Semestre) em.createQuery("SELECT s FROM Semestre s WHERE s.libelle=" + nSem + " AND s.filiere.id=" + s.getFiliere().getId()).getSingleResult();
            etudiant.setSemestreActuel(nextSemestre);
        }
        return etudiant;
    }

    //other project ..
    public List<Semestre> findByFiliere(Filiere f) {
        String requette = "SELECT s FROM Semestre s WHERE 1=1 ";
        if (f != null && f.getId() != null) {
            requette += SearchUtil.addConstraint("s.filiere", "id", "=", f.getId());
            requette += " ORDER BY s.libelle";
            System.out.println(requette);
            return em.createQuery(requette).getResultList();
        } else {
            return null;
        }
    }

    /**
     * Methode : find Semestre by Annee Author : Youssef Benihoud Date :
     * 09/06/2017
     *
     * @param annee
     * @param filiere
     * @return
     */
    public List<Semestre> findByAnnee(Annee annee, Filiere filiere) {
        System.out.println("==== findByAnnee Facade ====");
        System.out.println("Annee Facade id === " + annee.getId());
        List<Semestre> res = new ArrayList<>();

        if (annee != null) {
            res = em.createQuery("SELECT s FROM Semestre s WHERE s.annee.id ='" + annee.getId() + "' AND s.filiere.id=" + filiere.getId()).getResultList();
        }

        System.out.println("res Facade ==" + res);
        System.out.println("==== End FindByAnnee Facade ====");
        return res;
    }

   
    public Semestre findFirstSemOfFiliere(Filiere filiere){
        return (Semestre) em.createQuery("SELECT s FROM Semestre s WHERE s.libelle=1 AND s.filiere.id="+filiere.getId()).getSingleResult();
    }
}
