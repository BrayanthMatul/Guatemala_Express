/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.administrador_sucursal;

import com.mycompany.guatemala_express_proyecto.modelos.AdministradorSucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author matul
 */
public class DatosFlashAdministradorSucursalServicio {

    public void guardarDatosFlash(HttpServletRequest request, AdministradorSucursal administradorSucursal) {

        if (administradorSucursal == null) {
            return;
        }

        HttpSession session = request.getSession();

        Usuario usuario = administradorSucursal.getUsuario();
        Sucursal sucursal = administradorSucursal.getSucursal();

        if (usuario != null) {
            session.setAttribute("nombreUsuarioFlash", usuario.getNombreUsuario());
            session.setAttribute("nitFlash", usuario.getNit());
            session.setAttribute("dpiFlash", usuario.getDpi());
            session.setAttribute("nombreCompletoFlash", usuario.getNombreCompleto());
            session.setAttribute("telefonoFlash", usuario.getTelefono());
            session.setAttribute("direccionFlash", usuario.getDireccion());
            session.setAttribute("correoElectronicoFlash", usuario.getCorreoElectronico());
        }

        if (sucursal != null) {
            session.setAttribute("sucursalIdFlash", sucursal.getId());
        }
    }

    public void colocarDatosFlash(HttpServletRequest request, HttpSession session) {
        moverARequest(request, session, "mensajeFlash", "mensaje");
        moverARequest(request, session, "nombreUsuarioFlash", "nombreUsuario");
        moverARequest(request, session, "nitFlash", "nit");
        moverARequest(request, session, "dpiFlash", "dpi");
        moverARequest(request, session, "nombreCompletoFlash", "nombreCompleto");
        moverARequest(request, session, "telefonoFlash", "telefono");
        moverARequest(request, session, "direccionFlash", "direccion");
        moverARequest(request, session, "correoElectronicoFlash", "correoElectronico");
        moverARequest(request, session, "sucursalIdFlash", "sucursalId");
    }

    private void moverARequest(HttpServletRequest request, HttpSession session, String nombreSession,
            String nombreRequest) {

        Object valor = session.getAttribute(nombreSession);

        if (valor != null) {
            request.setAttribute(nombreRequest, valor);
            session.removeAttribute(nombreSession);
        }
    }

}
