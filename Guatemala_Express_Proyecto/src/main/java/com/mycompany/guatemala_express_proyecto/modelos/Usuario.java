/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.modelos;

import java.math.BigDecimal;

import com.mycompany.guatemala_express_proyecto.enums.Rol;

/**
 *
 * @author matul
 */
public class Usuario {

    private int id;
    private String nit;
    private String dpi;
    private String nombreCompleto;
    private String telefono;
    private String direccion;
    private String correoElectronico;
    private String contrasenia;
    private Rol rol;
    private BigDecimal saldo;
    private boolean estado;

    public Usuario() {
    }

    public Usuario(int id, String nit, String dpi, String nombreCompleto, String telefono, String direccion,
            String correoElectronico, String contrasenia, Rol rol, BigDecimal saldo, boolean estado) {
        this.id = id;
        this.nit = nit;
        this.dpi = dpi;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.saldo = saldo;
        this.estado = estado;
    }

    public Usuario(String nit, String dpi, String nombreCompleto, String telefono, String direccion,
            String correoElectronico, String contrasenia, Rol rol) {
        this.nit = nit;
        this.dpi = dpi;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
