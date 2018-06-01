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
import javax.persistence.OneToOne;

/**
 *
 * @author Topo
 */
@Entity
public class CoeffCalibrage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float coeff;
    private float noteMinimal;
    private int nbrMin;
    private int nbrMax;
    private String annee;
    
    @OneToOne
    private EtablissementType etablissement;

    public CoeffCalibrage() {
    }

    public float getCoeff() {
        return coeff;
    }

    public void setCoeff(float coeff) {
        this.coeff = coeff;
    }

    public float getNoteMinimal() {
        return noteMinimal;
    }

    public void setNoteMinimal(float noteMinimal) {
        this.noteMinimal = noteMinimal;
    }

    public int getNbrMin() {
        return nbrMin;
    }

    public void setNbrMin(int nbrMin) {
        this.nbrMin = nbrMin;
    }

    public int getNbrMax() {
        return nbrMax;
    }

    public void setNbrMax(int nbrMax) {
        this.nbrMax = nbrMax;
    }

    public EtablissementType getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(EtablissementType etablissement) {
        this.etablissement = etablissement;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
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
        if (!(object instanceof CoeffCalibrage)) {
            return false;
        }
        CoeffCalibrage other = (CoeffCalibrage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.CoeffCalibrage[ id=" + id + " ]";
    }
    
}
