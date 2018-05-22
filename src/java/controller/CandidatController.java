package controller;

import bean.Candidat;
import bean.Niveau;
import bean.Section;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.CandidatFacade;

import java.io.Serializable;
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

@Named("candidatController")
@SessionScoped
public class CandidatController implements Serializable {

    @EJB
    private service.CandidatFacade ejbFacade;
    @EJB
    private service.CondidatureFacade condidatureFacade;
    @EJB
    private service.PieceEtudiantFacade pieceEtudiantFacade;
    @EJB
    private service.NiveauFacade niveauFacade;
    private List<Candidat> items = null;
    private Candidat selected;
    private int typeInscription;
    private String cne;
    private Section section;
    private Niveau niveau;
    private List<Niveau> niveaus = null;

    public CandidatController() {
    }

    public Candidat getSelected() {
        if(selected == null){
            selected = new Candidat();
        }
        return selected;
    }

    public void setSelected(Candidat selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CandidatFacade getFacade() {
        return ejbFacade;
    }
    public void niveauBySection(){
        
        niveaus = niveauFacade.findBySection(section);
        System.out.println("ha les niveau===>"+niveaus);
    }
    
    public void findBycreataria(){
        items = null;
        System.out.println("ha niveau==>"+niveau+"o ha section==>"+section+"o ha cne==>"+cne);
        items = pieceEtudiantFacade.findByNiveauAndSection(niveau,section,cne);
       
        System.out.println("ha l items===>"+items);
    }
    public void validerCandidat(){
        System.out.println("ha selected==>"+selected);
        condidatureFacade.validerCandidature(selected);
       
        items.remove(items.indexOf(selected));
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage("Candidat valider"));
        System.out.println("ha l items==>"+items);
    }

    public Candidat prepareCreate() {
        selected = new Candidat();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CandidatCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CandidatUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CandidatDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Candidat> getItems() {
        if (items == null) {
            items = getFacade().findNonvalider();
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

    public Candidat getCandidat(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Candidat> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Candidat> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Candidat.class)
    public static class CandidatControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CandidatController controller = (CandidatController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "candidatController");
            return controller.getCandidat(getKey(value));
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
            if (object instanceof Candidat) {
                Candidat o = (Candidat) object;
                return getStringKey(o.getCne());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Candidat.class.getName()});
                return null;
            }
        }

    }
    //test
    
     public void testwalo(Candidat e){
         System.out.println("hahowa dkhel");
         selected = e;
         
     }

    public int getTypeInscription() {
        return typeInscription;
    }

    public void setTypeInscription(int typeInscription) {
        this.typeInscription = typeInscription;
    }

    public Section getSection() {
        if(section == null){
            section = new Section();
        }
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public List<Niveau> getNiveaus() {
        
        return niveaus;
    }

    public void setNiveaus(List<Niveau> niveaus) {
        this.niveaus = niveaus;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }
    
    
    
    
    
    
     

}
