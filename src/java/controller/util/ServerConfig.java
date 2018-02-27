/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;

import bean.Departement;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Chaimaa-abd
 */
public class ServerConfig {

    private static String vmParam = "pfe.files.path";//chemin dont laquelle on va creer le dosqsier globale qui aura pour bute de contenir la totalitees des dossier d un abonnee
    private static Item item;

//    public static void createDepartmFiles(Departement d) {
//        createFile(new File(getCommuneFilePath(d, true) + "\\" + item.getObject().toString()));
//    }

//    private static String getContextParameter(String paramInWeb) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        String myConstantValue = ctx.getExternalContext().getInitParameter(paramInWeb);
//        return myConstantValue;
//    }

    public static void upload(UploadedFile uploadedFile, String destinationPath, String nameOfUploadeFile) {
        try {
            String fileSavePath = destinationPath + "\\" + nameOfUploadeFile;
            System.out.println("ha file save path :::" + fileSavePath);
            InputStream fileContent = null;
            fileContent = uploadedFile.getInputstream();
            System.out.println("ha fileContent " + fileContent.toString());
            System.out.println("ha faile save path " + new File(fileSavePath).toPath().toString());
            Files.copy(fileContent, new File(fileSavePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            JsfUtil.addErrorMessage(e, "Erreur Upload image");
            System.out.println("No update !!!!");
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ServerConfigUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
