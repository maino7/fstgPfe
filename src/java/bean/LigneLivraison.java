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
@Table(name="LigneLivraison")
public class LigneLivraison extends Ligne implements Serializable {


    @ManyToOne
    private Livraison livraison;

    

   

    public Livraison getLivraison() {
        if (livraison == null) {
            livraison = new Livraison();
        }
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }
    
    

    public LigneLivraison() {
    }

    public LigneLivraison(Long id) {
        super(id);
    }

    public LigneLivraison(Long id, double quantite) {
        super(id, quantite);
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
        if (!(object instanceof LigneLivraison)) {
            return false;
        }
        LigneLivraison other = (LigneLivraison) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return id + "";
    }

}
