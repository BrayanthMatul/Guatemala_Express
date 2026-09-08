package com.mycompany.guatemala_express_proyecto.modelos;

import java.util.ArrayList;

public class MenuItem {

    private String label;
    private String url;
    private ArrayList<MenuItem> subOpciones;

    public MenuItem(String label, String url) {
        this.label = label;
        this.url = url;
        this.subOpciones = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<MenuItem> getSubOpciones() {
        return subOpciones;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void agregarSubOpcion(String label, String url) {
        this.subOpciones.add(new MenuItem(label, url));
    }

}
