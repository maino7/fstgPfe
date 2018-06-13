/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Candidat;

import bean.CoeffCalibrage;
import bean.ConcourExamMatiere;

import bean.ConcourNiveau;

import bean.Condidature;
import bean.EtablissementType;
import bean.ExamCandidat;
import bean.Facture;
import bean.Niveau;
import bean.PieceEtudiant;

import controller.util.PdfUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import bean.PiecesParNiveau;
import controller.CandidatController;
import controller.util.HashageUtil;
import java.util.Date;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author HP
 */
@Stateless
public class CandidatFacade extends AbstractFacade<Candidat> {

    @EJB
    private CondidatureFacade condidatureFacade;
    @EJB
    private ConcourNiveauFacade cnf;
    @EJB
    private PieceEtudiantFacade pef;
    @EJB
    private CoeffCalibrageFacade ccf;
    @EJB
    private PieceEtudiantFacade etudiantFacade;
    @EJB
    private ExamCandidatFacade examCandidatFacade;
    @EJB
    private ConcourExamMatiereFacade cemf;
    List<Candidat> crest = new ArrayList();
    List<Candidat> Cabsent = new ArrayList();
    List<Candidat> condAdmis = new ArrayList();
    List<Candidat> condAttente = new ArrayList();

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidatFacade() {
        super(Candidat.class);
    }

    public List<Candidat> findNonvalider() {
        return em.createQuery("SELECT c.candidat FROM Condidature c WHERE c.condidatureValide='0'").getResultList();
    }

    public List<Candidat> findByniveau(Niveau n) {
        return em.createQuery("SELECT p.condidature.candidat FROM PieceEtudiant p WHERE p.piecesParNiveau.niveau=" + n.getId()).getResultList();
    }

    public float calculeMoy(Candidat c) {
        float moy = (c.getNoteS1() + c.getNoteS2() + c.getNoteS3() + c.getNoteS4()) / 4;
        return moy;
    }

