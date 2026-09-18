/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.gasto_taller;

import com.mycompany.guatemala_express_proyecto.modelos.GastoTaller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author matul
 */
public class DatosFlashGastoTallerServicio {

    public void guardarDatosFlash(HttpServletRequest request, GastoTaller gastoTaller) {
        HttpSession session = request.getSession();

        if (gastoTaller.getBus() != null) {
            session.setAttribute("numeroPlacaFlash", gastoTaller.getBus().getNumeroPlaca());
        }

        session.setAttribute("montoManoDeObraFlash", gastoTaller.getMontoManoDeObra());
        session.setAttribute("montoRepuestosFlash", gastoTaller.getMontoRepuestos());
    }

    public void colocarDatosFlash(HttpServletRequest request, HttpSession session) {
        moverARequest(request, session, "mensajeFlash", "mensaje");
        moverARequest(request, session, "numeroPlacaFlash", "numeroPlaca");
        moverARequest(request, session, "montoManoDeObraFlash", "montoManoDeObra");
        moverARequest(request, session, "montoRepuestosFlash", "montoRepuestos");
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
