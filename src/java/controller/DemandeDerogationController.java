package controller;

import bean.DemandeDerogation;
import bean.Etudiant;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import java.io.IOException;
import service.DemandeDerogationFacade;

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

@Named("demandeDerogationController")
@SessionScoped
public class DemandeDerogationController implements Serializable {

    @EJB
    private service.DemandeDerogationFacade ejbFacade;
    @EJB
    private service.EtudiantFacade etudiantFacade;
    @EJB
    private service.DemandeFacade demandeFacade;
    private List<DemandeDerogation> items = null;
    private DemandeDerogation selected;
    private Etudiant etudiant;
    private DemandeDerogation demandeEtud;
    private String msg;

    public DemandeDerogationController() {
    }

    public DemandeDerogation getSelected() {
        if (selected == null) {
            selected = new DemandeDerogation();
        }
        return selected;
    }

    public DemandeDerogation findDerogation() {
        Etudiant e = (Etudiant) SessionUtil.getConnectedUser();
        etudiant = etudiantFacade.find(e.getCne());
        DemandeDerogation dd = ejbFacade.findDerogation(etudiant);
        System.out.println("wach demande kayna " + dd);
        return dd;
    }

    public void setSelected(DemandeDerogation selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandeDerogationFacade getFacade() {
        return ejbFacade;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void dDemandeEtud() {
        Etudiant e = (Etudiant) SessionUtil.getConnectedUser();
        etudiant = etudiantFacade.find(e.getCne());
        demandeEtud = demandeFacade.DemDerogation(etudiant);
        System.out.println("ettaaat" + demandeEtud.getEtat());
        if (demandeEtud.getEtat() == 0) {
            msg = "En Traitement";
        } else if (demandeEtud.getEtat() > 0) {
            msg = "Accepted";
        } else {
            msg = "Refused";
        }

        if (demandeEtud == null) {
            demandeEtud = new DemandeDerogation();
        }

    }

    public DemandeDerogation getDemandeEtud() {

        if (demandeEtud == null) {
            demandeEtud = new DemandeDerogation();
        }
        return demandeEtud;
    }

    public void setDemandeEtud(DemandeDerogation demandeEtud) {
        this.demandeEtud = demandeEtud;
    }

    public void prepareCreate() {
        Etudiant e = (Etudiant) SessionUtil.getConnectedUser();
        selected = new DemandeDerogation();
        selected.setEtudiant(etudiantFacade.find(e.getCne()));
        initializeEmbeddableKey();
    }

    public Etudiant getEtudiant() {
        Etudiant e = (Etudiant) SessionUtil.getConnectedUser();
        etudiant = etudiantFacade.find(e.getCne());
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public void send() throws IOException {
        selected.setEtat(0);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeDerogationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            SessionUtil.redirect("../home/Home.xhtml");
        }
    }

    public void create() throws IOException {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeDerogationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        SessionUtil.redirect("../home/Home.xhtml");
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeDerogationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeDerogationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandeDerogation> getItems() {
        if (items == null) {
            items = new ArrayList<>();
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

    public DemandeDerogation getDemandeDerogation(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandeDerogation> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandeDerogation> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DemandeDerogation.class)
    public static class DemandeDerogationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeDerogationController controller = (DemandeDerogationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeDerogationController");
            return controller.getDemandeDerogation(getKey(value));
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
            if (object instanceof DemandeDerogation) {
                DemandeDerogation o = (DemandeDerogation) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandeDerogation.class.getName()});
                return null;
            }
        }

    }

}
