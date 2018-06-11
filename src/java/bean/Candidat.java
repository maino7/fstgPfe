/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Topo
 */
@Entity
public class Candidat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String cne;
    private String cin;
    private String nomLat;
    private String prenomLat;
    private boolean sexe;
    private boolean handicap;
    @Email
    private String email;
    private String telephone;
    private String nomAr;
    private String prenomAr;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    private String lieuNaissance;
    private String anneeBac;
    private boolean typeInscription;//true cycle false master
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateInscription;
    private String photo;
    private String ip;
    private String adresse;
    private String lieuAr;
    private boolean etat;
    private boolean fonctionnaire;
    private String lastPdf;
    private boolean export;
    private int anneeInscriptionEnsSup;
    private int anneeInscriptionUniv;
    private int anneeInscriptionEtab;
    private float noteS1;
    private float noteS2;
    private float noteS3;
    private float noteS4;
    private float noteS5;
    private float noteS6;
    private String specialite;
    private String mentionBac;
    private String mentionDiplome;
    private String optionLicence;
    private String anneeObtLicence;
    private String modeDeValidation;
    private String anneeDeValidation;
    private int nombreDinscription;
    private int valideApresRattrapage;
    private float moyCalibr;

    private int etablissementPreInsc = 15;
    private Long secret; // hada wa7d lcode tayt3ta l les etudiant 3la wd les document dialhom -ta ana mafhmtch- 
    @ManyToOne
    private Profession professionDeLaMere;

