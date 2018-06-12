/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Topo
 */
@Entity
public class ConcourExamMatiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float coeff;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateExam;
    @ManyToOne
    private MatiereConcour matiereConcour;
    @ManyToOne
    private ConcourNiveau concourNiveau;

    public ConcourExamMatiere() {
    }

    public float getCoeff() {
        return coeff;
    }

    public void setCoeff(float coeff) {
        this.coeff = coeff;
    }

    public Date getDateExam() {
        return dateExam;
    }

    public void setDateExam(Date dateExam) {
        this.dateExam = dateExam;
    }

    public MatiereConcour getMatiereConcour() {
        return matiereConcour;
    }

    public void setMatiereConcour(MatiereConcour matiereConcour) {
        this.matiereConcour = matiereConcour;
    }

    public ConcourNiveau getConcourNiveau() {
        return concourNiveau;
    }

    public void setConcourNiveau(ConcourNiveau concourNiveau) {
        this.concourNiveau = concourNiveau;
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
        if (!(object instanceof ConcourExamMatiere)) {
            return false;
        }
        ConcourExamMatiere other = (ConcourExamMatiere) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.ConcourExamMatiere[ matiere=" + matiereConcour + " ]";
    }
    
}
