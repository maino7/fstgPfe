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
<<<<<<< OURS
 * @author Topo
 */
@Entity
public class PieceEtudiant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Condidature condidature;
    @ManyToOne
    private PiecesParNiveau piecesParNiveau;

    public PieceEtudiant() {
    }

    public Condidature getCondidature() {
        return condidature;
    }

    public void setCondidature(Condidature condidature) {
        this.condidature = condidature;
    }

    public PiecesParNiveau getPiecesParNiveau() {
        return piecesParNiveau;
    }

    public void setPiecesParNiveau(PiecesParNiveau piecesParNiveau) {
        this.piecesParNiveau = piecesParNiveau;
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
        if (!(object instanceof PieceEtudiant)) {
            return false;
        }
        PieceEtudiant other = (PieceEtudiant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.PiecesFournie[ id=" + id + " ]";

    }
    
}
