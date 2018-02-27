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

/**
 *
 * @author xXx-BlackAngel-xXx
 */
@Entity
public class DemandeDerogation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Etudiant etudiant;
    private String description;
    private int anne_baccalaureat;
    private int anne_etude;//  1 premier année 2-3-4
    private int nombreSemestresValide;
    private int nombreMdulesValide;
    private int etat;
    
    @ManyToOne
    private Filiere filiere;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAnne_baccalaureat() {
        return anne_baccalaureat;
    }

    public void setAnne_baccalaureat(int anne_baccalaureat) {
        this.anne_baccalaureat = anne_baccalaureat;
    }

    public int getAnne_etude() {
        return anne_etude;
    }

    public void setAnne_etude(int anne_etude) {
        this.anne_etude = anne_etude;
    }

    public int getNombreSemestresValide() {
        return nombreSemestresValide;
    }

    public void setNombreSemestresValide(int nombreSemestresValide) {
        this.nombreSemestresValide = nombreSemestresValide;
    }

    public int getNombreMdulesValide() {
        return nombreMdulesValide;
    }

    public void setNombreMdulesValide(int nombreMdulesValide) {
        this.nombreMdulesValide = nombreMdulesValide;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DemandeDerogation)) {
            return false;
        }
        DemandeDerogation other = (DemandeDerogation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Demande_Derogation[ id=" + id + " ]";
    }
    
}
