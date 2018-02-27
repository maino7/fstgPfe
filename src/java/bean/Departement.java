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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author hp
 */
@Entity
public class Departement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String intitule;
    private String img;
    private String description;
    
    @OneToMany(mappedBy = "departement")
    private List<Filiere> filieres;
    @OneToMany(mappedBy = "departement")
    private List<Enseignant> enseignants;
    @OneToOne
    private Enseignant chefDepartement ;
    public List<Filiere> getFilieres() {
        return filieres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Enseignant getChefDepartement() {
        if(chefDepartement==null){
            chefDepartement=new  Enseignant();
        }
        return chefDepartement;
    }

    public void setChefDepartement(Enseignant chefDepartement) {
        this.chefDepartement = chefDepartement;
    }
    
    public void setFilieres(List<Filiere> filieres) {
        this.filieres = filieres;
    }

    public List<Enseignant> getEnseignants() {
       
        return enseignants;
    }

    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

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

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departement)) {
            return false;
        }
        Departement other = (Departement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return intitule;
    }

}
