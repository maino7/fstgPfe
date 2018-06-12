package controller;

import bean.Candidat;
import bean.ConcourNiveau;
import bean.Condidature;
import bean.Niveau;
import bean.Section;
import controller.util.EmailUtil;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.ServerConfigUtil;
import java.io.IOException;
import service.CandidatFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import net.sf.jasperreports.engine.JRException;

import javax.mail.MessagingException;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import service.ConcourExamMatiereFacade;
import service.ConcourNiveauFacade;
import service.CondidatureFacade;
import service.EmailFacade;
import service.NiveauFacade;
import service.PieceEtudiantFacade;

@Named("candidatController")
@SessionScoped
public class CandidatController implements Serializable {

    @EJB
    private service.CandidatFacade ejbFacade;
    @EJB
    private service.CondidatureFacade condidatureFacade;
    @EJB
    private service.ConcourNiveauFacade concourNiveauFacade;
    @EJB
    private service.ConcourExamMatiereFacade concourExamMatiereFacade;
    @EJB
    private service.PieceEtudiantFacade pieceEtudiantFacade;
    @EJB
    private service.NiveauFacade niveauFacade;
    @EJB
    private service.EmailFacade emailFacade;
    @EJB
    private service.SemestreFacade semestreFacade;
    private List<Candidat> items = null;
    private List<Candidat> candidatsAdmis = null;
    private List<Candidat> candidatsEcrit = null;
    public static List<Candidat> candidatsFinalA = null;
    public static List<Candidat> candidatsFinalT = null;
    private Candidat selected;
    private Condidature condidature;
    private int typeInscription;
    private UploadedFile uploadedFile;
    private String fileName;
    private boolean male;
    private boolean female;
    private boolean handicap;
    private boolean nonHandicap;
    private String cne;
    private Section section;
    private Niveau niveau;
    private Niveau niveau2;
    private List<Niveau> niveaus = null;
    private String test;
    ConcourNiveau concourNiveau = new ConcourNiveau();
    private float noteS1;
    private float noteS2;
    private float noteS3;
    private float noteS4;
    private float noteS5;
    private float noteS6;
    private String modeDeValidation1;
    private String anneeDeValidation1;
    private int nombreDinscription1;
    private int valideApresRattrapage1;
    private String modeDeValidation2;
    private String anneeDeValidation2;
    private int nombreDinscription2;
    private int valideApresRattrapage2;
    private String modeDeValidation3;
    private String anneeDeValidation3;
    private int nombreDinscription3;
    private int valideApresRattrapage3;
    private String modeDeValidation4;
    private String anneeDeValidation4;
    private int nombreDinscription4;
    private int valideApresRattrapage4;
    private String modeDeValidation5;
    private String anneeDeValidation5;
    private int nombreDinscription5;
    private int valideApresRattrapage5;
    private String modeDeValidation6;
    private String anneeDeValidation6;
    private int nombreDinscription6;
    private int valideApresRattrapage6;

