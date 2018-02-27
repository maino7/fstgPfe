package controller;

import bean.DemandeLicence;
import bean.DemandeLicenceItem;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.DemandeLicenceItemFacade;

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

@Named("demandeLicenceItemController")
@SessionScoped
public class DemandeLicenceItemController implements Serializable {

    @EJB
    private service.DemandeLicenceItemFacade ejbFacade;
    private List<DemandeLicenceItem> items = null;
    private DemandeLicenceItem selected;

    public String Detail(DemandeLicence nouveau) {
        if(nouveau!=null){
            items = ejbFacade.findDLIByDemandeLicenceOrderByPriority(nouveau);
            return "/admin/DetailDemandeLicence";
        }return null;
    }
    
    public DemandeLicenceItemController() {
    }

    public DemandeLicenceItem getSelected() {
        return selected;
    }

    public void setSelected(DemandeLicenceItem selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandeLicenceItemFacade getFacade() {
        return ejbFacade;
    }

    public DemandeLicenceItem prepareCreate() {
        selected = new DemandeLicenceItem();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeLicenceItemCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeLicenceItemUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeLicenceItemDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandeLicenceItem> getItems() {
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

    public DemandeLicenceItem getDemandeLicenceItem(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandeLicenceItem> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandeLicenceItem> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DemandeLicenceItem.class)
    public static class DemandeLicenceItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeLicenceItemController controller = (DemandeLicenceItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeLicenceItemController");
            return controller.getDemandeLicenceItem(getKey(value));
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
            if (object instanceof DemandeLicenceItem) {
                DemandeLicenceItem o = (DemandeLicenceItem) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandeLicenceItem.class.getName()});
                return null;
            }
        }

    }

}
