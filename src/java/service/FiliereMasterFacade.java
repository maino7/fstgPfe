/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.FiliereMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xXx-BlackAngel-xXx
 */
@Stateless
public class FiliereMasterFacade extends AbstractFacade<FiliereMaster> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FiliereMasterFacade() {
        super(FiliereMaster.class);
    }

    public List<FiliereMaster> findForInsc(boolean b) {
        int a;
        if (b == true) {
            a = 1;
        } else {
            a = 2;
        }
        try {
            return em.createQuery("SELECT f FROM FiliereMaster f WHERE f.type='" + a + "'").getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
