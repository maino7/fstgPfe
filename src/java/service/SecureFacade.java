/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Enseignant;
import bean.Etudiant;
import bean.Secure;
import controller.util.HashageUtil;
import controller.util.PdfUtil;
import controller.util.SessionUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author BENIHOUD Youssef
 */
@Stateless
public class SecureFacade extends AbstractFacade<Secure> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    ///*********************Methode Enseignant*************************///
    
    /**
     * Methode : Check if the Answer of The Question is Correct <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 21/05/2017 <br/>
     *  
     * @param item
     * @param selected
     */
    public void checkAnswersEns( String item , String selected )
    {
        System.out.println("=== Start CheckAnswers item Facade ====");
        Enseignant enseignant = (Enseignant) SessionUtil.getAttribute("deviceIssue");
        List<Secure> es = enseignant.getSecures();
        
        for (Secure e : es) {
            e.setCorrect(true);
            if(e.getQuest().equals(item))
            {
                
                if(!e.getResp().equals(HashageUtil.sha256(selected)))
                {
                    System.out.println("***** it's not correct *****");
                    System.out.println("e.getQuest == "+e.getQuest()+"::: item === "+item);
                    System.out.println("selected === "+selected+" ## e.getResp === "+e.getResp());
                    e.setCorrect(false);
                    edit(e);
                }
            }
            edit(e);
        }
        System.out.println("=== End CheckAnswers item Facade ====");
    }
    
    public boolean validateEns()
    {
        System.out.println("==== Start Validate Answers for Enseignnat ====");
        Enseignant enseignant = (Enseignant) SessionUtil.getAttribute("deviceIssue");
        List<Secure> es = enseignant.getSecures();
        
        for (Secure e : es) {
            
            if (!e.isCorrect())
            {
                System.out.println("**** There is a wrong Answer ****");
                return false;
            }
            
        }
        
        System.out.println("==== End Validate Answers for Enseignnat ====");
        return true;
        
    }
    
    public void createSecureEns(Secure test)
    {
        Enseignant enseignant = (Enseignant) SessionUtil.getConnectedUser();
        Secure secure = new Secure();
        secure.setId(test.getId());
        secure.setCorrect(false);
        secure.setQuest(test.getQuest());
        secure.setResp(HashageUtil.sha256(test.getResp()));
        
        if ( enseignant != null )
        {
           if ( !secure.getQuest().equals("") && !secure.getResp().equals("") )
            if ( !secure.getQuest().equals(""))
                if (!secure.getResp().equals(""))
        {
            secure.setEnseignant(enseignant);
            create(secure);
        }
        }
    }
    
    ///*********************Methode Enseignant*************************///
    

///*********************Methode Entudiant*************************///
    
    /**
     * Methode : Check if the Answer of The Question is Correct <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 05/06/2017 <br/>
     *  
     * @param item
     * @param selected
     */
    public void checkAnswersEtud( String item , String selected )
    {
        System.out.println("=== Start CheckAnswers item Facade ====");
        Etudiant etudiant = (Etudiant) SessionUtil.getAttribute("deviceIssue");
        List<Secure> es = etudiant.getSecures();
        
        for (Secure e : es) {
            e.setCorrect(true);
            if(e.getQuest().equals(item))
            {
                
                if(!e.getResp().equals(HashageUtil.sha256(selected)))
                {
                    System.out.println("***** it's not correct *****");
                    System.out.println("e.getQuest == "+e.getQuest()+"::: item === "+item);
                    System.out.println("selected === "+selected+" ## e.getResp === "+e.getResp());
                    e.setCorrect(false);
                    edit(e);
                }
            }
            edit(e);
        }
        System.out.println("=== End CheckAnswers item Facade ====");
    }
    
    public boolean validateEtud()
    {
        System.out.println("==== Start Validate Answers for Etudiant ====");
        Etudiant etudiant = (Etudiant) SessionUtil.getAttribute("deviceIssue");
        List<Secure> es = etudiant.getSecures();
        
        for (Secure e : es) {
            
            if (!e.isCorrect())
            {
                System.out.println("**** There is a wrong Answer ****");
                return false;
            }
            
        }
        
        System.out.println("==== End Validate Answers for Etudiant ====");
        return true;
        
    }
    
    public void createSecureEtud(Secure test)
    {
        Etudiant etudiant = (Etudiant) SessionUtil.getConnectedUser();
        Secure secure = new Secure();
        secure.setId(test.getId());
        secure.setCorrect(false);
        secure.setQuest(test.getQuest());
        secure.setResp(HashageUtil.sha256(test.getResp()));
        
        if ( etudiant != null )
        {
           if ( !secure.getQuest().equals("") && !secure.getResp().equals("") )
            if ( !secure.getQuest().equals(""))
                if (!secure.getResp().equals(""))
        {
            secure.setEtudiant(etudiant);
            create(secure);
        }
        }
        
    }
    
    
    
    
///*********************Methode Entudiant*************************///
    
    
    /** PDF FOR SECURE **/
  /*  
    public void generatePdf(List<Secure> items , String nom ) throws JRException, IOException
    {
        Map<String,Object> params = new HashMap();
        params.put("name", nom);
        PdfUtil.generatePdf(items, params, "Security_Questions_"+nom, "/jasper/savedQuestions.jasper");
        
    }
    */
    /** PDF FOR SECURE **/
    
    
    /**
     * Methode : Clone Secure <br/>
     * Author : Youssef Benihoud <br/>
     * Date : 21/05/2017 <br/>
     * @param secure
     * @return clone
     */
    public Secure clonning(Secure secure)
    {
        Secure clone = new Secure();
        clone.setId(secure.getId());
        clone.setQuest(secure.getQuest());
        clone.setResp(secure.getResp());
        clone.setCorrect(secure.isCorrect());
        clone.setEnseignant(secure.getEnseignant());
        clone.setEtudiant(secure.getEtudiant());
        return clone;
    }
    
    public boolean itemsMoreThanThree(List<Secure> items )
    {
        if ( items != null )
        {
            if ( items.size() >= 3 )
            {
                return true;
            }
        }
        return false;
    }

    public SecureFacade() {
        super(Secure.class);
    }
    
}
