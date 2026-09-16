/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.administrador_sucursal.buses;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.modelos.AdministradorSucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.servicios.administrador_sucursal.AdministradorSucursalServicio;
import com.mycompany.guatemala_express_proyecto.servicios.buses.BusServicio;

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
@WebServlet(name = "ListarBusesServlet", urlPatterns = { "/administrador_sucursal/listar_buses" })
public class ListarBusesServlet extends HttpServlet {

        private final BusServicio busServicio = new BusServicio();
        private final AdministradorSucursalServicio administradorSucursalServicio = new AdministradorSucursalServicio();

        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
        // + sign on the left to edit the code.">
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

                if (session != null) {
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

                        String nombreUsuario = (String) session.getAttribute(
                                        "nombreUsuario");

                        try {

                                Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                                                .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);

                                if (administradorOptional.isPresent()) {

                                        Sucursal sucursal = administradorOptional.get().getSucursal();
                                        request.setAttribute("sucursal", sucursal);
                                        request.setAttribute("buses",
                                                        busServicio.obtenerBusesPorSucursal(sucursal.getId()));
                                } else {
                                        request.setAttribute("tituloModal", "Error");
                                        request.setAttribute("mensajeModal",
                                                        "No se encontró la sucursal asignada al administrador.");
                                }

                        } catch (SQLException e) {
                                request.setAttribute("tituloModal", "Error al obtener los buses");
                                request.setAttribute("mensajeModal",
                                                "No fue posible obtener la lista de buses de la sucursal.");
                        }
                }

                request.getRequestDispatcher(
                                "/WEB-INF/views/administrador_sucursal/buses/listar-buses.jsp")
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
        }

}
