/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Departement other = (Departement) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Departement{" + "id=" + id + ", intitule=" + intitule + ", img=" + img + ", description=" + description + ", filieres=" + filieres + ", enseignants=" + enseignants + ", chefDepartement=" + chefDepartement + '}';
    }

  
}
