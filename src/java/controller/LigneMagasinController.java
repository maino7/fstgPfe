package controller;

import bean.LigneMagasin;
import bean.Produit;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.LigneMagasinFacade;

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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("ligneMagasinController")
@SessionScoped
public class LigneMagasinController implements Serializable {

    @EJB
    private service.LigneMagasinFacade ejbFacade;
    @EJB
    private service.ProduitFacade pf;
    private List<LigneMagasin> items = null;
    private LigneMagasin selected;
    private double seuilDalerte;
    private double qtéMax;
    private double qtéMin;
    private List<LigneMagasin> magasins = null;

    public List<LigneMagasin> getMagasins() {
        if (magasins == null) {
            magasins = new ArrayList<LigneMagasin>();
        }
        return magasins;
    }

    public void setMagasins(List<Produit> produits) {
        this.magasins = magasins;
    }

    public double getSeuilDalerte() {
        return seuilDalerte;
    }

    public void setSeuilDalerte(double seuilDalerte) {
        this.seuilDalerte = seuilDalerte;
    }

    public double getQtéMax() {
        return qtéMax;
    }

    public void setQtéMax(double qtéMax) {
        this.qtéMax = qtéMax;
    }

    public double getQtéMin() {
        return qtéMin;
    }

    public void setQtéMin(double qtéMin) {
        this.qtéMin = qtéMin;
    }

    public LigneMagasinController() {
    }

    public LigneMagasin getSelected() {
        if (selected == null) {
            selected = new LigneMagasin();
        }
        return selected;
    }

    public void setSelected(LigneMagasin selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LigneMagasinFacade getFacade() {
        return ejbFacade;
    }

    public LigneMagasin prepareCreate() {
        selected = new LigneMagasin();
        initializeEmbeddableKey();
        qtéMax = 0;
        qtéMin = 0;
        seuilDalerte = 0;

        return selected;
    }

    public void searchProduitFinit() {
        List<LigneMagasin> plf = ejbFacade.searchProduitFinit();
        if (!plf.isEmpty()) {
            //JsfUtil.addErrorMessage(plf.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING!", plf.toString()));

        } else {
            JsfUtil.addSuccessMessage("There is no Product runing out");
        }
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "There is no Product runing out"));
    }

    public List<LigneMagasin> searchProduit() {
//        magasins=null;
        magasins = ejbFacade.searchProduit1(getSelected().getProduit(), getSelected().getMagasin(), getSeuilDalerte(), getQtéMax(), getQtéMin());
        prepareCreate();
        return magasins;

    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LigneMagasinCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LigneMagasinUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LigneMagasinDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<LigneMagasin> getItems() {
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

    public LigneMagasin getLigneMagasin(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<LigneMagasin> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<LigneMagasin> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = LigneMagasin.class)
    public static class LigneMagasinControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LigneMagasinController controller = (LigneMagasinController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ligneMagasinController");
            return controller.getLigneMagasin(getKey(value));
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
            if (object instanceof LigneMagasin) {
                LigneMagasin o = (LigneMagasin) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LigneMagasin.class.getName()});
                return null;
            }
        }

    }

}
