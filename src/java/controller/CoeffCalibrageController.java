package controller;

import bean.CoeffCalibrage;
import bean.EtablissementType;
import controller.util.DateUtil;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.CoeffCalibrageFacade;

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

@Named("coeffCalibrageController")
@SessionScoped
public class CoeffCalibrageController implements Serializable {

    @EJB
    private service.CoeffCalibrageFacade ejbFacade;
    private List<CoeffCalibrage> items = null;
    private CoeffCalibrage selected;
    private EtablissementType et;
    private float coeffi;
    private int nbrMax;
    private int nbrMin;
    private float noteMin;
    private String annee;
    private int editable;
    
    
    public CoeffCalibrageController() {
    }

    public CoeffCalibrage getSelected() {
        if(selected == null){
            selected = new CoeffCalibrage();
            String d = DateUtil.format(new Date());
            selected.setAnnee(d);
        }
        return selected;
    }

    public void setSelected(CoeffCalibrage selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CoeffCalibrageFacade getFacade() {
        return ejbFacade;
    }

    public CoeffCalibrage prepareCreate() {
        selected = new CoeffCalibrage();
        initializeEmbeddableKey();
        return selected;
    }
    //=======Methode=========//
    public void creer(){
        int i = getFacade().creerCoeff(selected);
        System.out.println("ha get annee==>"+selected.getAnnee());
        System.out.println("ha get selected==>"+selected);
        if(i == -1 || i == -2){
             FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Error"));
        } else if(i == -3){
             FacesContext.getCurrentInstance().addMessage("msgCritere",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Place Min > Place Max !","hadchi khayeb a sat welah"));
        }
        else {
             FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("success"));
        }
        selected = null;
        items = getFacade().findAll();
        System.out.println("ha l i==>"+i);
    }
    public void findByCretar(){
        System.out.println("ha coeff=>"+coeffi);
        System.out.println("ha nbrMax=>"+nbrMax);
        System.out.println("ha nbrMin=>"+nbrMin);
        System.out.println("ha noteMin=>"+noteMin);
        items = getFacade().findByCretaria(et, coeffi, nbrMax, nbrMin, noteMin, annee);
    }
    public void editable1(){
        if(editable == 0){
            editable = 1;
        }else {
            editable = 0;
        }
    }
    
     public void onRowEdit(RowEditEvent event) {
         CoeffCalibrage cf = (CoeffCalibrage)event.getObject();
         System.out.println("ha cf==>"+cf);
         System.out.println("nbr nim==>"+cf.getNbrMin());
         System.out.println("nbr max==>"+cf.getNbrMax());
                if(cf.getNbrMax() < cf.getNbrMin()){
                    System.out.println("dkhel l hadi");
                    FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Place Minimum > Place Maximum !"));
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
    
    public void test(){
        System.out.println("dkhel l button !");
    }
     
    
    //=======================//

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CoeffCalibrageCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CoeffCalibrageUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CoeffCalibrageDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CoeffCalibrage> getItems() {
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

    public CoeffCalibrage getCoeffCalibrage(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<CoeffCalibrage> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CoeffCalibrage> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CoeffCalibrage.class)
    public static class CoeffCalibrageControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CoeffCalibrageController controller = (CoeffCalibrageController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "coeffCalibrageController");
            return controller.getCoeffCalibrage(getKey(value));
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
            if (object instanceof CoeffCalibrage) {
                CoeffCalibrage o = (CoeffCalibrage) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CoeffCalibrage.class.getName()});
                return null;
            }
        }

    }

    public EtablissementType getEt() {
        return et;
    }

    public void setEt(EtablissementType et) {
        this.et = et;
    }

    public float getCoeffi() {
        return coeffi;
    }

    public void setCoeffi(float coeffi) {
        this.coeffi = coeffi;
    }

    public int getNbrMax() {
        return nbrMax;
    }

    public void setNbrMax(int nbrMax) {
        this.nbrMax = nbrMax;
    }

    public int getNbrMin() {
        return nbrMin;
    }

    public void setNbrMin(int nbrMin) {
        this.nbrMin = nbrMin;
    }

    public float getNoteMin() {
        return noteMin;
    }

    public void setNoteMin(float noteMin) {
        this.noteMin = noteMin;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public int getEditable() {
        return editable;
    }

    public void setEditable(int editable) {
        this.editable = editable;
    }

    
    

}
