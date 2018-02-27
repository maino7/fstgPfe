package controller;

import bean.Etudiant;
import bean.NoteSemestre;
import bean.Semestre;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.NoteSemestreFacade;

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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import service.EtudiantFacade;
import service.SemestreFacade;

@Named("noteSemestreController")
@SessionScoped
public class NoteSemestreController implements Serializable {

    @EJB
    private service.NoteSemestreFacade ejbFacade;
    @EJB
    private service.EtudiantFacade etudiantFacade;
    @EJB
    private service.SemestreFacade semestreFacade;
    private List<NoteSemestre> items = null;
    private NoteSemestre selected;
    private LineChartModel lineModel;
    private Etudiant etudiant;
    private List<NoteSemestre> noteSemestres;
    private List<Semestre> semestres;

    public NoteSemestreController() {
    }

    public NoteSemestre getSelected() {
        return selected;
    }

    public void setSelected(NoteSemestre selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    public LineChartModel initChart() {
        Etudiant e = (Etudiant) SessionUtil.getConnectedUser();
        etudiant = etudiantFacade.find(e.getCne());
        lineModel = ejbFacade.initBarModel(noteSemestres, etudiant);
        lineModel.setTitle("Statistique");
        lineModel.setAnimate(true);
        Axis xAxis = lineModel.getAxis(AxisType.X);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis(""));
        xAxis.setLabel("Les trimestres");
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("Note");
        yAxis.setMin(0);
        yAxis.setMax(ejbFacade.maxY(etudiant) * 1.1);
        return lineModel;

    }

    protected void initializeEmbeddableKey() {
    }

    public List<NoteSemestre> getNoteSemestres() {
        if (noteSemestres == null) {
            noteSemestres = new ArrayList<>();
        }
        return noteSemestres;
    }

    public void setNoteSemestres(List<NoteSemestre> noteSemestres) {
        this.noteSemestres = noteSemestres;
    }

    private NoteSemestreFacade getFacade() {
        return ejbFacade;
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

    public LineChartModel getLineModel() {
        if (lineModel == null) {
            lineModel = new LineChartModel();
        }
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public NoteSemestre prepareCreate() {
        selected = new NoteSemestre();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("NoteSemestreCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("NoteSemestreUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("NoteSemestreDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<NoteSemestre> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void semestreByEtudiant() {
        semestres = semestreFacade.findSemestreByNoteModulaireAndEtudiant(selected.getEtudiant());
    }

    public NoteSemestreFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(NoteSemestreFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public EtudiantFacade getEtudiantFacade() {
        return etudiantFacade;
    }

    public void setEtudiantFacade(EtudiantFacade etudiantFacade) {
        this.etudiantFacade = etudiantFacade;
    }

    public SemestreFacade getSemestreFacade() {
        return semestreFacade;
    }

    public void setSemestreFacade(SemestreFacade semestreFacade) {
        this.semestreFacade = semestreFacade;
    }

    public List<Semestre> getSemestres() {
        if (semestres == null) {
            semestres = new ArrayList();
        }
        return semestres;
    }

    public void setSemestres(List<Semestre> semestres) {
        this.semestres = semestres;
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

    public NoteSemestre getNoteSemestre(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<NoteSemestre> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<NoteSemestre> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = NoteSemestre.class)
    public static class NoteSemestreControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NoteSemestreController controller = (NoteSemestreController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "noteSemestreController");
            return controller.getNoteSemestre(getKey(value));
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
            if (object instanceof NoteSemestre) {
                NoteSemestre o = (NoteSemestre) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), NoteSemestre.class.getName()});
                return null;
            }
        }

    }

}
