/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeAttestaion;
import bean.Etudiant;
import bean.NoteSemestre;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xXx-BlackAngel-xXx
 */
@Stateless
public class DemandeAttestaionFacade extends AbstractFacade<DemandeAttestaion> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @EJB
    private NoteSemestreFacade noteSemestreFacade;

    public DemandeAttestaionFacade() {
        super(DemandeAttestaion.class);
    }

    // wach l etudiant imkan lih i demandé had attestation exp si 4 sem est valide =i9dar Deust..
    public int attestationType(Etudiant etudiant) {
        int res = 0;
        DemandeAttestaion attestaion;
        try {
            attestaion = (DemandeAttestaion) em.createQuery("SELECT a FROM DemandeAttestaion a WHERE a.etudiant.cne='" + etudiant.getCne() + "'").getSingleResult();

        } catch (Exception e) {
            attestaion = null;
        }
        if (attestaion == null) {
            List<NoteSemestre> deust = noteSemestreFacade.findNSByEtudiant(etudiant);

            if (deust.size() == 4) {
                res += 1;
            }
        }
        return res;
        //res =1 Deust only =2 Licence Only =3 les deux
    }

    public void addDemande(List<String> list, Etudiant etudiant) {
        DemandeAttestaion demande = new DemandeAttestaion();
        demande.setId(generateId("DemandeAttestaion", "id"));
        demande.setEtudiant(etudiant);

        for (String s : list) {
            if (s.equals("1")) {
                demande.setAttestationDeust(1);
            }

        }
        create(demande);

    }
}
