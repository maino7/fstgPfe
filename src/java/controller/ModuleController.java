package controller;

import bean.DemandeItem;
import bean.Etudiant;
import bean.Module;
import bean.NoteModulaire;
import bean.NoteSemestre;
import bean.Semestre;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import java.io.IOException;
import service.ModuleFacade;

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

@Named("moduleController")
@SessionScoped
public class ModuleController implements Serializable {

    @EJB
    private service.NoteSemestreFacade noteSemestreFacade;

    @EJB
    private service.NoteModulaireFacade noteModulaireFacade;

    @EJB
    private service.EtudiantFacade etudiantFacade;
    @EJB
    private service.DemandeItemFacade demandeItemFacade;
    @EJB
    private service.DemandeFacade demandeFacade;
    @EJB
    private service.ModuleFacade ejbFacade;
    @EJB
    private service.SemestreFacade semestreFacade;
    private List<Module> items = null;

    private List<Module> modulsNV = null;
    private List<Semestre> semestreNonValid = null;
    private List<Semestre> semestreNonValidDemande = null;
    private Semestre semestre;
    private Semestre semeNV;
    private Module selected;
    private List<NoteModulaire> noteModulaires;
    private NoteSemestre noteSemestre;
    private Etudiant etudiant;
    private int semestrevalide = 2;
    private int etatModule = 2;
    //pour la demande Item
    private Module moduleSelected;
    private String descriptionDemande;
    private List<Module> selectedModulsToStudy;
    private List<Module> selectedModulsToFree;
    private List<Module> ModulsToFree;
    private Module modulNoteValide;
    private List<DemandeItem> demandeItems = null;
    private DemandeItem demandeItem;
    private List<Semestre> semestresRestants;

    public ModuleController() {
    }

    //recherch des semestre non validé d'un etudiant
    public void initSemestres() {
        if (getEtudiant().getCne() == null) {
            setSemestreNonValid(new ArrayList<>());
        } else {
            semestreNonValid = noteSemestreFacade.findSemestresNonValide(etudiant, -1);
        }
    }

    public void initModuls() {
        if (getEtudiant().getCne() == null) {
            setSemestreNonValid(new ArrayList<>());
        } else {
            noteModulaires = new ArrayList<>();
            items = new ArrayList<>();
        }
    }

    public void preparDemande() {
        selectedModulsToStudy = new ArrayList<>();
        setDescriptionDemande("");
    }
// search Semestres with the option 0 sem non valide 1 sem valide else all semestres

    public void searchsemestres() {
        System.out.println("etudiant howa had khona" + getEtudiant());
        if (etudiant.getCne() == null) {
            setSemestreNonValid(new ArrayList<>());
        } else {
            switch (semestrevalide) {
                case 0:
                    setSemestreNonValid(noteSemestreFacade.findSemestresNonValide(etudiant, 0));
                    System.out.println("" + getSemestreNonValid());
                    break;
                case 1:

                    setSemestreNonValid(noteSemestreFacade.findSemestresNonValide(etudiant, 1));
                    System.out.println("" + getSemestreNonValid());

                    break;
                default:
                    setSemestreNonValid(noteSemestreFacade.findSemestresNonValide(etudiant, -1));
                    System.out.println("" + getSemestreNonValid());

                    break;
            }
        }
    }

    //search for note d'un module 
    public void findNoteModule(Module m) {
        if (etudiant.getCne() != null) {
            if (m == null) {
                noteModulaires = new ArrayList<>();
            } else {
                System.out.println("raha dakhlat l methode dyal finde moduls");
                noteModulaires = noteModulaireFacade.findNoteModule(etudiant, m);
            }
        }
    }

//Create a Demande with Demande Items
    public void addToDemande() throws IOException {
        List<Module> m = getSelectedModulsToStudy();
        System.out.println("lista hahya " + m);
        int res = demandeFacade.prepareDemande(m, descriptionDemande, etudiant);
        System.out.println("..........." + res);
        if (res < 0) {
            JsfUtil.addErrorMessage("please select 6 or 7 moduls max of this demande wont be delivred");
        }
        SessionUtil.redirect("../home/Home.xhtml");
    }
    public void addToDemandeAnneReserve() throws IOException {
        List<Module> m = getSelectedModulsToStudy();
        System.out.println("lista hahya " + m);
        int res = demandeFacade.prepareDemandeAnneReserve(m, descriptionDemande, etudiant);
        System.out.println("..........." + res);
        if (res < 0) {
            JsfUtil.addErrorMessage("please select 6 or 7 moduls max of this demande wont be delivred");
        }
        SessionUtil.redirect("../home/Home.xhtml");
    }

