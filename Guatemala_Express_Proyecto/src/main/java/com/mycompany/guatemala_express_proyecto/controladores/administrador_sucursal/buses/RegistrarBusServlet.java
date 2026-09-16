/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.administrador_sucursal.buses;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.AdministradorSucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Bus;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.servicios.administrador_sucursal.AdministradorSucursalServicio;
import com.mycompany.guatemala_express_proyecto.servicios.buses.BusServicio;
import com.mycompany.guatemala_express_proyecto.servicios.buses.DatosFlashBusServicio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 *
 * @author matul
 */
@MultipartConfig(maxFileSize = 5L * 1024 * 1024, maxRequestSize = 6L * 1024 * 1024)
@WebServlet(name = "RegistrarBusServlet", urlPatterns = { "/administrador_sucursal/registrar_bus" })
public class RegistrarBusServlet extends HttpServlet {

        private final BusServicio busServicio = new BusServicio();
        private final AdministradorSucursalServicio administradorSucursalServicio = new AdministradorSucursalServicio();
        private final DatosFlashBusServicio datosFlashBusServicio = new DatosFlashBusServicio();

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
                        datosFlashBusServicio.colocarDatosFlash(request, session);
                        String nombreUsuario = (String) session.getAttribute("nombreUsuario");

                        try {

                                Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                                                .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);

                                if (administradorOptional.isPresent()) {
                                        request.setAttribute("sucursal", administradorOptional.get().getSucursal());

                                } else {
                                        request.setAttribute("tituloModal", "Error");
                                        request.setAttribute("mensajeModal",
                                                        "No se encontró la sucursal asignada al administrador.");
                                }
                        } catch (SQLException e) {
                                request.setAttribute("tituloModal", "Error");
                                request.setAttribute("mensajeModal", "No fue posible obtener la sucursal asignada.");
                        }
                }

                request.getRequestDispatcher("/WEB-INF/views/administrador_sucursal/buses/registrar-bus.jsp")
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

                String nombreUsuario = (String) session.getAttribute(
                                "nombreUsuario");

                Bus bus = null;

                try {
                        Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                                        .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);

                        if (administradorOptional.isEmpty()) {
                                throw new DatosIncompletosException(
                                                "No se encontró la sucursal asignada al administrador.");
                        }

                        bus = construirBus(request, administradorOptional.get().getSucursal());
                        busServicio.registrarBus(bus);
                        session.setAttribute("tituloModal", "Registro exitoso");
                        session.setAttribute("mensajeModal", "El bus fue registrado correctamente.");

                        response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_buses");

                } catch (DatosIncompletosException | EntidadYaRegistradaException | NoGuardadoEnBDException
                                | SQLException e) {

                        session.setAttribute("mensajeFlash", e.getMessage());

                        if (bus != null) {
                                datosFlashBusServicio.guardarDatosFlash(request, bus);
                        }

                        response.sendRedirect(request.getContextPath() + "/administrador_sucursal/registrar_bus");
                }
        }

        private Bus construirBus(HttpServletRequest request, Sucursal sucursal) throws IOException, ServletException {

                Bus bus = new Bus();

                bus.setNumeroPlaca(request.getParameter("numeroPlaca"));
                bus.setSucursal(sucursal);
                bus.setMarca(request.getParameter("marca"));
                bus.setModelo(request.getParameter("modelo"));
                bus.setAnioFabricacion(convertirEntero(request.getParameter("anioFabricacion")));
                bus.setCapacidadPasajeros(convertirEntero(request.getParameter("capacidadPasajeros")));
                bus.setKilometrajeActual(convertirDecimal(request.getParameter("kilometrajeActual")));

                Part fotografiaPart = request.getPart("fotografia");

                if (fotografiaPart != null && fotografiaPart.getSize() > 0) {
                        bus.setFotografia(fotografiaPart.getInputStream().readAllBytes());
                }

                return bus;
        }

        private int convertirEntero(String valor) {
                if (valor == null || valor.isBlank()) {
                        return 0;
                }

                try {
                        return Integer.parseInt(valor);
                } catch (NumberFormatException e) {
                        return 0;
                }
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
