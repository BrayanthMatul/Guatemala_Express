/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.modelos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author matul
 */
public class Recarga {
    private int id;
    private String nombreUsuario;
    private LocalDateTime fechaHoraRecarga;
    private BigDecimal monto;

    public Recarga(String nombreUsuario, LocalDateTime fechaHoraRecarga, BigDecimal monto) {
        this.nombreUsuario = nombreUsuario;
        this.fechaHoraRecarga = fechaHoraRecarga;
        this.monto = monto;
    }

    public Recarga(int id, String nombreUsuario, LocalDateTime fechaHoraRecarga, BigDecimal monto) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.fechaHoraRecarga = fechaHoraRecarga;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public LocalDateTime getFechaHoraRecarga() {
        return fechaHoraRecarga;
    }

    public void setFechaHoraRecarga(LocalDateTime fechaHoraRecarga) {
        this.fechaHoraRecarga = fechaHoraRecarga;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

}
