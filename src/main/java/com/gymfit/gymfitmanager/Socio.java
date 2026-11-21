package com.gymfit.gymfitmanager;

import javafx.beans.property.SimpleStringProperty;

public class Socio {
    private final SimpleStringProperty socio;
    private final SimpleStringProperty cuota;
    private final SimpleStringProperty fecha;
    private final SimpleStringProperty clases;

    public Socio(String socio, String cuota, String fecha, String clases){
        this.socio = new SimpleStringProperty(socio);
        this.cuota = new SimpleStringProperty(cuota);
        this.fecha = new SimpleStringProperty(fecha);
        this.clases = new SimpleStringProperty(clases);
    }

    public SimpleStringProperty socioProperty() { return socio; }
    public SimpleStringProperty cuotaProperty() { return cuota; }
    public SimpleStringProperty fechaProperty() { return fecha; }
    public SimpleStringProperty clasesProperty() { return clases; }

}
