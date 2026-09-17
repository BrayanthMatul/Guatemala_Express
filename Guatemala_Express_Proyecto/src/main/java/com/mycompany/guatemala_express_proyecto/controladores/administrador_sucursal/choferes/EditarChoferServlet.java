/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.administrador_sucursal.choferes;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.AdministradorSucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Chofer;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;
import com.mycompany.guatemala_express_proyecto.servicios.administrador_sucursal.AdministradorSucursalServicio;
import com.mycompany.guatemala_express_proyecto.servicios.chofer.ChoferServicio;
import com.mycompany.guatemala_express_proyecto.servicios.chofer.DatosFlashChoferServicio;

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
@WebServlet(name = "EditarChoferServlet", urlPatterns = { "/administrador_sucursal/editar_chofer" })
public class EditarChoferServlet extends HttpServlet {

    private final ChoferServicio choferServicio = new ChoferServicio();
    private final AdministradorSucursalServicio administradorSucursalServicio = new AdministradorSucursalServicio();
    private final DatosFlashChoferServicio datosFlashChoferServicio = new DatosFlashChoferServicio();

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
        String nombreUsuarioChofer = request.getParameter("nombreUsuario");
        String nombreUsuarioAdministrador = (String) session.getAttribute("nombreUsuario");
        Object edicionChoferFlash = session.getAttribute("edicionChoferFlash");

        if (nombreUsuarioChofer == null || nombreUsuarioChofer.isBlank()) {
            session.setAttribute("tituloModal", "Error");
            session.setAttribute("mensajeModal", "No se indicó el chofer que desea editar.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_choferes");
            return;
        }

        try {
            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuarioAdministrador);
            Optional<Chofer> choferOptional = choferServicio.obtenerChoferPorNombreUsuario(nombreUsuarioChofer);

            if (administradorOptional.isEmpty() || choferOptional.isEmpty()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "No se encontró el chofer que desea editar.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_choferes");
                return;
            }

            Sucursal sucursalAdministrador = administradorOptional.get().getSucursal();
            Chofer chofer = choferOptional.get();

            if (chofer.getSucursal().getId() != sucursalAdministrador.getId()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "El chofer no pertenece a su sucursal.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_choferes");
                return;
            }

            if (edicionChoferFlash != null) {
                datosFlashChoferServicio.colocarDatosFlash(request, session);
                session.removeAttribute("edicionChoferFlash");
            } else {
                colocarDatosChoferEnRequest(request, chofer);
            }

            request.getRequestDispatcher("/WEB-INF/views/administrador_sucursal/choferes/editar-chofer.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            session.setAttribute("tituloModal", "Error");
            session.setAttribute("mensajeModal", "No fue posible obtener la información del chofer.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_choferes");
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
        String nombreUsuarioChofer = request.getParameter("nombreUsuario");
        String nombreUsuarioAdministrador = (String) session.getAttribute("nombreUsuario");
        Chofer chofer = null;

        try {
            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuarioAdministrador);
            Optional<Chofer> choferActualOptional = choferServicio.obtenerChoferPorNombreUsuario(nombreUsuarioChofer);

            if (administradorOptional.isEmpty() || choferActualOptional.isEmpty()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "No se encontró el chofer que desea editar.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_choferes");
                return;
            }

            Sucursal sucursalAdministrador = administradorOptional.get().getSucursal();

            if (choferActualOptional.get().getSucursal().getId() != sucursalAdministrador.getId()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "El chofer no pertenece a su sucursal.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_choferes");
                return;
            }

            chofer = construirChofer(request, sucursalAdministrador);
            choferServicio.actualizarChofer(chofer);
            session.setAttribute("tituloModal", "Actualización exitosa");
            session.setAttribute("mensajeModal", "El chofer fue actualizado correctamente.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_choferes");
        } catch (DatosIncompletosException | EntidadYaRegistradaException | NoGuardadoEnBDException | SQLException e) {

            if (chofer != null) {
                session.setAttribute("mensajeFlash", e.getMessage());
                datosFlashChoferServicio.guardarDatosFlash(request, chofer);
                session.setAttribute("edicionChoferFlash", true);
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/editar_chofer?nombreUsuario="
                        + URLEncoder.encode(nombreUsuarioChofer, StandardCharsets.UTF_8));
            } else {
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_choferes");
            }

        }
    }

    private Chofer construirChofer(HttpServletRequest request, Sucursal sucursal)
            throws IOException, ServletException {
        Chofer chofer = new Chofer();
        chofer.setUsuario(construirUsuario(request));
        chofer.setSucursal(sucursal);
        chofer.setNumeroLicencia(request.getParameter("numeroLicencia"));
        chofer.setTipoLicencia(request.getParameter("tipoLicencia"));
        chofer.setFechaVencimientoLicencia(convertirFecha(request.getParameter("fechaVencimientoLicencia")));
        chofer.setSalarioBasePorViaje(convertirDecimal(request.getParameter("salarioBasePorViaje")));
        Part fotografiaPart = request.getPart("fotografia");

        if (fotografiaPart != null && fotografiaPart.getSize() > 0) {
            chofer.setFotografia(fotografiaPart.getInputStream().readAllBytes());
        }

        return chofer;
    }

    private Usuario construirUsuario(HttpServletRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(request.getParameter("nombreUsuario"));
        usuario.setNit(request.getParameter("nit"));
        usuario.setDpi(request.getParameter("dpi"));
        usuario.setNombreCompleto(request.getParameter("nombreCompleto"));
        usuario.setTelefono(request.getParameter("telefono"));
        usuario.setDireccion(request.getParameter("direccion"));
        usuario.setCorreoElectronico(request.getParameter("correoElectronico"));
        return usuario;
    }

    private void colocarDatosChoferEnRequest(HttpServletRequest request, Chofer chofer) {
        Usuario usuario = chofer.getUsuario();
        request.setAttribute("nombreUsuario", usuario.getNombreUsuario());
        request.setAttribute("nit", usuario.getNit());
        request.setAttribute("dpi", usuario.getDpi());
        request.setAttribute("nombreCompleto", usuario.getNombreCompleto());
        request.setAttribute("telefono", usuario.getTelefono());
        request.setAttribute("direccion", usuario.getDireccion());
        request.setAttribute("correoElectronico", usuario.getCorreoElectronico());
        request.setAttribute("numeroLicencia", chofer.getNumeroLicencia());
        request.setAttribute("tipoLicencia", chofer.getTipoLicencia());
        request.setAttribute("fechaVencimientoLicencia", chofer.getFechaVencimientoLicencia());
        request.setAttribute("salarioBasePorViaje", chofer.getSalarioBasePorViaje());
        request.setAttribute("sucursal", chofer.getSucursal());
    }

    private LocalDate convertirFecha(String valor) {

        if (valor == null || valor.isBlank()) {
            return null;
        }

        try {
            return LocalDate.parse(valor);
        } catch (DateTimeParseException e) {
            return null;
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
