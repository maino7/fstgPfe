/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Annee;
import bean.Filiere;
import bean.Module;
import bean.NoteModulaire;
import bean.Semestre;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.ChartSeries;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import service.AnneeFacade;
import service.ExpressionBesoinFacade;
import service.FactureFacade;
import service.ModuleFacade;
import service.NoteModulaireFacade;
import service.SemestreFacade;

/**
 *
 * @author BENIHOUD Youssef
 */
@Named("chartController")
@SessionScoped
public class ChartController implements Serializable {

    private BarChartModel barModel;

    private boolean choice;
    private Filiere filiere;
    private Semestre semestre;
    private Module module;
    private int max;
    @EJB
    private NoteModulaireFacade noteModulaireFacade;
    @EJB
    private SemestreFacade semestreFacade;
    @EJB
    private ModuleFacade moduleFacade;
    @EJB
    private AnneeFacade anneeFacade;
    @EJB
    private FactureFacade factureFacade;
    @EJB
    private ExpressionBesoinFacade besoinFacade;

    @PostConstruct
    public void init() {
        createBarModels();
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public List<Semestre> getFindByFiliere() {
        if (filiere != null) {
            return semestreFacade.findByFiliere(filiere);
        }
        return new ArrayList<>();
    }

    public List<Module> getFindBySemestre() {
        System.out.println("choice == " + choice);
        if (semestre != null) {
            if (choice == false) {
                return moduleFacade.findBySemestre(semestre.getLibelle());
            }
        }
        return new ArrayList<>();
    }

    public boolean getChoice() {

        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
    }

    public Filiere getFiliere() {
        if (filiere == null) {
            filiere = new Filiere();
        }
        return filiere;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Semestre getSemestre() {
        if (semestre == null) {
            semestre = new Semestre();
        }
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public Module getModule() {
        if (module == null) {
            module = new Module();
        }
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    private BarChartModel initBarModel() {
        System.out.println("=== init Bar Model === ");
        BarChartModel model = new BarChartModel();
        List<Annee> annees = anneeFacade.findAll();

        List<NoteModulaire> nmV = new ArrayList<>();
        List<NoteModulaire> nmNV = new ArrayList<>();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Validé");
        for (Annee item : annees) {

            nmV = noteModulaireFacade.findbyChart(semestre, module, item, 1, choice);
            System.out.println("item.Libelle == " + item.getLibelle());
            System.out.println("nmV === " + nmV);
            if (nmV.isEmpty()) {
                boys.set(item.getLibelle(), 0);
            }
            boys.set(item.getLibelle(), nmV.size());
            if (max < nmV.size()) {
                max = nmV.size();
            }
        }

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Non Validé");

        for (Annee item : annees) {
            nmNV = noteModulaireFacade.findbyChart(semestre, module, item, 0, choice);
            System.out.println("item.Libelle == " + item.getLibelle());
            System.out.println("nmNV === " + nmNV);
            if (nmNV.isEmpty()) {
                girls.set(item.getLibelle(), 0);
            }
            girls.set(item.getLibelle(), nmNV.size());
            if (max <= nmNV.size()) {
                max = nmNV.size();
            }
        }

        model.addSeries(boys);
        model.addSeries(girls);
        model.setAnimate(true);
        model.setBarWidth(30);
        return model;
    }

    private BarChartModel initBarModel2() {
        BarChartModel model = new BarChartModel();
        List<Annee> annees = anneeFacade.findAll();
        ChartSeries factures = new ChartSeries();
        factures.setLabel("Fts");
        ChartSeries expressions = new ChartSeries();
        expressions.setLabel("Exp");

        annees.stream().forEach((a) -> {

            factures.set("" + a.getLibelle(), factureFacade.factureParAnnée(a.getLibelle()).size());
            System.out.println(factureFacade.factureParAnnée(a.getLibelle()).size());
            expressions.set("" + a.getLibelle(), besoinFacade.expressionParAnnée(a.getLibelle()).size());
            System.out.println(besoinFacade.expressionParAnnée(a.getLibelle()).size());
        });

        model.addSeries(factures);
        model.addSeries(expressions);
        model.setAnimate(true);
        model.setBarWidth(30);
        return model;
    }

    private void createBarModels() {
        createBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel2();
        barModel.setTitle("FACTURES AND EXPRESSIONS DE BESIONS");
//        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Années");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Nombres");
        yAxis.setMin(0);
        yAxis.setMax(6);

    }

    public Module getCorrectModule(Module module) {
        System.out.println("==== getCorrectModule ====");
        if (choice) {
            System.out.println("You have choosen Semestre ; if not then correct your methode");
            return null;
        }

        return module;
    }

    /**
     * Creates a new instance of ChartController
     */
    public ChartController() {
    }

}
