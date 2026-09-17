/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.ruta_regular;

import com.mycompany.guatemala_express_proyecto.modelos.RutaRegular;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author matul
 */
public class DatosFlashRutaRegularServicio {

    public void guardarDatosFlash(
            HttpServletRequest request,
            RutaRegular rutaRegular) {

        HttpSession session = request.getSession();

        if (rutaRegular.getSucursalDestino() != null) {
            session.setAttribute(
                    "idSucursalDestinoFlash",
                    rutaRegular.getSucursalDestino().getId());
        }

        session.setAttribute(
                "distanciaAproximadaKmFlash",
                rutaRegular.getDistanciaAproximadaKm());

        session.setAttribute(
                "precioBoletoFlash",
                rutaRegular.getPrecioBoleto());

        session.setAttribute(
                "duracionEstimadaFlash",
                rutaRegular.getDuracionEstimada());
    }

    public void colocarDatosFlash(
            HttpServletRequest request,
            HttpSession session) {

        moverARequest(
                request,
                session,
                "mensajeFlash",
                "mensaje");

        moverARequest(
                request,
                session,
                "idSucursalDestinoFlash",
                "idSucursalDestino");

        moverARequest(
                request,
                session,
                "distanciaAproximadaKmFlash",
                "distanciaAproximadaKm");

        moverARequest(
                request,
                session,
                "precioBoletoFlash",
                "precioBoleto");

        moverARequest(
                request,
                session,
                "duracionEstimadaFlash",
                "duracionEstimada");
    }

    private void moverARequest(
            HttpServletRequest request,
            HttpSession session,
            String nombreSession,
            String nombreRequest) {

        Object datoFlash = session.getAttribute(nombreSession);

        if (datoFlash != null) {
            request.setAttribute(
                    nombreRequest,
                    datoFlash);

            session.removeAttribute(nombreSession);
        }
    }

}
