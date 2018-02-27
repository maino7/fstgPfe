package controller;

import bean.AnneUniversitaire;
import bean.DemandeLicence;
import bean.DemandeLicenceItem;
import bean.Etudiant;
import bean.Licence;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.DemandeLicenceFacade;

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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import service.LicenceFacade;

@Named("demandeLicenceController")
@SessionScoped
public class DemandeLicenceController implements Serializable {

    @EJB
    private service.DemandeLicenceFacade ejbFacade;
    @EJB
    private service.DemandeLicenceItemFacade demandeLicenceItemFacade;
    @EJB
    private service.AnneUniversitaireFacade anneUniversitaireFacade;
    @EJB
    private service.EtudiantFacade etudiantFacade;
    @EJB
    private LicenceFacade licenceFacade;
    private List<DemandeLicence> items = null;
    private List<DemandeLicence> demandeLicences;
    private DemandeLicence selected;
    private DemandeLicence demandeLicence;
    private DemandeLicenceItem demandeLicenceItem;
    private List<DemandeLicenceItem> demandeLicenceItems;
    private List<DemandeLicenceItem> demandeLicenceItems1;
    private Etudiant etudiant;
    private boolean formCreat = true;
    private boolean formEdit = false;
    private boolean edit = false;
    private boolean sendDisabl = false;

    public void editDemandeLicence() {//rendred form
        formCreat = true;
        formEdit = false;
        edit = true;
        demandeLicenceItems = demandeLicenceItems1;
    }

    public void sendDemande() {//envoyer la demande en gerant les fautes de frp!!
        boolean etat1 = false;
        boolean etat2 = false;
        int x = 0;
        for (DemandeLicenceItem item1 : demandeLicenceItems) {
            for (DemandeLicenceItem item2 : demandeLicenceItems) {//ne pas repeter une priorite
                if (item1.getPriorite() == item2.getPriorite() && item1 != item2) {
                    etat1 = true;
                }
                if (item2.getPriorite() < 0 || item2.getPriorite() > demandeLicenceItems.size()) {
                    etat2 = true;
                }
            }
            if (item1.getPriorite() == 0) {
                x = 1;
            }
        }
        if (etat1 == false) {// chaque choix de licence a une priorite propre à lui donc je peux creer l'objet
            if (etat2 == true) {
                JsfUtil.addErrorMessage("Votre choix doit etre entre 1 et " + demandeLicenceItems.size());
            } else if (x == 0) {// etudiant a rempli tous les champs sans et sans error
                if (formCreat == true) {//n'a pas encore creé une demande
                    if (edit == false) {
                        System.out.println("====== edit=false");
                        selected = ejbFacade.prepareDemandeLicence();
                        AnneUniversitaire annUniv = anneUniversitaireFacade.prepareAnnee();
                        anneUniversitaireFacade.create(annUniv);
                        selected.setAnneUniversitaire(annUniv);
                        ejbFacade.create(selected);
                        for (DemandeLicenceItem item : demandeLicenceItems) {
                            item.setDemandeLicence(selected);
                            demandeLicenceItemFacade.create(item);
                        }
                        sendDisabl = true;//bloquer le button ENVOYER apres la creat de la demande
                        JsfUtil.addSuccessMessage("Votre demande d'orienter vers le licence science et technique dans la FSTG a bien été envoyer "
                                + ",veuillez attender une reponse de confirmation d'aprés notre administration");
                    } else {// modifier la demande d'orientation=== edit=true
                        System.out.println("===== edit = true ");
                        for (DemandeLicenceItem item : demandeLicenceItems) {
                            demandeLicenceItemFacade.edit(item);
                        }
                        selected = ejbFacade.findByEtud(etudiant);
                        System.out.println("=== selected " + selected);
                        selected.setDemandeLicenceItems(demandeLicenceItems);
                        ejbFacade.edit(selected);
                        JsfUtil.addSuccessMessage("Votre demande d'orienter vers le licence science et technique dans la FSTG a bien été modifier "
                                + ",veuillez attender une reponse de confirmation d'aprés notre administration");
                    }
                }
            } else if (x == 1) {//un champ est vide
                JsfUtil.addErrorMessage2("Vous devez remplir tous les champ !!");
            }
        } else {//donner la meme priorite à plus d'un licence
            JsfUtil.addErrorMessage("Vous ne devez pas donner la meme priorité à deux licences");
        }

    }

