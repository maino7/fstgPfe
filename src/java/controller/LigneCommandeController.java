package controller;

import bean.Commande;
import bean.LigneCommande;
import bean.Produit;
import bean.UserStock;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import java.io.IOException;
import service.LigneCommandeFacade;
import controller.util.Session;
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
import net.sf.jasperreports.engine.JRException;
import service.CommandeFacade;
import service.ProduitFacade;

@Named("ligneCommandeController")
@SessionScoped
public class LigneCommandeController implements Serializable {

    @EJB
    private service.LigneCommandeFacade ejbFacade;
    @EJB
    private CommandeFacade commandeFacade;
    @EJB
    private ProduitFacade produitFacade;
    private List<LigneCommande> items = null;
    private List<LigneCommande> commandeItems = new ArrayList<>();
    private List<Produit> produits = new ArrayList<>();
    private LigneCommande selected;
    private String cmdId;
    private Long ligneId = 1L;

    public LigneCommandeController() {
    }

    public List<Produit> getProduits() {
        if (produits == null) {
            produits = new ArrayList();
        }
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

//    public List<Produit> findByCategorie() {
//        produits = produitFacade.findByCategorie(getSelected().getProduit().getCategorie());
//        return produits;
//    }

    public String getCmdId() {
        return cmdId;
    }

    public Long getLigneId() {
        return ligneId;
    }

    public void setLigneId(Long ligneId) {
        this.ligneId = ligneId;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId;
    }

    public LigneCommande getSelected() {
        if (selected == null) {
            selected = new LigneCommande();
        }
        return selected;
    }

    public void setSelected(LigneCommande selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LigneCommandeFacade getFacade() {
        return ejbFacade;
    }

    public LigneCommande prepareCreate() {
        selected = new LigneCommande();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LigneCommandeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void ajouterLigneCommande() {
        LigneCommande ligneClone = ejbFacade.clone(selected);
        ligneClone.setId(ligneId);
        commandeItems.add(ligneClone);
        ligneId++;
        setSelected(null);
    }

    public void vider() {
        setSelected(null);
        commandeItems.clear();
    }

    public void supprimerLigne() {
        commandeItems.remove(selected);
        setSelected(null);
    }

    public Commande validerCommande() {
        Commande commande = commandeFacade.addCommande();
        ejbFacade.ajouterLigneCommande(commande, commandeItems);
        commande.setLigneCommandes(commandeItems);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Votre commande a été validée avec succès. Le numéro de la commande est " + commande.getId()));
        Session.updateAttribute(commande,"commande");
         vider();
        return commande;
    }

    public void validerImprimerOui() throws JRException, IOException {
           // Commande commande = validerCommande();
           Commande c=(Commande) Session.getAttribut("commande");
            imprimer(c);
           
    }

   

    public void imprimer(Commande commande) throws JRException, IOException {
        commandeFacade.imprimerCommande(commande);
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LigneCommandeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LigneCommandeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<LigneCommande> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<LigneCommande> getCommandeItems() {
        if (commandeItems == null) {
            commandeItems = new ArrayList();
        }
        return commandeItems;
    }

    public void setCommandeItems(List<LigneCommande> commandeItems) {
        this.commandeItems = commandeItems;
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

    public LigneCommande getLigneCommande(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<LigneCommande> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<LigneCommande> getItemsAvailableSelectOne() {
        return getFacade().findAll();

    }

    @FacesConverter(forClass = LigneCommande.class)
    public static class LigneCommandeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LigneCommandeController controller = (LigneCommandeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ligneCommandeController");
            return controller.getLigneCommande(getKey(value));
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
            if (object instanceof LigneCommande) {
                LigneCommande o = (LigneCommande) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LigneCommande.class.getName()});
                return null;
            }
        }

    }

}
