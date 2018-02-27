/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Departement;
import bean.Etudiant;
import bean.Filiere;
import bean.Module;
import bean.NoteModulaire;
import bean.NoteSemestre;
import bean.Pv;
import bean.Semestre;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import controller.util.DaoEngine;
import controller.util.HashageUtil;
import controller.util.SearchUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

/**
 *
 * @author Abed afriad
 */
@Stateless
public class PvFacade extends AbstractFacade<Pv> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PvFacade() {
        super(Pv.class);
    }
    @EJB
    ModuleFacade moduleFacade;
    @EJB
    SemestreFacade semestreFacade;
    @EJB
    EtudiantFacade etudiantFacade;
    @EJB
    NoteModulaireFacade noteModulaireFacade;
    @EJB
    NoteSemestreFacade noteSemestreFacade;
    @EJB
    FiliereFacade filiereFacade;
    private final JxlItem[] inscriptionEtudiant = new JxlItem[]{
        new JxlItem("Etudiant", "cne"),
        new JxlItem("Etudiant", "nom"),
        new JxlItem("Etudiant", "prenom"),
        new JxlItem("Etudiant", "dateNaissance"),
        new JxlItem("Etudiant", "gender"),
        new JxlItem("Etudiant", "email")

    };
    private final JxlItem[] pvTabs = new JxlItem[]{
        new JxlItem("Etudiant", "cne"),
        new JxlItem("Etudiant", "nom"),
        new JxlItem("Etudiant", "prenom"),
        new JxlItem("NoteModulaire", "noteBeforeJury"),
        new JxlItem("NoteModulaire", "mentionBeforeJury"),
        new JxlItem("NoteModulaire", "ptJury"),
        new JxlItem("NoteModulaire", "note"),
        new JxlItem("NoteModulaire", "mention"),
        new JxlItem("NoteModulaire", "etat"),
        new JxlItem("NoteModulaire", "noteBeforeJury"),
        new JxlItem("NoteModulaire", "mentionBeforeJury"),
        new JxlItem("NoteModulaire", "ptJury"),
        new JxlItem("NoteModulaire", "note"),
        new JxlItem("NoteModulaire", "mention"),
        new JxlItem("NoteModulaire", "etat"),
        new JxlItem("NoteModulaire", "noteBeforeJury"),
        new JxlItem("NoteModulaire", "mentionBeforeJury"),
        new JxlItem("NoteModulaire", "ptJury"),
        new JxlItem("NoteModulaire", "note"),
        new JxlItem("NoteModulaire", "mention"),
        new JxlItem("NoteModulaire", "etat"),
        new JxlItem("NoteModulaire", "noteBeforeJury"),
        new JxlItem("NoteModulaire", "mentionBeforeJury"),
        new JxlItem("NoteModulaire", "ptJury"),
        new JxlItem("NoteModulaire", "note"),
        new JxlItem("NoteModulaire", "mention"),
        new JxlItem("NoteModulaire", "etat"),
        new JxlItem("NoteModulaire", "noteBeforeJury"),
        new JxlItem("NoteModulaire", "mentionBeforeJury"),
        new JxlItem("NoteModulaire", "ptJury"),
        new JxlItem("NoteModulaire", "note"),
        new JxlItem("NoteModulaire", "mention"),
        new JxlItem("NoteModulaire", "etat"),
        new JxlItem("NoteModulaire", "noteBeforeJury"),
        new JxlItem("NoteModulaire", "mentionBeforeJury"),
        new JxlItem("NoteModulaire", "ptJury"),
        new JxlItem("NoteModulaire", "note"),
        new JxlItem("NoteModulaire", "mention"),
        new JxlItem("NoteModulaire", "etat"),
        new JxlItem("NoteSemestre", "total"),
        new JxlItem("NoteSemestre", "note"),
        new JxlItem("NoteSemestre", "nbrModuleValide"),
        new JxlItem("NoteSemestre", "mention")
    };

    private final JxlItem[] filiereTabs = new JxlItem[]{
        new JxlItem("Filiere", "libelle"),
        new JxlItem("Filiere", "abreviation"),
        new JxlItem("Filiere", "objectif")
    };
    private final JxlItem[] SemestreTabs = new JxlItem[]{
        new JxlItem("Filiere", "libelle"),
        new JxlItem("Filiere", "abreviation"),
        new JxlItem("Filiere", "objectif")
    };

    // get modules by module name recupered from sheet
    public List<Module> getModuleList(List<String> moduleNames, Semestre semestre) {
        List<Module> modules = new ArrayList<>();
        for (String moduleName : moduleNames) {
            if (modules.size() < 6) {
                if (!moduleName.equals("")) {
                    Module m = moduleFacade.findModuleByNameAndSemestre(moduleName, semestre);
                    System.out.println("--Module " + m);
                    if (m == null) {
                        return null;
                    }
                    modules.add(m);
                }
            }
        }
        return modules;
    }

    // persist the note modulaire 
    public void persistNoteModulaire(NoteModulaire noteModulaire) {
        NoteModulaire loaded = noteModulaireFacade.findNMByModuleAndEtudiant(noteModulaire);
        if (loaded != null) {
            noteModulaireFacade.remove(loaded);
        }
        System.out.println("note " + noteModulaire.getNote());
        int i = (noteModulaire.getNote() < 10) ? 0 : 1;
        noteModulaire.setEtat(i);
        noteModulaireFacade.create(noteModulaire);
    }

    // persist the note Semestre 
    public void persistNoteSemestre(NoteSemestre noteSemestre) {
        NoteSemestre loaded = noteSemestreFacade.findNSBySemestreAndEtudiant(noteSemestre);
        if (loaded != null) {
            noteSemestreFacade.remove(loaded);
        }
        System.out.println("noteSem " + noteSemestre.getNote());
        int i = (noteSemestre.getNote() < 10) ? 0 : 1;
        noteSemestre.setEtat(i);
        int c = noteSemestre.getNbrModuleValide();
        noteSemestre.setNbrModuleValide(6 - c);
        noteSemestreFacade.create(noteSemestre);
    }

    // Handle the comma troubles
    public String handleDouble(String value) {
        return value.replace(",", ".");
    }

    public int readXls(FileUploadEvent event, Semestre semestre) throws Exception {
//        if (semestre == null) {
//            return -6;
//        }
        Workbook workbook = null;
        try {
            InputStream excelFileToRead;
            if (event.getFile().getFileName().endsWith(".xlsx")) {
                OutputStream convertedExcel = xlsx2xls(event.getFile().getInputstream());
                excelFileToRead = event.getFile().getInputstream();

            } else {
                excelFileToRead = event.getFile().getInputstream();
            }
            workbook = Workbook.getWorkbook(excelFileToRead);

            int length = workbook.getSheets().length;
            System.out.println("Length " + length);
            Sheet pvSheet = workbook.getSheet("J-S");
            Sheet[] sheets = workbook.getSheets();
            for (int i = 0; i < sheets.length; i++) {
                Sheet sheet = sheets[i];
                System.out.println("sheet n " + i + " : " + sheet.getName());
            }

            Cell cellStart = pvSheet.findCell("CNE");
            //**********************************************
            //*******************WARNING********************
            //==============================================
            //Note that the cell is defined as Cell(int column, int row)

            int i = cellStart.getRow();
            int j = cellStart.getColumn();
            Cell cell = pvSheet.getCell(j, i);
            List<String> moduleNames = new ArrayList<>();
            Cell cellModuleName;
            for (int k = j + 3; k < pvSheet.getColumns(); k++) {
                cellModuleName = pvSheet.getCell(k, i - 1);
                if (!cell.getContents().equals("") && cell.getContents() != null) {
                    moduleNames.add(cellModuleName.getContents());
                    System.out.println(k + " ---" + cellModuleName.getContents() + "---");
                }
            }
            List<Module> modules = getModuleList(moduleNames, semestre);
            if (modules == null) {
                System.out.println("Module non trouveee  ");
                return -5;
            } else {
                for (int l = i + 1; l < pvSheet.getRows(); l++) {
                    cell = pvSheet.getCell(j, l);
                    if (cell.getContents().equals("")) {
                        break;
                    }
                    //find etudiant
                    Etudiant etudiant = etudiantFacade.find(Long.parseLong(cell.getContents()));
                    if (etudiant != null) {
                        //create new noteSemestre for that etudiant
                        NoteSemestre noteSemestre = new NoteSemestre();
                        noteSemestre.setEtudiant(etudiant);
                        noteSemestre.setSemestre(semestre);
                        NoteModulaire noteModulaire = new NoteModulaire();
                        List<NoteModulaire> noteModulaires = new ArrayList<>();

                        int n = 0;
                        int moduleNameCmp = 0;

                        for (int c = j; c < pvSheet.getColumns() + 1 && n < 43; c++) {
                            if (n == 3 || n == 9 || n == 15 || n == 21 || n == 27 || n == 33) {
                                noteModulaire = new NoteModulaire();
                                noteModulaire.setModule(modules.get(moduleNameCmp));
                                noteModulaire.setEtudiant(etudiant);
                                moduleNameCmp++;
                            }
                            JxlItem item = pvTabs[n];
                            cell = pvSheet.getCell(c, l);
                            switch (item.getBeanName()) {
                                case "Etudiant":
                                    etudiant = (Etudiant) daoEngineLauncher(cell, etudiant, item);
                                    break;
                                case "NoteModulaire":
                                    noteModulaire = (NoteModulaire) daoEngineLauncher(cell, noteModulaire, item);
                                    break;
                                case "NoteSemestre":
                                    noteSemestre = (NoteSemestre) daoEngineLauncher(cell, noteSemestre, item);
                                    break;
                                default:
                                    break;
                            }
                            n++;
                            if (n == 8 || n == 14 || n == 20 || n == 26 || n == 32 || n == 38) {
                                noteModulaires.add(noteModulaire);
                            }
                        }
                        noteModulaires.stream().forEach((noteM) -> {
                            persistNoteModulaire(noteM);
                        });
                        persistNoteSemestre(noteSemestre);
                        etudiant = semestreFacade.findNextSemestreForEtudiant(etudiant, semestre);
                        etudiantFacade.edit(etudiant);

                    } else {
                        String msg = "La Base de Donnees ne contient pas l'etudiant dont le CNE est : " + cell.getContents()
                                + ". Veuillez verifiez les etudiants dans votre fichier et dans la base de donnees. ";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", msg));
                        return -1;
                    }
                }
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

    public int readDeustResults(FileUploadEvent event, Filiere filiere) throws Exception {
//        if (filiere == null) {
//            return -6;
//        }
        Workbook workbook = null;
        try {
            InputStream excelFileToRead;
            if (event.getFile().getFileName().endsWith(".xlsx")) {
                OutputStream convertedExcel = xlsx2xls(event.getFile().getInputstream());
                excelFileToRead = event.getFile().getInputstream();

            } else {
                excelFileToRead = event.getFile().getInputstream();
            }
            workbook = Workbook.getWorkbook(excelFileToRead);
            int lenght = workbook.getSheets().length;
            Sheet deustSheet = workbook.getSheet("DEUST");

            Cell cellStartEtud = deustSheet.findCell("CNE");
            Cell cellStartRes = deustSheet.findCell("Résultat");
            //**********************************************
            //*******************WARNING********************
            //==============================================
            //Note that the cell is defined as Cell(int column, int row)

            int i = cellStartEtud.getRow();
            int jEtud = cellStartEtud.getColumn();
            int jRes = cellStartRes.getColumn();
            Cell cell;
            for (int l = i + 1; l < deustSheet.getRows(); l++) {
                cell = deustSheet.getCell(jEtud, l);
                System.out.println("ha etudiant cne : " + cell.getContents());
                //find etudiant
                if (cell.getContents().equals("")) {
                    break;
                }

                Etudiant etudiant = etudiantFacade.find(Long.parseLong(cell.getContents()));
                if (etudiant != null) {
                    cell = deustSheet.getCell(jRes, l);
                    String resultat = cell.getContents();
                    if (resultat.equalsIgnoreCase("Aj. Non Réins")) {
                        etudiant.setEtatDeust(1);
                    } else if (resultat.equalsIgnoreCase("Aj. Réins")) {
                        etudiant.setEtatDeust(2);
                    } else {
                        etudiant.setEtatDeust(3);
                    }
                    etudiantFacade.edit(etudiant);

                } else {
                    String msg = "La Base de Donnees ne contient pas l'etudiant dont le CNE est : " + cell.getContents()
                            + ". Veuillez verifiez les etudiants dans votre fichier et dans la base de donnees. ";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", msg));
                    return -1;
                }
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

    public int inscription(FileUploadEvent event, Filiere filiere) throws Exception {
//        if (filiere == null) {
//            return -6;
//        }
        Workbook workbook = null;
        try {
            InputStream excelFileToRead;
            if (event.getFile().getFileName().endsWith(".xlsx")) {
                OutputStream convertedExcel = xlsx2xls(event.getFile().getInputstream());
                excelFileToRead = event.getFile().getInputstream();

            } else {
                excelFileToRead = event.getFile().getInputstream();
            }
            workbook = Workbook.getWorkbook(excelFileToRead);
            int length = workbook.getSheets().length;
            System.out.println("Length: " + length);
            Sheet signUpSheet = workbook.getSheet(0);

            Cell cellStartEtud = signUpSheet.findCell("CNE");
            //**********************************************
            //*******************WARNING********************
            //==============================================
            //Note that the cell is defined as Cell(int column, int row)

            int i = cellStartEtud.getRow();
            int j = cellStartEtud.getColumn();
            Cell cell;
            for (int l = i + 1; l < signUpSheet.getRows(); l++) {
                cell = signUpSheet.getCell(j, l);
                //find etudiant
                Etudiant etudiant = new Etudiant();
                //create new noteSemestre for that etudiant
                etudiant.setFiliere(filiere);
                int n = 0;
                for (int c = j; c < signUpSheet.getColumns(); c++) {
                    JxlItem item = inscriptionEtudiant[n];
                    cell = signUpSheet.getCell(c, l);
                    switch (item.getBeanName()) {
                        case "Etudiant":
                            etudiant = (Etudiant) daoEngineLauncher(cell, etudiant, item);
                            break;
                        default:
                            break;
                    }
                    n++;
                }
                etudiant.setBlocked(false);
                etudiant.setMdpChanged(false);
                etudiant.setPassword(HashageUtil.sha256(SearchUtil.convertForPw(etudiant.getDateNaissance())));
                etudiant.setFiliere(filiere);
                etudiant.setSemestreActuel(semestreFacade.findFirstSemOfFiliere(filiere));
                etudiantFacade.createWithValidation(etudiant);
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

    public int FiliereSemestreModuleCreation(FileUploadEvent event, Departement departement) throws Exception {
        Workbook workbook = null;
        try {
//            if (departement == null) {
//                return -6;
//            }
            InputStream excelFileToRead;
            if (event.getFile().getFileName().endsWith(".xlsx")) {
                OutputStream convertedExcel = xlsx2xls(event.getFile().getInputstream());
                excelFileToRead = event.getFile().getInputstream();

            } else {
                excelFileToRead = event.getFile().getInputstream();
            }
            workbook = Workbook.getWorkbook(excelFileToRead);
            int length = workbook.getSheets().length;
            System.out.println("Length: " + length);
            Sheet filSemModSheet = workbook.getSheet(0);

            Cell cellStartEtud = filSemModSheet.findCell("Filiere a ajouter");
            //**********************************************
            //*******************WARNING********************
            //==============================================
            //Note that the cell is defined as Cell(int column, int row)

            int j = cellStartEtud.getColumn();
            int i = cellStartEtud.getRow();
            Cell cell;
            Filiere filiere = new Filiere();
            filiere.setDepartement(departement);
            int cmp = 0;
            for (int k = i + 1; k < i + 4; k++) {
                cell = filSemModSheet.getCell(j + 1, k);
                System.out.println("ha cell ch7al row " + k + " col " + j + 1);
                System.out.println("ha content  " + cell.getContents());
                System.out.println("ha cmp  " + cmp);
                JxlItem jxlItem = filiereTabs[cmp];
                daoEngineLauncher(cell, filiere, jxlItem);
                cmp++;
                if (cmp == 3) {
                    break;
                }
            }

            Cell cellSemestre = filSemModSheet.findCell("SEMESTRES");
            i = cellSemestre.getRow();
            j = cellSemestre.getColumn();

            List<Module> modules = new ArrayList();
            List<Semestre> semestres = new ArrayList();
            for (int l = i + 1; l < i + 5; l++) {
                cell = filSemModSheet.getCell(j, l);
                System.out.println("cell sem " + cell.getContents() + " row " + l + " col " + j);
                Semestre semestre = new Semestre();
                semestre.setFiliere(filiere);
                semestre.setLibelle(Integer.parseInt(cell.getContents()));
                for (int cl = j + 1; cl < j + 7; cl++) {
                    System.out.println("ha cl " + cl + " w ha j " + j);
                    cell = filSemModSheet.getCell(cl, l);
                    System.out.println("cell mod  " + cell.getContents() + " row " + l + " col " + cl);

                    Module module = new Module();
                    module.setFiliere(filiere);
                    module.setSemestre(semestre);
                    module.setNom(cell.getContents());
                    modules.add(module);
                }
                semestre.setModules(modules);
                semestres.add(semestre);
            }
            persistFiliereSemsMods(filiere, semestres, modules);

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

    public void persistFiliereSemsMods(Filiere filiere, List<Semestre> semestres, List<Module> modules) {
        filiereFacade.createWithValidation(filiere);
        for (Semestre s : semestres) {
            semestreFacade.createWithValidation(s);
        }
        for (Module m : modules) {
            moduleFacade.createWithValidation(m);
        }
    }

    // Methode to set the attribute getted from sheet on an object
    public Object daoEngineLauncher(Cell cell, Object o, JxlItem item) throws Exception {
        String type = cell.getType() + "";
        System.out.println("object " + o.getClass());
        System.out.println("value " + cell.getContents());
        if (type.equalsIgnoreCase("empty") || cell.getContents().equalsIgnoreCase("abi")) {
            DaoEngine.launchSetterByName(o, item.getAttribute(), 0 + "");
        } else {
            DaoEngine.launchSetterByName(o, item.getAttribute(), handleDouble(cell.getContents()));
        }
        return o;
    }

    public OutputStream xlsx2xls(InputStream fileIn) throws InvalidFormatException, IOException {

        try {
            XSSFWorkbook wbIn = new XSSFWorkbook(fileIn);
            File fileOut = new File("fileOut");
//            File outF = new File(outFn);
//            if (outF.exists()) {
//                outF.delete();
//            }

            org.apache.poi.ss.usermodel.Workbook wbOut = new HSSFWorkbook();
            int sheetCnt = wbIn.getNumberOfSheets();
            for (int i = 0; i < sheetCnt; i++) {
                org.apache.poi.ss.usermodel.Sheet sIn = wbIn.getSheetAt(0);
                org.apache.poi.ss.usermodel.Sheet sOut = wbOut.createSheet(sIn.getSheetName());
                Iterator<org.apache.poi.ss.usermodel.Row> rowIt = sIn.rowIterator();
                while (rowIt.hasNext()) {
                    org.apache.poi.ss.usermodel.Row rowIn = rowIt.next();
                    org.apache.poi.ss.usermodel.Row rowOut = sOut.createRow(rowIn.getRowNum());

                    Iterator<org.apache.poi.ss.usermodel.Cell> cellIt = rowIn.cellIterator();
                    while (cellIt.hasNext()) {
                        org.apache.poi.ss.usermodel.Cell cellIn = cellIt.next();
                        org.apache.poi.ss.usermodel.Cell cellOut = rowOut.createCell(cellIn.getColumnIndex(), cellIn.getCellType());

                        switch (cellIn.getCellType()) {
                            case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK:
                                break;

                            case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
                                cellOut.setCellValue(cellIn.getBooleanCellValue());
                                break;

                            case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR:
                                cellOut.setCellValue(cellIn.getErrorCellValue());
                                break;

                            case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA:
                                cellOut.setCellFormula(cellIn.getCellFormula());
                                break;

                            case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
                                cellOut.setCellValue(cellIn.getNumericCellValue());
                                break;

                            case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
                                cellOut.setCellValue(cellIn.getStringCellValue());
                                break;
                        }

                        {
                            CellStyle styleIn = cellIn.getCellStyle();
                            CellStyle styleOut = cellOut.getCellStyle();
                            styleOut.setDataFormat(styleIn.getDataFormat());
                        }
                        cellOut.setCellComment(cellIn.getCellComment());

                    }
                }
            }

            try (OutputStream out = new BufferedOutputStream(new FileOutputStream(fileOut))) {
                wbOut.write(out);

                return out;
            }
        } finally {
            fileIn.close();
        }
    }

}

class JxlItem {

    private String beanName;
    private String attribute;

    public JxlItem(String beanName, String attribute) {

        this.attribute = attribute;
        this.beanName = beanName;

    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
