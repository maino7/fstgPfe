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
public class NoteModulaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double note;
    private Double noteBeforeJury;
    private String mention;
    private String mentionBeforeJury;
    private Integer etat; // added 12/05/2017
    private Double ptJury;

    @ManyToOne
    private Etudiant etudiant;
    @ManyToOne
    private Module module;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
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

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Double getPtJury() {
        return ptJury;
    }

    public void setPtJury(Double ptJury) {
        this.ptJury = ptJury;
    }
    
    public void setMention(String mention) {
        this.mention = mention;
    }

    public Double getNoteBeforeJury() {
        return noteBeforeJury;
    }

    public void setNoteBeforeJury(Double noteBeforeJury) {
        this.noteBeforeJury = noteBeforeJury;
    }

    public String getMentionBeforeJury() {
        return mentionBeforeJury;
    }

    public void setMentionBeforeJury(String mentionBeforeJury) {
        this.mentionBeforeJury = mentionBeforeJury;
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
        if (!(object instanceof NoteModulaire)) {
            return false;
        }
        NoteModulaire other = (NoteModulaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.NoteModulaire[ id=" + id + " ]";
    }

}
