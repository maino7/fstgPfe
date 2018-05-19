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
import javax.persistence.OneToOne;

/**
 *
 * @author hp
 */
@Entity
public class Filiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String libelle;
    private String abreviation;
    private String objectif;
    @OneToMany(mappedBy = "filiere")
    private List<Enseignant> enseignants;
    private int typeFiliere ;// laisser vide
    private int typeFormation ; // 1=>Initial , 2==> continue
    @ManyToOne
    private Section section;
    @ManyToOne
    private Departement departement;
    @OneToOne
    private Enseignant responsableFiliere;
    @OneToMany(mappedBy = "filiere")
    private List<Module> modules;
    @OneToMany(mappedBy = "filiere")
    private List<Semestre> semestres;

    @OneToMany(mappedBy = "filiere")
    private List<Etudiant> etudiants;
    
    
    public Filiere() {
    }
   
    public List<Semestre> getSemestres() {
        return semestres;
    }

    public void setSemestres(List<Semestre> semestres) {
        this.semestres = semestres;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
    

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    
    public int getTypeFormation() {
        return typeFormation;
    }

    public void setTypeFormation(int typeFormation) {
        this.typeFormation = typeFormation;
    }

    public List<Module> getModules() {
        return modules;
    }
    

    public String getAbreviation() {
        return abreviation;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public int getTypeFiliere() {
        return typeFiliere;
    }

    public void setTypeFiliere(int typeFiliere) {
        this.typeFiliere = typeFiliere;
    }

    public Enseignant getResponsableFiliere() {
        return responsableFiliere;
    }

    public void setResponsableFiliere(Enseignant responsableFiliere) {
        this.responsableFiliere = responsableFiliere;
    }

    public List<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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
        if (!(object instanceof Filiere)) {
            return false;
        }
        Filiere other = (Filiere) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return abreviation;
    }

}
