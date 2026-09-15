/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.administrador_sistema.sucursales;

import java.io.IOException;
import java.sql.SQLException;

import com.mycompany.guatemala_express_proyecto.servicios.sucursales.SucursalServicio;

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
@WebServlet(name = "ListaSucursalesServlet", urlPatterns = { "/administrador_sistema/lista_sucursales" })
public class ListaSucursalesServlet extends HttpServlet {

        private final SucursalServicio sucursalServicio = new SucursalServicio();

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
                }

                try {
                        request.setAttribute(
                                        "sucursales",
                                        sucursalServicio.obtenerSucursales());

                } catch (SQLException e) {
                        request.setAttribute(
                                        "tituloModal",
                                        "Error al obtener las sucursales");

                        request.setAttribute(
                                        "mensajeModal",
                                        "Ocurrió un error al obtener la lista de sucursales. "
                                                        + "Por favor, inténtelo de nuevo más tarde.");
                }

                request.getRequestDispatcher(
                                "/WEB-INF/views/administrador_sistema/sucursales/lista-sucursales.jsp")
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
