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
 * @author Topo
 */
@Entity
public class ConcourNiveau implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Niveau niveau;
    private int nbrDePladeAdmis;
    private String annee;
    private String Description;
    private int nbrDePlaceEcrit;
    private int nbrDePlaceOrale;
    @OneToMany(mappedBy = "concourNiveau")
    private List<ConcourExamMatiere> concourExamMatieres;

    public List<ConcourExamMatiere> getConcourExamMatieres() {
        return concourExamMatieres;
    }

    public void setConcourExamMatieres(List<ConcourExamMatiere> concourExamMatieres) {
        this.concourExamMatieres = concourExamMatieres;
    }
    

    public ConcourNiveau() {
    }

    public ConcourNiveau(Long id) {
        this.id = id;
    }
    

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public int getNbrDePladeAdmis() {
        return nbrDePladeAdmis;
    }

    public void setNbrDePladeAdmis(int nbrDePladeAdmis) {
        this.nbrDePladeAdmis = nbrDePladeAdmis;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getNbrDePlaceEcrit() {
        return nbrDePlaceEcrit;
    }

    public void setNbrDePlaceEcrit(int nbrDePlaceEcrit) {
        this.nbrDePlaceEcrit = nbrDePlaceEcrit;
    }

    public int getNbrDePlaceOrale() {
        return nbrDePlaceOrale;
    }

    public void setNbrDePlaceOrale(int nbrDePlaceOrale) {
        this.nbrDePlaceOrale = nbrDePlaceOrale;
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
        if (!(object instanceof ConcourNiveau)) {
            return false;
        }
        ConcourNiveau other = (ConcourNiveau) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return niveau+"";
    }
    
}
