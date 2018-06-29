/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author HP
 */
@Entity
public class AnneUniversitaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String anneeUni;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Annee anneeMin;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Annee anneeMax;
     @OneToMany(mappedBy = "anneUniversitaire")
    private List<Condidature> condidatures;

    
    
    public AnneUniversitaire() {
    }

    public Long getId() {
        return id;
    }

    public Annee getAnneeMin() {
        return anneeMin;
    }

    public void setAnneeMin(Annee anneeMin) {
        this.anneeMin = anneeMin;
    }

    public String getAnneeUni() {
        return anneeUni;
    }

    public void setAnneeUni(String anneeUni) {
        this.anneeUni = anneeUni;
    }
    

    public Annee getAnneeMax() {
        return anneeMax;
    }

    public void setAnneeMax(Annee anneeMax) {
        this.anneeMax = anneeMax;
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
        if (!(object instanceof AnneUniversitaire)) {
            return false;
        }
        AnneUniversitaire other = (AnneUniversitaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnneeUniversitaire "+anneeMin+"/"+anneeMax;
    }
    
}
