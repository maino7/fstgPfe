package controller;

import bean.ExpressionBesoin;
import bean.LigneReception;
import bean.Reception;
import bean.UserStock;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.LigneReceptionFacade;
import controller.util.Session;
import java.io.IOException;
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
import net.sf.jasperreports.engine.JRException;
import service.ReceptionFacade;

@Named("ligneReceptionController")
@SessionScoped
public class LigneReceptionController implements Serializable {

    @EJB
    private service.LigneReceptionFacade ejbFacade;
    private List<LigneReception> items = null;
    private List<LigneReception> receptionItmes = new ArrayList<>();
    @EJB
    private ReceptionFacade receptionFacade;

    private LigneReception selected;
    private ExpressionBesoin expressionBesoin;
    private Date daterecep;
    private Long idLing = 1L;

    public Long getIdLing() {
        return idLing;
    }

    public void setIdLing(Long idLing) {
        this.idLing = idLing;
    }

    public LigneReceptionController() {
    }

    public LigneReception getSelected() {
        if (selected == null) {
            selected = new LigneReception();
        }
        return selected;
    }

    public void setSelected(LigneReception selected) {
        this.selected = selected;
    }

    public ExpressionBesoin getExpressionBesoin() {
        if (expressionBesoin == null) {
            expressionBesoin = new ExpressionBesoin();
        }
        return expressionBesoin;
    }

    public void setExpressionBesoin(ExpressionBesoin expressionBesoin) {
        this.expressionBesoin = expressionBesoin;
    }

    public Date getDaterecep() {
        if (daterecep == null) {
            daterecep = new Date();
        }
        return daterecep;
    }

    public void setDaterecep(Date daterecep) {
        this.daterecep = daterecep;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LigneReceptionFacade getFacade() {
        return ejbFacade;
    }

    public LigneReception prepareCreate() {
        selected = new LigneReception();
        initializeEmbeddableKey();
        return selected;
    }

    public List<LigneReception> getReceptionItmes() {
        if (receptionItmes == null) {
            receptionItmes = new ArrayList();
        }
        return receptionItmes;
    }

    public void setReceptionItmes(List<LigneReception> receptionItmes) {
        this.receptionItmes = receptionItmes;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LigneReceptionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LigneReceptionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LigneReceptionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<LigneReception> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void creerLigne() {
        System.out.println("azert");
        LigneReception cloneLigne = ejbFacade.cloneLigneReception(selected);
        receptionItmes.add(cloneLigne);
        cloneLigne.setId(idLing);
        setSelected(null);
        idLing++;
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

    public LigneReception getLigneReception(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<LigneReception> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<LigneReception> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = LigneReception.class)
    public static class LigneReceptionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LigneReceptionController controller = (LigneReceptionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ligneReceptionController");
            return controller.getLigneReception(getKey(value));
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
            if (object instanceof LigneReception) {
                LigneReception o = (LigneReception) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LigneReception.class.getName()});
                return null;
            }
        }

    }

    public void info() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("recu ajouter", ""));
//        context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
    }

    public void info1() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Votre recu a été enregstrer avec sucée", ""));
    }

    public void vider() {
        setSelected(null);
        receptionItmes.clear();
    }

    public void supprimerLigne() {
        receptionItmes.remove(selected);
        setSelected(null);
    }

    public Reception validerReception() {

        String idSession = ((UserStock) SessionUtil.getConnectedUser()).getId();
        Reception reception = receptionFacade.addReception(idSession, expressionBesoin, daterecep);
        if (reception == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Vous devez remplir tout les champs !"));
        } else {
            for (int i = 0; i < receptionItmes.size(); i++) {
                LigneReception receptionItem = receptionItmes.get(i);
                ejbFacade.createLigneReception(receptionItem.getQuantite(), receptionItem.getProduit(), reception.getId());
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Votre commande a été validée avec succès. Le numéro de la reception est " + reception.getId()));
            if(reception.getLigneReceptions().isEmpty()){
                reception.setLigneReceptions(receptionItmes);
            }
            vider();
        }
        Session.updateAttribute(reception, "reception");
        return reception;
    }

    public void validerImprimerOui() throws JRException, IOException {
        Reception reception = (Reception) Session.getAttribut("reception");
        imprimer(reception);
    }

    public void validerImprimerNon() {
        System.out.println("je suis ici");
        if (receptionItmes.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Veuillez remplir la Facture avant de valider !"));
        } else {
            vider();
        }
    }

    public void imprimer(Reception reception) throws JRException, IOException {
        receptionFacade.imprimerFacture(reception);
    }

}
