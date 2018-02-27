package controller;

import bean.Etudiant;
import bean.Message;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.MessageFacade;

import java.io.Serializable;
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

@Named("messageController")
@SessionScoped
public class MessageController implements Serializable {

    @EJB
    private service.MessageFacade ejbFacade;
    private List<Message> items = null;
    private Message selected;
    private Date datMin;
    private Date datMax;
    private boolean luMsg;
    private String sujet;
    private int lu;
// pour EtudiantContact.xhtml
    @EJB
    private service.EtudiantFacade etudiantFacade;
    private Message selectedETudiant;
    private Etudiant etudiant;

    public void findMsg() {
        items = ejbFacade.searchMsg(lu, luMsg, datMin, datMax, sujet);
        switch (lu) {
            case 1:
                luMsg = true;
                break;
            case 0:
                luMsg = false;
                break;
            default:
                break;
        }
    }

    public void prepareLu(Message m) {
        selected = m;
        System.out.println(selected);
        selected.setLu(true);
        System.out.println(selected.isLu());
        ejbFacade.edit(m);
    }

    public String prepareCreate() {
        items = ejbFacade.findAll();
        selected = new Message();
        initializeEmbeddableKey();
        return "/message/contactUniversity?faces-redirect=true";
    }

    //pour un specific student
    public String prepareCreateEtudiant() {
        Etudiant e = (Etudiant) SessionUtil.getConnectedUser();
        etudiant = etudiantFacade.find(e.getCne());
        selectedETudiant = new Message();
        selectedETudiant.setNom(etudiant.getNom());
        selectedETudiant.setPrenom(etudiant.getPrenom());
        selectedETudiant.setEmail(etudiant.getEmail());
        System.out.println("emaail" + etudiant.getEmail());
        initializeEmbeddableKey();
        return "/message/EtudiantContactUniversity?faces-redirect=true";
    }

    public void createForEtudiant() {
        selectedETudiant.setDateEnvoi(new Date());
        getFacade().edit(selectedETudiant);
        // persist(PersistAction.CREATE, "Validation de message : ");
        JsfUtil.addSuccessMessage("Votre message a bien été envoyer");
        if (!JsfUtil.isValidationFailed()) {
            prepareCreateEtudiant();// Invalidate list of items to trigger re-query.
        }

    }

    public void create() {
        selected.setDateEnvoi(new Date());
        persist(PersistAction.CREATE, "Validation de message : ");
        JsfUtil.addSuccessMessage("Votre message a bien été envoyer");
        if (!JsfUtil.isValidationFailed()) {
            selected = new Message();  // Invalidate list of items to trigger re-query.
        }

    }

    public void deletList() {
        for (Message item : items) {
            ejbFacade.remove(item);
        }
    }

    protected void initializeEmbeddableKey() {
    }

    private MessageFacade getFacade() {
        return ejbFacade;
    }

    public MessageController() {
    }

    public Etudiant getEtudiant() {
        if (etudiant == null) {
            etudiant = new Etudiant();
        }
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Message getSelectedETudiant() {
        if (selectedETudiant == null) {
            selectedETudiant = new Message();
        }
        return selectedETudiant;
    }

    public void setSelectedETudiant(Message selectedETudiant) {
        this.selectedETudiant = selectedETudiant;
    }

    public Message getSelected() {
        if (selected == null) {
            selected = new Message();
        }
        return selected;
    }

    public void setSelected(Message selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MessageUpdated"));
    }

    public void destroy(Message msg) {
        selected = msg;
        System.out.println("=============" + msg);
        ejbFacade.remove(msg);
        items.remove(msg);
    }

    public List<Message> getItems() {
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

    public Message getMessage(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Message> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Message> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Message.class)
    public static class MessageControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MessageController controller = (MessageController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "messageController");
            return controller.getMessage(getKey(value));
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
            if (object instanceof Message) {
                Message o = (Message) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Message.class.getName()});
                return null;
            }
        }

    }

    public Date getDatMin() {
        return datMin;
    }

    public void setDatMin(Date datMin) {
        this.datMin = datMin;
    }

    public Date getDatMax() {
        return datMax;
    }

    public void setDatMax(Date datMax) {
        this.datMax = datMax;
    }

    public boolean isLuMsg() {
        return luMsg;
    }

    public void setLuMsg(boolean luMsg) {
        this.luMsg = luMsg;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public int getLu() {
        return lu;
    }

    public void setLu(int lu) {
        this.lu = lu;
    }

}
