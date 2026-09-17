/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.buses;

import com.mycompany.guatemala_express_proyecto.modelos.Bus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author matul
 */
public class DatosFlashBusServicio {

    public void guardarDatosFlash(HttpServletRequest request, Bus bus) {
        HttpSession session = request.getSession();
        session.setAttribute("sucursal", bus.getSucursal());
        session.setAttribute("numeroPlacaFlash", bus.getNumeroPlaca());
        session.setAttribute("marcaFlash", bus.getMarca());
        session.setAttribute("modeloFlash", bus.getModelo());
        session.setAttribute("anioFabricacionFlash", bus.getAnioFabricacion());
        session.setAttribute("capacidadPasajerosFlash", bus.getCapacidadPasajeros());
        session.setAttribute("kilometrajeActualFlash", bus.getKilometrajeActual());
    }

    public void colocarDatosFlash(HttpServletRequest request, HttpSession session) {
        moverARequest(request, session, "sucursal", "sucursal");
        moverARequest(request, session, "mensajeFlash", "mensaje");
        moverARequest(request, session, "numeroPlacaFlash", "numeroPlaca");
        moverARequest(request, session, "marcaFlash", "marca");
        moverARequest(request, session, "modeloFlash", "modelo");
        moverARequest(request, session, "anioFabricacionFlash", "anioFabricacion");
        moverARequest(request, session, "capacidadPasajerosFlash", "capacidadPasajeros");
        moverARequest(request, session, "kilometrajeActualFlash", "kilometrajeActual");
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
