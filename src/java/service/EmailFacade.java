/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Academie;
import static bean.ConcourExamMatiere_.concourNiveau;
import bean.ConcourNiveau;
import bean.DernierDiplome;
import bean.OptionBac;
import bean.Pays;
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
public class EmailFacade  {

    

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    


    public void SendMail(String cne, String tel, String adresse, String anneeBac, int anneeInsSup, int anneeInsEtab, int anneeInsUniv, String cin, Date dateInscription, Date dateNaissance, String mail, int etabPreIns, String lieuNaissance, String nomAR, String prenomAR, String nomLAT, String prenomLAT, float noteS1, float noteS2, float noteS3, float noteS4, float noteS5, float noteS6, String etablissement, DernierDiplome dernierDiplome, ConcourNiveau concourNiveau, OptionBac serieBac) throws MessagingException {

        String msg = "Merci pour votre préinscription en ligne au concours du Cycle Ingénieur de la FST Marrakech.</br>"
                + "votre numéro de préinscription est : " + 2125 + "</br>"
                + "Vous avez soumis les données suivantes: </br><table>\n"
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
                + "    <td>" + dateNaissance + "</td>\n"
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
                + "    <td><b> Nature du diplôme :</b></td>\n"
                + "    <td>" + dernierDiplome.getTitre() + "</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td><b> Etablissment :</b></td>\n"
                + "    <td>" + etablissement + "</td>\n"
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
            
                + "</table>";

        EmailUtil.sendMail2("hajar.hidi@aiesec.net", "RKQGVXRX", msg, mail, "FST MARRAKECH: Préinscription en ligne au concours du s=cycle d'ingénieur");

    }

}