    public CandidatController() {
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public ConcourNiveau getConcourNiveau() {
        if (concourNiveau == null) {
            concourNiveau = new ConcourNiveau();
        }
        return concourNiveau;
    }

    public void setConcourNiveau(ConcourNiveau concourNiveau) {
        this.concourNiveau = concourNiveau;
    }

    public void save() throws MessagingException {
        System.out.println("ha selected==>" + selected);
        System.out.println("ha condidature==>" + condidature);
        int i = getFacade().creerCycle(selected, getConcourNiveau());
        savePdf();
        System.out.println("ha save daz wa akhiran");
        emailFacade.SendMail(selected.getCne(), selected.getTelephone(), selected.getAdresse(), selected.getAnneeBac(), selected.getAnneeInscriptionEnsSup(), selected.getAnneeInscriptionEtab(), selected.getAnneeInscriptionUniv(), selected.getCin(), selected.getDateInscription(), selected.getDateNaissance(), selected.getEmail(), selected.getEtablissementPreInsc(), selected.getLieuNaissance(), selected.getNomAr(), selected.getPrenomAr(), selected.getNomLat(), selected.getPrenomLat(), selected.getNoteS1(), selected.getNoteS2(), selected.getNoteS3(), selected.getNoteS4(), selected.getNoteS5(), selected.getNoteS6(), selected.getEtablissement(), selected.getDernierDiplome(), concourNiveau, selected.getOptionBac(), selected.getAnneeInscriptionEnsSup(), selected.getAnneeInscriptionUniv(), selected.getAnneeInscriptionEtab(), selected.getProfessionDeLaMere());
    }

    public void saveMaster() throws MessagingException {
        ejbFacade.creerMaster(selected, getConcourNiveau());
        emailFacade.SendMailMaster(selected.getCne(), selected.getTelephone(), selected.getAdresse(), selected.getAnneeBac(), selected.getProfessionDeLaMere(), selected.getAnneeInscriptionEnsSup(), selected.getAnneeInscriptionEtab(), selected.getAnneeInscriptionUniv(), selected.getCin(), selected.getDateInscription(), selected.getDateNaissance(), selected.getEmail(), selected.getEtablissementPreInsc(), selected.getLieuNaissance(), selected.getNomAr(), selected.getPrenomAr(), selected.getNomLat(), selected.getPrenomLat(), selected.getEtablissement(), selected.getDernierDiplome(), concourNiveau, selected.getMentionDiplome(), selected.getOptionLicence(), selected.getAnneeObtLicence(), noteS1, noteS2, noteS3, noteS4, noteS5, noteS6, modeDeValidation1, anneeDeValidation1, nombreDinscription1, valideApresRattrapage1, modeDeValidation2, anneeDeValidation2, nombreDinscription2, valideApresRattrapage2, modeDeValidation3, anneeDeValidation3, nombreDinscription3, valideApresRattrapage3, modeDeValidation4, anneeDeValidation4, nombreDinscription4, valideApresRattrapage4, modeDeValidation5, anneeDeValidation5, nombreDinscription5, valideApresRattrapage5, modeDeValidation6, anneeDeValidation6, nombreDinscription6, valideApresRattrapage6);
        semestreFacade.saveSemestres(noteS1, noteS2, noteS3, noteS4, noteS5, noteS6, modeDeValidation1, anneeDeValidation1, nombreDinscription1, valideApresRattrapage1, modeDeValidation2, anneeDeValidation2, nombreDinscription2, valideApresRattrapage2, modeDeValidation3, anneeDeValidation3, nombreDinscription3, valideApresRattrapage3, modeDeValidation4, anneeDeValidation4, nombreDinscription4, valideApresRattrapage4, modeDeValidation5, anneeDeValidation5, nombreDinscription5, valideApresRattrapage5, modeDeValidation6, anneeDeValidation6, nombreDinscription6, valideApresRattrapage6, selected);

    }

//    public void saveSemesteresMaster() {
//        semestreFacade.saveSemestres(noteS1, noteS2, noteS3, noteS4, noteS5, noteS6, modeDeValidation1, anneeDeValidation1, nombreDinscription1, valideApresRattrapage1, modeDeValidation2, anneeDeValidation2, nombreDinscription2, valideApresRattrapage2, modeDeValidation3, anneeDeValidation3, nombreDinscription3, valideApresRattrapage3, modeDeValidation4, anneeDeValidation4, nombreDinscription4, valideApresRattrapage4, modeDeValidation5, anneeDeValidation5, nombreDinscription5, valideApresRattrapage5, modeDeValidation6, anneeDeValidation6, nombreDinscription6, valideApresRattrapage6, selected);
//    }
    public Condidature getCondidature() {
        if (condidature == null) {
            condidature = new Condidature();
        }
        return condidature;
    }

//    public void inscription() {
//        ejbFacade.creerMaster(selected, condidature);
//
//    }
    public void setCondidature(Condidature condidature) {
        this.condidature = condidature;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void checkOnce0(Boolean sexe, Candidat candidat) {

        if (male = true) {
            candidat.setSexe(sexe);
            female = false;
        }

        if (male == false) {
            candidat.setSexe(sexe);
            female = true;
        }
    }

    public void checkMany0(Boolean sexe, Candidat candidat) {
        if (female == true) {
            candidat.setSexe(sexe);
            male = false;
        }
        if (female == false) {
            candidat.setSexe(sexe);
            male = true;
        }
    }

    public void checkOnce(Boolean sexe) {

        if (male == true) {
            female = false;
        }
        if (male == false) {
            female = true;
        }
    }

    public void checkMany() {
        if (female == true) {
            male = false;
        }
        if (female == false) {
            male = true;
        }
    }

    public void checkOnce1() {
        if (handicap == true) {
            nonHandicap = false;
        }
        if (handicap == false) {
            nonHandicap = true;
        }
    }

    public void checkMany1() {
        if (nonHandicap == true) {
            handicap = false;
        }
        if (nonHandicap == false) {
            handicap = true;
        }
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public void upload(FileUploadEvent event) {
        fileName = event.getFile().getFileName();
        System.out.println("ha lfilename" + fileName);

        uploadedFile = event.getFile();
        System.out.println("ha  l file" + uploadedFile);

        JsfUtil.addSuccessMessage("File uploaded");
        System.out.println("ha lmessage daz ");

    }

    public void savePdf() {
        System.out.println("hanta dkhltiiiiii");
        // setTest(ServerConfigUtil.uploadPdf(uploadedFile, "pdf", fileName));
        // System.out.println("hahuwa lattribut" + test);
        selected.setLastPdf(ServerConfigUtil.uploadPdf(uploadedFile, fileName, "pdf"));
        System.out.println("hanta hanta khrjti");
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public boolean isHandicap() {
        return handicap;
    }

    public void setHandicap(boolean handicap) {
        this.handicap = handicap;
    }

    public boolean isNonHandicap() {
        return nonHandicap;
    }

    public void setNonHandicap(boolean nonHandicap) {
        this.nonHandicap = nonHandicap;
    }

    public Candidat getSelected() {
        if (selected == null) {
            selected = new Candidat();
        }
        return selected;
    }

    public void setSelected(Candidat selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CandidatFacade getFacade() {
        return ejbFacade;
    }

    //========Methode==========//
    public void niveauBySection() {

        niveaus = niveauFacade.findBySection(section);
        System.out.println("ha les niveau===>" + niveaus);
    }

    public void findBycreataria() {
        items = null;
        System.out.println("ha niveau==>" + niveau + "o ha section==>" + section + "o ha cne==>" + cne);
        items = pieceEtudiantFacade.findByNiveauAndSection(niveau, section, cne);

        System.out.println("ha l items===>" + items);
    }

    public int validerCandidat() {
        System.out.println("ha selected==>" + selected);

        if (concourNiveauFacade.calculePlaceRest(niveau) == -1) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Place limite atteint"));
            return -1;
        } else {
            condidatureFacade.validerCandidature(selected);

            items.remove(items.indexOf(selected));
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Candidat valider"));
            System.out.println("ha l items==>" + items);
            return 1;
        }

    }

    public void walo() {
        System.out.println("hahowa dkhel l walo");
        System.out.println("ha niv===>" + niveau);
        candidatsAdmis = getFacade().convoquer(niveau);

    }

    public void listEcrit() {
        int i = concourExamMatiereFacade.findIfEnd(niveau);
        if (i == 1) {
            candidatsEcrit = getFacade().ListeEcrit(niveau);
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Le concours est en cours"));
        }

    }

    public void listFinal() {
        getFacade().listFinal(niveau);
    }

    public void printList() throws JRException, IOException {
        System.out.println("Imprimer====>OK" + niveau);
        if (candidatsAdmis.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "List vide", "List vide"));
        } else {
            getFacade().printPdf(niveau, "l'écrit");
            FacesContext.getCurrentInstance().responseComplete();
        }

    }

    public void printList2() throws JRException, IOException {
        System.out.println("Imprimer====>OK" + niveau);
        if (candidatsEcrit.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "List vide", "List vide"));
        } else {
            getFacade().printPdf(niveau, "l'orale");
            FacesContext.getCurrentInstance().responseComplete();
        }

    }

