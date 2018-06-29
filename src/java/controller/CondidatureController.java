package controller;

import bean.Candidat;
import bean.CoeffCalibrage;
import bean.ConcourExamMatiere;
import bean.Condidature;
import bean.ExamCandidat;
import bean.Niveau;
import bean.Section;
import bean.UserStock;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.CondidatureFacade;

import java.io.Serializable;
import java.nio.charset.CodingErrorAction;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

@Named("condidatureController")
@SessionScoped
public class CondidatureController implements Serializable {

    @EJB
    private service.CondidatureFacade ejbFacade;
    @EJB
    private service.NiveauFacade niveauFacade;
    @EJB
    private service.PieceEtudiantFacade pieceEtudiantFacade;
    @EJB
    private service.ExamCandidatFacade examCandidatFacade;
    @EJB
    private service.ConcourExamMatiereFacade concourExamMatiereFacade;
    private List<Condidature> items = null;
    private Condidature selected;
    private Section section;
    private List<Niveau> niveaus = null;
    private List<ConcourExamMatiere> matieres = null;
    private List<ExamCandidat> exams = null;
    private Niveau niveau;
    private String cne;
    private int editable;
    private float noteF;
    private float noteO;
    private String redirecT = "detailleNote";

    public CondidatureController() {
    }

    public Condidature getSelected() {
        if (selected == null) {
            selected = new Condidature();
        }
        return selected;
    }

    public void setSelected(Condidature selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CondidatureFacade getFacade() {
        return ejbFacade;
    }

    //=====Methode=====//
    public void niveauBySection() {

        niveaus = niveauFacade.findBySection(section);
        System.out.println("ha les niveau===>" + niveaus);
    }

    public void findBycreataria() {
        System.out.println("ha cne li jat==>" + cne);
        items = null;
        items = pieceEtudiantFacade.findByNiveauAndSection2(niveau, section, cne);
    }

    public String test(Condidature c) {
        return examCandidatFacade.findByCandidature(c);
    }

    public String affectModal() {
        return examCandidatFacade.findByCandidature(selected);
    }

    public void affect(Condidature c) {
        if (selected == null) {
            selected = new Condidature();
        }
        selected = c;
        matieres = concourExamMatiereFacade.findByCondidature(c);
        redirecT = "detailleNote";
        System.out.println("ha matere==>" + matieres);
    }

    public void affect2(Condidature c) {
        if (selected == null) {
            selected = new Condidature();
        }
        selected = c;
        redirecT = "modifNote";
        exams = examCandidatFacade.finbByCandidature(c);
        System.out.println("ha exams==>" + exams);
    }

    public void enterNote(ConcourExamMatiere cEx) {
        System.out.println("ha note==>" + noteF);
        int r = examCandidatFacade.createExam(selected, cEx, noteF);
        if (r == -2) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Note > 20 !"));
            noteF = 0;
        } else if (r == -1) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Erro"));
            noteF = 0;
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage());
            //FacesMessage.SEVERITY_ERROR,"Error Title", "Error Message"
            noteF = 0;
            matieres.remove(matieres.indexOf(cEx));
            update();
        }

    }

    public int test2(ConcourExamMatiere cem) {
        return examCandidatFacade.findByCondidatureMatiere(selected, cem);
    }

    public void editNote(ExamCandidat ex) {
        System.out.println("ha la note ==>" + noteF);
        ex.setNoteCalc(noteF);
        examCandidatFacade.edit(ex);
        noteF = 0;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("note modifier"));
    }

    public void editeOrale() {
        System.out.println("ha la note li jat orale===+>" + selected.getMoyenneOrale());
        getFacade().edit(selected);
        selected = new Condidature();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("note modifier"));

    }

    public void onEditEvent(RowEditEvent event) {
        examCandidatFacade.edit((ExamCandidat) event.getObject());
        JsfUtil.addSuccessMessage("User Edited Succesfully");

    }

    public void onCancel() {
        JsfUtil.addErrorMessage("Modification annulée");
    }
    
     public void onRowEdit(RowEditEvent event) {
         ExamCandidat cf = (ExamCandidat)event.getObject();
                if(cf.getNoteCalc() > 20){
                    System.out.println("dkhel l hadi");
                    FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Note > 20"));
                } else{
        examCandidatFacade.edit(cf);
        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("success"));
                }
    }

    //================//
    public Condidature prepareCreate() {
        selected = new Condidature();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CondidatureCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CondidatureUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CondidatureDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Condidature> getItems() {
        if (items == null) {
            items = getFacade().findAllnonReussi();
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

    public Condidature getCondidature(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Condidature> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Condidature> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Condidature.class)
    public static class CondidatureControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CondidatureController controller = (CondidatureController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "condidatureController");
            return controller.getCondidature(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Condidature) {
                Condidature o = (Condidature) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Condidature.class.getName()});
                return null;
            }
        }

    }

    public Section getSection() {
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
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public List<ConcourExamMatiere> getMatieres() {
        return matieres;
    }

    public void setMatieres(List<ConcourExamMatiere> matieres) {
        this.matieres = matieres;
    }

    public float getNoteF() {

        return noteF;
    }

    public void setNoteF(float noteF) {
        this.noteF = noteF;
    }

    public List<ExamCandidat> getExams() {

        return exams;
    }

    public void setExams(List<ExamCandidat> exams) {
        this.exams = exams;
    }

    public String getRedirecT() {
        return redirecT;
    }

    public void setRedirecT(String redirecT) {
        this.redirecT = redirecT;
    }

    public int getEditable() {
        return editable;
    }

    public void setEditable(int editable) {
        this.editable = editable;
    }

    public float getNoteO() {
        return noteO;
    }

    public void setNoteO(float noteO) {
        this.noteO = noteO;
    }

}
