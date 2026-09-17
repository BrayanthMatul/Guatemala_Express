/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.chofer;

import com.mycompany.guatemala_express_proyecto.modelos.Chofer;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author matul
 */
public class DatosFlashChoferServicio {

    public void guardarDatosFlash(HttpServletRequest request, Chofer chofer) {
        HttpSession session = request.getSession();
        Usuario usuario = chofer.getUsuario();

        if (usuario != null) {
            session.setAttribute("nombreUsuarioFlash", usuario.getNombreUsuario());
            session.setAttribute("nitFlash", usuario.getNit());
            session.setAttribute("dpiFlash", usuario.getDpi());
            session.setAttribute("nombreCompletoFlash", usuario.getNombreCompleto());
            session.setAttribute("telefonoFlash", usuario.getTelefono());
            session.setAttribute("direccionFlash", usuario.getDireccion());
            session.setAttribute("correoElectronicoFlash", usuario.getCorreoElectronico());
        }

        session.setAttribute("numeroLicenciaFlash", chofer.getNumeroLicencia());
        session.setAttribute("tipoLicenciaFlash", chofer.getTipoLicencia());
        session.setAttribute("fechaVencimientoLicenciaFlash", chofer.getFechaVencimientoLicencia());
        session.setAttribute("salarioBasePorViajeFlash", chofer.getSalarioBasePorViaje());
        session.setAttribute("sucursal", chofer.getSucursal());
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
        moverARequest(request, session, "numeroLicenciaFlash", "numeroLicencia");
        moverARequest(request, session, "tipoLicenciaFlash", "tipoLicencia");
        moverARequest(request, session, "fechaVencimientoLicenciaFlash", "fechaVencimientoLicencia");
        moverARequest(request, session, "salarioBasePorViajeFlash", "salarioBasePorViaje");
        moverARequest(request, session, "sucursal", "sucursal");
    }

    private void moverARequest(HttpServletRequest request, HttpSession session, String nombreSession,
            String nombreRequest) {

        Object datoFlash = session.getAttribute(nombreSession);

        if (datoFlash != null) {
            request.setAttribute(nombreRequest, datoFlash);
            session.removeAttribute(nombreSession);
        }
    }

}
