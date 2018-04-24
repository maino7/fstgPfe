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
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Entity
public class Livraison implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLivraison;
    @ManyToOne
    private Commande commande;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "livraison")
    private List<LigneLivraison> ligneLivraisons;
    @ManyToOne
    private UserStock userStock;

    public Livraison() {
    }

    public Livraison(String id) {
        this.id = id;
    }

    public UserStock getUserStock() {
        return userStock;
    }

    public void setUserStock(UserStock userStock) {
        this.userStock = userStock;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public Commande getCommande() {
        if (commande == null) {
            commande = new Commande();
        }
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Livraison other = (Livraison) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }





    @Override
    public String toString() {
        return id ;
    }

}
