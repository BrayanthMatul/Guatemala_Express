/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.modelos;

import java.math.BigDecimal;
import java.time.LocalTime;

/**
 *
 * @author matul
 */
public class RutaRegular {
    private int id;
    private Sucursal sucursalOrigen;
    private Sucursal sucursalDestino;
    private BigDecimal distanciaAproximadaKm;
    private BigDecimal precioBoleto;
    private LocalTime duracionEstimada;
    private boolean estado;

    public RutaRegular() {
    }

    public RutaRegular(int id, Sucursal sucursalOrigen, Sucursal sucursalDestino, BigDecimal distanciaAproximadaKm,
            BigDecimal precioBoleto, LocalTime duracionEstimada, boolean estado) {
        this.id = id;
        this.sucursalOrigen = sucursalOrigen;
        this.sucursalDestino = sucursalDestino;
        this.distanciaAproximadaKm = distanciaAproximadaKm;
        this.precioBoleto = precioBoleto;
        this.duracionEstimada = duracionEstimada;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sucursal getSucursalOrigen() {
        return sucursalOrigen;
    }

    public void setSucursalOrigen(Sucursal sucursalOrigen) {
        this.sucursalOrigen = sucursalOrigen;
    }

    public Sucursal getSucursalDestino() {
        return sucursalDestino;
    }

    public void setSucursalDestino(Sucursal sucursalDestino) {
        this.sucursalDestino = sucursalDestino;
    }

    public BigDecimal getDistanciaAproximadaKm() {
        return distanciaAproximadaKm;
    }

    public void setDistanciaAproximadaKm(
            BigDecimal distanciaAproximadaKm) {

        this.distanciaAproximadaKm = distanciaAproximadaKm;
    }

    public BigDecimal getPrecioBoleto() {
        return precioBoleto;
    }

    public void setPrecioBoleto(BigDecimal precioBoleto) {
        this.precioBoleto = precioBoleto;
    }

    public LocalTime getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(
            LocalTime duracionEstimada) {

        this.duracionEstimada = duracionEstimada;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
