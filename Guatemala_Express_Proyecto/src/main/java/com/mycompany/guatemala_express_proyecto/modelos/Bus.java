/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.modelos;

import java.math.BigDecimal;

import com.mycompany.guatemala_express_proyecto.enums.EstadoOperativo;

/**
 *
 * @author matul
 */
public class Bus {

    private String numeroPlaca;
    private Sucursal sucursal;
    private String marca;
    private String modelo;
    private int anioFabricacion;
    private int capacidadPasajeros;
    private BigDecimal kilometrajeActual;
    private boolean estado;
    private byte[] fotografia;
    private EstadoOperativo estadoOperativo;
    private String fotografiaBase64;

    public Bus() {
    }

    public Bus(String numeroPlaca, Sucursal sucursal, String marca, String modelo, int anioFabricacion,
            int capacidadPasajeros, BigDecimal kilometrajeActual, boolean estado, byte[] fotografia,
            EstadoOperativo estadoOperativo) {
        this.numeroPlaca = numeroPlaca;
        this.sucursal = sucursal;
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.capacidadPasajeros = capacidadPasajeros;
        this.kilometrajeActual = kilometrajeActual;
        this.estado = estado;
        this.fotografia = fotografia;
        this.estadoOperativo = estadoOperativo;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public BigDecimal getKilometrajeActual() {
        return kilometrajeActual;
    }

    public void setKilometrajeActual(BigDecimal kilometrajeActual) {
        this.kilometrajeActual = kilometrajeActual;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

    public EstadoOperativo getEstadoOperativo() {
        return estadoOperativo;
    }

    public void setEstadoOperativo(EstadoOperativo estadoOperativo) {
        this.estadoOperativo = estadoOperativo;
    }

    public String getFotografiaBase64() {
        return fotografiaBase64;
    }

    public void setFotografiaBase64(String fotografiaBase64) {
        this.fotografiaBase64 = fotografiaBase64;
    }

}
