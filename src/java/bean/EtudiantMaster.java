/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Abed
 */
@Entity
public class EtudiantMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long cne;
    private String cin;
    private String nomLat;
    private String prenomLat;
    private int sexe;//1 male / 2 female
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
    private int etablissementPreInsc=15;
    private Long secret; // hada wa7d lcode tayt3ta l les etudiant 3la wd les document dialhom -ta ana mafhmtch-
    @ManyToOne
    private FiliereMaster filiereMaster;
    @ManyToOne
    private Profession professionPere;
    @ManyToOne
    private Profession professionMere;
    @ManyToOne
    private DernierDiplome TypeDernierDiplome;
    @ManyToOne
    private Pays pays;
    @ManyToOne
    private MentionDiplome mentionBac;
    @ManyToOne
    private OptionBac optionBac;
    @ManyToOne
    private Lycee lycee;
    @ManyToOne
    private Lieu province;
    @ManyToOne
    private Academie academie;
    
    public Long getId() {
        return cne;
    }

    public void setId(Long cne) {
        this.cne = cne;
    }

    public Long getCne() {
        return cne;
    }

    public void setCne(Long cne) {
        this.cne = cne;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
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

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
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

    public FiliereMaster getFiliereMaster() {
        return filiereMaster;
    }

    public void setFiliereMaster(FiliereMaster filiereMaster) {
        this.filiereMaster = filiereMaster;
    }

    public Profession getProfessionPere() {
        return professionPere;
    }

    public void setProfessionPere(Profession professionPere) {
        this.professionPere = professionPere;
    }

    public Profession getProfessionMere() {
        return professionMere;
    }

    public void setProfessionMere(Profession professionMere) {
        this.professionMere = professionMere;
    }

    public DernierDiplome getTypeDernierDiplome() {
        return TypeDernierDiplome;
    }

    public void setTypeDernierDiplome(DernierDiplome TypeDernierDiplome) {
        this.TypeDernierDiplome = TypeDernierDiplome;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public MentionDiplome getMentionBac() {
        return mentionBac;
    }

    public void setMentionBac(MentionDiplome mentionBac) {
        this.mentionBac = mentionBac;
    }

    public OptionBac getOptionBac() {
        return optionBac;
    }

    public void setOptionBac(OptionBac optionBac) {
        this.optionBac = optionBac;
    }

    public Lycee getLycee() {
        return lycee;
    }

    public void setLycee(Lycee lycee) {
        this.lycee = lycee;
    }

    public Lieu getProvince() {
        return province;
    }

    public void setProvince(Lieu province) {
        this.province = province;
    }

    public Academie getAcademie() {
        return academie;
    }

    public void setAcademie(Academie academie) {
        this.academie = academie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cne != null ? cne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the cne fields are not set
        if (!(object instanceof EtudiantMaster)) {
            return false;
        }
        EtudiantMaster other = (EtudiantMaster) object;
        if ((this.cne == null && other.cne != null) || (this.cne != null && !this.cne.equals(other.cne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.EtudiantMaster[ cne=" + cne + " ]";
    }
    
}