    public String addItemDemande() {//create or edit
        Etudiant e  = connectedEtud();
        etudiant = etudiantFacade.find(e.getCne());
        if (etudiant.getEtatDeust() == 3) {//admis
            demandeLicenceItems1 = demandeLicenceItemFacade.findByEtu(etudiant);
            if (demandeLicenceItems1.size() > 0) {//deja orienté
                System.out.println(" list !=null===size dyalha= " + demandeLicenceItems1.size());
                formCreat = false;
                formEdit = true;
            } else {// n'a pas encore envoyer leur demandeLicence=>Prepare create demande
                demandeLicenceItems = new ArrayList<>();
                List<Licence> licences = licenceFacade.findLicence(etudiant);
                for (Licence licence : licences) {
                    DemandeLicenceItem d = demandeLicenceItemFacade.prepareDemandeItem(licence);
                    demandeLicenceItems.add(d);
                }
            }
            return "/demandeLicence/OrientationLicence.xhtml?faces-redirect=true";
        } else {
            JsfUtil.addErrorMessage("Vous n'etes pas admis pour orienter vers la licence ");
            return null;
        }
    }

    public String priorityChoix() {
        int x = licenceFacade.findLicence(etudiant).size();
        String choixPriorite = "(";
        for (int i = 1; i < x; i++) {
            choixPriorite += i + ",";
        }
        choixPriorite += x + ")";
        return choixPriorite;
    }

    public boolean disablButton() {//pour ne pas entrer par url et envoyer des demandes==
        if (etudiant == null || etudiant.getCne() == null || sendDisabl == true) {
            return true;
        } else {
            return false;
        }
    }

    public Etudiant connectedEtud() {
        Etudiant etudiant = (Etudiant) SessionUtil.getConnectedUser();
        return etudiant;
    }

    public DemandeLicenceController() {
    }

    public DemandeLicence getSelected() {
        return selected;
    }

    public void setSelected(DemandeLicence selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandeLicenceFacade getFacade() {
        return ejbFacade;
    }

    public DemandeLicence prepareCreate() {
        selected = new DemandeLicence();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeLicenceCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeLicenceUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeLicenceDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandeLicence> getItems() {
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

    public DemandeLicence getDemandeLicence(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandeLicence> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandeLicence> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DemandeLicence.class)
    public static class DemandeLicenceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeLicenceController controller = (DemandeLicenceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeLicenceController");
            return controller.getDemandeLicence(getKey(value));
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
            if (object instanceof DemandeLicence) {
                DemandeLicence o = (DemandeLicence) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandeLicence.class.getName()});
                return null;
            }
        }

    }

    public DemandeLicenceItem getDemandeLicenceItem() {
        if (demandeLicenceItem == null) {
            demandeLicenceItem = new DemandeLicenceItem();
        }
        return demandeLicenceItem;
    }

    public void setDemandeLicenceItem(DemandeLicenceItem demandeLicenceItem) {
        this.demandeLicenceItem = demandeLicenceItem;
    }

    public List<DemandeLicenceItem> getDemandeLicenceItems() {
        if (demandeLicenceItems == null) {
            demandeLicenceItems = new ArrayList();
        }
        return demandeLicenceItems;
    }

    public void setDemandeLicenceItems(List<DemandeLicenceItem> demandeLicenceItems) {
        this.demandeLicenceItems = demandeLicenceItems;
    }

    public List<DemandeLicenceItem> getDemandeLicenceItems1() {
        if (demandeLicenceItems1 == null) {
            demandeLicenceItems1 = new ArrayList();
        }
        return demandeLicenceItems1;
    }

    public void setDemandeLicenceItems1(List<DemandeLicenceItem> demandeLicenceItems1) {
        this.demandeLicenceItems1 = demandeLicenceItems1;
    }

    public Etudiant getEtudiant() {
        return etudiant = (Etudiant) SessionUtil.getConnectedUser();
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public boolean isFormCreat() {
        return formCreat;
    }

    public void setFormCreat(boolean formCreat) {
        this.formCreat = formCreat;
    }

    public boolean isFormEdit() {
        return formEdit;
    }

    public void setFormEdit(boolean formEdit) {
        this.formEdit = formEdit;
    }

    public DemandeLicence getDemandeLicence() {
        if (demandeLicence == null) {
            demandeLicence = new DemandeLicence();
        }
        return demandeLicence;
    }

    public void setDemandeLicence(DemandeLicence demandeLicence) {
        this.demandeLicence = demandeLicence;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isSendDisabl() {
        return sendDisabl;
    }

    public void setSendDisabl(boolean sendDisabl) {
        this.sendDisabl = sendDisabl;
    }

}
