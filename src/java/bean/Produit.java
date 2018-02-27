/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Entity
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String libelle;
    private String description;
    private double prix;
    @ManyToOne
    private Categorie categorie;
    @ManyToOne
    private Marque marque;
    @OneToMany(mappedBy = "produit")
    private List<LigneReception> ligneReceptions;
    @OneToMany(mappedBy = "produit")
    private List<LigneMagasin> ligneMagasins;
    @OneToMany(mappedBy = "produit")
    private List<LigneLivraison> ligneLivraisons;
    @OneToMany(mappedBy = "produit")
    private List<LigneFacture> ligneFactures;
    @OneToMany(mappedBy = "produit")
    private List<LigneExpressionBesoin> ligneExpressionBesoins;
    @OneToMany(mappedBy = "produit")
    private List<LigneCommande> ligneCommandes;

    public Produit() {
    }

    public Produit(String id) {
        this.id = id;
    }

    public Produit(String id, String libelle, double prix) {
        this.id = id;
        this.libelle = libelle;
        this.prix = prix;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Categorie getCategorie() {
        if (categorie == null) {
            categorie = new Categorie();
        }
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Marque getMarque() {
        if (marque == null) {
            marque = new Marque();
        }
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public List<LigneReception> getLigneReceptions() {
        if (ligneReceptions == null) {
            ligneReceptions = new ArrayList();
        }
        return ligneReceptions;
    }

    public void setLigneReceptions(List<LigneReception> ligneReceptions) {
        this.ligneReceptions = ligneReceptions;
    }

    public List<LigneMagasin> getLigneMagasins() {
        if (ligneMagasins == null) {
            ligneMagasins = new ArrayList();
        }
        return ligneMagasins;
    }

    public void setLigneMagasins(List<LigneMagasin> ligneMagasins) {
        this.ligneMagasins = ligneMagasins;
    }

    public List<LigneLivraison> getLigneLivraisons() {
        if (ligneLivraisons == null) {
            ligneLivraisons = new ArrayList();
        }
        return ligneLivraisons;
    }

    public void setLigneLivraisons(List<LigneLivraison> ligneLivraisons) {
        this.ligneLivraisons = ligneLivraisons;
    }

    public List<LigneFacture> getLigneFactures() {
        if (ligneFactures == null) {
            ligneFactures = new ArrayList();
        }
        return ligneFactures;
    }

    public void setLigneFactures(List<LigneFacture> ligneFactures) {
        this.ligneFactures = ligneFactures;
    }

    public List<LigneExpressionBesoin> getLigneExpressionBesoins() {
        if (ligneExpressionBesoins == null) {
            ligneExpressionBesoins = new ArrayList();
        }
        return ligneExpressionBesoins;
    }

    public void setLigneExpressionBesoins(List<LigneExpressionBesoin> ligneExpressionBesoins) {
        this.ligneExpressionBesoins = ligneExpressionBesoins;
    }

    public List<LigneCommande> getLigneCommandes() {
        if (ligneCommandes == null) {
            ligneCommandes = new ArrayList();
        }
        return ligneCommandes;
    }

    public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return libelle;
    }

}