    //================//
    public Candidat prepareCreate() {
        selected = new Candidat();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CandidatCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CandidatUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CandidatDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Candidat> getItems() {
        if (items == null) {
            items = getFacade().findNonvalider();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Candidat getCandidat(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Candidat> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Candidat> getItemsAvailableSelectOne() {
        return getFacade().findAll();

    }

    @FacesConverter(forClass = Candidat.class)
    public static class CandidatControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CandidatController controller = (CandidatController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "candidatController");
            return controller.getCandidat(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Candidat) {
                Candidat o = (Candidat) object;
                return getStringKey(o.getCne());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Candidat.class.getName()});
                return null;
            }
        }

    }
//test

    public void testwalo(Candidat e) {
        System.out.println("hahowa dkhel");
        selected = e;

    }

    public int getTypeInscription() {
        return typeInscription;
    }

    public void setTypeInscription(int typeInscription) {
        this.typeInscription = typeInscription;
    }

    public Section getSection() {
        if (section == null) {
            section = new Section();
        }
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public List<Niveau> getNiveaus() {

        return niveaus;
    }

    public void setNiveaus(List<Niveau> niveaus) {
        this.niveaus = niveaus;
    }

    public Niveau getNiveau() {
        if (niveau == null) {
            niveau = new Niveau();
        }
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public String getCne() {
        if (cne == null) {
            cne = "";
        }
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public Niveau getNiveau2() {
        if (niveau2 == null) {
            niveau2 = new Niveau();
        }
        return niveau2;
    }

    public void setNiveau2(Niveau niveau2) {
        this.niveau2 = niveau2;
    }

    public List<Candidat> getCandidatsAdmis() {
        return candidatsAdmis;
    }

    public void setCandidatsAdmis(List<Candidat> candidatsAdmis) {
        this.candidatsAdmis = candidatsAdmis;
    }

    public List<Candidat> getCandidatsEcrit() {
        return candidatsEcrit;
    }

    public void setCandidatsEcrit(List<Candidat> candidatsEcrit) {
        this.candidatsEcrit = candidatsEcrit;
    }

    public List<Candidat> getCandidatsFinalA() {
        return candidatsFinalA;
    }

    public static List<Candidat> getCandidatsFinalT() {
        return candidatsFinalT;
    }

    public static void setCandidatsFinalT(List<Candidat> candidatsFinalT) {
        CandidatController.candidatsFinalT = candidatsFinalT;
    }

    public CandidatFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CandidatFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public CondidatureFacade getCondidatureFacade() {
        return condidatureFacade;
    }

    public void setCondidatureFacade(CondidatureFacade condidatureFacade) {
        this.condidatureFacade = condidatureFacade;
    }

    public ConcourNiveauFacade getConcourNiveauFacade() {
        return concourNiveauFacade;
    }

    public void setConcourNiveauFacade(ConcourNiveauFacade concourNiveauFacade) {
        this.concourNiveauFacade = concourNiveauFacade;
    }

    public ConcourExamMatiereFacade getConcourExamMatiereFacade() {
        return concourExamMatiereFacade;
    }

    public void setConcourExamMatiereFacade(ConcourExamMatiereFacade concourExamMatiereFacade) {
        this.concourExamMatiereFacade = concourExamMatiereFacade;
    }

    public PieceEtudiantFacade getPieceEtudiantFacade() {
        return pieceEtudiantFacade;
    }

    public void setPieceEtudiantFacade(PieceEtudiantFacade pieceEtudiantFacade) {
        this.pieceEtudiantFacade = pieceEtudiantFacade;
    }

    public NiveauFacade getNiveauFacade() {
        return niveauFacade;
    }

    public void setNiveauFacade(NiveauFacade niveauFacade) {
        this.niveauFacade = niveauFacade;
    }

    public EmailFacade getEmailFacade() {
        return emailFacade;
    }

    public void setEmailFacade(EmailFacade emailFacade) {
        this.emailFacade = emailFacade;
    }

    public float getNoteS1() {
        return noteS1;
    }

    public void setNoteS1(float noteS1) {
        this.noteS1 = noteS1;
    }

    public float getNoteS2() {
        return noteS2;
    }

    public void setNoteS2(float noteS2) {
        this.noteS2 = noteS2;
    }

    public float getNoteS3() {
        return noteS3;
    }

    public void setNoteS3(float noteS3) {
        this.noteS3 = noteS3;
    }

    public float getNoteS4() {
        return noteS4;
    }

    public void setNoteS4(float noteS4) {
        this.noteS4 = noteS4;
    }

    public float getNoteS5() {
        return noteS5;
    }

    public void setNoteS5(float noteS5) {
        this.noteS5 = noteS5;
    }

    public float getNoteS6() {
        return noteS6;
    }

    public void setNoteS6(float noteS6) {
        this.noteS6 = noteS6;
    }

    public String getModeDeValidation1() {
        return modeDeValidation1;
    }

    public void setModeDeValidation1(String modeDeValidation1) {
        this.modeDeValidation1 = modeDeValidation1;
    }

    public String getAnneeDeValidation1() {
        return anneeDeValidation1;
    }

    public void setAnneeDeValidation1(String anneeDeValidation1) {
        this.anneeDeValidation1 = anneeDeValidation1;
    }

    public int getNombreDinscription1() {
        return nombreDinscription1;
    }

    public void setNombreDinscription1(int nombreDinscription1) {
        this.nombreDinscription1 = nombreDinscription1;
    }

    public int getValideApresRattrapage1() {
        return valideApresRattrapage1;
    }

    public void setValideApresRattrapage1(int valideApresRattrapage1) {
        this.valideApresRattrapage1 = valideApresRattrapage1;
    }

    public String getModeDeValidation2() {
        return modeDeValidation2;
    }

    public void setModeDeValidation2(String modeDeValidation2) {
        this.modeDeValidation2 = modeDeValidation2;
    }

    public String getAnneeDeValidation2() {
        return anneeDeValidation2;
    }

    public void setAnneeDeValidation2(String anneeDeValidation2) {
        this.anneeDeValidation2 = anneeDeValidation2;
    }

    public int getNombreDinscription2() {
        return nombreDinscription2;
    }

    public void setNombreDinscription2(int nombreDinscription2) {
        this.nombreDinscription2 = nombreDinscription2;
    }

    public int getValideApresRattrapage2() {
        return valideApresRattrapage2;
    }

    public void setValideApresRattrapage2(int valideApresRattrapage2) {
        this.valideApresRattrapage2 = valideApresRattrapage2;
    }

    public String getModeDeValidation3() {
        return modeDeValidation3;
    }

    public void setModeDeValidation3(String modeDeValidation3) {
        this.modeDeValidation3 = modeDeValidation3;
    }

    public String getAnneeDeValidation3() {
        return anneeDeValidation3;
    }

    public void setAnneeDeValidation3(String anneeDeValidation3) {
        this.anneeDeValidation3 = anneeDeValidation3;
    }

    public int getNombreDinscription3() {
        return nombreDinscription3;
    }

    public void setNombreDinscription3(int nombreDinscription3) {
        this.nombreDinscription3 = nombreDinscription3;
    }

    public int getValideApresRattrapage3() {
        return valideApresRattrapage3;
    }

    public void setValideApresRattrapage3(int valideApresRattrapage3) {
        this.valideApresRattrapage3 = valideApresRattrapage3;
    }

    public String getModeDeValidation4() {
        return modeDeValidation4;
    }

    public void setModeDeValidation4(String modeDeValidation4) {
        this.modeDeValidation4 = modeDeValidation4;
    }

    public String getAnneeDeValidation4() {
        return anneeDeValidation4;
    }

    public void setAnneeDeValidation4(String anneeDeValidation4) {
        this.anneeDeValidation4 = anneeDeValidation4;
    }

    public int getNombreDinscription4() {
        return nombreDinscription4;
    }

    public void setNombreDinscription4(int nombreDinscription4) {
        this.nombreDinscription4 = nombreDinscription4;
    }

    public int getValideApresRattrapage4() {
        return valideApresRattrapage4;
    }

    public void setValideApresRattrapage4(int valideApresRattrapage4) {
        this.valideApresRattrapage4 = valideApresRattrapage4;
    }

    public String getModeDeValidation5() {
        return modeDeValidation5;
    }

    public void setModeDeValidation5(String modeDeValidation5) {
        this.modeDeValidation5 = modeDeValidation5;
    }

    public String getAnneeDeValidation5() {
        return anneeDeValidation5;
    }

    public void setAnneeDeValidation5(String anneeDeValidation5) {
        this.anneeDeValidation5 = anneeDeValidation5;
    }

    public int getNombreDinscription5() {
        return nombreDinscription5;
    }

    public void setNombreDinscription5(int nombreDinscription5) {
        this.nombreDinscription5 = nombreDinscription5;
    }

    public int getValideApresRattrapage5() {
        return valideApresRattrapage5;
    }

    public void setValideApresRattrapage5(int valideApresRattrapage5) {
        this.valideApresRattrapage5 = valideApresRattrapage5;
    }

    public String getModeDeValidation6() {
        return modeDeValidation6;
    }

    public void setModeDeValidation6(String modeDeValidation6) {
        this.modeDeValidation6 = modeDeValidation6;
    }

    public String getAnneeDeValidation6() {
        return anneeDeValidation6;
    }

    public void setAnneeDeValidation6(String anneeDeValidation6) {
        this.anneeDeValidation6 = anneeDeValidation6;
    }

    public int getNombreDinscription6() {
        return nombreDinscription6;
    }

    public void setNombreDinscription6(int nombreDinscription6) {
        this.nombreDinscription6 = nombreDinscription6;
    }

    public int getValideApresRattrapage6() {
        return valideApresRattrapage6;
    }

    public void setValideApresRattrapage6(int valideApresRattrapage6) {
        this.valideApresRattrapage6 = valideApresRattrapage6;
    }

}
