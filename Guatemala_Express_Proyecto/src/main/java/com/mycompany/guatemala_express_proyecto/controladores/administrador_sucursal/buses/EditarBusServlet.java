/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.administrador_sucursal.buses;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
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
@WebServlet(name = "EditarBusServlet", urlPatterns = { "/administrador_sucursal/editar_bus" })
public class EditarBusServlet extends HttpServlet {

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
                String numeroPlaca = request.getParameter("numeroPlaca");

                if (numeroPlaca == null || numeroPlaca.isBlank()) {
                        request.setAttribute("tituloModal", "Error");
                        request.setAttribute("mensajeModal", "No se indicó el bus que desea editar.");
                } else if (session == null) {
                        request.setAttribute("tituloModal", "Error");
                        request.setAttribute("mensajeModal", "No se encontró una sesión activa.");

                } else {
                        String nombreUsuario = (String) session.getAttribute("nombreUsuario");

                        try {

                                Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                                                .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);
                                Optional<Bus> busOptional = busServicio.obtenerBusPorNumeroPlaca(numeroPlaca);

                                if (administradorOptional.isEmpty() || busOptional.isEmpty()) {
                                        request.setAttribute("tituloModal", "Error");
                                        request.setAttribute("mensajeModal", "No se encontró el bus que desea editar.");
                                } else {

                                        Sucursal sucursalAdministrador = administradorOptional.get().getSucursal();
                                        Bus bus = busOptional.get();

                                        if (bus.getSucursal().getId() != sucursalAdministrador.getId()) {
                                                request.setAttribute("tituloModal", "Error");
                                                request.setAttribute("mensajeModal",
                                                                "El bus no pertenece a su sucursal.");
                                        } else {
                                                request.setAttribute("sucursal", sucursalAdministrador);
                                                Object edicionBusFlash = session.getAttribute("edicionBusFlash");

                                                if (edicionBusFlash != null) {
                                                        datosFlashBusServicio.colocarDatosFlash(request, session);
                                                        request.setAttribute("numeroPlaca", numeroPlaca);
                                                        session.removeAttribute("edicionBusFlash");
                                                } else {
                                                        colocarDatosBusEnRequest(request, bus);
                                                }
                                        }
                                }

                        } catch (SQLException e) {
                                request.setAttribute("tituloModal", "Error");
                                request.setAttribute("mensajeModal", "No fue posible obtener la información del bus.");
                        }
                }

                request.getRequestDispatcher("/WEB-INF/views/administrador_sucursal/buses/editar-bus.jsp")
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

                String numeroPlaca = request.getParameter("numeroPlaca");
                String nombreUsuario = (String) session.getAttribute("nombreUsuario");
                Bus bus = null;

                try {

                        Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                                        .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);
                        Optional<Bus> busActualOptional = busServicio.obtenerBusPorNumeroPlaca(numeroPlaca);

                        if (administradorOptional.isEmpty() || busActualOptional.isEmpty()) {
                                throw new NoGuardadoEnBDException("No se encontró el bus que desea actualizar.");
                        }

                        Sucursal sucursalAdministrador = administradorOptional.get().getSucursal();

                        if (busActualOptional.get().getSucursal().getId() != sucursalAdministrador.getId()) {
                                throw new NoGuardadoEnBDException("El bus no pertenece a su sucursal.");
                        }

                        bus = construirBus(request, sucursalAdministrador);
                        busServicio.editarBus(bus);
                        session.setAttribute("tituloModal", "Actualización exitosa");
                        session.setAttribute("mensajeModal", "El bus fue actualizado correctamente.");
                        response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_buses");
                } catch (DatosIncompletosException | NoGuardadoEnBDException | SQLException e) {
                        session.setAttribute("mensajeFlash", e.getMessage());

                        if (bus != null) {
                                datosFlashBusServicio.guardarDatosFlash(request, bus);
                        }

                        session.setAttribute("edicionBusFlash", true);

                        if (numeroPlaca != null && !numeroPlaca.isBlank()) {
                                response.sendRedirect(request.getContextPath()
                                                + "/administrador_sucursal/editar_bus?numeroPlaca="
                                                + URLEncoder.encode(numeroPlaca, StandardCharsets.UTF_8));

                        } else {
                                response.sendRedirect(
                                                request.getContextPath() + "/administrador_sucursal/listar_buses");
                        }
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
                Part fotografiaPart = request.getPart("fotografia");

                if (fotografiaPart != null && fotografiaPart.getSize() > 0) {
                        bus.setFotografia(fotografiaPart.getInputStream().readAllBytes());
                }

                return bus;
        }

        private void colocarDatosBusEnRequest(HttpServletRequest request, Bus bus) {
                request.setAttribute("numeroPlaca", bus.getNumeroPlaca());
                request.setAttribute("marca", bus.getMarca());
                request.setAttribute("modelo", bus.getModelo());
                request.setAttribute("anioFabricacion", bus.getAnioFabricacion());
                request.setAttribute("capacidadPasajeros", bus.getCapacidadPasajeros());
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
