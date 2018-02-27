package controller;

import bean.DemandeReleveNoteItem;
import bean.Etudiant;
import bean.Semestre;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import java.io.IOException;
import service.DemandeReleveNoteItemFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import service.EtudiantFacade;
import service.NoteSemestreFacade;

@Named("demandeReleveNoteItemController")
@SessionScoped
public class DemandeReleveNoteItemController implements Serializable {

    @EJB
    private service.DemandeReleveNoteItemFacade ejbFacade;
    @EJB
    private service.NoteSemestreFacade noteSemestreFacade;
    @EJB
    private service.DemandeReleveNoteFacade demandeReleveNoteFacade;
    @EJB
    private service.EtudiantFacade etudiantFacade;
    private List<DemandeReleveNoteItem> items = null;
    private DemandeReleveNoteItem selected;
    //for DemandeReleveNote.xhtml
    private List<String> semestres;
    private List<String> selectedSemestres;
    private Etudiant etudiant;

    public DemandeReleveNoteItemController() {
    }

    public DemandeReleveNoteItem getSelected() {
        return selected;
    }
//initialis the list for the student to shose releve the note

    public void initSemestres() {
        Etudiant e = (Etudiant) SessionUtil.getConnectedUser();
        etudiant = etudiantFacade.find(e.getCne());
        List<Semestre> seme = noteSemestreFacade.findSemestresForReleveNote(etudiant);
        int i;
        List<String> lst = new ArrayList<>();
        for (i = 0; i < seme.size(); i++) {
            String s = "" + seme.get(i);
            lst.add(s);
        }
        setSemestres(lst);
    }

    //Create a Demande with Demande Items
    public void addToDemande() throws IOException {
        List<String> m = selectedSemestres;
        System.out.println("lista dyal les string "+m);
        System.out.println("etud f contro "+etudiant);
        demandeReleveNoteFacade.prepareDemande(m, etudiant);
        JsfUtil.addErrorMessage("demande successfully delivered");
        SessionUtil.redirect("../home/Home.xhtml");

    }

    public List<String> getSemestres() {
        if (semestres == null) {
            semestres = new ArrayList<>();
        }
        return semestres;
    }

    public void initView()
    {
        selectedSemestres=new ArrayList<>();
    }
    public List<String> getSelectedSemestres() {
        if (selectedSemestres == null) {
            selectedSemestres = new ArrayList<>();
        }
        return selectedSemestres;
    }

    public DemandeReleveNoteItemFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DemandeReleveNoteItemFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public NoteSemestreFacade getNoteSemestreFacade() {
        return noteSemestreFacade;
    }

    public void setNoteSemestreFacade(NoteSemestreFacade noteSemestreFacade) {
        this.noteSemestreFacade = noteSemestreFacade;
    }

    public EtudiantFacade getEtudiantFacade() {
        if (etudiantFacade == null) {
            etudiantFacade = new EtudiantFacade();
        }
        return etudiantFacade;
    }

    public void setEtudiantFacade(EtudiantFacade etudiantFacade) {
        this.etudiantFacade = etudiantFacade;
    }

    public void setSelectedSemestres(List<String> selectedSemestres) {
        this.selectedSemestres = selectedSemestres;
    }

    public void setSemestres(List<String> semestres) {
        this.semestres = semestres;
    }

    public Etudiant getEtudiant() {
        Etudiant e = (Etudiant) SessionUtil.getConnectedUser();
        etudiant = etudiantFacade.find(e.getCne());
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public void setSelected(DemandeReleveNoteItem selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandeReleveNoteItemFacade getFacade() {
        return ejbFacade;
    }

    public DemandeReleveNoteItem prepareCreate() {
        selected = new DemandeReleveNoteItem();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeReleveNoteItemCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeReleveNoteItemUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeReleveNoteItemDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandeReleveNoteItem> getItems() {
        if (items == null) {
            items = getFacade().findAll();
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

    public DemandeReleveNoteItem getDemandeReleveNoteItem(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandeReleveNoteItem> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandeReleveNoteItem> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DemandeReleveNoteItem.class)
    public static class DemandeReleveNoteItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeReleveNoteItemController controller = (DemandeReleveNoteItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeReleveNoteItemController");
            return controller.getDemandeReleveNoteItem(getKey(value));
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
            if (object instanceof DemandeReleveNoteItem) {
                DemandeReleveNoteItem o = (DemandeReleveNoteItem) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandeReleveNoteItem.class.getName()});
                return null;
            }
        }

    }

}
