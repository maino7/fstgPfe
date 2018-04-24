/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Ligne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
   
    protected double quantite;
    
    @ManyToOne
    protected Produit produit;
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        if (produit == null) {
            produit = new Produit();
        }
        return produit;
    }

    public void setProduit(Produit produit) {

        this.produit = produit;
    }
    
    
    

    public Ligne() {
    }

    public Ligne(Long id) {
        this.id = id;
    }

    public Ligne(Long id, double quantite) {
        this.id = id;
        this.quantite = quantite;
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
        if (!(object instanceof Ligne)) {
            return false;
        }
        Ligne other = (Ligne) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return  id +"";
    }
    
}
