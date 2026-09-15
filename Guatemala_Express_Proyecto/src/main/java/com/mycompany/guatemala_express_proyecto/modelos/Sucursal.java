/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.modelos;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author matul
 */
public class Sucursal {

    private int id;
    private String nombre;
    private String departamento;
    private String municipio;
    private BigDecimal longitud;
    private BigDecimal latitud;
    private String telefono;
    private LocalDate fechaApertura;

    public Sucursal() {
    }

    public Sucursal(int id, String nombre, String departamento,
            String municipio, BigDecimal longitud, BigDecimal latitud,
            String telefono, LocalDate fechaApertura) {

        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.municipio = municipio;
        this.longitud = longitud;
        this.latitud = latitud;
        this.telefono = telefono;
        this.fechaApertura = fechaApertura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

}
