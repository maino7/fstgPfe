package controller;

import bean.DemandeAttestaion;
import bean.Etudiant;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import java.io.IOException;
import service.DemandeAttestaionFacade;

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

@Named("demandeAttestaionController")
@SessionScoped
public class DemandeAttestaionController implements Serializable {

    @EJB
    private service.EtudiantFacade etudiantFacade;

    @EJB
    private service.DemandeAttestaionFacade ejbFacade;
    private List<DemandeAttestaion> items = null;
    private DemandeAttestaion selected;
    private List<String> selectedList;
    private Etudiant etudiant;
    int res1 = 0;

    public DemandeAttestaionController() {
    }

    public DemandeAttestaion getSelected() {
        if (selected == null) {
            selected = new DemandeAttestaion();
        }
        return selected;
    }

    public void initList() {
        Etudiant e = (Etudiant) SessionUtil.getConnectedUser();
        etudiant = etudiantFacade.find(e.getCne());
        System.out.println("etudiant =" + etudiant);
        res1 = ejbFacade.attestationType(etudiant);
        System.out.println("resultat = " + res1);

    }

    public void addToDemande() throws IOException {
        System.out.println("hahya list " + selectedList);
        List<String> m = selectedList;
        ejbFacade.addDemande(m, etudiant);
        SessionUtil.redirect("../home/Home.xhtml");

    }

    public void setSelected(DemandeAttestaion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public int getRes1() {
        return res1;
    }

    public void setRes1(int res1) {
        this.res1 = res1;
    }

    private DemandeAttestaionFacade getFacade() {
        return ejbFacade;
    }

    public DemandeAttestaion prepareCreate() {
        selected = new DemandeAttestaion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeAttestaionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeAttestaionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeAttestaionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandeAttestaion> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<String> getSelectedList() {
        if (selectedList == null) {
            selectedList = new ArrayList<>();
        }
        return selectedList;
    }

    public void setSelectedList(List<String> selectedList) {
        this.selectedList = selectedList;
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

    public DemandeAttestaion getDemandeAttestaion(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandeAttestaion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandeAttestaion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DemandeAttestaion.class)
    public static class DemandeAttestaionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeAttestaionController controller = (DemandeAttestaionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeAttestaionController");
            return controller.getDemandeAttestaion(getKey(value));
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
            if (object instanceof DemandeAttestaion) {
                DemandeAttestaion o = (DemandeAttestaion) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandeAttestaion.class.getName()});
                return null;
            }
        }

    }

}
