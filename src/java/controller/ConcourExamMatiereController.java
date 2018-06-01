package controller;

import bean.ConcourExamMatiere;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.ConcourExamMatiereFacade;

import java.io.Serializable;
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

@Named("concourExamMatiereController")
@SessionScoped
public class ConcourExamMatiereController implements Serializable {

    @EJB
    private service.ConcourExamMatiereFacade ejbFacade;
    private List<ConcourExamMatiere> items = null;
    private ConcourExamMatiere selected;
    private int rendere;

    public ConcourExamMatiereController() {
    }

    public ConcourExamMatiere getSelected() {
        return selected;
    }

    public void setSelected(ConcourExamMatiere selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ConcourExamMatiereFacade getFacade() {
        return ejbFacade;
    }

    public ConcourExamMatiere prepareCreate() {
        selected = new ConcourExamMatiere();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ConcourExamMatiereCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ConcourExamMatiereUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ConcourExamMatiereDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ConcourExamMatiere> getItems() {
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

    public ConcourExamMatiere getConcourExamMatiere(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<ConcourExamMatiere> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ConcourExamMatiere> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ConcourExamMatiere.class)
    public static class ConcourExamMatiereControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConcourExamMatiereController controller = (ConcourExamMatiereController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "concourExamMatiereController");
            return controller.getConcourExamMatiere(getKey(value));
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
            if (object instanceof ConcourExamMatiere) {
                ConcourExamMatiere o = (ConcourExamMatiere) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ConcourExamMatiere.class.getName()});
                return null;
            }
        }

    }

    public int getRendere() {
        return rendere;
    }

    public void setRendere(int rendere) {
        this.rendere = rendere;
    }
    

}
