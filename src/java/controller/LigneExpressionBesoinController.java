package controller;

import bean.ExpressionBesoin;
import bean.LigneCommande;
import bean.LigneExpressionBesoin;
import bean.UserStock;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.LigneExpressionBesoinFacade;

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

@Named("ligneExpressionBesoinController")
@SessionScoped
public class LigneExpressionBesoinController implements Serializable {

    @EJB
    private service.LigneExpressionBesoinFacade ejbFacade;
    @EJB
    private service.ExpressionBesoinFacade expressionBesoinFacade;
    private List<LigneExpressionBesoin> items = new ArrayList<>();
    private List<LigneExpressionBesoin> expressionItems = new ArrayList<>();
    private Long idLigne = 1L;

    private LigneExpressionBesoin selected;

    public List<LigneExpressionBesoin> getExpressionItems() {
        if (expressionItems == null) {
            expressionItems = new ArrayList<>();
        }
        return expressionItems;
    }

    public void setExpressionItems(List<LigneExpressionBesoin> expressionItems) {
        this.expressionItems = expressionItems;
    }

    public LigneExpressionBesoinController() {
    }

    public LigneExpressionBesoin getSelected() {
        if (selected == null) {
            selected = new LigneExpressionBesoin();
        }
        return selected;
    }

    public void setSelected(LigneExpressionBesoin selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LigneExpressionBesoinFacade getFacade() {
        return ejbFacade;
    }

    public LigneExpressionBesoin prepareCreate() {
        selected = new LigneExpressionBesoin();
        initializeEmbeddableKey();
        return selected;
    }

    public void vider() {
        setSelected(null);
        expressionItems.clear();
    }

    public void supprimerLigne() {
        expressionItems.remove(selected);
        setSelected(null);
    }

    public void creeLigne() {
        System.out.println("azert");
        LigneExpressionBesoin ligneClone = ejbFacade.cloneLigneExpressionBesoin(selected);
        ligneClone.setId(idLigne);
        expressionItems.add(ligneClone);
        idLigne++;

    }

    public void validerExpressionDeBesoin() {
        String idSession = ((UserStock) SessionUtil.getConnectedUser()).getId();
        System.out.println("hahowa l id " + idSession);

        ExpressionBesoin expressionBesoin = expressionBesoinFacade.addExpressionDeBesoin(idSession);
        System.out.println("ok tcryaa");

        for (int i = 0; i < expressionItems.size(); i++) {
            LigneExpressionBesoin expressionItem = expressionItems.get(i);
            ejbFacade.createLigneExp(expressionBesoin.getId(), expressionItem.getQuantite(), expressionItem.getProduit());
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Votre Expression De Besoin a été validée avec succès."));
        vider();
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LigneExpressionBesoinCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LigneExpressionBesoinUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LigneExpressionBesoinDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<LigneExpressionBesoin> getItems() {
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

    public LigneExpressionBesoin getLigneExpressionBesoin(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<LigneExpressionBesoin> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<LigneExpressionBesoin> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = LigneExpressionBesoin.class)
    public static class LigneExpressionBesoinControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LigneExpressionBesoinController controller = (LigneExpressionBesoinController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ligneExpressionBesoinController");
            return controller.getLigneExpressionBesoin(getKey(value));
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
            if (object instanceof LigneExpressionBesoin) {
                LigneExpressionBesoin o = (LigneExpressionBesoin) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LigneExpressionBesoin.class.getName()});
                return null;
            }
        }

    }

}
