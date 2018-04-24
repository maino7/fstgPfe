/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.LigneMagasin;
import bean.Magasin;
import bean.Produit;
import controller.util.SearchUtil;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Stateless
public class LigneMagasinFacade extends AbstractFacade<LigneMagasin> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneMagasinFacade() {
        super(LigneMagasin.class);
    }

    public List<LigneMagasin> searchProduitFinit() {
        return em.createQuery("SELECT l FROM LigneMagasin l WHERE l.quantite<=l.produit.seuilAlert").getResultList();
    }

    public List<LigneMagasin> searchProduit(Produit produit, Magasin magasin, double seuilDalerte, double qtéMax, double qtéMin) {
        List<LigneMagasin> res = new ArrayList<>();
        String req = "SELECT l FROM LigneMagasin l WHERE 1=1 ";
        if (produit != null) {
            System.out.println("------------------------Produit----------------");
            System.out.println(produit);
            req += SearchUtil.addConstraint("l", "produit.id", "=", produit.getId());
        }
        if (magasin != null) {
            System.out.println("------------------------Magasin----------------");
            System.out.println(magasin);
            req += SearchUtil.addConstraint("l", "magasin.id", "=", magasin.getId());

        }
        if (seuilDalerte != 0) {
            System.out.println("------------------------SeuilDa----------------");
            System.out.println(seuilDalerte);
            req += SearchUtil.addConstraint("l", "produit.seuilAlert", "=", seuilDalerte);
        }
        if (qtéMax != 0 || qtéMin != 0) {
            System.out.println("------------------------QtéMAX----------------");
            System.out.println(qtéMax);
            System.out.println("------------------------QtéMIN----------------");
            System.out.println(qtéMin);
            req += SearchUtil.addConstraintMinMax("l", "quantite", qtéMin, qtéMax);

        }
        res = em.createQuery(req).getResultList();
        System.out.println("------------------------Resultat----------------");
        System.out.println(res);

        return res;
    }

    public List<LigneMagasin> searchProduit1(Produit produit, Magasin magasin, double seuilDalerte, double qtéMax, double qtéMin) {
        List<LigneMagasin> resInitiale = new ArrayList();
        List<LigneMagasin> resMag = new ArrayList();
        List<LigneMagasin> resPrd = new ArrayList();
        List<LigneMagasin> resSeuil = new ArrayList();
        List<LigneMagasin> resMinMax = new ArrayList();
        List<LigneMagasin> resMixte = new ArrayList();

        resInitiale = em.createQuery("SELECT l FROM LigneMagasin l WHERE 1=1").getResultList();
//        System.out.println("------------------------ResultatInitiale----------------");
//        System.out.println(resInitiale);
//        if (magasin != null) {
////           resInitiale.stream().forEach((l) -> {
////               if(l.getMagasin().getId().equals(magasin.getId())){
////                         
////               }
////                       
////                       });
//            resMag = resInitiale.stream().filter(t -> t.getMagasin().getId() != magasin.getId()).collect(Collectors.toList());
//
//        }if(produit!=null){
//            if(resMag.isEmpty()){
//                resPrd=resInitiale.stream().filter(t -> t.getProduit().getId() != produit.getId()).collect(Collectors.toList());
//                
//            }else if(!resMag.isEmpty()){
//                resMixte=resMag.stream().filter(t -> t.getProduit().getId() != produit.getId()).collect(Collectors.toList());
//            }
//            
//        }if(seuilDalerte!=0.0){
//            if(resPrd.isEmpty()&&resMag.isEmpty()){
//             resSeuil=resInitiale.stream().filter(t -> t.getProduit().getSeuilAlert() != seuilDalerte).collect(Collectors.toList());   
//                
//            }else if(!resPrd.isEmpty()){
//                resMixte=resPrd.stream().filter(t -> t.getProduit().getSeuilAlert() != seuilDalerte).collect(Collectors.toList());
//                
//            }else if(!resMag.isEmpty()){
//                resMixte=resMag.stream().filter(t -> t.getProduit().getSeuilAlert() != seuilDalerte).collect(Collectors.toList());
//            }
//                
//            
//            
//        }
        if (!magasin.equals(null)) {
            System.out.println("------------------------------------------MAGASIN!=null---------------------------------");
            System.out.println(magasin);
            resInitiale.forEach((t) -> {
                if (t.getMagasin().getId().equals(magasin.getId())) {
                    resMag.add(t);
                }
            });
        }
        if (!produit.equals(null)) {
            System.out.println("------------------------------------------PRODUIT!=null---------------------------------");
            System.out.println(produit);
            resInitiale.forEach((t) -> {
                if (t.getProduit().getId().equals(produit.getId())) {
                    resMag.add(t);
                }
            });
        }
        if (seuilDalerte != 0.0) {
            System.out.println("------------------------------------------SeuilDalerte!=null---------------------------------");
            System.out.println(seuilDalerte);
            resInitiale.forEach((t) -> {
                if (t.getProduit().getSeuilAlert() == seuilDalerte) {
                    resMag.add(t);
                }
            });
        }
        if (qtéMax != 0.0) {
            System.out.println("------------------------------------------QTEMAX!=null---------------------------------");
            System.out.println(qtéMax);
            resInitiale.forEach((t) -> {
                if (t.getQuantite() <= qtéMax) {
                    resMag.add(t);
                }
            });
        }
        if (qtéMin != 0.0) {
            System.out.println("------------------------------------------QTEMIN!=null---------------------------------");
            System.out.println(qtéMin);
            resInitiale.forEach((t) -> {
                if (t.getQuantite() >= qtéMin) {
                    resMag.add(t);
                }
            });
        }
        if (qtéMin != 0.0 && qtéMax != 0.0) {
            System.out.println("------------------------------------------QTEBJOUJ!=null---------------------------------");
            System.out.println(qtéMax + "" + qtéMin);
            resMag.clear();
            resInitiale.forEach((t) -> {
                if (t.getQuantite() >= qtéMin && t.getQuantite() <= qtéMax) {
                    resMag.add(t);
                }
            });
        }

        System.out.println(resMag);
        return resMag;

    }

    public List<LigneMagasin> findbyProduit(Produit produit) {

        return em.createQuery("SELECT le FROM LigneMagasin le WHERE le.produit.libelle='" + produit.getLibelle() + "'").getResultList();

    }

}
