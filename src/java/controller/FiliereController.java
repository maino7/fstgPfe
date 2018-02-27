package controller;

import bean.Enseignant;
import bean.Filiere;
import bean.Semestre;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.FiliereFacade;

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

@Named("filiereController")
@SessionScoped
public class FiliereController implements Serializable {

    @EJB
    private service.FiliereFacade ejbFacade;
    @EJB
    private service.ModuleFacade moduleFacade;
    @EJB
    private service.SemestreFacade semestreFacade;
    @EJB
    private service.EnseignantFacade enseignantFacade;
    private List<Filiere> items = null;
    private Filiere selected;
    private Filiere filiere;
    private List<Filiere> filieres;
    private List<Semestre> semestres;
    private Semestre semestre;
    private int typeFilier;
    private int typFormation;
    private String filiereEnLettre;

    public List<Filiere> findFiliere() {
        return ejbFacade.findByType(typeFilier, typFormation);
    }

    public String typFilierToLetter() {
        String typFilier;
        if (typeFilier == 1) {
            typFilier = "Tron commun";
            return typFilier;
        }
        if (typeFilier == 2) {
            typFilier = "Licence science et technique";
            return typFilier;
        }
        if (typeFilier == 3) {
            typFilier = "Cycle d'ingenieurs";
            return typFilier;
        }
        if (typeFilier == 4) {
            typFilier = "Master science et technique";
            return typFilier;
        }

        return "";
    }

    public String typFormatToLettre() {
        String typeFormation;
        if (typFormation == 1) {
            typeFormation = "Formation initiale";
            return typeFormation;
        }
        if (typFormation == 2) {
            typeFormation = "Formation continue";
            return typeFormation;
        }
        return "";
    }

    public String redirect(int x, int y) {
        filieres = ejbFacade.findByType(x, y);
        return "/filiere/FormationInitial?faces-redirect=true";
    }

    public String toTC() {
        typeFilier = 1;
        typFormation = 1;
        return redirect(1, 1);
    }

    public String toLicence() {
        typeFilier = 2;
        typFormation = 1;
        return redirect(2, 1);
    }

    public String toCycle() {
        typeFilier = 3;
        typFormation = 1;
        return redirect(3, 1);
    }

    public String toMaster() {
        typeFilier = 4;
        typFormation = 1;
        return redirect(4, 1);
    }

    public String toDetailFilier(Filiere f) {
        filiere = f;
        semestres = semestreFacade.findByFiliere(f);
        return "/filiere/DetailFiliere?faces-redirect=true";
    }

    public String toLicenceContinu() {
        return redirect(2, 2);
    }

    public String toMasterContinu() {
        typeFilier = 4;
        typFormation = 2;
        return redirect(4, 2);
    }

//    //s'il y a une creation d'une branche de master continu apres=> un lien vas etre generer automatiquement!!
//    public String linkOfMasterContinue() {
//        filieres = ejbFacade.findByType(4, 2);
//        if (filieres.size() > 0) {
//            return "<li><p:commandLink value='Master' action='#{filiereController.toMasterContinu())}'/></li>";
//        }
//        return "";
//    }
    public List<Semestre> findSemstreByFilier() {
        return semestreFacade.findByFiliere(filiere);
    }

    public FiliereController() {
    }

    public Filiere getSelected() {
        return selected;
    }

    public void setSelected(Filiere selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FiliereFacade getFacade() {
        return ejbFacade;
    }

    public Filiere prepareCreate() {
        selected = new Filiere();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FiliereCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FiliereUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FiliereDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Filiere> getItems() {
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

    public Filiere getFiliere(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Filiere> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Filiere> getItemsAvailableSelectOne() {
//        List<Filiere> filiereItems = new ArrayList<>();
//        Enseignant enseignant = (Enseignant) SessionUtil.getConnectedUser();
//        if (enseignant != null) {
//            if (enseignantFacade.isEnsAdmin()) {
        List<Filiere> filiereItems = getFacade().findAll();
//            }
//
//            filiereItems.add(enseignant.getFiliere());
//
//        }

        return filiereItems;
    }

    @FacesConverter(forClass = Filiere.class, value = "filiereConverter")
    public static class FiliereControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FiliereController controller = (FiliereController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "filiereController");
            return controller.getFiliere(getKey(value));
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
            if (object instanceof Filiere) {
                Filiere o = (Filiere) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Filiere.class.getName()});
                return null;
            }
        }

    }

    public int getTypeFilier() {
        return typeFilier;
    }

    public void setTypeFilier(int typeFilier) {
        this.typeFilier = typeFilier;
    }

    public List<Filiere> getFilieres() {
        return filieres;
    }

    public void setFilieres(List<Filiere> filieres) {
        this.filieres = filieres;
    }

    public Semestre getSemestre() {
        if (semestre == null) {
            semestre = new Semestre();
        }
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public Filiere getFiliere() {
        if (filiere == null) {
            filiere = new Filiere();
        }
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public List<Semestre> getSemestres() {
        return semestres;
    }

    public void setSemestres(List<Semestre> semestres) {
        this.semestres = semestres;
    }

    public int getTypFormation() {
        return typFormation;
    }

    public void setTypFormation(int typFormation) {
        this.typFormation = typFormation;
    }

}
