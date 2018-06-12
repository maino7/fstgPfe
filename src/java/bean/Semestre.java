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
 * @author hp
 */
@Entity
public class Semestre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int libelle;
    private float noteS1;
    private float noteS2;
    private float noteS3;
    private float noteS4;
    private float noteS5;
    private float noteS6;
    private String modeDeValidation;
    private String anneeDeValidation;
    private int nombreDinscription;
    private int valideApresRattrapage;

    @ManyToOne
    private Filiere filiere;

    @OneToMany(mappedBy = "semestre")
    private List<Module> modules;
    @ManyToOne
    private Annee annee;
    @ManyToOne
    Candidat candidat;

    public Semestre() {
    }
                               
    public Semestre(int numero) {
        this.libelle = numero;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public int getLibelle() {
        return libelle;
    }

    public void setLibelle(int libelle) {
        this.libelle = libelle;
    }

    public float getNoteS1() {
        return noteS1;
    }

    public void setNoteS1(float noteS1) {
        this.noteS1 = noteS1;
    }

    public float getNoteS2() {
        return noteS2;
    }

    public void setNoteS2(float noteS2) {
        this.noteS2 = noteS2;
    }

    public float getNoteS3() {
        return noteS3;
    }

    public void setNoteS3(float noteS3) {
        this.noteS3 = noteS3;
    }

    public float getNoteS4() {
        return noteS4;
    }

    public void setNoteS4(float noteS4) {
        this.noteS4 = noteS4;
    }

    public float getNoteS5() {
        return noteS5;
    }

    public void setNoteS5(float noteS5) {
        this.noteS5 = noteS5;
    }

    public float getNoteS6() {
        return noteS6;
    }

    public void setNoteS6(float noteS6) {
        this.noteS6 = noteS6;
    }

    public String getModeDeValidation() {
        return modeDeValidation;
    }

    public void setModeDeValidation(String modeDeValidation) {
        this.modeDeValidation = modeDeValidation;
    }

    public String getAnneeDeValidation() {
        return anneeDeValidation;
    }

    public void setAnneeDeValidation(String anneeDeValidation) {
        this.anneeDeValidation = anneeDeValidation;
    }

    public int getNombreDinscription() {
        return nombreDinscription;
    }

    public void setNombreDinscription(int nombreDinscription) {
        this.nombreDinscription = nombreDinscription;
    }

    public int getValideApresRattrapage() {
        return valideApresRattrapage;
    }

    public void setValideApresRattrapage(int valideApresRattrapage) {
        this.valideApresRattrapage = valideApresRattrapage;
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
        if (!(object instanceof Semestre)) {
            return false;
        }
        Semestre other = (Semestre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + libelle;
    }

}
