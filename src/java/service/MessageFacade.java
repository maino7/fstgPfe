/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Message;
import controller.util.SearchUtil;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class MessageFacade extends AbstractFacade<Message> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }

    public List<Message> searchMsg(int lu,boolean luMsg,Date datMin, Date datMax, String sujet) {
        String requette = "SELECT msg FROM Message msg WHERE 1=1";
        requette += SearchUtil.addConstraintMinMaxDate("msg", "dateEnvoi", datMin, datMax);
       if(lu>=0){
            requette += SearchUtil.addConstraint("msg", "lu", "=", luMsg);
        }
        if (sujet != null) {
            requette += SearchUtil.addConstraint("msg", "sujet", "=", sujet);
        }
        System.out.println("===================  " + requette);
        return em.createQuery(requette).getResultList();
    }

}
