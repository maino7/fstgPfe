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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String password;
    private String nom;
    private String prenom;
    private String telephone;
    @OneToMany(mappedBy = "user")
    private List<Reception> receptions;
    @OneToMany(mappedBy = "user")
    private List<Livraison> livraisons;
    @OneToMany(mappedBy = "user")
    private List<ExpressionBesoin> expressionBesoins;
    @OneToMany(mappedBy = "user")
    private List<Commande> commandes;
    @ManyToOne
    private EntiteAdministrative entiteAdministrative;

    public User(String id, String password, String nom, String telephone) {
        this.id = id;
        this.password = password;
        this.nom = nom;
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Reception> getReceptions() {
        if (receptions == null) {
            receptions = new ArrayList();
        }
        return receptions;
    }

    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
    }

    public List<Livraison> getLivraisons() {
        if (livraisons == null) {
            livraisons = new ArrayList();
        }
        return livraisons;
    }

    public void setLivraisons(List<Livraison> livraisons) {
        this.livraisons = livraisons;
    }

    public List<ExpressionBesoin> getExpressionBesoins() {
        if (expressionBesoins == null) {
            expressionBesoins = new ArrayList();
        }
        return expressionBesoins;
    }

    public void setExpressionBesoins(List<ExpressionBesoin> expressionBesoins) {
        this.expressionBesoins = expressionBesoins;
    }

    public List<Commande> getCommandes() {
        if (commandes == null) {
            commandes = new ArrayList();
        }
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public EntiteAdministrative getEntiteAdministrative() {
        if (entiteAdministrative == null) {
            entiteAdministrative = new EntiteAdministrative();
        }
        return entiteAdministrative;
    }

    public void setEntiteAdministrative(EntiteAdministrative entiteAdministrative) {
        this.entiteAdministrative = entiteAdministrative;
    }

    public User() {
    }

    public User(String id) {
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

}
