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
 * @author Abed
 */
@Entity
public class NoteSemestre implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double note;
    private String mention;
    private Integer nbrModuleValide;
    private Integer etat; // added 12/05/2017
    private Double total; // added by abed 23/05/2017
    private Integer etatReleve=0; // 0 never took  releve else  1

    @ManyToOne
    private Etudiant etudiant;
    @ManyToOne
    private Semestre semestre;

    public Long getId() {
        return id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEtatReleve() {
        return etatReleve;
    }

    public void setEtatReleve(Integer etatReleve) {
        this.etatReleve = etatReleve;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }
    
    public Integer getNbrModuleValide() {
        return nbrModuleValide;
    }

    public void setNbrModuleValide(Integer nbrModuleValide) {
        this.nbrModuleValide = nbrModuleValide;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
        if (!(object instanceof NoteSemestre)) {
            return false;
        }
        NoteSemestre other = (NoteSemestre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.NoteSemestre[ id=" + id + " ]";
    }

}
