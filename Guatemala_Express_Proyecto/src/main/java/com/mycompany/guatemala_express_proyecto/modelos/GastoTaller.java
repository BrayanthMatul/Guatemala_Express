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
public class GastoTaller {

    private int id;
    private Bus bus;
    private BigDecimal montoManoDeObra;
    private BigDecimal montoRepuestos;
    private LocalDate fechaMantenimiento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public BigDecimal getMontoManoDeObra() {
        return montoManoDeObra;
    }

    public void setMontoManoDeObra(BigDecimal montoManoDeObra) {
        this.montoManoDeObra = montoManoDeObra;
    }

    public BigDecimal getMontoRepuestos() {
        return montoRepuestos;
    }

    public void setMontoRepuestos(BigDecimal montoRepuestos) {
        this.montoRepuestos = montoRepuestos;
    }

    public LocalDate getFechaMantenimiento() {
        return fechaMantenimiento;
    }

    public void setFechaMantenimiento(LocalDate fechaMantenimiento) {
        this.fechaMantenimiento = fechaMantenimiento;
    }

}
