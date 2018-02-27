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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Entity
public class EntiteAdministrative implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String libelle;
    @OneToOne
    private Magasin magasin;
    @OneToMany(mappedBy = "entiteAdministrative")
    private List<User> users;
    @OneToMany(mappedBy = "entiteAdministrative")
    private List<UserStock> userStocks;

    public EntiteAdministrative() {
    }

    public EntiteAdministrative(String id) {
        this.id = id;
    }

    public EntiteAdministrative(String id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public List<UserStock> getUserStocks() {
        return userStocks;
    }

    public void setUserStocks(List<UserStock> userStocks) {
        this.userStocks = userStocks;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    public List<User> getUsers() {
        if (users == null) {
            users = new ArrayList();
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
        if (!(object instanceof EntiteAdministrative)) {
            return false;
        }
        EntiteAdministrative other = (EntiteAdministrative) object;
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
