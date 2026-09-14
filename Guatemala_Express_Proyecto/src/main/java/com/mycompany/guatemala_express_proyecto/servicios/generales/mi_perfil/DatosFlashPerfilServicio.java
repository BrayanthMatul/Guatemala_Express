/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.generales.mi_perfil;

import com.mycompany.guatemala_express_proyecto.modelos.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author matul
 */
public class DatosFlashPerfilServicio {

    public void guardarDatosFlash(HttpServletRequest request, Usuario usuario) {
        HttpSession session = request.getSession();
        session.setAttribute("nombreUsuarioFlash", usuario.getNombreUsuario());
        session.setAttribute("nitFlash", usuario.getNit());
        session.setAttribute("dpiFlash", usuario.getDpi());
        session.setAttribute("nombreCompletoFlash", usuario.getNombreCompleto());
        session.setAttribute("telefonoFlash", usuario.getTelefono());
        session.setAttribute("direccionFlash", usuario.getDireccion());
        session.setAttribute("correoElectronicoFlash", usuario.getCorreoElectronico());
    }

    public void colocarDatosFlash(HttpServletRequest request, HttpSession session) {
        Object mensaje = session.getAttribute("mensajeFlash");
        Object nombreUsuario = session.getAttribute("nombreUsuarioFlash");
        Object nit = session.getAttribute("nitFlash");
        Object dpi = session.getAttribute("dpiFlash");
        Object nombreCompleto = session.getAttribute("nombreCompletoFlash");
        Object telefono = session.getAttribute("telefonoFlash");
        Object direccion = session.getAttribute("direccionFlash");
        Object correoElectronico = session.getAttribute("correoElectronicoFlash");

        if (mensaje != null) {
            request.setAttribute("mensaje", mensaje);
            session.removeAttribute("mensajeFlash");
        }

        if (nombreUsuario != null) {
            request.setAttribute("nombreUsuario", nombreUsuario);
            session.removeAttribute("nombreUsuarioFlash");
        }

        if (nit != null) {
            request.setAttribute("nit", nit);
            session.removeAttribute("nitFlash");
        }

        if (dpi != null) {
            request.setAttribute("dpi", dpi);
            session.removeAttribute("dpiFlash");
        }

        if (nombreCompleto != null) {
            request.setAttribute("nombreCompleto", nombreCompleto);
            session.removeAttribute("nombreCompletoFlash");
        }

        if (telefono != null) {
            request.setAttribute("telefono", telefono);
            session.removeAttribute("telefonoFlash");
        }

        if (direccion != null) {
            request.setAttribute("direccion", direccion);
            session.removeAttribute("direccionFlash");
        }

        if (correoElectronico != null) {
            request.setAttribute("correoElectronico", correoElectronico);
            session.removeAttribute("correoElectronicoFlash");
        }

    }

}
