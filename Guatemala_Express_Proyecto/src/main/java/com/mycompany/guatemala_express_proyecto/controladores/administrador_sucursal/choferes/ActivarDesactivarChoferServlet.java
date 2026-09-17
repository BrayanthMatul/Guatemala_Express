/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.mycompany.guatemala_express_proyecto.controladores.administrador_sucursal.choferes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.AdministradorSucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Chofer;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.servicios.administrador_sucursal.AdministradorSucursalServicio;
import com.mycompany.guatemala_express_proyecto.servicios.chofer.ChoferServicio;
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
@WebServlet(name = "ActivarDesactivarChoferServlet", urlPatterns = {
        "/administrador_sucursal/activar_desactivar_chofer" })
public class ActivarDesactivarChoferServlet extends HttpServlet {

    private final ChoferServicio choferServicio = new ChoferServicio();
    private final AdministradorSucursalServicio administradorSucursalServicio = new AdministradorSucursalServicio();
    private final UsuarioServicio usuarioServicio = new UsuarioServicio();

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
        String nombreUsuarioAdministrador = (String) session.getAttribute("nombreUsuario");

        if (tituloModal != null) {
            request.setAttribute("tituloModal", tituloModal);
            session.removeAttribute("tituloModal");
        }

        if (mensajeModal != null) {
            request.setAttribute("mensajeModal", mensajeModal);
            session.removeAttribute("mensajeModal");
        }

        try {
            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuarioAdministrador);

            if (administradorOptional.isEmpty()) {
                request.setAttribute("tituloModal", "Error");
                request.setAttribute("mensajeModal",
                        "No se encontró la sucursal asignada al administrador.");

            } else {
                Sucursal sucursal = administradorOptional.get().getSucursal();
                request.setAttribute("sucursal", sucursal);
                request.setAttribute("choferes",
                        choferServicio.obtenerChoferesPorSucursal(sucursal.getId()));

            }

        } catch (SQLException e) {
            request.setAttribute("tituloModal", "Error al obtener los choferes");
            request.setAttribute("mensajeModal", "No fue posible obtener la lista de choferes.");
        }

        request.getRequestDispatcher(
                "/WEB-INF/views/administrador_sucursal/choferes/activar-desactivar-chofer.jsp")
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
        String nombreUsuarioAdministrador = (String) session.getAttribute("nombreUsuario");
        String nombreUsuarioChofer = request.getParameter("nombreUsuario");
        String nuevoEstadoParametro = request.getParameter("nuevoEstado");

        try {

            if (nombreUsuarioAdministrador == null || nombreUsuarioAdministrador.isBlank()
                    || nombreUsuarioChofer == null || nombreUsuarioChofer.isBlank() || nuevoEstadoParametro == null
                    || nuevoEstadoParametro.isBlank()) {
                session.setAttribute("tituloModal", "Error al cambiar el estado");
                session.setAttribute("mensajeModal", "Faltan datos para cambiar el estado del chofer.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/activar_desactivar_chofer");
                return;
            }

            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuarioAdministrador);
            Optional<Chofer> choferOptional = choferServicio
                    .obtenerChoferPorNombreUsuario(nombreUsuarioChofer);

            if (administradorOptional.isEmpty() || choferOptional.isEmpty()) {
                session.setAttribute("tituloModal", "Error al cambiar el estado");
                session.setAttribute("mensajeModal",
                        "No se encontró el chofer o la sucursal asignada al administrador.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/activar_desactivar_chofer");
                return;
            }

            int idSucursalAdministrador = administradorOptional.get().getSucursal().getId();

            int idSucursalChofer = choferOptional.get().getSucursal().getId();

            if (idSucursalChofer != idSucursalAdministrador) {
                session.setAttribute("tituloModal", "Error al cambiar el estado");
                session.setAttribute("mensajeModal", "El chofer no pertenece a su sucursal.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/activar_desactivar_chofer");
                return;
            }

            boolean nuevoEstado = Boolean.parseBoolean(nuevoEstadoParametro);
            boolean actualizado = usuarioServicio.cambiarEstadoUsuario(nombreUsuarioChofer, nuevoEstado);

            if (!actualizado) {
                session.setAttribute("tituloModal", "Error al cambiar el estado");
                session.setAttribute("mensajeModal", "No fue posible actualizar el estado del chofer.");
            } else {
                session.setAttribute("tituloModal", "Cambio de estado exitoso");
                session.setAttribute("mensajeModal", "El estado del chofer fue actualizado correctamente.");
            }

        } catch (SQLException e) {
            session.setAttribute("tituloModal", "Error al cambiar el estado");
            session.setAttribute("mensajeModal", e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/administrador_sucursal/activar_desactivar_chofer");
    }

}
