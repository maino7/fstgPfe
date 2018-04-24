package controller;

import bean.EntiteAdministrative;
import bean.ExpressionBesoin;
import bean.LigneExpressionBesoin;
import bean.Produit;
import bean.UserStock;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.LigneExpressionBesoinFacade;

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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import service.ExpressionBesoinFacade;
import service.UserStockFacade;

@Named("ligneExpressionBesoinController")
@SessionScoped
public class LigneExpressionBesoinController implements Serializable {

    @EJB
    private service.LigneExpressionBesoinFacade ejbFacade;
    @EJB
    private service.ExpressionBesoinFacade expressionBesoinFacade;
    @EJB
    private service.UserStockFacade userStockFacade;
    private List<LigneExpressionBesoin> items = new ArrayList<>();
    private List<ExpressionBesoin> expressionBesoins = new ArrayList<>();
    private List<LigneExpressionBesoin> expressionItems = new ArrayList<>();
    private List<LigneExpressionBesoin> listDetail = new ArrayList<>();
    private List<LigneExpressionBesoin> listDetail1 = new ArrayList<>();
    private List<UserStock> userStocks = new ArrayList<>();
    private List<ExpressionBesoin> itemsFind = new ArrayList<>();

    private Long idLigne = 1L;
    private Date dateMin = null;
    private Date dateMax = null;
    private EntiteAdministrative entiteAdministrative;
    private UserStock userStock;

    public UserStock getUserStock() {
        if (userStock == null) {
            userStock = new UserStock();
        }
        return userStock;
    }

    public void setUserStock(UserStock userStock) {
        this.userStock = userStock;
    }

    public List<LigneExpressionBesoin> getListDetail1() {
        return listDetail1;
    }

    public void setListDetail1(List<LigneExpressionBesoin> listDetail1) {
        this.listDetail1 = listDetail1;

    }

    public EntiteAdministrative getEntiteAdministrative() {
        if (entiteAdministrative == null) {
            entiteAdministrative = new EntiteAdministrative();
        }
        return entiteAdministrative;
    }

    public void setEntiteAdministrative(EntiteAdministrative entiteAdministrative) {
        this.entiteAdministrative = entiteAdministrative;
    }

    public UserStockFacade getUserStockFacade() {
        return userStockFacade;
    }

    public void setUserStockFacade(UserStockFacade userStockFacade) {
        this.userStockFacade = userStockFacade;
    }

    public List<UserStock> getUserStocks() {
        return userStocks;
    }

    public void setUserStocks(List<UserStock> userStocks) {
        this.userStocks = userStocks;
    }

    public List<ExpressionBesoin> getItemsFind() {

        return itemsFind;
    }

    public void setItemsFind(List<ExpressionBesoin> itemsFind) {
        this.itemsFind = itemsFind;
    }

    public Date getDateMin() {
        if (dateMin == null) {
            dateMin = new Date();
        }
        return dateMin;
    }

    public void setDateMin(Date dateMin) {
        this.dateMin = dateMin;
    }

    public Date getDateMax() {
        if (dateMax == null) {
            dateMax = new Date();
        }
        return dateMax;
    }

    public void setDateMax(Date dateMax) {
        this.dateMax = dateMax;
    }

    private LigneExpressionBesoin selected;

    public List<LigneExpressionBesoin> getListDetail() {
        return listDetail;
    }

    public void setListDetail(List<LigneExpressionBesoin> listDetail) {
        this.listDetail = listDetail;
    }

    public LigneExpressionBesoinFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(LigneExpressionBesoinFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public ExpressionBesoinFacade getExpressionBesoinFacade() {
        return expressionBesoinFacade;
    }

    public void setExpressionBesoinFacade(ExpressionBesoinFacade expressionBesoinFacade) {
        this.expressionBesoinFacade = expressionBesoinFacade;
    }

    public List<ExpressionBesoin> getExpressionBesoins() {
        return expressionBesoins;
    }

    public void setExpressionBesoins(List<ExpressionBesoin> expressionBesoins) {
        this.expressionBesoins = expressionBesoins;
    }

    public Long getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(Long idLigne) {
        this.idLigne = idLigne;
    }

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

    public void findByEntite() {
        userStocks = userStockFacade.findByEntite(entiteAdministrative);
    }

    public void creeLigne() {
        LigneExpressionBesoin ligneClone = ejbFacade.cloneLigneExpressionBesoin(selected);
        ligneClone.setId(idLigne);
        expressionItems.add(ligneClone);
        idLigne++;
        setSelected(null);
    }

    public ExpressionBesoin validerExpressionDeBesoin() {
        String idSession = ((UserStock) SessionUtil.getConnectedUser()).getId();
        ExpressionBesoin expressionBesoin = expressionBesoinFacade.addExpressionDeBesoin(idSession);
        ejbFacade.createLigneExp(expressionBesoin, expressionItems);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Votre Expression De Besoin a été validée avec succès. Le numéro de l'expression est: " + expressionBesoin.getId()));
        return expressionBesoin;
    }

    public void validerImprimerOui() {
        if (expressionItems.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Veuillez remplir l'expression de besoin avant de valider !"));
        } else {
            ExpressionBesoin expressionBesoin = validerExpressionDeBesoin();
            imprimer(expressionBesoin);
            vider();
        }
    }

    public void validerImprimerNon() {
        if (expressionItems.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Veuillez remplir l'expression de besoin avant de valider !"));
        } else {
            validerExpressionDeBesoin();
            vider();
        }
    }

    public void imprimer(ExpressionBesoin expressionBesoin) {
        expressionBesoinFacade.imprimerCommande(expressionBesoin);
    }

    public void findExp() {
        expressionBesoins = expressionBesoinFacade.findBycriteria(userStock.getId(), dateMin, dateMax, selected.getProduit().getId());
//        System.out.println(userStock);
//        System.out.println(dateMin);
//        System.out.println(dateMax);
//        System.out.println(selected.getProduit().getId());
    }

    public void testCombo() {
        System.out.println("=====" + selected.getExpressionBesoin().getUserStock().getId());
    }

    public void findBycriteria() {
        String idSession = ((UserStock) SessionUtil.getConnectedUser()).getId();
        System.out.println("hahowa l id " + idSession);
        itemsFind = expressionBesoinFacade.findBycriteria(idSession, dateMin, dateMax, selected.getProduit().getId());
        System.out.println("OK");
    }

    public void showDetail(ExpressionBesoin item) {
        listDetail = ejbFacade.findByExp(item.getId());
    }

    public void showDetailDoyen(ExpressionBesoin item) {
        listDetail1 = ejbFacade.findByExp(item.getId());
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
