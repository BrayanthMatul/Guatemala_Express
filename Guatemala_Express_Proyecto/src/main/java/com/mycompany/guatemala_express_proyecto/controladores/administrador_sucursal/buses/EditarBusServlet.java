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
        String nombreUsuario = (String) session.getAttribute("nombreUsuario");

        if (numeroPlaca == null || numeroPlaca.isBlank()) {
            session.setAttribute("tituloModal", "Error");
            session.setAttribute("mensajeModal", "No se indicó el bus que desea editar.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_buses");
            return;
        }

        try {
            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);
            Optional<Bus> busOptional = busServicio.obtenerBusPorNumeroPlaca(numeroPlaca);

            if (administradorOptional.isEmpty() || busOptional.isEmpty()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "No se encontró el bus que desea editar.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_buses");
                return;
            }

            Sucursal sucursalAdministrador = administradorOptional.get().getSucursal();
            Bus bus = busOptional.get();

            if (bus.getSucursal().getId() != sucursalAdministrador.getId()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "El bus no pertenece a su sucursal.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_buses");
                return;
            }

            if (session.getAttribute("edicionBus") != null) {
                datosFlashBusServicio.colocarDatosFlash(request, session);
                session.removeAttribute("edicionBus");
            } else {
                colocarDatosBusEnRequest(request, bus);
            }

            request.getRequestDispatcher("/WEB-INF/views/administrador_sucursal/buses/editar-bus.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            session.setAttribute("tituloModal", "Error");
            session.setAttribute("mensajeModal", "No fue posible obtener la información del bus.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_buses");
        }

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
        Bus bus = null;

        try {

            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);
            Optional<Bus> busActualOptional = busServicio.obtenerBusPorNumeroPlaca(numeroPlaca);

            if (administradorOptional.isEmpty() || busActualOptional.isEmpty()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "No se encontró el bus que desea editar.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_buses");
                return;
            }

            Sucursal sucursalAdministrador = administradorOptional.get().getSucursal();

            if (busActualOptional.get().getSucursal().getId() != sucursalAdministrador.getId()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "El bus no pertenece a su sucursal.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_buses");
                return;
            }

            bus = construirBus(request, sucursalAdministrador);
            busServicio.editarBus(bus);
            session.setAttribute("tituloModal", "Actualización exitosa");
            session.setAttribute("mensajeModal", "El bus fue actualizado correctamente.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_buses");
        } catch (DatosIncompletosException | NoGuardadoEnBDException | SQLException e) {

            if (bus != null) {
                session.setAttribute("mensajeFlash", e.getMessage());
                datosFlashBusServicio.guardarDatosFlash(request, bus);
                session.setAttribute("edicionBus", true);
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/editar_bus?numeroPlaca="
                        + URLEncoder.encode(numeroPlaca, StandardCharsets.UTF_8));
            } else {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "No se encontró el bus que desea editar.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_buses");
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
        request.setAttribute("sucursal", bus.getSucursal());
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

}
