package controller;

import bean.UserStock;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.UserFacade;
import controller.util.HashageUtil;
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
import org.primefaces.event.RowEditEvent;
import service.UserStockFacade;

@Named("userStockController")
@SessionScoped
public class UserStockController implements Serializable {

    @EJB
    private service.UserStockFacade ejbFacade;
    private List<UserStock> items = null;
    private UserStock selected;

    public UserStockController() {
    }

    public UserStock getSelected() {
        if(selected==null){
            selected=new UserStock();
                          }
        return selected;
    }

    public void setSelected(UserStock selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserStockFacade getFacade() {
        return ejbFacade;
    }

    public UserStock prepareCreate() {
        selected = new UserStock();
        initializeEmbeddableKey();
        return selected;
    }
    public void onEditEvent(RowEditEvent event) {
        ejbFacade.edit((UserStock) event.getObject());
        JsfUtil.addSuccessMessage("User Edited Succesfully");

    }

    public void onCancel() {
        JsfUtil.addErrorMessage("Modification annulée");
    }


    public void create() {
        persist(PersistAction.CREATE,"UserStockCreated");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "UserStockUpdated");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "UserStockDeleted");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UserStock> getItems() {
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
                    selected.setPassword(HashageUtil.sha256(selected.getPassword()));
                    getFacade().edit(selected);
                    selected=null;
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

    public UserStock getUser(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<UserStock> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UserStock> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UserStock.class)
    public static class UserStockControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserStockController controller = (UserStockController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userStockController");
            return controller.getUser(getKey(value));
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
            if (object instanceof UserStock) {
                UserStock o = (UserStock) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UserStock.class.getName()});
                return null;
            }
        }

    }

}
