package controller;

import bean.EtudiantMaster;
import bean.FiliereMaster;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import java.io.IOException;
import service.EtudiantMasterFacade;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.primefaces.event.FlowEvent;

@Named("etudiantMasterController")
@SessionScoped
public class EtudiantMasterController implements Serializable {

    @EJB
    private service.FiliereMasterFacade filiereMasterFacade;
    @EJB
    private service.EtudiantMasterFacade ejbFacade;
    private List<EtudiantMaster> items = null;
    private EtudiantMaster selected;
    private List<FiliereMaster> itemsInscription;
    private boolean skip;
    private boolean natio;

    public boolean isSkip() {
        return skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public EtudiantMasterController() {
    }

    public boolean isNatio() {
        return natio;
    }

    public List<FiliereMaster> getItemsInscription() {
        if (itemsInscription == null) {
            itemsInscription = new ArrayList<>();
        }
        return itemsInscription;
    }

    public void setItemsInscription(List<FiliereMaster> itemsInscription) {
        this.itemsInscription = itemsInscription;
    }

    public void setNatio(boolean natio) {
        this.natio = natio;
    }

    public EtudiantMaster getSelected() {
        if (selected == null) {
            selected = new EtudiantMaster();
        }
        return selected;
    }

    public void setSelected(EtudiantMaster selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EtudiantMasterFacade getFacade() {
        return ejbFacade;
    }

    public EtudiantMaster prepareCreate() {
        selected = new EtudiantMaster();
        initializeEmbeddableKey();
        return selected;
    }

    public List<FiliereMaster> initIns() {
        System.out.println("aaaaa  " + selected.isTypeInscription());
        itemsInscription = filiereMasterFacade.findForInsc(selected.isTypeInscription());
        System.out.println("ssssssss  " + itemsInscription);
        return itemsInscription;
    }

    public void create() throws IOException {
        selected.setDateInscription(new Date());
        selected.setIp("192.168.5.4");
        selected.setExport(false);
        selected.setLastPdf("65446799.pdf");
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EtudiantMasterCreated"));
         JsfUtil.addSuccessMessage("Votre inscription a était enregistré avec success ");
         SessionUtil.redirect("../template/Home.xhtml");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            selected = new EtudiantMaster();
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EtudiantMasterUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EtudiantMasterDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<EtudiantMaster> getItems() {
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

    public EtudiantMaster getEtudiantMaster(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<EtudiantMaster> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<EtudiantMaster> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = EtudiantMaster.class)
    public static class EtudiantMasterControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EtudiantMasterController controller = (EtudiantMasterController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "etudiantMasterController");
            return controller.getEtudiantMaster(getKey(value));
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
            if (object instanceof EtudiantMaster) {
                EtudiantMaster o = (EtudiantMaster) object;
                return getStringKey(o.getCne());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EtudiantMaster.class.getName()});
                return null;
            }
        }

    }

}
