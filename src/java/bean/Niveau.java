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
public class Niveau implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Filiere filiere;
    
    @ManyToOne
    private Semestre semestre;
   
    @OneToMany(mappedBy = "niveau")
    private List<PiecesParNiveau> piecesParSecs;
    @OneToOne(mappedBy = "niveau")
    private ConcourNiveau concourNiveau;
    @OneToMany(mappedBy = "niveau")
    private List<Etudiant> etudiants;
    

    public ConcourNiveau getConcourNiveau() {
        return concourNiveau;
    }

    public void setConcourNiveau(ConcourNiveau concourNiveau) {
        this.concourNiveau = concourNiveau;
    }
    

       public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }
    

    

  
    public List<PiecesParNiveau> getPiecesParSecs() {
        return piecesParSecs;
    }

    public void setPiecesParSecs(List<PiecesParNiveau> piecesParSecs) {
        this.piecesParSecs = piecesParSecs;
    }
    

    public Long getId() {
        return id;
    }

    public Niveau() {
    }

  

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
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
        if (!(object instanceof Niveau)) {
            return false;
        }
        Niveau other = (Niveau) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return filiere+" "+semestre;
    }
    
}
