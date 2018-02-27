package controller;

import bean.Filiere;
import bean.Licence;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.LicenceFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@Named("licenceController")
@SessionScoped
public class LicenceController implements Serializable {

    @EJB
    private service.LicenceFacade ejbFacade;
    @EJB
    private service.FiliereFacade filiereFacade;
    private List<Licence> items = null;
    private Licence selected;
    private DefaultDashboardModel model;

   
    public DefaultDashboardModel init() {
        model = new DefaultDashboardModel();;
        DashboardColumn column = new DefaultDashboardColumn();
        for (Licence item : items) {
            column.addWidget(item.getNom());
        }
        model.addColumn(column);
        return model;
    }

    public List<Filiere> tcFiliere() {
        return filiereFacade.findByType(1, 1);
    }

    public LicenceController() {
    }

    public Licence getSelected() {
        return selected;
    }

    public void setSelected(Licence selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LicenceFacade getFacade() {
        return ejbFacade;
    }

    public Licence prepareCreate() {
        selected = new Licence();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LicenceCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LicenceUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LicenceDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Licence> getItems() {
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

    public Licence getLicence(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Licence> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Licence> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Licence.class)
    public static class LicenceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LicenceController controller = (LicenceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "licenceController");
            return controller.getLicence(getKey(value));
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
            if (object instanceof Licence) {
                Licence o = (Licence) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Licence.class.getName()});
                return null;
            }
        }

    }

    public DefaultDashboardModel getModel() {
        if(model==null){
            model=new DefaultDashboardModel();
        }
        return model;
    }

    public void setModel(DefaultDashboardModel model) {
        this.model = model;
    }


}
