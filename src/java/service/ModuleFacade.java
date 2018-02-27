/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Enseignant;
import bean.Etudiant;
import bean.Module;
import bean.Semestre;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
import javax.ejb.EJB;

/**
 *
 * @author Abed
 */
@Stateless
public class ModuleFacade extends AbstractFacade<Module> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;
    
    @EJB
    private PvFacade pvFacade;
    @EJB
    private EnseignantFacade enseignantFacade;

    private final JxlItem[] moduleTools = new JxlItem[]{
        new JxlItem("Module", "nom"),
        new JxlItem("Enseignant", "nom")
        

    };
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModuleFacade() {
        super(Module.class);
    }

    public List<Module> findModulsSemestreActuel(String a) {
        System.out.println("kat9alab 3la lmodul b String");
        return em.createQuery("SELECT m from Module m where m.nom='" + a + "'").getResultList();
    }

    public List<Module> findModulsSemestreActuel(Etudiant etudiant) {
        return em.createQuery("SELECT m from Module m where m.filiere.id='" + etudiant.getFiliere().getId() + "' AND m.semestre.id='" + etudiant.getSemestreActuel().getId() + "'").getResultList();
    }

    // Abed's
    public Module findModuleByNameAndSemestre(String name, Semestre semestre) {
        Module m;
        try {
            m = (Module) em.createQuery("SELECT m FROM Module m WHERE m.nom='" + name + "' AND m.semestre.id=" + semestre.getId()).getSingleResult();
        } catch (Exception exception) {
            m = null;
        }
        return m;
    }
    
    public List<Module> findBySemestre(int semestre)
    {
        List<Module> res = new ArrayList<>();
        if( semestre != 0 )
        {
            
           res = em.createQuery(" SELECT m FROM Module m WHERE m.semestre.libelle ='"+semestre+"'").getResultList();
           
        }
        
        return res;
    }
    
    
    /** EXCEL METHODS  **/
    
    
    public int readExcel(FileUploadEvent event, Module moduleGiven) throws Exception {
        Workbook workbook = null;
        System.out.println("Module Filiere === "+moduleGiven.getFiliere());
        System.out.println("Module Semestre === "+moduleGiven.getSemestre());
        
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

            Cell cellStartEtud = signUpSheet.findCell("NOM");
            //**********************************************
            //*******************WARNING********************
            //==============================================
            //Note that the cell is defined as Cell(int column, int row)

            int i = cellStartEtud.getRow();
            int j = cellStartEtud.getColumn();
            Cell cell;
            for (int l = i + 1; l < signUpSheet.getRows(); l++) {
                cell = signUpSheet.getCell(j, l);
                //find Module
                Module module = new Module();
                Enseignant givenEns = new Enseignant();
                
                
                module.setFiliere(moduleGiven.getFiliere());
                module.setSemestre(moduleGiven.getSemestre());
                int n = 0;
                for (int c = j; c < signUpSheet.getColumns(); c++) {
                    JxlItem item = moduleTools[n];
                    cell = signUpSheet.getCell(c, l);
                    switch (item.getBeanName()) {
                        case "Module":
                            module = (Module) pvFacade.daoEngineLauncher(cell, module, item);
                            break;
                        case "Enseignant":
                            givenEns = (Enseignant) pvFacade.daoEngineLauncher(cell, givenEns, item);
                            break;
                        default:
                            break;
                    }
                    n++;
                }
                System.out.println("Enseignant == "+givenEns.getNom());
                System.out.println(enseignantFacade.findSingleEnsByFullName(givenEns));
                module.setEnseignant(enseignantFacade.findSingleEnsByFullName(givenEns));
                System.out.println("Module Enseignant === "+module.getEnseignant());
                module.setFiliere(moduleGiven.getFiliere());
                System.out.println("getFiliere Module === "+moduleGiven.getFiliere());
                module.setSemestre(moduleGiven.getSemestre());
                System.out.println("getSemestre() Module === "+moduleGiven.getSemestre());
                createWithValidation(module);
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
}
