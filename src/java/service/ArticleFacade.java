/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Article;
import controller.util.JsfUtil;
import controller.util.SearchUtil;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mac
 */
@Stateless
public class ArticleFacade extends AbstractFacade<Article> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    public Article findArticleByTitle(String selectedTitle) {
        String rqt = "SELECT a FROM Article a WHERE 1=1";
        rqt += SearchUtil.addConstraint("a", "title", "=", selectedTitle);
        return (Article) em.createQuery(rqt).getSingleResult();

    }

    public List<Article> findArticleByType(int type) {

        String rqt = "SELECT a FROM Article a WHERE 1=1";
        rqt += SearchUtil.addConstraint("a", "type", "=", type);
        return em.createQuery(rqt).getResultList();

    }

    public boolean verifyArticle(Article article) {
        for (Article a : findAll()) {
            if (a.getTitle().equals(article.getTitle()) ){
                JsfUtil.addErrorMessage("Ce titre exist deja !");
                return false;
            }
        }

        return true;
    }

    public Article clone(Article article) {

        Article cloned = new Article();
        if (verifyArticle(article)) {
            cloned.setContent(article.getContent());
            cloned.setDatePublication(article.getDatePublication());
            cloned.setId(article.getId());
            if (article.getImages() == null) {
                article.setImages(new ArrayList<>());
            }
            cloned.setImages(article.getImages());

            cloned.setTitle(article.getTitle());
            cloned.setType(article.getType());
        } else {

        }
        return cloned;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticleFacade() {
        super(Article.class);
    }

}
