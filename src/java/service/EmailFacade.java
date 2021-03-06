/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Candidat;
import static bean.Candidat_.etablissement;
import bean.ConcourNiveau;
import bean.DernierDiplome;
import bean.EtablissementType;
import bean.OptionBac;
import bean.Profession;
import controller.util.DateUtil;
import controller.util.EmailUtil;
import java.util.Date;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class EmailFacade {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    public void SendMail(String cne, String tel, String adresse, String anneeBac, int anneeInsSup, int anneeInsEtab, int anneeInsUniv, String cin, Date dateInscription, Date dateNaissance, String mail, int etabPreIns, String lieuNaissance, String nomAR, String prenomAR, String nomLAT, String prenomLAT, float noteS1, float noteS2, float noteS3, float noteS4, float noteS5, float noteS6, EtablissementType etablissementType, DernierDiplome dernierDiplome, ConcourNiveau concourNiveau, OptionBac serieBac, int anneeInscriptionEnsSup, int anneeInscriptionUniv, int anneeInscriptionEtab, Profession professionMere, Candidat candidat) throws MessagingException {

        String msg = "<b>Merci pour votre préinscription en ligne au concours du Cycle d'Ingénierie de la FSTG Marrakech.</b><br></br>"
                + "<br></br> <b>Connectez vous avec le mot de passe suivant :</b> " + candidat.getPassword() + "</br>"
                + "<b>Vous avez soumis les données suivantes:</b> </br><table>\n"
                + " <tr>\n"
                + "    <td> <b>Filière choisie:</b> </td>\n"
                + "    <td>" + concourNiveau + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>Nom :</b></td>\n"
                + "    <td>" + nomLAT + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>Prénom :</b></td>\n"
                + "    <td>" + prenomLAT + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>Nom Arabe : </b></td>\n"
                + "    <td>" + nomAR + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>Lieu de naissance :</b></td>\n"
                + "    <td>" + prenomAR + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>Date de naissance : :</b></td>\n"
                + "    <td>" + DateUtil.format2(dateNaissance) + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>Lieu de naissance :</b></td>\n"
                + "    <td>" + lieuNaissance + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>CIN :</b></td>\n"
                + "    <td>" + cin + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>CNE :</b></td>\n"
                + "    <td>" + cne + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>E-mail :</b></td>\n"
                + "    <td>" + mail + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>Télephone (GSM):</b></td>\n"
                + "    <td>" + tel + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Ville:</b></td>\n"
                + "    <td>" + lieuNaissance + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Moyenne semestre 1:</b></td>\n"
                + "    <td>" + noteS1 + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Moyenne semestre 2:</b></td>\n"
                + "    <td>" + noteS2 + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Moyenne semestre 3:</b></td>\n"
                + "    <td>" + noteS3 + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Moyenne semestre 4:</b></td>\n"
                + "    <td>" + noteS4 + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Moyenne semestre 5:</b></td>\n"
                + "    <td>" + noteS5 + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Moyenne semestre 6:</b></td>\n"
                + "    <td>" + noteS6 + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Année Inscription Universitaire:</b></td>\n"
                + "    <td>" + anneeInsUniv + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Année Inscription aux études supérieurs :</b></td>\n"
                + "    <td>" + anneeInsSup + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Profession de la mère :</b></td>\n"
                + "    <td>" + professionMere + "</td>\n"
                + "  </tr>\n"
                + "</table>";

        EmailUtil.sendMail2("hajar.hidi@aiesec.net", "RKQGVXRX", msg, mail, "FST MARRAKECH: Préinscription en ligne au concours du s=cycle d'ingénieur");

    }

    public void SendMailMaster(String cne, String tel, String adresse, String anneeBac, Profession professionMere, int anneeInsSup, int anneeInsEtab, int anneeInsUniv, String cin, Date dateInscription, Date dateNaissance, String mail, int etabPreIns, String lieuNaissance, String nomAR, String prenomAR, String nomLAT, String prenomLAT, EtablissementType etablissementType, DernierDiplome dernierDiplome, ConcourNiveau concourNiveau, String mentionDiplome, String optionLicence, String anneeObtLicence, float noteS1, float noteS2, float noteS3, float noteS4, float noteS5, float noteS6, String modeDeValidation1, String anneeDeValidation1, int nombreDinscription1, int valideApresRattrapage1, String modeDeValidation2, String anneeDeValidation2, int nombreDinscription2, int valideApresRattrapage2, String modeDeValidation3, String anneeDeValidation3, int nombreDinscription3, int valideApresRattrapage3, String modeDeValidation4, String anneeDeValidation4, int nombreDinscription4, int valideApresRattrapage4, String modeDeValidation5, String anneeDeValidation5, int nombreDinscription5, int valideApresRattrapage5, String modeDeValidation6, String anneeDeValidation6, int nombreDinscription6, int valideApresRattrapage6, Candidat candidat) throws MessagingException {

        String msg = "<b>Merci pour votre préinscription en ligne au concours du Master de la FSTG Marrakech.</b><br></br>"
                + "<br></br><b>Connectez vous avec le mot de passe suivant:</b> " + candidat.getPassword() + "</br>"
                + "<b>Vous avez soumis les données suivantes:</b> </br><table>\n"
                + " <tr>\n"
                + "    <td> <b>Filière choisie:</b> </td>\n"
                + "    <td>" + concourNiveau + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>Nom :</b></td>\n"
                + "    <td>" + nomLAT + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>Prénom :</b></td>\n"
                + "    <td>" + prenomLAT + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>Nom Arabe : </b></td>\n"
                + "    <td>" + nomAR + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>Lieu de naissance :</b></td>\n"
                + "    <td>" + prenomAR + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>Date de naissance : :</b></td>\n"
                + "    <td>" + DateUtil.format2(dateNaissance) + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>Lieu de naissance :</b></td>\n"
                + "    <td>" + lieuNaissance + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>CIN :</b></td>\n"
                + "    <td>" + cin + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>CNE :</b></td>\n"
                + "    <td>" + cne + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>E-mail :</b></td>\n"
                + "    <td>" + mail + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b>Télephone (GSM):</b></td>\n"
                + "    <td>" + tel + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Etablissment :</b></td>\n"
                + "    <td>" + etablissementType.getTitre() + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Ville:</b></td>\n"
                + "    <td>" + lieuNaissance + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Année Inscription Universitaire:</b></td>\n"
                + "    <td>" + anneeInsUniv + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Année Inscription aux études supérieurs :</b></td>\n"
                + "    <td>" + anneeInsSup + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Année Obtention Licence:</b></td>\n"
                + "    <td>" + anneeObtLicence + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Option Licence :</b></td>\n"
                + "    <td>" + optionLicence + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Mention Licence :</b></td>\n"
                + "    <td>" + mentionDiplome + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Profession de la mère :</b></td>\n"
                + "    <td>" + professionMere + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Moyenne semestre 1:</b></td>\n"
                + "    <td>" + noteS1 + "</td>"
                + "    <td><b> Année de validation:</b></td>\n"
                + "    <td>" + anneeDeValidation1 + "</td>\n"
                + "    <td><b> Mode de Validation :</b></td>\n"
                + "    <td>" + modeDeValidation1 + "</td>\n"
                + "    <td><b> Nombre de fois inscrits à ce semestre:</b></td>\n"
                + "    <td>" + nombreDinscription1 + "</td>\n"
                + "    <td><b> Nombre de modules validés après rattrapage:</b></td>\n"
                + "    <td>" + valideApresRattrapage1 + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Moyenne semestre 2:</b></td>\n"
                + "    <td>" + noteS2 + "</td>"
                + "    <td><b> Année de validation:</b></td>\n"
                + "    <td>" + anneeDeValidation2 + "</td>\n"
                + "    <td><b> Mode de Validation :</b></td>\n"
                + "    <td>" + modeDeValidation2 + "</td>\n"
                + "    <td><b> Nombre de fois inscrits à ce semestre:</b></td>\n"
                + "    <td>" + nombreDinscription2 + "</td>\n"
                + "    <td><b> Nombre de modules validés après rattrapage:</b></td>\n"
                + "    <td>" + valideApresRattrapage2 + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Moyenne semestre 3:</b></td>\n"
                + "    <td>" + noteS3 + "</td>"
                + "    <td><b> Année de validation:</b></td>\n"
                + "    <td>" + anneeDeValidation3 + "</td>\n"
                + "    <td><b> Mode de Validation :</b></td>\n"
                + "    <td>" + modeDeValidation3 + "</td>\n"
                + "    <td><b> Nombre de fois inscrits à ce semestre:</b></td>\n"
                + "    <td>" + nombreDinscription3 + "</td>\n"
                + "    <td><b> Nombre de modules validés après rattrapage:</b></td>\n"
                + "    <td>" + valideApresRattrapage3 + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Moyenne semestre 4:</b></td>\n"
                + "    <td>" + noteS4 + "</td>"
                + "    <td><b> Année de validation:</b></td>\n"
                + "    <td>" + anneeDeValidation4 + "</td>\n"
                + "    <td><b> Mode de Validation :</b></td>\n"
                + "    <td>" + modeDeValidation4 + "</td>\n"
                + "    <td><b> Nombre de fois inscrits à ce semestre:</b></td>\n"
                + "    <td>" + nombreDinscription4 + "</td>\n"
                + "    <td><b> Nombre de modules validés après rattrapage:</b></td>\n"
                + "    <td>" + valideApresRattrapage4 + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Moyenne semestre 5:</b></td>\n"
                + "    <td>" + noteS5 + "</td>"
                + "    <td><b> Année de validation:</b></td>\n"
                + "    <td>" + anneeDeValidation5 + "</td>\n"
                + "    <td><b> Mode de Validation :</b></td>\n"
                + "    <td>" + modeDeValidation5 + "</td>\n"
                + "    <td><b> Nombre de fois inscrits à ce semestre:</b></td>\n"
                + "    <td>" + nombreDinscription5 + "</td>\n"
                + "    <td><b> Nombre de modules validés après rattrapage:</b></td>\n"
                + "    <td>" + valideApresRattrapage5 + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Moyenne semestre 6:</b></td>\n"
                + "    <td>" + noteS6 + "</td>"
                + "    <td><b> Année de validation:</b></td>\n"
                + "    <td>" + anneeDeValidation6 + "</td>\n"
                + "    <td><b> Mode de Validation :</b></td>\n"
                + "    <td>" + modeDeValidation6 + "</td>\n"
                + "    <td><b> Nombre de fois inscrits à ce semestre:</b></td>\n"
                + "    <td>" + nombreDinscription6 + "</td>\n"
                + "    <td><b> Nombre de modules validés après rattrapage:</b></td>\n"
                + "    <td>" + valideApresRattrapage6 + "</td>\n"
                + "  </tr>\n"
                + "</table>";

        EmailUtil.sendMail2("hajar.hidi@aiesec.net", "RKQGVXRX", msg, mail, "FST MARRAKECH: Préinscription en ligne au concours du s=cycle d'ingénieur");

    }

}
