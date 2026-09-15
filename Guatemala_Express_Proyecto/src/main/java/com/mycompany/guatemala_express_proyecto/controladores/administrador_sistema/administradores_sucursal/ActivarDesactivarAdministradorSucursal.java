/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.administrador_sistema.administradores_sucursal;

import java.io.IOException;
import java.sql.SQLException;

import com.mycompany.guatemala_express_proyecto.servicios.administrador_sucursal.AdministradorSucursalServicio;
import com.mycompany.guatemala_express_proyecto.servicios.usuarios.UsuarioServicio;

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
@WebServlet(name = "ActivarDesactivarAdministradorSucursal", urlPatterns = {
                "/administrador_sistema/activar_desactivar_administrador_sucursal" })
public class ActivarDesactivarAdministradorSucursal extends HttpServlet {

        private final AdministradorSucursalServicio administradorSucursalServicio = new AdministradorSucursalServicio();
        private final UsuarioServicio usuarioServicio = new UsuarioServicio();

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
                        request.setAttribute("administradoresSucursal",
                                        administradorSucursalServicio.obtenerAdministradoresSucursal());

                } catch (SQLException e) {
                        request.setAttribute("tituloModal", "Error al obtener administradores de sucursal");
                        request.setAttribute("mensajeModal",
                                        "Ocurrió un error al obtener la lista de administradores de sucursal. Por favor, inténtelo de nuevo más tarde.");
                }

                request.getRequestDispatcher(
                                "/WEB-INF/views/administrador_sistema/administradores_sucursal/activar-desactivar-administradores-sucursal.jsp")
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
                String nombreUsuario = request.getParameter("nombreUsuario");
                boolean nuevoEstado = Boolean.parseBoolean(request.getParameter("nuevoEstado"));

                HttpSession session = request.getSession();

                try {
                        boolean exito = usuarioServicio.cambiarEstadoUsuario(nombreUsuario, nuevoEstado);

                        if (exito) {
                                session.setAttribute("tituloModal", "Cambio de estado exitoso");
                                session.setAttribute("mensajeModal",
                                                "El estado del administrador de sucursal ha sido actualizado correctamente.");

                        } else {
                                session.setAttribute("tituloModal", "Error");

                                session.setAttribute("mensajeModal",
                                                "No fue posible actualizar el estado el administrador de sucursal.");
                        }

                } catch (SQLException e) {
                        session.setAttribute("tituloModal", "Error al cambiar el estado");
                        session.setAttribute("mensajeModal",
                                        "Ocurrió un error al actualizar el estado del administrador de sucursal. Inténtelo nuevamente.");
                }

                response.sendRedirect(request.getContextPath()
                                + "/administrador_sistema/activar_desactivar_administrador_sucursal");
        }

}
