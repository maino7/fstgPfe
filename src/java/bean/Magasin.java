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
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Entity
public class Magasin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @ManyToOne
    private Stock stock;
    @OneToMany(mappedBy = "magasin")
    private List<LigneMagasin> ligneMagasins;



    public Stock getStock() {
        if (stock == null) {
            stock = new Stock();
        }
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
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

    public Magasin() {
    }

    public Magasin(String id) {
        this.id = id;
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
        if (!(object instanceof Magasin)) {
            return false;
        }
        Magasin other = (Magasin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + "";
    }

}
