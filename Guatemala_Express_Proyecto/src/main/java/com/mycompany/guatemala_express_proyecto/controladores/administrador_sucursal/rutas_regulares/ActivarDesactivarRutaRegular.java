/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.mycompany.guatemala_express_proyecto.controladores.administrador_sucursal.rutas_regulares;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.AdministradorSucursal;
import com.mycompany.guatemala_express_proyecto.modelos.RutaRegular;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.servicios.administrador_sucursal.AdministradorSucursalServicio;
import com.mycompany.guatemala_express_proyecto.servicios.ruta_regular.RutaRegularServicio;

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
@WebServlet(name = "ActivarDesactivarRutaRegular", urlPatterns = {
        "/administrador_sucursal/activar_desactivar_ruta_regular" })
public class ActivarDesactivarRutaRegular extends HttpServlet {

    private final RutaRegularServicio rutaRegularServicio = new RutaRegularServicio();
    private final AdministradorSucursalServicio administradorSucursalServicio = new AdministradorSucursalServicio();

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

        String nombreUsuario = (String) session.getAttribute("nombreUsuario");

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
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);

            if (administradorOptional.isEmpty()) {
                request.setAttribute("tituloModal", "Error");
                request.setAttribute("mensajeModal", "No se encontró la sucursal asignada al administrador.");
            } else {
                Sucursal sucursal = administradorOptional.get().getSucursal();
                request.setAttribute("sucursal", sucursal);
                request.setAttribute("rutasRegulares", rutaRegularServicio.obtenerRutasPorSucursal(sucursal.getId()));
            }

        } catch (SQLException e) {
            request.setAttribute("tituloModal", "Error al obtener las rutas regulares");
            request.setAttribute("mensajeModal", "No fue posible obtener la lista de rutas regulares.");
        }

        request.getRequestDispatcher(
                "/WEB-INF/views/administrador_sucursal/rutas_regulares/activar-desactivar-ruta.jsp")
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
        String nombreUsuario = (String) session.getAttribute("nombreUsuario");
        String idRutaParametro = request.getParameter("idRuta");
        String nuevoEstadoParametro = request.getParameter("nuevoEstado");

        if (nombreUsuario == null || nombreUsuario.isBlank() || idRutaParametro == null || idRutaParametro.isBlank()
                || nuevoEstadoParametro == null || nuevoEstadoParametro.isBlank()) {
            session.setAttribute("tituloModal", "Error al cambiar el estado");
            session.setAttribute("mensajeModal", "Faltan datos para cambiar el estado de la ruta regular.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/activar_desactivar_ruta_regular");
            return;
        }

        int idRuta;

        try {
            idRuta = Integer.parseInt(idRutaParametro);
        } catch (NumberFormatException e) {
            session.setAttribute("tituloModal", "Error al cambiar el estado");
            session.setAttribute("mensajeModal", "El identificador de la ruta regular no es válido.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/activar_desactivar_ruta_regular");
            return;
        }

        try {
            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(
                            nombreUsuario);

            Optional<RutaRegular> rutaOptional = rutaRegularServicio.obtenerRutaPorId(idRuta);

            if (administradorOptional.isEmpty() || rutaOptional.isEmpty()) {
                session.setAttribute("tituloModal", "Error al cambiar el estado");
                session.setAttribute("mensajeModal", "No se encontró la ruta regular o la sucursal asignada.");
                response.sendRedirect(
                        request.getContextPath() + "/administrador_sucursal/activar_desactivar_ruta_regular");

                return;
            }

            int idSucursalAdministrador = administradorOptional.get().getSucursal().getId();
            int idSucursalOrigen = rutaOptional.get().getSucursalOrigen().getId();

            if (idSucursalOrigen != idSucursalAdministrador) {
                session.setAttribute("tituloModal", "Error al cambiar el estado");
                session.setAttribute("mensajeModal", "La ruta regular no pertenece a su sucursal.");
                response.sendRedirect(
                        request.getContextPath() + "/administrador_sucursal/activar_desactivar_ruta_regular");

                return;
            }

            boolean nuevoEstado = Boolean.parseBoolean(nuevoEstadoParametro);
            rutaRegularServicio.actualizarEstado(idRuta, nuevoEstado);
            session.setAttribute("tituloModal", "Cambio de estado exitoso");
            session.setAttribute("mensajeModal", "El estado de la ruta regular fue actualizado correctamente.");

        } catch (DatosIncompletosException | NoGuardadoEnBDException | SQLException e) {
            session.setAttribute("tituloModal", "Error al cambiar el estado");
            session.setAttribute("mensajeModal", e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/administrador_sucursal/activar_desactivar_ruta_regular");
    }

}
