/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Departement;
import bean.Filiere;
import controller.util.SearchUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**IMPORT EXCEL TOOLS**/

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.primefaces.event.FileUploadEvent;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**IMPORT EXCEL TOOLS**/

/**
 *
 * @author HP
 */
@Stateless
public class FiliereFacade extends AbstractFacade<Filiere> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;
    
    @EJB
    private PvFacade pvFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /** EXCEL TOOLS**/
    
    private final JxlItem[] filiereTools = new JxlItem[]{
        new JxlItem("Filiere", "libelle"),
        new JxlItem("Filiere", "abreviation"),
        new JxlItem("Filiere", "objectif"),
       /* new JxlItem("Filiere", "typeFiliere"),
        new JxlItem("Filiere", "typeFormation"),
        new JxlItem("Filiere", "departement"),
        new JxlItem("Filiere", "responsableFiliere")
        */
        

    };
    
    /** EXCEL TOOLS**/

    

    public FiliereFacade() {
        super(Filiere.class);
    }

    public List<Filiere> findByType(int x, int y) {
        String requette = "SELECT f FROM Filiere f WHERE f.typeFiliere='" + x + "' AND f.typeFormation='" + y + "'";
        System.out.println(requette);
        return em.createQuery(requette).getResultList();
    }

    public List<Filiere> findByDepartm(Departement d,int x) {
        String requette = "SELECT f FROM Filiere f WHERE 1=1";
        if (x > 0) {
            requette += SearchUtil.addConstraint("f", "typeFiliere", "=", x);
        }
        if (d != null && d.getId() != null) {
            requette += SearchUtil.addConstraint("f.departement", "id", "=", d.getId());
            requette += " ORDER BY f.libelle";
        }
        return em.createQuery(requette).getResultList();
    }
    
    
    /** EXCEL METHODS  **/
    
    
    public int readExcel(FileUploadEvent event, Filiere filiereGiven) throws Exception {
        Workbook workbook = null;
        System.out.println("Filiere TypeFiliere === "+filiereGiven.getTypeFiliere());
        System.out.println("Filiere TypeFormation === "+filiereGiven.getTypeFormation());
        System.out.println("Filiere Responsable === "+filiereGiven.getResponsableFiliere());
        System.out.println("Filiere Departement === "+filiereGiven.getDepartement());
        try {
            InputStream excelFileToRead;
            if (event.getFile().getFileName().endsWith(".xlsx")) {
                OutputStream convertedExcel = pvFacade.xlsx2xls(event.getFile().getInputstream());
                excelFileToRead = event.getFile().getInputstream();

            } else {
                excelFileToRead = event.getFile().getInputstream();
            }
            workbook = Workbook.getWorkbook(excelFileToRead);
            int length = workbook.getSheets().length;
            System.out.println("Length: " + length);
            Sheet signUpSheet = workbook.getSheet(0);

            Cell cellStartEtud = signUpSheet.findCell("LIBELLE");
            //**********************************************
            //*******************WARNING********************
            //==============================================
            //Note that the cell is defined as Cell(int column, int row)

            int i = cellStartEtud.getRow();
            int j = cellStartEtud.getColumn();
            Cell cell;
            for (int l = i + 1; l < signUpSheet.getRows(); l++) {
                cell = signUpSheet.getCell(j, l);
                //find Filiere
                Filiere filiere = new Filiere();
                //create new noteSemestre for that etudiant
                
                filiere.setTypeFiliere(filiereGiven.getTypeFiliere());
                filiere.setTypeFormation(filiereGiven.getTypeFormation());
                filiere.setDepartement(filiereGiven.getDepartement());
                filiere.setResponsableFiliere(filiereGiven.getResponsableFiliere());
                
                int n = 0;
                for (int c = j; c < signUpSheet.getColumns(); c++) {
                    JxlItem item = filiereTools[n];
                    cell = signUpSheet.getCell(c, l);
                    switch (item.getBeanName()) {
                        case "Filiere":
                            filiere = (Filiere) pvFacade.daoEngineLauncher(cell, filiere, item);
                            break;
                        default:
                            break;
                    }
                    n++;
                }
                //filiere.setLibelle(filiere.getLibelle());
                //filiere.setAbreviation(filiere.getAbreviation());
                //filiere.setObjectif(filiere.getObjectif());
                filiere.setTypeFiliere(filiereGiven.getTypeFiliere());
                filiere.setTypeFormation(filiereGiven.getTypeFormation());
                filiere.setDepartement(filiereGiven.getDepartement());
                filiere.setResponsableFiliere(filiereGiven.getResponsableFiliere());
                createWithValidation(filiere);
            }

            System.out.println("************************************");
            System.out.println("============ SHEET DONE ============");
            System.out.println("************************************");
        } catch (IOException e) {
            e.printStackTrace();
            return -3;
        } catch (BiffException e) {
            e.printStackTrace();
            return -3;
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return 1;
    }
    
    /** EXCEL METHODS  **/

}
