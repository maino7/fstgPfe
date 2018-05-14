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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Topo
 */
@Entity
public class MatiereConcour implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    private String numApg; // pr requete de recherche facile
    
    @OneToMany(mappedBy = "matiereConcour")
    private List<ConcourExamMatiere> concourExamMatieres;
    @OneToMany(mappedBy = "matiereConcour")
    private List<ExamCandidat> examCandidats;
    

    public List<ConcourExamMatiere> getConcourExamMatieres() {
        return concourExamMatieres;
    }

    public void setConcourExamMatieres(List<ConcourExamMatiere> concourExamMatieres) {
        this.concourExamMatieres = concourExamMatieres;
    }

    public List<ExamCandidat> getExamCandidats() {
        return examCandidats;
    }

    public void setExamCandidats(List<ExamCandidat> examCandidats) {
        this.examCandidats = examCandidats;
    }

    
    

    public MatiereConcour() {
    }

    public MatiereConcour(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNumApg() {
        return numApg;
    }

    public void setNumApg(String numApg) {
        this.numApg = numApg;
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
        if (!(object instanceof MatiereConcour)) {
            return false;
        }
        MatiereConcour other = (MatiereConcour) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.MatiereConcour[ id=" + id + " ]";
    }
    
}
