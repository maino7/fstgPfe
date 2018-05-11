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
public class Condidature implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Candidat candidat;
    @ManyToOne
    private AnneUniversitaire anneUniversitaire;
    private int typeInscription; // 1:TC,2:Master,3:Cycle
    private int condidatureValide; // 1,0
    private int pieceValide; // a discuter
    private boolean reussi;
    private float moyenneEcrit;
    private float moyenneOrale;
    private float moyenneGenerale;
    @OneToMany(mappedBy = "condidature")
    private List<PieceEtudiant> piecesFournies;
    @OneToMany(mappedBy = "condidature")
    private List<ExamCandidat> examCandidats;

    public AnneUniversitaire getAnneUniversitaire() {
        return anneUniversitaire;
    }

    public void setAnneUniversitaire(AnneUniversitaire anneUniversitaire) {
        this.anneUniversitaire = anneUniversitaire;
    }

    public List<PieceEtudiant> getPiecesFournies() {
        return piecesFournies;
    }

    public void setPiecesFournies(List<PieceEtudiant> piecesFournies) {
        this.piecesFournies = piecesFournies;
    }

    public List<ExamCandidat> getExamCandidats() {
        return examCandidats;
    }

    public void setExamCandidats(List<ExamCandidat> examCandidats) {
        this.examCandidats = examCandidats;
    }
    
    

    public Long getId() {
        return id;
    }

    public Condidature() {
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public int getTypeInscription() {
        return typeInscription;
    }

    public void setTypeInscription(int typeInscription) {
        this.typeInscription = typeInscription;
    }

    public int getCondidatureValide() {
        return condidatureValide;
    }

    public void setCondidatureValide(int condidatureValide) {
        this.condidatureValide = condidatureValide;
    }

    public int getPieceValide() {
        return pieceValide;
    }

    public void setPieceValide(int pieceValide) {
        this.pieceValide = pieceValide;
    }

    public boolean isReussi() {
        return reussi;
    }

    public void setReussi(boolean reussi) {
        this.reussi = reussi;
    }

    public float getMoyenneEcrit() {
        return moyenneEcrit;
    }

    public void setMoyenneEcrit(float moyenneEcrit) {
        this.moyenneEcrit = moyenneEcrit;
    }

    public float getMoyenneOrale() {
        return moyenneOrale;
    }

    public void setMoyenneOrale(float moyenneOrale) {
        this.moyenneOrale = moyenneOrale;
    }

    public float getMoyenneGenerale() {
        return moyenneGenerale;
    }

    public void setMoyenneGenerale(float moyenneGenerale) {
        this.moyenneGenerale = moyenneGenerale;
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
        if (!(object instanceof Condidature)) {
            return false;
        }
        Condidature other = (Condidature) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Inscription[ id=" + id + " ]";
    }
    
}