    public void initTabPane(int i) {
        if (etudiant.getCne() != null) {

            setNoteModulaires(noteModulaireFacade.findNoteModulaire(etudiant, i));
            Semestre sem = new Semestre();
            sem.setLibelle(i);
            noteSemestre = noteSemestreFacade.findNSBySemestreLibelleAndEtudiant(getEtudiant(), sem);
            System.out.println("ns " + noteSemestre);
        }
    }

    public void findModuls(Semestre s) {
        if (etudiant.getCne() != null) {
            if (s == null) {
                items = new ArrayList<>();
            } else {
                setNoteModulaires(noteModulaireFacade.findNoteModulaire(etudiant, s, etatModule));
            }
        }
    }

    public void findModulsNotValids() {

        System.out.println("raha dakhlat l methode dyal finde modulsNoteValids");
        modulsNV = noteModulaireFacade.findModuls(etudiant, getSemeNV(), 0);
        System.out.println(modulsNV);
        System.out.println("wach lisa 3amra ?" + getSelectedModulsToStudy());

    }

    public List<Semestre> getSemestresRestants() {

        if (semestresRestants == null) {
            System.out.println("rah dik list raha null :3 ");
            semestresRestants = new ArrayList<>();
        } else {
            System.out.println("hado homa les semestres li radi i9rahom had l3am" + semestresRestants);
            semestresRestants = noteSemestreFacade.findeSemesterDeCetAnne(getEtudiant());
        }

        return semestresRestants;
    }

    public void setSemestresRestants(List<Semestre> semestresRestants) {
        this.semestresRestants = semestresRestants;
    }

    public Semestre getSemeNV() {
        if (semeNV == null) {
            semeNV = new Semestre();

        }
        return semeNV;
    }

    public void setSemeNV(Semestre semeNV) {
        this.semeNV = semeNV;
    }

    public int getEtatModule() {
        return etatModule;
    }

    public NoteSemestre getNoteSemestre() {
        return noteSemestre;
    }

    public void setNoteSemestre(NoteSemestre noteSemestre) {
        this.noteSemestre = noteSemestre;
    }

    public String getDescriptionDemande() {
        return descriptionDemande;
    }

    public void setDescriptionDemande(String descriptionDemande) {
        this.descriptionDemande = descriptionDemande;
    }

    public Module getModulNoteValide() {
        if (modulNoteValide == null) {
            modulNoteValide = new Module();
        }
        return modulNoteValide;
    }

    public void setModulNoteValide(Module modulNoteValide) {
        this.modulNoteValide = modulNoteValide;
    }

    public Module getModuleSelected() {
        if (moduleSelected == null) {
            moduleSelected = new Module();
        }
        return moduleSelected;
    }

    public void setModuleSelected(Module moduleSelected) {
        this.moduleSelected = moduleSelected;
    }

    public List<NoteModulaire> getNoteModulaires() {
        if (noteModulaires == null) {
            noteModulaires = new ArrayList<>();
        }
        return noteModulaires;
    }

    public List<Module> getSelectedModulsToStudy() {
        if (selectedModulsToStudy == null) {
            selectedModulsToStudy = new ArrayList<>();
        }
        return selectedModulsToStudy;
    }

    public void setSelectedModulsToStudy(List<Module> selectedModulsToStudy) {
        this.selectedModulsToStudy = selectedModulsToStudy;
    }

    public List<Module> getSelectedModulsToFree() {
        if (selectedModulsToFree == null) {
            selectedModulsToFree = new ArrayList<>();
        }
        return selectedModulsToFree;
    }

    public void setSelectedModulsToFree(List<Module> selectedModulsToFree) {
        this.selectedModulsToFree = selectedModulsToFree;
    }

    public List<Module> getModulsToFree() {
        if (ModulsToFree == null) {
            ModulsToFree = new ArrayList<>();
        }
        return ModulsToFree;
    }

    public void setModulsToFree(List<Module> ModulsToFree) {
        this.ModulsToFree = ModulsToFree;
    }

