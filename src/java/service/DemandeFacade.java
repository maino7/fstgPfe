/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Demande;
import bean.DemandeDerogation;
import bean.DemandeItem;
import bean.Etudiant;
import bean.Module;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abed
 */
@Stateless
public class DemandeFacade extends AbstractFacade<Demande> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;
    @EJB
    private service.DemandeItemFacade demandeItemFacade;
    @EJB
    private service.DemandeFacade demandeFacade;
    @EJB
    private service.ModuleFacade ejbFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeFacade() {
        super(Demande.class);
    }

    public void addDemande(List<DemandeItem> demandeItems, String message, Etudiant etudiant) {
        Demande demande = new Demande();
        demande.setId(generateId("Demande", "id"));
        demande.setDescription(message);
        demande.setEtat(0);
        demande.setEtudiant(etudiant);
        create(demande);
        for (DemandeItem dem : demandeItems) {
            dem.setDemande(demande);
            demandeItemFacade.edit(dem);
        }
    }

    public int prepareDemande(List<Module> m, String descriptionDemande, Etudiant etudiant) {
        List<DemandeItem> demList = new ArrayList<>();
        int i;
        if (m.size() == 7 || m.size() == 6) {
            for (i = 0; i < m.size(); i++) {
                Module x = ejbFacade.findModulsSemestreActuel("" + m.get(i)).get(0);
                DemandeItem di = new DemandeItem();
                di.setModule(x);
                di.setSemestre(x.getSemestre());
                di.setId(demandeItemFacade.generateId("DemandeItem", "id"));
                demandeItemFacade.create(di);
                demList.add(di);
            }
            addDemande(demList, descriptionDemande, etudiant);
            return 1;

        } else {
            return -1;
        }

    }

    public int prepareDemandeAnneReserve(List<Module> m, String descriptionDemande, Etudiant etudiant) {
        List<DemandeItem> demList = new ArrayList<>();
        int i;

        for (i = 0; i < m.size(); i++) {
            Module x = ejbFacade.findModulsSemestreActuel("" + m.get(i)).get(0);
            DemandeItem di = new DemandeItem();
            di.setModule(x);
            di.setSemestre(x.getSemestre());
            di.setId(demandeItemFacade.generateId("DemandeItem", "id"));
            demandeItemFacade.create(di);
            demList.add(di);
        }
        addDemande(demList, descriptionDemande, etudiant);
        return 1;

    }

    public DemandeDerogation DemDerogation(Etudiant etudiant) {
        try {
            return (DemandeDerogation) em.createQuery("SELECT d from DemandeDerogation d where d.etudiant.cne='" + etudiant.getCne() + "'").getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
//    public DemandeDerogation acceptedDemDerogation(Etudiant etudiant) {
//        try {
//            return (DemandeDerogation) em.createQuery("SELECT d from DemandeDerogation d where d.etudiant.cne='" + etudiant.getCne() + "'").getSingleResult();
//
//        } catch (Exception e) {
//            return null;
//        }
//
//    }

    /**
     * Methode : find Demandes by Etudiant <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 18/05/2017 <br/>
     * 0% <br/>
     *
     * @param cne of Etudiant <br/>
     * @return List of Demandes <br/>
     */
    public List<Demande> findByEtudiant(Long cne) {
        System.out.println("=== Start find By Etudiant ===");
        try {
            System.out.println("=== End find By Etudiant ===");
            return em.createQuery("SELECT d FROM Demande d WHERE d.etudiant.cne ='" + cne + "'").getResultList();
        } catch (Exception e) {
            System.out.println(" Exception === " + e.getMessage());
            return new ArrayList<>();
        }

    }

    /**
     * Abed's - find New Demandes <br/>
     * Date : 01/06/2017 <br/>
     *
     *
     * @return List of Demandes <br/>
     */
    public List<Demande> findNewDemandes() {
        System.out.println("=== Start find By Etudiant ===");
        try {
            System.out.println("=== End find By Etudiant ===");
            return em.createQuery("SELECT d FROM Demande d WHERE d.etat =0").getResultList();
        } catch (Exception e) {
            System.out.println(" Exception === " + e.getMessage());
            return new ArrayList<>();
        }

    }

    /**
     * Abed's - find Demandes accepted or decined from Enseignant<br/>
     * Date : 01/06/2017 <br/>
     *
     *
     * @return List of Demandes <br/>
     */
    public List<Demande> findDoneDemandes() {
        System.out.println("=== Start find By Etudiant ===");
        try {
            System.out.println("=== End find By Etudiant ===");
            return em.createQuery("SELECT d FROM Demande d WHERE d.etat <> 0").getResultList();
        } catch (Exception e) {
            System.out.println(" Exception === " + e.getMessage());
            return new ArrayList<>();
        }

    }

}
