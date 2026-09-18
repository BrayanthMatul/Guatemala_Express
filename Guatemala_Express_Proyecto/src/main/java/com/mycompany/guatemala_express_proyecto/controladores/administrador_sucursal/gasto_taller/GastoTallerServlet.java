/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.mycompany.guatemala_express_proyecto.controladores.administrador_sucursal.gasto_taller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.AdministradorSucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Bus;
import com.mycompany.guatemala_express_proyecto.modelos.GastoTaller;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.servicios.administrador_sucursal.AdministradorSucursalServicio;
import com.mycompany.guatemala_express_proyecto.servicios.buses.BusServicio;
import com.mycompany.guatemala_express_proyecto.servicios.gasto_taller.DatosFlashGastoTallerServicio;
import com.mycompany.guatemala_express_proyecto.servicios.gasto_taller.GastoTallerServicio;

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
@WebServlet(name = "GastoTallerServlet", urlPatterns = { "/administrador_sucursal/gasto_taller" })
public class GastoTallerServlet extends HttpServlet {

    private final GastoTallerServicio gastoTallerServicio = new GastoTallerServicio();
    private final AdministradorSucursalServicio administradorSucursalServicio = new AdministradorSucursalServicio();
    private final BusServicio busServicio = new BusServicio();
    private final DatosFlashGastoTallerServicio datosFlashGastoTallerServicio = new DatosFlashGastoTallerServicio();

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
        datosFlashGastoTallerServicio.colocarDatosFlash(request, session);

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

            if (administradorOptional.isPresent()) {
                Sucursal sucursal = administradorOptional.get().getSucursal();
                request.setAttribute("sucursal", sucursal);
                request.setAttribute("buses", busServicio.obtenerBusesActivosPorSucursal(sucursal.getId()));
            } else {
                request.setAttribute("tituloModal", "Error");
                request.setAttribute("mensajeModal", "No se encontró la sucursal asignada al administrador.");
            }

        } catch (SQLException e) {
            request.setAttribute("tituloModal", "Error al obtener los datos");
            request.setAttribute("mensajeModal", "No fue posible obtener los buses y gastos de taller de la sucursal.");
        }

        request.getRequestDispatcher("/WEB-INF/views/administrador_sucursal/taller/gasto-taller.jsp")
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
        GastoTaller gastoTaller = null;

        try {
            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);

            if (administradorOptional.isEmpty()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "No se encontró la sucursal asignada al administrador.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/gasto_taller");
                return;
            }

            Sucursal sucursal = administradorOptional.get().getSucursal();
            gastoTaller = construirGastoTaller(request);
            gastoTallerServicio.registrarGastoTaller(gastoTaller, sucursal.getId());
            session.setAttribute("tituloModal", "Registro exitoso");
            session.setAttribute("mensajeModal", "El gasto de taller fue registrado correctamente.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_gastos_taller");
        } catch (DatosIncompletosException | NoGuardadoEnBDException | SQLException e) {
            session.setAttribute("mensajeFlash", e.getMessage());

            if (gastoTaller != null) {
                datosFlashGastoTallerServicio.guardarDatosFlash(request, gastoTaller);
            }

            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/gasto_taller");

        }

    }

    private GastoTaller construirGastoTaller(HttpServletRequest request) {

        Bus bus = new Bus();
        bus.setNumeroPlaca(request.getParameter("numeroPlaca"));

        GastoTaller gastoTaller = new GastoTaller();
        gastoTaller.setBus(bus);
        gastoTaller.setMontoManoDeObra(convertirDecimal(request.getParameter("montoManoDeObra")));
        gastoTaller.setMontoRepuestos(convertirDecimal(request.getParameter("montoRepuestos")));

        return gastoTaller;
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
