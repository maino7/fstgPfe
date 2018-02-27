/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author HP
 */
@Entity
public class EquipeRecherche implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @ManyToOne
    private Enseignant chefEquipe;
    @OneToMany(mappedBy = "equipeRecherche")
    private List<Etudiant> membres;
    @ManyToOne
    private Laboratoire laboratoire;
    @OneToMany(mappedBy = "equipeRecherche")
    private List<ThemeRecherche> themes;

    public EquipeRecherche() {
    }

    
    public Long getId() {
        return id;
    }

    public List<ThemeRecherche> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeRecherche> themes) {
        this.themes = themes;
    }

    public Laboratoire getLaboratoire() {
        return laboratoire;
    }

    public void setLaboratoire(Laboratoire laboratoire) {
        this.laboratoire = laboratoire;
    }

    public List<Etudiant> getEtudiants() {
        return membres;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.membres = etudiants;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   

    public Enseignant getChefEquipe() {
        return chefEquipe;
    }

    public void setChefEquipe(Enseignant chefEquipe) {
        this.chefEquipe = chefEquipe;
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
        if (!(object instanceof EquipeRecherche)) {
            return false;
        }
        EquipeRecherche other = (EquipeRecherche) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }

}
