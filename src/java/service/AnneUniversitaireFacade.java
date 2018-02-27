/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.AnneUniversitaire;
import bean.Annee;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class AnneUniversitaireFacade extends AbstractFacade<AnneUniversitaire> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnneUniversitaireFacade() {
        super(AnneUniversitaire.class);
    }

    public AnneUniversitaire prepareAnnee() {
        AnneUniversitaire anUniv = new AnneUniversitaire();
        anUniv.setId(generateId("AnneUniversitaire", "id"));
        Annee annMin = new Annee(generateId("Annee", "id"), new Date().getYear() + 1900);
        Annee annMax = new Annee(generateId("Annee", "id"), new Date().getYear() + 1901);
        anUniv.setAnneeMax(annMax);
        anUniv.setAnneeMin(annMin);
        return anUniv;
    }
}
