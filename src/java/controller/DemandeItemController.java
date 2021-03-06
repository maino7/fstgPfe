package controller;

import bean.Demande;
import bean.DemandeItem;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.DemandeItemFacade;

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


@Named("demandeItemController")
@SessionScoped
public class DemandeItemController implements Serializable {


    @EJB private service.DemandeItemFacade ejbFacade;
    private List<DemandeItem> items = null;
    private DemandeItem selected;

    public DemandeItemController() {
    }
    
    //============= Methode ==================

    public void findByDemande(Demande demande)
    {
        System.out.println("========= findByDemande Controller ======");
        items = ejbFacade.findByDemande(demande.getId());
        System.out.println("======== End FindByDemande Controller ==========");
    }
    
    public void declineDemItem(DemandeItem demandeItem)
    {
        System.out.println("***** Accepter ******");
        items.remove(items.indexOf(demandeItem));
        ejbFacade.remove(demandeItem);
    }
    
    public void accepterDemItem(DemandeItem demandeItem)
    {
        // To Do What It has to be done
    }
    
    //============= Methode ==================
    
    public DemandeItem getSelected() {
        if ( selected == null )
        {
            selected = new DemandeItem();
        }
        return selected;
    }

    public void setSelected(DemandeItem selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandeItemFacade getFacade() {
        return ejbFacade;
    }

    public DemandeItem prepareCreate() {
        selected = new DemandeItem();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeItemCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeItemUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeItemDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandeItem> getItems() {
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

    public DemandeItem getDemandeItem(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandeItem> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandeItem> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass=DemandeItem.class)
    public static class DemandeItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeItemController controller = (DemandeItemController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeItemController");
            return controller.getDemandeItem(getKey(value));
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
            if (object instanceof DemandeItem) {
                DemandeItem o = (DemandeItem) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandeItem.class.getName()});
                return null;
            }
        }

    }

}
