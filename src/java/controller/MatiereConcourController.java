package controller;

import bean.MatiereConcour;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.MatiereConcourFacade;

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
import org.primefaces.event.RowEditEvent;

@Named("matiereConcourController")
@SessionScoped
public class MatiereConcourController implements Serializable {

    @EJB
    private service.MatiereConcourFacade ejbFacade;
    private List<MatiereConcour> items = null;
    private MatiereConcour selected;

    public MatiereConcourController() {
    }

    public MatiereConcour getSelected() {
        if(selected == null){
            selected = new MatiereConcour();
        }
        return selected;
    }

    public void setSelected(MatiereConcour selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MatiereConcourFacade getFacade() {
        return ejbFacade;
    }

    public MatiereConcour prepareCreate() {
        selected = new MatiereConcour();
        initializeEmbeddableKey();
        return selected;
    }
    //=========Methode==========//
    public void supp(MatiereConcour m){
        System.out.println("ha l matiere==>"+m.getId());
        selected = m;
        
    }
    public void creer(){
        create();
        selected = new MatiereConcour();
    }
    public void updatet(){
        update();
        selected = new MatiereConcour();
    }

    //==========================//

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MatiereConcourCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MatiereConcourUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MatiereConcourDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MatiereConcour> getItems() {
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

    public MatiereConcour getMatiereConcour(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<MatiereConcour> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MatiereConcour> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MatiereConcour.class)
    public static class MatiereConcourControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MatiereConcourController controller = (MatiereConcourController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "matiereConcourController");
            return controller.getMatiereConcour(getKey(value));
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
            if (object instanceof MatiereConcour) {
                MatiereConcour o = (MatiereConcour) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MatiereConcour.class.getName()});
                return null;
            }
        }

    }

}
