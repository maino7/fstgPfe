package controller;

import bean.ExpressionBesoin;
import bean.LigneExpressionBesoin;
import bean.UserStock;
import controller.util.DateUtil;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.ExpressionBesoinFacade;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Temporal;

@Named("expressionBesoinController")
@SessionScoped
public class ExpressionBesoinController implements Serializable {

    @EJB
    private service.ExpressionBesoinFacade ejbFacade;

    private List<ExpressionBesoin> items = null;
    private List<ExpressionBesoin> itemsFind = null;
    private List<ExpressionBesoin> expressionsToday = null;
    private ExpressionBesoin selected;
    private List<LigneExpressionBesoin> expressionItems = new ArrayList<>();
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMin;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMax;

    public List<ExpressionBesoin> getExpressionsToday() {
        expressionsToday = ejbFacade.findByDate();
        return expressionsToday;
    }

    public void setExpressionsToday(List<ExpressionBesoin> expressionsToday) {
        this.expressionsToday = expressionsToday;
    }

    public ExpressionBesoinFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ExpressionBesoinFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<ExpressionBesoin> getItemsFind() {
        return itemsFind;
    }

    public void setItemsFind(List<ExpressionBesoin> itemsFind) {
        this.itemsFind = itemsFind;
    }

    public List<LigneExpressionBesoin> getExpressionItems() {
        return expressionItems;
    }

    public void setExpressionItems(List<LigneExpressionBesoin> expressionItems) {
        this.expressionItems = expressionItems;
    }

    public Date getDateMin() {
        return dateMin;
    }

    public void setDateMin(Date dateMin) {
        this.dateMin = dateMin;
    }

    public Date getDateMax() {
        return dateMax;
    }

    public void setDateMax(Date dateMax) {
        this.dateMax = dateMax;
    }

    public ExpressionBesoinController() {
    }

    public ExpressionBesoin getSelected() {
        return selected;
    }

    public void setSelected(ExpressionBesoin selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ExpressionBesoinFacade getFacade() {
        return ejbFacade;
    }

    public ExpressionBesoin prepareCreate() {
        selected = new ExpressionBesoin();
        initializeEmbeddableKey();
        return selected;
    }

//    public void findByUser() {
//        String idSession = ((UserStock) SessionUtil.getConnectedUser()).getId();
//        System.out.println("hahowa l id " + idSession);
//        itemsFind = ejbFacade.findByDate(idSession, dateMin, dateMax);
//        System.out.println("OK");
//
//    }
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ExpressionBesoinCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ExpressionBesoinUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ExpressionBesoinDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ExpressionBesoin> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void findByDate() {
        expressionsToday = ejbFacade.findByDate();
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

    public ExpressionBesoin getExpressionBesoin(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<ExpressionBesoin> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ExpressionBesoin> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ExpressionBesoin.class)
    public static class ExpressionBesoinControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ExpressionBesoinController controller = (ExpressionBesoinController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "expressionBesoinController");
            return controller.getExpressionBesoin(getKey(value));
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
            if (object instanceof ExpressionBesoin) {
                ExpressionBesoin o = (ExpressionBesoin) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ExpressionBesoin.class.getName()});
                return null;
            }
        }

    }

}