//    private int etablissementPreInsc=15;
    // private Long secret; // hada wa7d lcode tayt3ta l les etudiant 3la wd les document dialhom -ta ana mafhmtch-// hada wa7d lcode tayt3ta l les etudiant 3la wd les document dialhom -ta ana mafhmtch-
    @ManyToOne
    private OptionBac optionBac;
    @ManyToOne
    private DernierDiplome dernierDiplome;
    @OneToOne(mappedBy = "candidat")
    private Condidature condidature;
    @ManyToOne
    private Lycee lycee;

    @ManyToOne
    private EtablissementType etablissement;
    @OneToOne(mappedBy = "candidat")
    private Etudiant etudiant;
    @ManyToOne
    private Pays pays;

    @ManyToOne
    private Academie academie;
    @ManyToOne
    private Licence licence;
    @OneToMany(mappedBy = "candidat")
    private List<Semestre> semestres;

    public Candidat() {
    }

    public List<Semestre> getSemestres() {
        return semestres;
    }

    public void setSemestres(List<Semestre> semestres) {
        this.semestres = semestres;
    }

    public Academie getAcademie() {
        if (academie == null) {
            academie = new Academie();
        }
        return academie;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getOptionLicence() {
        return optionLicence;
    }

    public void setOptionLicence(String optionLicence) {
        this.optionLicence = optionLicence;
    }

    public String getMentionBac() {
        return mentionBac;
    }

    public String getAnneeObtLicence() {
        return anneeObtLicence;
    }

    public Licence getLicence() {
        return licence;
    }

    public void setLicence(Licence licence) {
        this.licence = licence;
    }

    public void setAnneeObtLicence(String anneeObtLicence) {
        this.anneeObtLicence = anneeObtLicence;
    }

    public void setMentionBac(String mentionBac) {
        this.mentionBac = mentionBac;
    }

    public String getMentionDiplome() {
        return mentionDiplome;
    }

    public void setMentionDiplome(String mentionDiplome) {
        this.mentionDiplome = mentionDiplome;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setAcademie(Academie academie) {
        this.academie = academie;
    }

    public boolean isSexe() {
        return sexe;
    }

    public void setNoteS1(float noteS1) {
        this.noteS1 = noteS1;
    }

    public void setNoteS2(float noteS2) {
        this.noteS2 = noteS2;
    }

    public void setNoteS3(float noteS3) {
        this.noteS3 = noteS3;
    }

    public void setNoteS4(float noteS4) {
        this.noteS4 = noteS4;
    }

    public float getNoteS5() {
        return noteS5;
    }

    public void setNoteS5(float noteS5) {
        this.noteS5 = noteS5;
    }

    public float getNoteS6() {
        return noteS6;
    }

    public void setNoteS6(float noteS6) {
        this.noteS6 = noteS6;
    }

    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Profession getProfessionDeLaMere() {
        return professionDeLaMere;
    }

    public void setProfessionDeLaMere(Profession professionDeLaMere) {
        this.professionDeLaMere = professionDeLaMere;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Condidature getCondidature() {
        return condidature;
    }

    public Lycee getLycee() {
        if (lycee == null) {
            lycee = new Lycee();
        }
        return lycee;
    }

    public void setLycee(Lycee lycee) {
        this.lycee = lycee;
    }

    public void setCondidature(Condidature condidature) {
        this.condidature = condidature;
    }

    public EtablissementType getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(EtablissementType etablissement) {
        this.etablissement = etablissement;
    }

    public String getNomLat() {
        return nomLat;
    }

    public void setNomLat(String nomLat) {
        this.nomLat = nomLat;
    }

    public String getPrenomLat() {
        return prenomLat;
    }

    public void setPrenomLat(String prenomLat) {
        this.prenomLat = prenomLat;
    }

    public boolean isHandicap() {
        return handicap;
    }

    public void setHandicap(boolean handicap) {
        this.handicap = handicap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNomAr() {
        return nomAr;
    }

    public void setNomAr(String nomAr) {
        this.nomAr = nomAr;
    }

    public String getPrenomAr() {
        return prenomAr;
    }

    public void setPrenomAr(String prenomAr) {
        this.prenomAr = prenomAr;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getAnneeBac() {
        return anneeBac;
    }

    public void setAnneeBac(String anneeBac) {
        this.anneeBac = anneeBac;
    }

    public boolean isTypeInscription() {
        return typeInscription;
    }

    public void setTypeInscription(boolean typeInscription) {
        this.typeInscription = typeInscription;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLieuAr() {
        return lieuAr;
    }

    public void setLieuAr(String lieuAr) {
        this.lieuAr = lieuAr;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public boolean isFonctionnaire() {
        return fonctionnaire;
    }

    public void setFonctionnaire(boolean fonctionnaire) {
        this.fonctionnaire = fonctionnaire;
    }

    public String getLastPdf() {
        return lastPdf;
    }

    public void setLastPdf(String lastPdf) {
        this.lastPdf = lastPdf;
    }

    public boolean isExport() {
        return export;
    }

    public void setExport(boolean export) {
        this.export = export;
    }

    public int getAnneeInscriptionEnsSup() {
        return anneeInscriptionEnsSup;
    }

    public void setAnneeInscriptionEnsSup(int anneeInscriptionEnsSup) {
        this.anneeInscriptionEnsSup = anneeInscriptionEnsSup;
    }

    public int getAnneeInscriptionUniv() {
        return anneeInscriptionUniv;
    }

    public void setAnneeInscriptionUniv(int anneeInscriptionUniv) {
        this.anneeInscriptionUniv = anneeInscriptionUniv;
    }

    public int getAnneeInscriptionEtab() {
        return anneeInscriptionEtab;
    }

    public void setAnneeInscriptionEtab(int anneeInscriptionEtab) {
        this.anneeInscriptionEtab = anneeInscriptionEtab;
    }

    public int getEtablissementPreInsc() {
        return etablissementPreInsc;
    }

    public void setEtablissementPreInsc(int etablissementPreInsc) {
        this.etablissementPreInsc = etablissementPreInsc;
    }

    public Long getSecret() {
        return secret;
    }

    public void setSecret(Long secret) {
        this.secret = secret;
    }

    public OptionBac getOptionBac() {
        return optionBac;
    }

    public void setOptionBac(OptionBac optionBac) {
        this.optionBac = optionBac;
    }

    public DernierDiplome getDernierDiplome() {
        return dernierDiplome;
    }

    public void setDernierDiplome(DernierDiplome dernierDiplome) {
        this.dernierDiplome = dernierDiplome;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public float getNoteS1() {
        return noteS1;
    }

    public void setNoteS1(int noteS1) {
        this.noteS1 = noteS1;
    }

    public float getNoteS2() {
        return noteS2;
    }

    public void setNoteS2(int noteS2) {
        this.noteS2 = noteS2;
    }

    public float getNoteS3() {
        return noteS3;
    }

    public void setNoteS3(int noteS3) {
        this.noteS3 = noteS3;
    }

    public float getNoteS4() {
        return noteS4;
    }

    public void setNoteS4(int noteS4) {
        this.noteS4 = noteS4;
    }

    public String getModeDeValidation() {
        return modeDeValidation;
    }

    public void setModeDeValidation(String modeDeValidation) {
        this.modeDeValidation = modeDeValidation;
    }

    public String getAnneeDeValidation() {
        return anneeDeValidation;
    }

    public void setAnneeDeValidation(String anneeDeValidation) {
        this.anneeDeValidation = anneeDeValidation;
    }

    public int getNombreDinscription() {
        return nombreDinscription;
    }

    public void setNombreDinscription(int nombreDinscription) {
        this.nombreDinscription = nombreDinscription;
    }

    public int getValideApresRattrapage() {
        return valideApresRattrapage;
    }

    public void setValideApresRattrapage(int valideApresRattrapage) {
        this.valideApresRattrapage = valideApresRattrapage;
    }

    public float getMoyCalibr() {
        return moyCalibr;
    }

    public void setMoyCalibr(float moyCalibr) {
        this.moyCalibr = moyCalibr;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.cne);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Candidat other = (Candidat) obj;
        if (!Objects.equals(this.cne, other.cne)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomLat;
    }

}
