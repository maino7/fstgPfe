/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Candidat;
import bean.CoeffCalibrage;
import bean.ConcourNiveau;
import bean.Condidature;
import bean.EtablissementType;
import bean.Niveau;
import controller.util.DateUtil;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ouss
 */
@Stateless
public class ConcourNiveauFacade extends AbstractFacade<ConcourNiveau> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @EJB
    private service.CondidatureFacade condidatureFacade;
    @EJB
    private service.CandidatFacade candidatFacade;
    @EJB
    private service.CoeffCalibrageFacade ccf;
    @EJB
    private service.PieceEtudiantFacade pieceEtudiantFacade;

    
    
    public ConcourNiveauFacade() {
        super(ConcourNiveau.class);
    }
    
    public int calculePlaceRest(Niveau niveau){
        ConcourNiveau c = (ConcourNiveau) em.createQuery("SELECT c FROM ConcourNiveau c WHERE c.niveau.id="+niveau.getId()).getSingleResult();
        int placeC = c.getNbrDePlaceEcrit();
        int placeCond = condidatureFacade.placeReste(niveau);
        if(placeCond >= placeC){
            return -1;
        } else {
            return 1;
        }
    }
    //========Methode test========//
    public int placeListRest(List<Candidat> c,EtablissementType eta){
        int i =0;
        for (Candidat candidat : c) {
            if(candidat.getEtablissement().getAbrApg().equals("FST")){
                i++;
            }
        }
        return i;
    }
    public List<Candidat> candidatAdmis(ConcourNiveau coNiveau){
        List<Candidat> cand1 = candidatFacade.findByniveau(coNiveau.getNiveau());
        System.out.println("ha list ta3 les candidat==>"+cand1);
        for (Candidat candidat : cand1) {
            CoeffCalibrage coeff = ccf.findByEtab(candidat.getEtablissement());
            float moy = candidatFacade.calculeMoy(candidat);
            if(coeff.getNoteMinimal()>moy){
                cand1.remove(candidat);
            }
        }
        System.out.println("o ha la list li kat khrej==>"+cand1);
        return cand1;
    }
    
   public List<ConcourNiveau> findByNiveau(Niveau n){
       String d = DateUtil.format(new Date());
       return em.createQuery("SELECT c FROM ConcourNiveau c WHERE c.annee='"+d+"'"+" AND c.niveau.id="+n.getId()).getResultList();
   }
    public int verification(ConcourNiveau c){
         List<ConcourNiveau> cc = findByNiveau(c.getNiveau());
        
        if(c.getNbrDePlaceOrale() > c.getNbrDePlaceEcrit()){
            return -1;
        }else if( c.getNbrDePlaceOrale() < c.getNbrDePladeAdmis()){
            return -2;
        } else if(!cc.isEmpty()){
           return -3;
        } else {
            return 1;
        }
    }
    public void listIsPrint(String type,Niveau n){
         List<ConcourNiveau> cList = findByNiveau(n);
         if(!cList.isEmpty()){
             ConcourNiveau c = cList.get(0);
             if(type.equals("listC")){
                 c.setListC(1);
             }else if(type.equals("listO")){
                 c.setListO(1);
             }else {
                 c.setListF(1);
             }
         }
    }
    
     public ConcourNiveau findByCandidat(Candidat candidat){
        Niveau n = new Niveau();
        ConcourNiveau concourNiveau = new ConcourNiveau();
        Condidature cond = condidatureFacade.findByCandidat(candidat);
        if(cond != null){
             n = pieceEtudiantFacade.findNiveau(cond);
             if(n != null){
                  concourNiveau = findByNiveau(n).get(0);
             }
        }
        
        return concourNiveau;
       
    }
     
    public Niveau findNiveauByCandidat(Candidat candidat){
        Niveau n = new Niveau();
        Condidature condidature = condidatureFacade.findByCandidat(candidat);
        if(condidature != null){
            n = pieceEtudiantFacade.findNiveau(condidature);
        }
        return n;
    }
      // type : 1 listC - 2 : listO - 3: listF
     public int ifListIsPrint(ConcourNiveau c,int type){
         if(type == 1){
             if(c.getListC() == 1){
                 return 1;
             }else {
                 return -1;
             }
         }else if(type == 2){
             if(c.getListO() == 1){
                 return 1;
             }else {
                 return -1;
             }
         }else{
             if(c.getListF() == 1){
                 return 1;
             }else {
                 return -1;
             }
         }
        
     }
    
    
    
    //===========================//
    
    
    
}
