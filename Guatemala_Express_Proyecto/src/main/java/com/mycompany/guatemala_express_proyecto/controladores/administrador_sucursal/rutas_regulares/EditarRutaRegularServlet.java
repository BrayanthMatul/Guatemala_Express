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
@WebServlet(name = "EditarRutaRegularServlet", urlPatterns = { "/administrador_sucursal/editar_ruta_regular" })
public class EditarRutaRegularServlet extends HttpServlet {

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
        String idRutaParametro = request.getParameter("idRuta");
        String nombreUsuario = (String) session.getAttribute("nombreUsuario");
        int idRuta = convertirEntero(idRutaParametro);

        if (idRuta <= 0) {
            session.setAttribute("tituloModal", "Error");
            session.setAttribute("mensajeModal", "No se indicó la ruta regular que desea editar.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_rutas_regulares");
            return;
        }

        try {
            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);
            Optional<RutaRegular> rutaOptional = rutaRegularServicio.obtenerRutaPorId(idRuta);

            if (administradorOptional.isEmpty() || rutaOptional.isEmpty()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "No se encontró la ruta regular que desea editar.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_rutas_regulares");
                return;
            }

            Sucursal sucursalAdministrador = administradorOptional.get().getSucursal();
            RutaRegular rutaRegular = rutaOptional.get();

            if (rutaRegular.getSucursalOrigen().getId() != sucursalAdministrador.getId()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "La ruta regular no pertenece a su sucursal.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_rutas_regulares");
                return;
            }

            request.setAttribute("idRuta", rutaRegular.getId());
            request.setAttribute("sucursalOrigen", sucursalAdministrador);
            request.setAttribute("sucursales", sucursalServicio.obtenerSucursales());

            if (session.getAttribute("edicionRutaRegular") != null) {
                datosFlashRutaRegularServicio.colocarDatosFlash(request, session);
                session.removeAttribute("edicionRutaRegular");
            } else {
                colocarDatosRutaEnRequest(request, rutaRegular);
            }

            request.getRequestDispatcher("/WEB-INF/views/administrador_sucursal/rutas_regulares/editar-ruta.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            session.setAttribute("tituloModal", "Error");
            session.setAttribute("mensajeModal", "No fue posible obtener la información de la ruta regular.");
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
        String idRutaParametro = request.getParameter("idRuta");
        int idRuta = convertirEntero(idRutaParametro);
        RutaRegular rutaRegular = null;

        if (idRuta <= 0) {
            session.setAttribute("tituloModal", "Error");
            session.setAttribute("mensajeModal", "No se indicó la ruta regular que desea editar.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_rutas_regulares");
            return;
        }

        try {
            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);
            Optional<RutaRegular> rutaActualOptional = rutaRegularServicio.obtenerRutaPorId(idRuta);

            if (administradorOptional.isEmpty() || rutaActualOptional.isEmpty()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "No se encontró la ruta regular que desea editar.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_rutas_regulares");
                return;
            }

            Sucursal sucursalAdministrador = administradorOptional.get().getSucursal();
            RutaRegular rutaActual = rutaActualOptional.get();

            if (rutaActual.getSucursalOrigen().getId() != sucursalAdministrador.getId()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "La ruta regular no pertenece a su sucursal.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_rutas_regulares");
                return;
            }

            rutaRegular = construirRuta(request, idRuta, sucursalAdministrador);
            rutaRegularServicio.actualizarRuta(rutaRegular);
            session.setAttribute("tituloModal", "Actualización exitosa");
            session.setAttribute("mensajeModal", "La ruta regular fue actualizada correctamente.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_rutas_regulares");
        } catch (DatosIncompletosException | EntidadYaRegistradaException | NoGuardadoEnBDException | SQLException e) {

            if (rutaRegular != null) {
                session.setAttribute("mensajeFlash", e.getMessage());
                datosFlashRutaRegularServicio.guardarDatosFlash(request, rutaRegular);
                session.setAttribute("edicionRutaRegular", true);
                response.sendRedirect(
                        request.getContextPath() + "/administrador_sucursal/editar_ruta_regular" + "?idRuta=" + idRuta);
            } else {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "No fue posible obtener la ruta regular que desea editar.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_rutas_regulares");
            }
        }
    }

    private RutaRegular construirRuta(HttpServletRequest request, int idRuta, Sucursal sucursalOrigen) {
        Sucursal sucursalDestino = new Sucursal();
        sucursalDestino.setId(convertirEntero(request.getParameter("idSucursalDestino")));
        RutaRegular rutaRegular = new RutaRegular();

        rutaRegular.setId(idRuta);
        rutaRegular.setSucursalOrigen(sucursalOrigen);
        rutaRegular.setSucursalDestino(sucursalDestino);
        rutaRegular.setDistanciaAproximadaKm(convertirDecimal(request.getParameter("distanciaAproximadaKm")));
        rutaRegular.setPrecioBoleto(convertirDecimal(request.getParameter("precioBoleto")));
        rutaRegular.setDuracionEstimada(convertirDuracion(request.getParameter("duracionEstimada")));

        return rutaRegular;
    }

    private void colocarDatosRutaEnRequest(HttpServletRequest request, RutaRegular rutaRegular) {
        request.setAttribute("idSucursalDestino", rutaRegular.getSucursalDestino().getId());
        request.setAttribute("distanciaAproximadaKm", rutaRegular.getDistanciaAproximadaKm());
        request.setAttribute("precioBoleto", rutaRegular.getPrecioBoleto());
        request.setAttribute("duracionEstimada", rutaRegular.getDuracionEstimada());
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
