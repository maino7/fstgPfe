package controller;

import bean.Article;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.ServerConfigUtil;
import service.ArticleFacade;

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
import org.primefaces.model.UploadedFile;

@Named("articleController")
@SessionScoped
public class ArticleController implements Serializable {

    @EJB
    private service.ArticleFacade ejbFacade;
    private List<Article> items = null;
    private Article selected;
    private Article contentToRender;
    private Article selectedActivite;
    private Article selectedRecherche;
    private Article selectedFaculte;
    private Article selectedEspaceEtudiant;
    private UploadedFile uploadedFile;

    public ArticleController() {

    }

    public Article getSelected() {
        return selected;
    }

    public void setSelected(Article selected) {
        this.selected = selected;
    }

    public Article getSelectedActivite() {
        return selectedActivite;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void setSelectedActivite(Article selectedActivite) {
        this.selectedActivite = selectedActivite;
    }

    public Article getContentToRender() {
        return contentToRender;
    }

    public void setContentToRender(Article contentToRender) {
        this.contentToRender = contentToRender;
    }

    public Article getSelectedRecherche() {
        return selectedRecherche;
    }

    public void setSelectedRecherche(Article selectedRecherche) {
        this.selectedRecherche = selectedRecherche;
    }

    public Article getSelectedFaculte() {
        return selectedFaculte;
    }

    public void setSelectedFaculte(Article selectedFaculte) {
        this.selectedFaculte = selectedFaculte;
    }

    public Article getSelectedEspaceEtudiant() {
        return selectedEspaceEtudiant;
    }

    public void setSelectedEspaceEtudiant(Article selectedEspaceEtudiant) {
        this.selectedEspaceEtudiant = selectedEspaceEtudiant;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ArticleFacade getFacade() {
        return ejbFacade;
    }

    public List<Article> findArticleByType(int type) {

        return ejbFacade.findArticleByType(type);
    }

    public Article findArticleByTitle() {
        contentToRender = ejbFacade.findArticleByTitle(selected.getTitle());
        return ejbFacade.findArticleByTitle(selected.getTitle());
    }

    public Article findArticleByTitle(String title) {
        Article a = ejbFacade.findArticleByTitle(title);
        System.out.println("content "+a.getContent());
        return a;
    }

    public void handleUpload() {

        String destinationPath = ServerConfigUtil.getArticleFilePath();
        String nameOfUploadedFile = uploadedFile.getFileName();
//        ServerConfigUtil.upload(uploadedFile, destinationPath, nameOfUploadedFile);
        ejbFacade.clone(selected).getImages().add("../resources/images/UploadedImages/" + nameOfUploadedFile);

    }

    public Article prepareCreate() {

        selected = new Article();
        initializeEmbeddableKey();
        return selected;
    }

    public void setDefaultContent() {
        contentToRender = ejbFacade.findArticleByTitle("Home");

    }

    public boolean checkContentTorender(String title) {
        boolean i = false;

        if (contentToRender == null) {
            i = false;

        } else {
            
           
                if (contentToRender.getTitle().equals(title)) {
                    i = true;

                } else {
                    i = false;
                }

            }
            return i;
        }
   

    public String setRenderedContent(String title) {
        contentToRender = ejbFacade.findArticleByTitle(title);
        return "/template/menu.xhtml" ;
    }

    public void create() {
        handleUpload();
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ArticleCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ArticleUpdated"));
    }

    public void destroy() {

        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ArticleDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Article> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            //Filter Content Input !!! 
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

    public Article getArticle(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Article> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Article> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Article.class)
    public static class ArticleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArticleController controller = (ArticleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "articleController");
            return controller.getArticle(getKey(value));
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
            if (object instanceof Article) {
                Article o = (Article) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Article.class.getName()});
                return null;
            }
        }

    }

}
