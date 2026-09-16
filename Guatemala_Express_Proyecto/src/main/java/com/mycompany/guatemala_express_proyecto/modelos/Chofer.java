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
public class Chofer {

    private Usuario usuario;
    private Sucursal sucursal;
    private byte[] fotografia;
    private String fotografiaBase64;
    private String numeroLicencia;
    private String tipoLicencia;
    private LocalDate fechaVencimientoLicencia;
    private BigDecimal salarioBasePorViaje;

    public Chofer() {
    }

    public Chofer(Usuario usuario, Sucursal sucursal, byte[] fotografia, String numeroLicencia, String tipoLicencia,
            LocalDate fechaVencimientoLicencia, BigDecimal salarioBasePorViaje) {
        this.usuario = usuario;
        this.sucursal = sucursal;
        this.fotografia = fotografia;
        this.numeroLicencia = numeroLicencia;
        this.tipoLicencia = tipoLicencia;
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
        this.salarioBasePorViaje = salarioBasePorViaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

    public String getFotografiaBase64() {
        return fotografiaBase64;
    }

    public void setFotografiaBase64(String fotografiaBase64) {
        this.fotografiaBase64 = fotografiaBase64;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public LocalDate getFechaVencimientoLicencia() {
        return fechaVencimientoLicencia;
    }

    public void setFechaVencimientoLicencia(
            LocalDate fechaVencimientoLicencia) {

        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
    }

    public BigDecimal getSalarioBasePorViaje() {
        return salarioBasePorViaje;
    }

    public void setSalarioBasePorViaje(
            BigDecimal salarioBasePorViaje) {

        this.salarioBasePorViaje = salarioBasePorViaje;
    }

}