    public String randomPw() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        System.out.println("random pw==>" + saltStr);
        return saltStr;

    }

    public Candidat creerCycle(Candidat candidat, ConcourNiveau concourNiveau) {
        if (candidat == null) {

            return null;
        } else {
            Condidature condidature = new Condidature();
            Niveau niveau = concourNiveau.getNiveau();
            PiecesParNiveau piecesParNiveau = (PiecesParNiveau) em.createQuery("SELECT p from PiecesParNiveau p where p.niveau.id" + niveau.getId()).getResultList().get(0);
            PieceEtudiant pieceEtudiant = new PieceEtudiant();
            pieceEtudiant.setPiecesParNiveau(piecesParNiveau);
            condidature.setId(generateId("Condidature", "id"));
            candidat.setCondidature(condidature);
            Date date = new Date();
            String rndPassword = randomPw();
            candidat.setPassword(rndPassword);
            candidat.setDateInscription(date);
            condidature.setCandidat(candidat);
            condidature.setTypeInscription(3);
            pieceEtudiant.setCondidature(condidature);
            condidatureFacade.create(condidature);
            etudiantFacade.create(pieceEtudiant);
            create(candidat);
            System.out.println("ha lcreation" + candidat);
            return candidat;
        }
//        return 1;

    }

    public Candidat creerMaster(Candidat candidat, ConcourNiveau concourNiveau) {
        if (candidat == null) {

            return null;
        } else {
            Condidature condidature = new Condidature();
            Niveau niveau = concourNiveau.getNiveau();
            PiecesParNiveau piecesParNiveau = (PiecesParNiveau) em.createQuery("SELECT p from PiecesParNiveau p where p.niveau.id=" + niveau.getId()).getResultList().get(0);
            PieceEtudiant pieceEtudiant = new PieceEtudiant();
            pieceEtudiant.setPiecesParNiveau(piecesParNiveau);
            condidature.setId(generateId("Condidature", "id"));
            candidat.setCondidature(condidature);
            Date date = new Date();
            candidat.setDateInscription(date);
            condidature.setCandidat(candidat);
            condidature.setTypeInscription(2);
            pieceEtudiant.setCondidature(condidature);
            condidatureFacade.create(condidature);
            etudiantFacade.create(pieceEtudiant);
            create(candidat);
            System.out.println("ha lcreation" + candidat);
            return candidat;
        }
    }

    public void infosCandidat() {

    }

    //)====Methode test ============//
    public int countCandiEtab(List<Candidat> c, Candidat cand) {
        EtablissementType type = cand.getEtablissement();
        int j = 0;
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getEtablissement().equals(type)) {
                j++;
            }

        }
        return j;
    }

    public List<Candidat> minConvoquer(List<Candidat> c) {
        List<Candidat> cc = new ArrayList<>();
        List<Candidat> cClone = new ArrayList<>();

        for (int i = 0; i < c.size(); i++) {
            int min = ccf.findByEtab(c.get(i).getEtablissement()).getNbrMin();

            if (countCandiEtab(cc, c.get(i)) < min) {
                cc.add(c.get(i));

            } else {
                crest.add(c.get(i));
            }
        }

        return cc;
    }

    public List<Candidat> completerConv(List<Candidat> crest, List<Candidat> cmin, Niveau n) {

        int place = cnf.findByNiveau(n).get(0).getNbrDePlaceEcrit();
        System.out.println("ha nbr de place==>" + place);
        if (cmin.size() == place) {
            System.out.println("les place li kaynin rah wselhom");
            return cmin;
        } else {
            for (int i = 0; i < crest.size(); i++) {
                cmin.add(crest.get(i));
                if (cmin.size() == place) {
                    break;
                }
            }

        }
        Collections.sort(cmin, new Comparator<Candidat>() {
            @Override
            public int compare(Candidat o1, Candidat o2) {
                return Float.valueOf(o2.getMoyCalibr()).compareTo(o1.getMoyCalibr());
            }
        });

        return cmin;
    }

    public int verfiPasseExam(Candidat c, Niveau n) {
        int z = 0;
        Condidature condi = condidatureFacade.findByCandidat(c);
        List<ExamCandidat> exams = examCandidatFacade.finbByCandidature(condi);
        System.out.println("**********************");
        System.out.println("ha ach dowez khona ==>" + exams);
        List<ConcourExamMatiere> matiers = cemf.findByCondidature(condi);
        System.out.println("ha les exam li jaw==>" + matiers);
        System.out.println("*************************");
        if (exams.isEmpty() || matiers.isEmpty()) {
            return -2;
        } else {
            for (int i = 0; i < exams.size(); i++) {
                for (int j = 0; j < matiers.size(); j++) {
                    if (exams.get(i).getMatiereConcour().equals(matiers.get(j).getMatiereConcour())) {
                        z++;
                    }

                }

            }
            System.out.println("ha z==>" + z);
            System.out.println("ha matiers.size()==>" + matiers.size());
            if (z == matiers.size()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public List<Candidat> sortByMoy(List<Candidat> c) {
        List<Condidature> cond = new ArrayList<>();

        for (int i = 0; i < c.size(); i++) {
            Condidature condi = condidatureFacade.findByCandidat(c.get(i));
            cond.add(condi);
        }
        Collections.sort(cond, new Comparator<Condidature>() {
            @Override
            public int compare(Condidature o1, Condidature o2) {
                return Float.valueOf(o2.getMoyenneEcrit()).compareTo(o1.getMoyenneEcrit());
            }
        });
        List<Candidat> candidats = cond.stream().map(x -> x.getCandidat()).collect(Collectors.toList());
        return candidats;
    }

    //==============================//
    //======Methode de Liste=====//
    public List<Candidat> convoquer(Niveau n) {
        List<Candidat> c = new ArrayList<>();
        c = pef.findnonValiderniveau(n);
        if (n == null) {

            return c;
        } else if (c.isEmpty()) {
            return c;
        } else {

            for (Candidat candidat : c) {
                CoeffCalibrage coeff = ccf.findByEtab(candidat.getEtablissement());
                float moy = calculeMoy(candidat) * coeff.getCoeff();
                candidat.setMoyCalibr(moy);

            }

            Collections.sort(c, new Comparator<Candidat>() {
                @Override
                public int compare(Candidat o1, Candidat o2) {
                    return Float.valueOf(o2.getMoyCalibr()).compareTo(o1.getMoyCalibr());
                }
            });

            List<Candidat> ccmin = minConvoquer(c);
            List<Candidat> ccfinal = ccmin;
            if (!crest.isEmpty()) {
                ccfinal = completerConv(crest, ccmin, n);

            }

            crest = new ArrayList<>();
            return ccfinal;

        }

    }

    public List<Candidat> ListeEcrit(Niveau n) {
        List<Candidat> c = pef.findValiderniveau(n);
        List<Candidat> cecrit = new ArrayList<>();
        System.out.println("ha la list ta3 nass=>" + c);
        System.out.println("//===========//");
        int place = cnf.findByNiveau(n).get(0).getNbrDePlaceOrale();
        System.out.println("ha nbr de place==>" + place);
        System.out.println("//===========//");
        if (!c.isEmpty()) {
            for (int i = 0; i < c.size(); i++) {
                if (verfiPasseExam(c.get(i), n) == 1) {
                    System.out.println("ha siyed rah dowez=>" + c.get(i).getNomLat());

                    float moy = 0;
                    Condidature cond = condidatureFacade.findByCandidat(c.get(i));
                    List<ExamCandidat> e = examCandidatFacade.finbByCandidature(cond);
                    System.out.println("ha les exam li dowez==>" + e);
                    if (!e.isEmpty()) {
                        for (int j = 0; j < e.size(); j++) {
                            moy = e.get(j).getNoteCalc() + moy;
                        }
                    }
                    moy = moy / e.size();
                    System.out.println("ha l moyen te3o==>" + moy);
                    cond.setMoyenneEcrit(moy);
                    condidatureFacade.edit(cond);
                    cecrit.add(c.get(i));
                }
            }
            List<Candidat> cfinal = sortByMoy(cecrit);
            return cfinal;
        } else {
            return cecrit;
        }

    }

    public List<ArrayList<Candidat>> listFinal(Niveau n) {
        List<Candidat> candEcrit = ListeEcrit(n);
        int placeA = cnf.findByNiveau(n).get(0).getNbrDePladeAdmis();
        List<ArrayList<Candidat>> l;
        l = new ArrayList<>();
        System.out.println("*****************************************");
        System.out.println("ha la list li jat==>" + candEcrit);
        if (!candEcrit.isEmpty()) {
            List<Condidature> condidatures = new ArrayList<>();
            for (int i = 0; i < candEcrit.size(); i++) {
                condidatures.add(condidatureFacade.findByCandidat(candEcrit.get(i)));
            }
            for (Condidature condidature : condidatures) {
                float moy = (condidature.getMoyenneEcrit() + condidature.getMoyenneOrale()) / 2;
                condidature.setMoyenneGenerale(moy);
                condidatureFacade.edit(condidature);
            }
            Collections.sort(condidatures, new Comparator<Condidature>() {
                @Override
                public int compare(Condidature o1, Condidature o2) {
                    return Float.valueOf(o2.getMoyenneGenerale()).compareTo(o1.getMoyenneGenerale());
                }
            });

            if (condidatures.size() > placeA) {
                l.add((ArrayList<Candidat>) condidatures.stream().map(x -> x.getCandidat()).collect(Collectors.toList()).subList(0, placeA - 1));
                l.add((ArrayList<Candidat>) condidatures.stream().map(x -> x.getCandidat()).collect(Collectors.toList()).subList(placeA, condidatures.size() - 1));
            } else {
                l.add((ArrayList<Candidat>) condidatures.stream().map(x -> x.getCandidat()).collect(Collectors.toList()));
                l.add(new ArrayList<>());
            }

            return l;
        } else {
            l.add(new ArrayList<>());
            l.add(new ArrayList<>());
            return l;
        }

    }

    //=======================================//
    public void printPdf(Niveau n, String typeC, List<Candidat> c) throws JRException, IOException {
        Map<String, Object> params = new HashMap();
        params.put("filiere", n.toString());
        params.put("typeC", typeC);

        PdfUtil.generatePdf(c, params, "Admis-" + typeC + "-" + n.toString(), "/jasper/tetsTable.jasper");
    }

}
