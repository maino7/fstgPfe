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
import javax.persistence.OneToOne;

/**
 *
 * @author Topo
 */
@Entity
public class ExamCandidat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float noteCalc;
    @ManyToOne
    private Condidature condidature;
   
    @ManyToOne
    private MatiereConcour matiereConcour;

    public ExamCandidat() {
    }

    public float getNoteCalc() {
        return noteCalc;
    }

    public void setNoteCalc(float noteCalc) {
        this.noteCalc = noteCalc;
    }

    public Condidature getCondidature() {
        return condidature;
    }

    public void setCondidature(Condidature condidature) {
        this.condidature = condidature;
    }

    public MatiereConcour getMatiereConcour() {
        return matiereConcour;
    }

    public void setMatiereConcour(MatiereConcour matiereConcour) {
        this.matiereConcour = matiereConcour;
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
        if (!(object instanceof ExamCandidat)) {
            return false;
        }
        ExamCandidat other = (ExamCandidat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return matiereConcour.getTitre()+" : "+noteCalc;
    }
    
}
