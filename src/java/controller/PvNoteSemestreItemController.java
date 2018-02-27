package controller;

import bean.PvNoteSemestreItem;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.PvNoteSemestreItemFacade;

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

@Named("pvNoteSemestreItemController")
@SessionScoped
public class PvNoteSemestreItemController implements Serializable {

    @EJB
    private service.PvNoteSemestreItemFacade ejbFacade;
    private List<PvNoteSemestreItem> items = null;
    private PvNoteSemestreItem selected;

    public PvNoteSemestreItemController() {
    }

    public PvNoteSemestreItem getSelected() {
        return selected;
    }

    public void setSelected(PvNoteSemestreItem selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PvNoteSemestreItemFacade getFacade() {
        return ejbFacade;
    }

    public PvNoteSemestreItem prepareCreate() {
        selected = new PvNoteSemestreItem();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PvNoteSemestreItemCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PvNoteSemestreItemUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PvNoteSemestreItemDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PvNoteSemestreItem> getItems() {
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

    public PvNoteSemestreItem getPvNoteSemestreItem(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<PvNoteSemestreItem> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PvNoteSemestreItem> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PvNoteSemestreItem.class)
    public static class PvNoteSemestreItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PvNoteSemestreItemController controller = (PvNoteSemestreItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pvNoteSemestreItemController");
            return controller.getPvNoteSemestreItem(getKey(value));
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
            if (object instanceof PvNoteSemestreItem) {
                PvNoteSemestreItem o = (PvNoteSemestreItem) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PvNoteSemestreItem.class.getName()});
                return null;
            }
        }

    }

}
