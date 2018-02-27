/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Entity
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCommande;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "commande")
    private List<Livraison> livraisons;
    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommandes;
    @OneToMany(mappedBy = "commande")
    private List<Facture> factures;
    @ManyToOne
    private UserStock userStock;

    public Commande() {
    }

    public Commande(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserStock getUserStock() {
        return userStock;
    }

    public void setUserStock(UserStock userStock) {
        this.userStock = userStock;
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

    public List<LigneCommande> getLigneCommandes() {
        if (ligneCommandes == null) {
            ligneCommandes = new ArrayList();
        }
        return ligneCommandes;
    }

    public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public List<Facture> getFactures() {
        if (factures == null) {
            factures = new ArrayList();
        }
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id;
    }

}
