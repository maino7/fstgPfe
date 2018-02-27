/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author HP
 */
@Named("mapController")
@SessionScoped
public class mapController implements Serializable {

    /**
     * Creates a new instance of mapController
     */
    private MapModel emptyModel;

    public mapController() {
    }

    public MapModel addModel() {
        getEmptyModel().addOverlay(new Marker(new LatLng(31.6445494, -8.020280299999968)));
        return emptyModel;
    }

    public MapModel getEmptyModel() {
        if (emptyModel == null) {
            emptyModel = new DefaultMapModel();
        }
        return emptyModel;
    }

    public void setEmptyModel(MapModel emptyModel) {
        this.emptyModel = emptyModel;
    }

}
