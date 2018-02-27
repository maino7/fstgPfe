/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Etudiant;
import bean.Filiere;
import bean.NoteSemestre;
import bean.Semestre;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author xXx-BlackAngel-xXx
 */
@Stateless
public class NoteSemestreFacade extends AbstractFacade<NoteSemestre> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NoteSemestreFacade() {
        super(NoteSemestre.class);
    }

    public List<Semestre> findeSemesterDeCetAnne(Etudiant etudiant) {
        if (etudiant.getSemestreActuel().getLibelle() == 3) {
            System.out.println("sm.libelle=S3 OR sm.libelle=S4");
            return em.createQuery("SELECT sm FROM Semestre sm WHERE sm.libelle='3' OR sm.libelle='4' AND sm.filiere.id='" + etudiant.getFiliere().getId() + "'").getResultList();
        }
        if (etudiant.getSemestreActuel().getLibelle() == 5) {
            System.out.println("sm.libelle=S5 OR sm.libelle=S6");

            return em.createQuery("SELECT sm FROM Semestre sm WHERE sm.libelle='5' OR sm.libelle='6' AND sm.filiere.id='" + etudiant.getFiliere().getId() + "'").getResultList();
        } else {
            return null;
        }
    }

    public List<Filiere> findFilierEtudiant(Etudiant etudiant) {
        return em.createQuery("SELECT DISTINCT( ns.semestre.filiere) FROM NoteSemestre ns where   ns.etudiant.cne='" + etudiant.getCne() + "'").getResultList();

    }

    public List<Semestre> findSemestresNonValideDemande(Etudiant etudiant) {
        List<Semestre> semestres = new ArrayList<>();
        List<Filiere> filieres = findFilierEtudiant(etudiant);
        System.out.println("hada howa etudiant"+etudiant);
        System.out.println("les filier"+filieres);
        for (Filiere f : filieres) {

            if (etudiant.getSemestreActuel().getLibelle() == 3) {
                System.out.println(" raja3  1111");

                semestres.addAll(em.createQuery("SELECT DISTINCT( ns.semestre) FROM NoteSemestre ns where ns.semestre.libelle='1'  AND ns.etat='0' AND ns.etudiant.cne='" + etudiant.getCne() + "' AND ns.semestre.filiere.id=" + f.getId()).getResultList());
            }
            if (etudiant.getSemestreActuel().getLibelle() == 4) {
                System.out.println(" raja3  2222");
                semestres.addAll(em.createQuery("SELECT DISTINCT( ns.semestre) FROM NoteSemestre ns where ns.semestre.libelle='2'  AND ns.etat='0' AND ns.etudiant.cne='" + etudiant.getCne() + "' AND ns.semestre.filiere.id=" + f.getId()).getResultList());
            }
            if (etudiant.getSemestreActuel().getLibelle() == 5) {
                System.out.println(" raja3  1111 et 3333");
                semestres.addAll(em.createQuery("SELECT DISTINCT( ns.semestre) FROM NoteSemestre ns where ns.etat='0'    AND ( ns.semestre.libelle='1' OR ns.semestre.libelle='3' ) AND ns.etudiant.cne='" + etudiant.getCne() + "' AND ns.semestre.filiere.id=" + f.getId()).getResultList());
            }
            if (etudiant.getSemestreActuel().getLibelle() == 6) {
                System.out.println(" raja3  2222 et 4444");
                semestres.addAll(em.createQuery("SELECT DISTINCT( ns.semestre) FROM NoteSemestre ns where ns.etat='0'    AND (ns.semestre.libelle='4' OR ns.semestre.libelle='2') AND ns.etudiant.cne='" + etudiant.getCne() + "' AND ns.semestre.filiere.id=" + f.getId()).getResultList());
            }
        }
        if (!semestres.isEmpty()) {
            return semestres;
        } else {
            System.out.println(" raja3  lista khawya ");
            return null;
        }

    }

    public List<Semestre> findSemestresAnneReserve(Etudiant etudiant) {
        List<Semestre> semestres = new ArrayList<>();

        if (etudiant.getSemestreActuel().getLibelle() == 5) {
            System.out.println(" raja3  1111 et 3333");
            semestres.addAll(em.createQuery("SELECT s FROM Semestre s,NoteSemestre ns where s.id=ns.semestre.id  AND (ns.semestre.libelle='1' OR ns.semestre.libelle='3') AND ns.etudiant.cne='" + etudiant.getCne() + "'").getResultList());
        }
        if (etudiant.getSemestreActuel().getLibelle() == 6) {
            System.out.println(" raja3  2222 et 4444");
            semestres.addAll(em.createQuery("SELECT DISTINCT( ns.semestre) FROM NoteSemestre ns where AND (ns.semestre.libelle='4' OR ns.semestre.libelle='2') AND ns.etudiant.cne='" + etudiant.getCne() + "'").getResultList());
        }

        if (!semestres.isEmpty()) {
            return semestres;
        } else {
            System.out.println(" raja3  lista khawya ");
            return null;
        }

    }

    public List<Semestre> findSemestresForReleveNote(Etudiant etudiant) {

        System.out.println("SELECT sm FROM Semestre sm,NoteSemestre ns where sm.id=ns.semestre.id ");
        System.out.println("cne " + etudiant.getCne());
        return em.createQuery("SELECT sm FROM Semestre sm,NoteSemestre ns where   sm.id=ns.semestre.id AND ns.etatReleve='0'  AND ns.etudiant.cne=" + etudiant.getCne()).getResultList();

    }

    public List<Semestre> findSemestresNonValide(Etudiant etudiant, int type) {
        if (type == 0) {
            System.out.println("SELECT sm FROM Semestre sm,NoteSemestre ns where sm.id=ns.semestre.id AND ns.etat=0");
            return em.createQuery("SELECT sm FROM Semestre sm,NoteSemestre ns where sm.id=ns.semestre.id AND ns.etat='0' AND ns.etudiant.cne=" + etudiant.getCne()).getResultList();
        }
        if (type == 1) {
            System.out.println("SELECT sm FROM Semestre sm,NoteSemestre ns where sm.id=ns.semestre.id AND ns.etat=1");
            return em.createQuery("SELECT sm FROM Semestre sm,NoteSemestre ns where sm.id=ns.semestre.id AND ns.etat='1' AND ns.etudiant.cne=" + etudiant.getCne()).getResultList();
        } else {
            System.out.println("SELECT sm FROM Semestre sm,NoteSemestre ns where sm.id=ns.semestre.id ");
            System.out.println("cne " + etudiant.getCne());
            return em.createQuery("SELECT sm FROM Semestre sm,NoteSemestre ns where sm.id=ns.semestre.id  AND ns.etudiant.cne=" + etudiant.getCne()).getResultList();
        }
    }
