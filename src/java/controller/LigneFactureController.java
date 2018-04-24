package controller;

import bean.Commande;
import bean.Facture;
import bean.Fournisseur;
import bean.LigneCommande;
import bean.LigneFacture;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.LigneFactureFacade;

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
import service.FactureFacade;

@Named("ligneFactureController")
@SessionScoped
public class LigneFactureController implements Serializable {

    @EJB
    private service.LigneFactureFacade ejbFacade;
    @EJB
    private FactureFacade factureFacade;
    private List<LigneFacture> items = null;
    private LigneFacture selected;
    private Long ligneId = 1L;
    private List<LigneFacture> factureItems = new ArrayList<>();
    private Date dateFacture;
    private Commande commande;
    private Fournisseur fournisseur;
    private double prixTotale;
    private int nombre;
    private double qte;

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }
    

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public void test() {
        System.out.println(prixTotale);
    }

    public List<LigneFacture> getFactureItems() {
        if (factureItems == null) {
            factureItems = new ArrayList();
        }
        return factureItems;
    }

    public Date getDateFacture() {
        if (dateFacture == null) {
            dateFacture = new Date();
        }
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public Commande getCommande() {
        if (commande == null) {
            commande = new Commande();
        }
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Fournisseur getFournisseur() {
        if (fournisseur == null) {
            fournisseur = new Fournisseur();
        }
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Double getPrixTotale() {
        return prixTotale;
    }

    public void setPrixTotale(Double prixTotale) {
        this.prixTotale = prixTotale;
    }

    public void setFactureItems(List<LigneFacture> factureItems) {
        this.factureItems = factureItems;
    }

    public Long getLigneId() {
        return ligneId;
    }

    public void setLigneId(Long ligneId) {
        this.ligneId = ligneId;
    }

    public LigneFactureController() {
    }

    public LigneFacture getSelected() {
        if (selected == null) {
            selected = new LigneFacture();
        }
        return selected;
    }

    public void setSelected(LigneFacture selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LigneFactureFacade getFacade() {
        return ejbFacade;
    }

    public LigneFacture prepareCreate() {
        selected = new LigneFacture();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LigneFactureCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LigneFactureUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LigneFactureDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<LigneFacture> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void validerFacture() {
        System.out.println(dateFacture + " " + commande.getId() + " " + fournisseur.getId() );
            Facture facture = factureFacade.addFacture(dateFacture, commande, fournisseur, factureItems);
        System.out.println("facture t3del");
        if (facture == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Vous devez remplir tout les champs !"));
        } else {
            for (int i = 0; i < factureItems.size(); i++) {
                LigneFacture factureItem = factureItems.get(i);
                ejbFacade.createLigneFacture(facture.getId(), factureItem.getQuantite(), factureItem.getProduit());
                System.out.println("had lingFacture itcrya");
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Votre commande a été validée avec succès. Le numéro de la commande est " + facture.getId()));
            vider();

        }

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

    public LigneFacture getLigneFacture(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<LigneFacture> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<LigneFacture> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = LigneFacture.class)
    public static class LigneFactureControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LigneFactureController controller = (LigneFactureController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ligneFactureController");
            return controller.getLigneFacture(getKey(value));
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
            if (object instanceof LigneFacture) {
                LigneFacture o = (LigneFacture) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LigneFacture.class.getName()});
                return null;
            }
        }

    }

    public void ajouterLigneFacture() {
        LigneFacture ligneClone = ejbFacade.clone(selected);
        ligneClone.setId(ligneId);
        factureItems.add(ligneClone);
        ligneId++;
        setSelected(null);
    }

    public void vider() {
        setSelected(null);
        factureItems.clear();
    }
    
   public void validerImprimerOui() {
        if (factureItems.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Veuillez remplir la Facture avant de valider !"));
        } else {
            Facture facture = new Facture();
            validerFacture();
            imprimer(facture);
            vider();
        }
    }

    public void validerImprimerNon() {
        System.out.println("je suis ici");
        if (factureItems.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Veuillez remplir la Facture avant de valider !"));
        } else {
            validerFacture();
            vider();
        }
    }

    public void imprimer(Facture facture) {
        factureFacade.imprimerFacture(facture);
    }

}
