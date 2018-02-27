/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author hp
 */
@Entity
public class Enseignant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String cin;
    private String nom;
    private String prenom;
    @Email
    private String email;
    private String password;
    private String telephone;
    private String gender;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    private boolean blocked;
    private int nbrCnx;
    private boolean mdpChanged;
    private boolean admine;
    private String grade;
    @ManyToOne
    private Departement departement;
    @ManyToOne
    private Filiere filiere;
    @OneToMany(mappedBy = "chefEquipe")
    private List<EquipeRecherche> equipeRecherches;
    @OneToMany(mappedBy = "enseignant")
    private List<Secure> secures;
    //private int tentativeRest;

    @OneToMany(mappedBy = "enseignant")
    private List<Module> modules;

    @OneToMany(mappedBy = "user")
    private List<Device> devices;

    public Filiere getFiliere() {
        return filiere;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getNbrCnx() {
        return nbrCnx;
    }

    public void setNbrCnx(int nbrCnx) {
        this.nbrCnx = nbrCnx;
    }

    public boolean isMdpChanged() {
        return mdpChanged;
    }

    public void setMdpChanged(boolean mdpChanged) {
        this.mdpChanged = mdpChanged;
    }

    public boolean isAdmine() {
        return admine;
    }

    public void setAdmine(boolean admine) {
        this.admine = admine;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public List<EquipeRecherche> getEquipeRecherches() {
        return equipeRecherches;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setEquipeRecherches(List<EquipeRecherche> equipeRecherches) {
        this.equipeRecherches = equipeRecherches;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public List<Secure> getSecures() {
        return secures;
    }

    public void setSecures(List<Secure> secures) {
        this.secures = secures;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cin != null ? cin.hashCode() : 0);
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
        final Enseignant other = (Enseignant) obj;
        if (!Objects.equals(this.cin, other.cin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

}
