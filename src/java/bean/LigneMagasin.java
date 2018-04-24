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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Entity
@Table(name="LigneMagasin")
public class LigneMagasin extends  Ligne implements Serializable {


    private double quantitaDef;
 
    @ManyToOne
    private Magasin magasin;





    public double getQuantitaDef() {
        return quantitaDef;
    }

    public void setQuantitaDef(double quantitaDef) {
        this.quantitaDef = quantitaDef;
    }



    public Magasin getMagasin() {
        if (magasin == null) {
            magasin = new Magasin();
        }
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    
    
    
    
    
    
    
    public LigneMagasin() {
    }

    public LigneMagasin(Long id) {
        super(id);
    }

    public LigneMagasin(double quantitaDef, Long id, double quantite) {
        super(id, quantite);
        this.quantitaDef = quantitaDef;
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
        if (!(object instanceof LigneMagasin)) {
            return false;
        }
        LigneMagasin other = (LigneMagasin) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return  "LE PRODUIT  "+produit.getId()+" DE Quantité "+quantite+" EST PRESQUE FINIT";
    }

}