    public void modulsSmestreActuel() {
        List<Semestre> s = noteSemestreFacade.findSemestresNonValideDemande(getEtudiant());
        System.out.println("hado les semestres " + s);
        List<Module> mood = new ArrayList<>();
        if (s != null) {
            if (s.size() == 1) {

                mood.addAll(noteModulaireFacade.findModuls(etudiant, s.get(0), 0));
                System.out.println("hado les modules non valide f smestre non valide 1" + noteModulaireFacade.findModuls(etudiant, s.get(0), 0));
            } else if (s.size() > 1) {
                mood.addAll(noteModulaireFacade.findModuls(etudiant, s.get(0), 0));
                System.out.println("hado les modules non valide f smestre non valide 2" + noteModulaireFacade.findModuls(etudiant, s.get(0), 0));

                mood.addAll(noteModulaireFacade.findModuls(etudiant, s.get(1), 0));
                System.out.println("hado les modules non valide f smestre non valide 2 " + noteModulaireFacade.findModuls(etudiant, s.get(1), 0));

            }
            mood.addAll(noteModulaireFacade.modulsOfSemestreActuel(getEtudiant()));
        }
        if (mood == null) {
            mood = new ArrayList<>();
        }
        setModulsNV(mood);
    }

    public void modulsAnneReserve() {
        List<Semestre> s = noteSemestreFacade.findSemestresAnneReserve(getEtudiant());
        System.out.println("hado les semestres " + s);
        List<Module> mood = new ArrayList<>();
        if (s != null) {
            mood.addAll(noteModulaireFacade.findModuls(etudiant, s.get(0), 0));
            System.out.println("hado les modules non valide f smestre non valide 2" + noteModulaireFacade.findModuls(etudiant, s.get(0), 0));
            mood.addAll(noteModulaireFacade.findModuls(etudiant, s.get(1), 0));
            System.out.println("hado les modules non valide f smestre non valide 2 " + noteModulaireFacade.findModuls(etudiant, s.get(1), 0));
        }
        if (mood == null) {
            mood = new ArrayList<>();
        }
        setModulsNV(mood);
    }

    public List<Module> getModulsNV() {
        if (modulsNV == null) {
            modulsNV = new ArrayList<>();
        }

        return modulsNV;
    }

    public void setModulsNV(List<Module> modulsNV) {
        this.modulsNV = modulsNV;
    }

    public void setNoteModulaires(List<NoteModulaire> noteModulaires) {
        this.noteModulaires = noteModulaires;
    }

    public List<Semestre> getSemestreNonValidDemande() {
        List<Semestre> s = noteSemestreFacade.findSemestresNonValideDemande(getEtudiant());
        System.out.println("" + noteSemestreFacade.findSemestresNonValideDemande(getEtudiant()));
        if (s.size() == 1) {
            modulsNV = noteModulaireFacade.findModuls(etudiant, s.get(0), 0);
        }
        return s;
    }

    public void setSemestreNonValidDemande(List<Semestre> semestreNonValidDemande) {
        this.semestreNonValidDemande = semestreNonValidDemande;
    }

    public void setEtatModule(int etatModule) {
        this.etatModule = etatModule;
    }

    public List<Semestre> getSemestreNonValid() {
        if (semestreNonValid == null) {
            semestreNonValid = new ArrayList<>();
        }
        return semestreNonValid;
    }

    public void setSemestreNonValid(List<Semestre> semestreNonValid) {
        this.semestreNonValid = semestreNonValid;
    }

    public int getSemestrevalide() {
        return semestrevalide;
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

    public void setSemestrevalide(int semestrevalide) {
        this.semestrevalide = semestrevalide;
    }

    public DemandeItem getDemandeItem() {
        if (demandeItem == null) {
            demandeItem = new DemandeItem();
        }
        return demandeItem;
    }

    public void setDemandeItem(DemandeItem demandeItem) {
        this.demandeItem = demandeItem;
    }

    public Module getSelected() {
        if (selected == null) {
            selected = new Module();
        }
        return selected;
    }

    public List<DemandeItem> getDemandeItems() {
        if (demandeItems == null) {
            demandeItems = new ArrayList<>();
        }
        return demandeItems;
    }

    public void setDemandeItems(List<DemandeItem> demandeItems) {
        this.demandeItems = demandeItems;
    }

    public Etudiant getEtudiant() {
        Etudiant e = (Etudiant) SessionUtil.getConnectedUser();
        etudiant = etudiantFacade.find(e.getCne());
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public void setSelected(Module selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ModuleFacade getFacade() {
        return ejbFacade;
    }

    public Module prepareCreate() {
        selected = new Module();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ModuleCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ModuleUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ModuleDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Module> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Semestre> semestresByFiliere() {
        return semestreFacade.findByFiliere(selected.getFiliere());
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

    public Module getModule(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Module> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Module> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Module.class)
    public static class ModuleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ModuleController controller = (ModuleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "moduleController");
            return controller.getModule(getKey(value));
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
            if (object instanceof Module) {
                Module o = (Module) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Module.class.getName()});
                return null;
            }
        }

    }

}
