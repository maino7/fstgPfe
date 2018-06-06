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
        emailFacade.SendMail(selected.getCne(), selected.getTelephone(), selected.getAdresse(), selected.getAnneeBac(), selected.getAnneeInscriptionEnsSup(), selected.getAnneeInscriptionEtab(), selected.getAnneeInscriptionUniv(), selected.getCin(), selected.getDateInscription(), selected.getDateNaissance(), selected.getEmail(), selected.getEtablissementPreInsc(), selected.getLieuNaissance(), selected.getNomAr(), selected.getPrenomAr(), selected.getNomLat(), selected.getPrenomLat(), selected.getNoteS1(), selected.getNoteS2(), selected.getNoteS3(), selected.getNoteS4(), selected.getNoteS5(), selected.getNoteS6(), selected.getEtablissement(), selected.getDernierDiplome(), concourNiveau, selected.getOptionBac());
    }

    public Condidature getCondidature() {
        if (condidature == null) {
            condidature = new Condidature();
        }
        return condidature;
    }

    public void inscription() {
        ejbFacade.creerMaster(selected, condidature);

    }

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
        }else {
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
        if(cne == null){
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
    

    
    

}
