/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.modelos;

/**
 *
 * @author matul
 */
public class AdministradorSucursal {
    private Usuario usuario;
    private Sucursal sucursal;

    public AdministradorSucursal() {
    }

    public AdministradorSucursal(Usuario usuario, Sucursal sucursal) {
        this.usuario = usuario;
        this.sucursal = sucursal;
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

}
