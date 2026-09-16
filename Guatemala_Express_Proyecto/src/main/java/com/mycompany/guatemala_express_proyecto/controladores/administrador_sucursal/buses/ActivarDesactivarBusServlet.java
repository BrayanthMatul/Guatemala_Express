/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.administrador_sucursal.buses;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.AdministradorSucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Bus;
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
@WebServlet(name = "ActivarDesactivarBusServlet", urlPatterns = { "/administrador_sucursal/activar_desactivar_bus" })
public class ActivarDesactivarBusServlet extends HttpServlet {

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

                        String nombreUsuario = (String) session.getAttribute("nombreUsuario");

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

                request.getRequestDispatcher("/WEB-INF/views/administrador_sucursal/buses/activar-desactivar-bus.jsp")
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
                String numeroPlaca = request.getParameter("numeroPlaca");
                String nuevoEstadoParametro = request.getParameter("nuevoEstado");

                try {
                        if (nombreUsuario == null || nombreUsuario.isBlank() || numeroPlaca == null
                                        || numeroPlaca.isBlank() || nuevoEstadoParametro == null
                                        || nuevoEstadoParametro.isBlank()) {
                                throw new DatosIncompletosException(
                                                "No se indicaron los datos necesarios para cambiar el estado.");
                        }

                        Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                                        .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);

                        Optional<Bus> busOptional = busServicio.obtenerBusPorNumeroPlaca(numeroPlaca);

                        if (administradorOptional.isEmpty() || busOptional.isEmpty()) {
                                throw new NoGuardadoEnBDException("No se encontró el bus que desea actualizar.");
                        }

                        int idSucursalAdministrador = administradorOptional.get().getSucursal().getId();

                        int idSucursalBus = busOptional.get().getSucursal().getId();

                        if (idSucursalBus != idSucursalAdministrador) {
                                throw new NoGuardadoEnBDException("El bus no pertenece a su sucursal.");
                        }

                        boolean nuevoEstado = Boolean.parseBoolean(nuevoEstadoParametro);
                        busServicio.cambiarEstadoBus(numeroPlaca, nuevoEstado);
                        session.setAttribute("tituloModal", "Cambio de estado exitoso");
                        session.setAttribute("mensajeModal", "El estado del bus fue actualizado correctamente.");

                } catch (DatosIncompletosException | NoGuardadoEnBDException | SQLException e) {
                        session.setAttribute("tituloModal", "Error al cambiar el estado");
                        session.setAttribute("mensajeModal", e.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/activar_desactivar_bus");
        }

}
