package controller;

import bean.Departement;
import bean.Enseignant;
import bean.Filiere;
import bean.Semestre;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.ServerConfigUtil;
import service.DepartementFacade;

import java.io.Serializable;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.UploadedFile;

@Named("departementController")
@SessionScoped
public class DepartementController implements Serializable {

    @EJB
    private service.DepartementFacade ejbFacade;
    @EJB
    private service.EnseignantFacade enseignantFacade;
    @EJB
    private service.SemestreFacade semestreFacade;
    @EJB
    private service.FiliereFacade filiereFacade;
    private List<Departement> items = null;
    private Departement selected;
    private List<Enseignant> enseignants;
    private List<Filiere> tc;
    private List<Filiere> licences;
    private List<Filiere> master;
    private List<Filiere> cycle;
    private UploadedFile uploadedFile;
    private Filiere filiere;
    private List<Semestre> semestres;

//    private FileUploadEvent fileUploadEvent;
    public String toDetail(Departement d) {
        selected = d;
        tc = filiereFacade.findByDepartm(d, 1);
        licences = filiereFacade.findByDepartm(d, 2);
        cycle = filiereFacade.findByDepartm(d, 3);
        master = filiereFacade.findByDepartm(d, 4);
        enseignants = enseignantFacade.findByDepartm(selected);
        System.out.println("==========>enseigna siz==  " + enseignants.size());
        return "/departement/DepartementDetail?faces-redirect=true";

    }

    public String toDetailFilier(Filiere f) {
        filiere = f;
        semestres = semestreFacade.findByFiliere(f);
        System.out.println("========= size semestre "+semestres.size());
        return "/departement/DetailFiliere?faces-redirect=true";
    }

    public DepartementController() {
    }

    public Departement getSelected() {
        if (selected == null) {
            selected = new Departement();
        }
        return selected;
    }

    public void setSelected(Departement selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DepartementFacade getFacade() {
        return ejbFacade;
    }

    public Departement prepareCreate() {
        selected = new Departement();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        uploadFiles();
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DepartementCreated"));
        if (!JsfUtil.isValidationFailed()) {
            System.out.println("========== invalidate list of items");
            getItems().add(ejbFacade.clone(selected));
            prepareCreate();    // Invalidate list of items to trigger re-query.
        }
//        fileUploadEvent = null;
    }

    public void uploadFiles() {
        System.out.println("IN");
        if (uploadedFile != null) {
            System.out.println("upfile not nul");
            String chemin = "C:\\pfe\\photo";
            ServerConfigUtil.upload(uploadedFile, chemin);
            ejbFacade.clone(selected).setImg(chemin + "//" + uploadedFile.getFileName());
            System.out.println("=======in uploadFiles img = " + selected.getImg());

        } else {
            System.out.println("up====null");
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DepartementUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DepartementDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Departement> getItems() {
        if (items == null) {
            items = ejbFacade.findAllDeprtm();
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

    public Departement getDepartement(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Departement> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Departement> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Departement.class, value = "departementConverter")
    public static class DepartementControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DepartementController controller = (DepartementController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "departementController");
            return controller.getDepartement(getKey(value));
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
            if (object instanceof Departement) {
                Departement o = (Departement) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Departement.class.getName()});
                return null;
            }
        }

    }

    public List<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public List<Filiere> getTc() {
        return tc;
    }

    public void setTc(List<Filiere> tc) {
        this.tc = tc;
    }

    public List<Filiere> getLicences() {
        return licences;
    }

    public void setLicences(List<Filiere> licences) {
        this.licences = licences;
    }

    public List<Filiere> getMaster() {
        return master;
    }

    public void setMaster(List<Filiere> master) {
        this.master = master;
    }

    public List<Filiere> getCycle() {
        return cycle;
    }

    public void setCycle(List<Filiere> cycle) {
        this.cycle = cycle;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

//    public FileUploadEvent getFileUploadEvent() {
//        return fileUploadEvent;
//    }
//
//    public void setFileUploadEvent(FileUploadEvent fileUploadEvent) {
//        this.fileUploadEvent = fileUploadEvent;
//    }
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
    

}
