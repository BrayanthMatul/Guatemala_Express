/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.mycompany.guatemala_express_proyecto.controladores.administrador_sucursal.rutas_regulares;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.AdministradorSucursal;
import com.mycompany.guatemala_express_proyecto.modelos.RutaRegular;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.servicios.administrador_sucursal.AdministradorSucursalServicio;
import com.mycompany.guatemala_express_proyecto.servicios.ruta_regular.DatosFlashRutaRegularServicio;
import com.mycompany.guatemala_express_proyecto.servicios.ruta_regular.RutaRegularServicio;
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
@WebServlet(name = "RegistrarRutaRegularServlet", urlPatterns = { "/administrador_sucursal/registrar_ruta_regular" })
public class RegistrarRutaRegularServlet extends HttpServlet {

    private final RutaRegularServicio rutaRegularServicio = new RutaRegularServicio();
    private final SucursalServicio sucursalServicio = new SucursalServicio();
    private final AdministradorSucursalServicio administradorSucursalServicio = new AdministradorSucursalServicio();
    private final DatosFlashRutaRegularServicio datosFlashRutaRegularServicio = new DatosFlashRutaRegularServicio();

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
        datosFlashRutaRegularServicio.colocarDatosFlash(request, session);
        String nombreUsuario = (String) session.getAttribute("nombreUsuario");

        try {
            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);

            if (administradorOptional.isPresent()) {
                Sucursal sucursalOrigen = administradorOptional.get().getSucursal();
                request.setAttribute("sucursalOrigen", sucursalOrigen);
                request.setAttribute("sucursales", sucursalServicio.obtenerSucursales());
                request.getRequestDispatcher("/WEB-INF/views/administrador_sucursal/rutas_regulares/registrar-ruta.jsp")
                        .forward(request, response);
            } else {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "No se encontró la sucursal asignada al administrador.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_rutas_regulares");
            }

        } catch (SQLException e) {
            session.setAttribute("tituloModal", "Error al cargar el formulario");
            session.setAttribute("mensajeModal", "No fue posible obtener las sucursales.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_rutas_regulares");
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
        RutaRegular rutaRegular = null;

        try {
            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);

            if (administradorOptional.isEmpty()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "No se encontró la sucursal asignada al administrador.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_rutas_regulares");
                return;
            }

            Sucursal sucursalOrigen = administradorOptional.get().getSucursal();
            rutaRegular = construirRuta(request, sucursalOrigen);
            rutaRegularServicio.registrarNuevaRuta(rutaRegular);
            session.setAttribute("tituloModal", "Registro exitoso");
            session.setAttribute("mensajeModal", "La ruta regular fue registrada correctamente.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_rutas_regulares");
        } catch (DatosIncompletosException | EntidadYaRegistradaException | NoGuardadoEnBDException | SQLException e) {
            session.setAttribute("mensajeFlash", e.getMessage());
            if (rutaRegular != null) {
                datosFlashRutaRegularServicio.guardarDatosFlash(request, rutaRegular);
            }

            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/registrar_ruta_regular");
        }
    }

    private RutaRegular construirRuta(HttpServletRequest request, Sucursal sucursalOrigen) {

        Sucursal sucursalDestino = new Sucursal();
        sucursalDestino.setId(convertirEntero(request.getParameter("idSucursalDestino")));
        RutaRegular rutaRegular = new RutaRegular();
        rutaRegular.setSucursalOrigen(sucursalOrigen);
        rutaRegular.setSucursalDestino(sucursalDestino);
        rutaRegular.setDistanciaAproximadaKm(convertirDecimal(request.getParameter("distanciaAproximadaKm")));
        rutaRegular.setPrecioBoleto(convertirDecimal(request.getParameter("precioBoleto")));
        rutaRegular.setDuracionEstimada(convertirDuracion(request.getParameter("duracionEstimada")));

        return rutaRegular;
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

    private LocalTime convertirDuracion(String valor) {

        if (valor == null || valor.isBlank()) {
            return null;
        }

        try {
            return LocalTime.parse(valor);

        } catch (DateTimeParseException e) {
            return null;
        }
    }

}
