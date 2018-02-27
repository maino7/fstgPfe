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
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private double prixTotale;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFacture;
    @ManyToOne
    private Commande commande;
    @ManyToOne
    private Fournisseur fournisseur;
    @OneToMany(mappedBy = "facture")
    private List<LigneFacture> ligneFactures;

    public Facture() {
    }

    public Facture(String id, double prixTotale) {
        this.id = id;
        this.prixTotale = prixTotale;
    }

    public Facture(String id) {
        this.id = id;
    }

    public double getPrixTotale() {
        return prixTotale;
    }

    public void setPrixTotale(double prixTotale) {
        this.prixTotale = prixTotale;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
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

    public Fournisseur getFournisseur() {
        if (fournisseur == null) {
            fournisseur = new Fournisseur();
        }
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
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
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
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
