/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.sucursales;

import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author matul
 */
public class DatosFlashSucursalServicio {

    public void guardarDatosFlash(
            HttpServletRequest request, Sucursal sucursal) {

        HttpSession session = request.getSession();
        session.setAttribute("sucursalFlash", sucursal);
    }

    public void colocarDatosFlash(
            HttpServletRequest request, HttpSession session) {

        Object mensajeFlash = session.getAttribute("mensajeFlash");

        if (mensajeFlash != null) {
            request.setAttribute("mensaje", mensajeFlash);
            session.removeAttribute("mensajeFlash");
        }

        Sucursal sucursal = (Sucursal) session.getAttribute("sucursalFlash");

        if (sucursal != null) {
            request.setAttribute("id", sucursal.getId());
            request.setAttribute("nombre", sucursal.getNombre());
            request.setAttribute("departamento", sucursal.getDepartamento());
            request.setAttribute("municipio", sucursal.getMunicipio());
            request.setAttribute("longitud", sucursal.getLongitud());
            request.setAttribute("latitud", sucursal.getLatitud());
            request.setAttribute("telefono", sucursal.getTelefono());
            request.setAttribute("fechaApertura", sucursal.getFechaApertura());

            session.removeAttribute("sucursalFlash");
        }
    }

}
