/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;

import bean.Article;
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
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Chaimaa-abd
 */
public class ServerConfigUtil {

    private static String rootPath = "/Users/mac/NetBeansProjects/FSG_WebSite/web/resources/images/UploadedImages";
    private static String departPath = "pfe.files.path";//chemin dont laquelle on va creer le dosqsier globale qui aura pour bute de contenir la totalitees des dossier d un abonnee
    public static String path1 = "/Users/HP/Documents/NetBeansProjects/Pfe/web/resources/pdfCycle";
    public static String pdfPath = "C:\\Users\\HP\\Documents\\NetBeansProjects\\Pfe\\web\\resources\\pdfCycle";
    private static List<Item> articlePaths = new ArrayList();
//    private static String pathPieceJoint = "resources";

//    static {
//        filesPath(articlePaths, "photo_Folder");
//    }
//    private static String getContextParameter(String paramInWeb) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        String myConstantValue = ctx.getExternalContext().getInitParameter(paramInWeb);
//        return myConstantValue;
//    }
//
//    private static void filesPath(List<Item> items, String nameVariable) {
//        String filesName = getContextParameter(nameVariable);
//        String paths[] = filesName.split(",");
//        for (int i = 0; i < paths.length; i++) {
//            String path = paths[i];
//            String[] oneFileConfig = path.split("=");
//            items.add(new Item(oneFileConfig[0], oneFileConfig[1]));
//        }
//    }
//    private static String findFileByPath(List<Item> items, String path) {
//        for (int i = 0; i < items.size(); i++) {
//            Item sessionItem = items.get(i);
//            if (sessionItem.getKey().equalsIgnoreCase(path)) {
//                return sessionItem.getObject().toString();
//            }
//        }
//        return null;
//    }
    public static String getArticleFilePath() {
        return rootPath;

    }

    public static String getDepartFilePath() {
        return departPath;
    }

    public static String uploadPdf(UploadedFile uploadedFile, String type, String uploadedFileName) {
        try {
            String nameOfUploadedFile = uploadedFile.getFileName();
            File file = new File(nameOfUploadedFile.replace('\\', '/'));
//            String fileSavePath = destinationPath + "\\" + nameOfUploadedFile;
            String fileName = uploadedFileName + "." + FilenameUtils.getExtension(nameOfUploadedFile);
            String fileSavePath = pdfPath + "\\" + type + "\\" + fileName;

            InputStream fileContent = uploadedFile.getInputstream();
            Path path = new File(fileSavePath).toPath();
            System.out.println(path);
            Files.copy(fileContent, path, StandardCopyOption.REPLACE_EXISTING);
            return "/resources/pdfCycle/" + type + "/" + fileName;
        } catch (IOException e) {
            System.out.println("----- Mat uploada walo ==> No update==");
            JsfUtil.addErrorMessage(e, "Erreur Upload");
            return "";
        }

    }

    public static void upload(UploadedFile uploadedFile, String destinationPath) {
        try {
            String nameOfUploadedFile = uploadedFile.getFileName();
            String fileSavePath = destinationPath + "\\" + nameOfUploadedFile;
            System.out.println("ha file savePath*****" + fileSavePath);
            System.out.println("ha faile save path " + new File(fileSavePath).toPath().toString());
            InputStream fileContent = uploadedFile.getInputstream();
            Path path = new File(fileSavePath).toPath();
            System.out.println(path);
            Files.copy(fileContent, path, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            System.out.println("------- error upload image== No update==");
            JsfUtil.addErrorMessage(e, "Erreur Upload image");

        }

    }

}
