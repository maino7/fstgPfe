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
    private double seuilAlert;
    @ManyToOne
    private Categorie categorie;
    @ManyToOne
    private Marque marque;
    @OneToMany(mappedBy = "produit")
    private List<Ligne> lignes;


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

    public double getSeuilAlert() {
        return seuilAlert;
    }

    public void setSeuilAlert(double seuilAlert) {
        this.seuilAlert = seuilAlert;
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

    public List<Ligne> getLignes() {
        if (lignes == null) {
            lignes = new ArrayList();
        }
        return lignes;
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