// used to find change the attribut etatvalide in demandeReleveNote

    public NoteSemestre changeAtributetatReleve(Semestre semestre, Etudiant etudiant) {
        System.out.println("sem" + semestre);
        System.out.println("etud" + etudiant);

        try {
            return (NoteSemestre) em.createQuery("SELECT ns FROM NoteSemestre ns WHERE  ns.etudiant.cne='" + etudiant.getCne() + "' AND ns.semestre.libelle='" + semestre.getLibelle() + "'").getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Abed's- cette methode fait la recherche du noteSemestre selon ID du
     * semestre
     *
     * @param noteSemestre
     * @return
     */
    public NoteSemestre findNSBySemestreAndEtudiant(NoteSemestre noteSemestre) {
        try {
            return (NoteSemestre) em.createQuery("SELECT ns FROM NoteSemestre ns WHERE  ns.etudiant.cne='" + noteSemestre.getEtudiant().getCne() + "' AND ns.semestre.id='" + noteSemestre.getSemestre().getId() + "'").getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Abed's- cette methode fait la recherche du noteSemestre selon Libelle du
     * semestre
     *
     * @param etudiant
     * @param semestre
     * @return
     */
    public NoteSemestre findNSBySemestreLibelleAndEtudiant(Etudiant etudiant, Semestre semestre) {
        try {
            return (NoteSemestre) em.createQuery("SELECT ns FROM NoteSemestre ns WHERE  ns.etudiant.cne='" + etudiant.getCne() + "' AND ns.semestre.libelle='" + semestre.getLibelle() + "'").getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    // used in attestationFacad  Deust=0 
    public List<NoteSemestre> findNSByEtudiant(Etudiant etudiant) {
        try {

            return em.createQuery("SELECT ns FROM NoteSemestre ns WHERE ns.etat='1' AND ns.semestre.libelle IN ('1','2','3','4') AND ns.etudiant.cne='" + etudiant.getCne() + "'").getResultList();

        } catch (NoResultException e) {
            return null;
        }
    }

    public LineChartModel initBarModel(List<NoteSemestre> noteList, Etudiant etudiant) {
        ChartSeries firstSerie = new ChartSeries();
        noteList = allSemestresForStudent(etudiant);
        for (NoteSemestre notes : noteList) {

            firstSerie.set("Semestre " + notes.getSemestre().getLibelle(), notes.getNote());
        }
        LineChartModel modelLine = new LineChartModel();
        modelLine.addSeries(firstSerie);
        modelLine.setShowPointLabels(true);
        return modelLine;
    }

    public List<NoteSemestre> allSemestresForStudent(Etudiant etudiant) {

        return em.createQuery("SELECT ns FROM NoteSemestre ns WHERE  ns.etudiant.cne='" + etudiant.getCne() + "' order by ns.semestre.libelle").getResultList();

    }

    public Double maxY(Etudiant etudiant) {
        List<NoteSemestre> taxes = allSemestresForStudent(etudiant);
        Double max = 0.0;

        for (NoteSemestre taxeTrim : taxes) {
            if (taxeTrim.getNote() > max) {
                max = taxeTrim.getNote();
            }

        }
        return max;
    }
    
    public NoteSemestre findSingleByEtudSem(Etudiant etudiant, Semestre semestre)
    {
        try {
        return (NoteSemestre) em.createQuery("SELECT nm FROM NoteSemestre nm WHERE nm.semestre.id='"+semestre.getId()+"' AND nm.etudiant.cne ='"+etudiant.getCne()+"'").getSingleResult();
        } catch (Exception e) {
            System.out.println("e message == "+e);
            return new NoteSemestre();
        }
    }
}
