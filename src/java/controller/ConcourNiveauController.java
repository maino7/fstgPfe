package controller;

import bean.CoeffCalibrage;
import bean.ConcourExamMatiere;
import bean.ConcourNiveau;
import bean.MatiereConcour;
import controller.util.DateUtil;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.ConcourNiveauFacade;

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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.RowEditEvent;

@Named("concourNiveauController")
@SessionScoped
public class ConcourNiveauController implements Serializable {

    @EJB
    private service.ConcourNiveauFacade ejbFacade;
    @EJB
    private service.ConcourExamMatiereFacade concourExamMatiereFacade;
    private List<ConcourNiveau> items = null;
    private ConcourNiveau selected;
    private ConcourExamMatiere concourExamMatiere;
    private String dateExam;

    public ConcourNiveauController() {
    }

    public ConcourNiveau getSelected() {
        if (selected == null) {
            selected = new ConcourNiveau();
        }
        return selected;
    }

    public void setSelected(ConcourNiveau selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ConcourNiveauFacade getFacade() {
        return ejbFacade;
    }

    public ConcourNiveau prepareCreate() {
        selected = new ConcourNiveau();
        initializeEmbeddableKey();
        return selected;
    }

    //=====Methode=====//
    public void test() {
        System.out.println("khedamt");

    }

    public void creer() {
        int i = getFacade().verification(selected);
        if (i == -1) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "nbr orale supérieure a nbr ecrit", ""));
        } else if (i == -2) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "nbr d'admis superieure a nbr orale", ""));
        } else if (i == -3) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ce Concours existe deja", ""));
        } else {
            selected.setAnnee(DateUtil.format(new Date()));
            create();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("success"));
            selected = new ConcourNiveau();
        }

    }
    
    public void creerExam(){
        System.out.println("=========CREEREXAM=============");
        if(concourExamMatiereFacade.verfiyExist(concourExamMatiere) == -1){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Epreuve deja crée", ""));
        }else {
         System.out.println("HA L EXAas===========>"+concourExamMatiere);
        Date d = DateUtil.convert(dateExam);
        concourExamMatiere.setDateExam(d);
        concourExamMatiereFacade.create(concourExamMatiere);
         FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", ""));
         concourExamMatiere = new ConcourExamMatiere();
        }
    }
    
         public void onRowEdit(RowEditEvent event) {
         ConcourNiveau cf = (ConcourNiveau)event.getObject();
         System.out.println("ha cf==>"+cf);
         System.out.println("nbr nim==>"+cf.getNbrDePlaceOrale());
         System.out.println("nbr max==>"+cf.getNbrDePlaceEcrit());
                if(cf.getNbrDePlaceOrale() > cf.getNbrDePlaceEcrit()){
                    System.out.println("dkhel l hadi");
                    FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Nombre orale > Nombre écrit !"));
                } else if(cf.getNbrDePladeAdmis()> cf.getNbrDePlaceOrale()){
                    JsfUtil.addErrorMessage2("Place admis > place orale");
                } else{
        getFacade().edit(cf);
        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("success"));
                }
    }
     
    public void onRowCancel(RowEditEvent event) {
        
       FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("update canceled"));
    }
    //=================//

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ConcourNiveauCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ConcourNiveauUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ConcourNiveauDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ConcourNiveau> getItems() {
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

    public ConcourNiveau getConcourNiveau(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<ConcourNiveau> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ConcourNiveau> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ConcourNiveau.class)
    public static class ConcourNiveauControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConcourNiveauController controller = (ConcourNiveauController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "concourNiveauController");
            return controller.getConcourNiveau(getKey(value));
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
            if (object instanceof ConcourNiveau) {
                ConcourNiveau o = (ConcourNiveau) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ConcourNiveau.class.getName()});
                return null;
            }
        }

    }

    public ConcourExamMatiere getConcourExamMatiere() {
        if(concourExamMatiere == null){
            concourExamMatiere = new ConcourExamMatiere();
        }
        return concourExamMatiere;
    }

    public void setConcourExamMatiere(ConcourExamMatiere concourExamMatiere) {
        this.concourExamMatiere = concourExamMatiere;
    }

    public String getDateExam() {
        return dateExam;
    }

    public void setDateExam(String dateExam) {
        this.dateExam = dateExam;
    }
    

}
