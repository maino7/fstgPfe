package controller;

import bean.Actualite;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.ServerConfigUtil;
import controller.util.ServerConfigUtill;
import service.ActualiteFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.UploadedFile;

@Named("actualiteController")
@SessionScoped
public class ActualiteController implements Serializable {

    @EJB
    private service.ActualiteFacade ejbFacade;
    private List<Actualite> items = null;
    private Actualite selected;
    private UploadedFile uploadedFile;

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public ActualiteFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ActualiteFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public ActualiteController() {
    }

    public Actualite getSelected() {
        if (selected == null) {
            selected = new Actualite();
        }

        return selected;
    }

    public void setSelected(Actualite selected) {
       

        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ActualiteFacade getFacade() {
        return ejbFacade;
    }

    public Actualite prepareCreate() {
        selected = new Actualite();
        initializeEmbeddableKey();
        return selected;

    }

    public void create() {
        handleUpload();
         if(selected.getDateDebut()==null && selected.getDateExpiration()==null){
            selected.setEtat(1);
        }
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ActualiteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ActualiteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ActualiteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Actualite> getItems() {
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

    public Actualite getActualite(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Actualite> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Actualite> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Actualite.class)
    public static class ActualiteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ActualiteController controller = (ActualiteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "actualiteController");
            return controller.getActualite(getKey(value));
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
            if (object instanceof Actualite) {
                Actualite o = (Actualite) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Actualite.class.getName()});
                return null;
            }
        }

    }

    public void handleUpload() {
        if (uploadedFile == null) {
            System.out.println("walooooo");
        } else {
            System.out.println("INNNNNNN");
            String nameOfUploadedFile = uploadedFile.getFileName();
            ServerConfigUtill.upload(uploadedFile, nameOfUploadedFile);
            selected.setFile(nameOfUploadedFile);

        }
    }
    
    }

 

