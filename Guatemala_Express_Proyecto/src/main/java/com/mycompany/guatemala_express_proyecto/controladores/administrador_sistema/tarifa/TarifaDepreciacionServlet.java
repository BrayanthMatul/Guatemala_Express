/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.mycompany.guatemala_express_proyecto.controladores.administrador_sistema.tarifa;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.Configuracion;
import com.mycompany.guatemala_express_proyecto.servicios.tarifa_depreciacion.TarifaDepreciacionServicio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author matul
 */
@WebServlet(name = "TarifaDepreciacionServlet", urlPatterns = { "/administrador_sistema/tarifa_depreciacion" })
public class TarifaDepreciacionServlet extends HttpServlet {

    private final TarifaDepreciacionServicio tarifaDepreciacionServicio = new TarifaDepreciacionServicio();

    /**
     * Handles the HTTP <code>GET</code> method.
     * 
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Object tituloModal = session.getAttribute("tituloModal");
        Object mensajeModal = session.getAttribute("mensajeModal");

        if (tituloModal != null) {
            request.setAttribute("tituloModal", tituloModal);
            session.removeAttribute("tituloModal");
        }

        if (mensajeModal != null) {
            request.setAttribute("mensajeModal", mensajeModal);
            session.removeAttribute("mensajeModal");
        }

        try {
            Optional<Configuracion> configuracionOptional = tarifaDepreciacionServicio.obtenerTarifaDepreciacion();

            if (configuracionOptional.isPresent()) {
                request.setAttribute("tarifaDepreciacion", configuracionOptional.get());
            } else {
                request.setAttribute("tituloModal", "Error al obtener la tarifa");
                request.setAttribute("mensajeModal", "No se encontró la configuración de la tarifa de depreciación.");
            }

        } catch (SQLException e) {
            request.setAttribute("tituloModal", "Error al obtener la tarifa");
            request.setAttribute("mensajeModal", "No fue posible obtener la tarifa de depreciación.");
        }

        request.getRequestDispatcher("/WEB-INF/views/administrador_sistema/tarifa/tarifa-depreciacion.jsp")
                .forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * 
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Configuracion configuracion = new Configuracion();
        configuracion.setValor(convertirDecimal(request.getParameter("valor")));

        try {
            tarifaDepreciacionServicio.actualizarTarifaDepreciacion(configuracion);
            session.setAttribute("tituloModal", "Actualización exitosa");
            session.setAttribute("mensajeModal", "La tarifa de depreciación fue actualizada correctamente.");
        } catch (DatosIncompletosException | NoGuardadoEnBDException | SQLException e) {
            session.setAttribute("tituloModal", "Error al actualizar la tarifa");
            session.setAttribute("mensajeModal", e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/administrador_sistema/tarifa_depreciacion");
    }

    private BigDecimal convertirDecimal(String valor) {

        if (valor == null || valor.isBlank()) {
            return null;
        }

        try {
            return new BigDecimal(valor);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
