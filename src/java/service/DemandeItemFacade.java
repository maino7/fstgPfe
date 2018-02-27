/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeItem;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abed
 */
@Stateless
public class DemandeItemFacade extends AbstractFacade<DemandeItem> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeItemFacade() {
        super(DemandeItem.class);
    }
    
    
    /**
     * Methode : find DemandeItem by Demande <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 18/05/2017 <br/>
     * 0% <br/>
     * @param id of Demande <br/>
     * @return List of Demande <br/>
     */
    public List<DemandeItem> findByDemande(Long id)
    {
        System.out.println("==== Start find DemandeItem by Demande ====");
        try {
            System.out.println("==== End find DemandeItem by Demande ====");
            return em.createQuery("SELECT di FROM DemandeItem di WHERE di.demande.id ='"+id+"'").getResultList();
        } catch (Exception e) {
            System.out.println("Exception === "+e.getMessage());
            return new ArrayList<>();
        }
    }
    
}
