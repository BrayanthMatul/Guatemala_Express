/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.administrador_sucursal.choferes;

import java.io.IOException;
import java.math.BigDecimal;
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
@WebServlet(name = "RegistrarChoferServlet", urlPatterns = { "/administrador_sucursal/registrar_chofer" })
public class RegistrarChoferServlet extends HttpServlet {

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
        datosFlashChoferServicio.colocarDatosFlash(request, session);
        String nombreUsuario = (String) session.getAttribute("nombreUsuario");

        try {
            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);

            if (administradorOptional.isPresent()) {
                request.setAttribute("sucursal", administradorOptional.get().getSucursal());
                request.getRequestDispatcher("/WEB-INF/views/administrador_sucursal/choferes/registrar-chofer.jsp")
                        .forward(request, response);
            } else {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "No se encontró la sucursal asignada al administrador.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_choferes");
            }
        } catch (SQLException e) {
            session.setAttribute("tituloModal", "Error");
            session.setAttribute("mensajeModal", "No fue posible obtener la sucursal asignada.");
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
        String nombreUsuarioAdministrador = (String) session.getAttribute("nombreUsuario");
        Chofer chofer = null;

        try {
            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuarioAdministrador);

            if (administradorOptional.isEmpty()) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "No se encontró la sucursal asignada al administrador.");
                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_choferes");
                return;
            }

            chofer = construirChofer(request, administradorOptional.get().getSucursal());
            choferServicio.registrarChofer(chofer);
            session.setAttribute("tituloModal", "Registro exitoso");
            session.setAttribute("mensajeModal", "El chofer fue registrado correctamente.");
            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/listar_choferes");
        } catch (DatosIncompletosException | EntidadYaRegistradaException | NoGuardadoEnBDException
                | SQLException e) {
            session.setAttribute("mensajeFlash", e.getMessage());

            if (chofer != null) {
                datosFlashChoferServicio.guardarDatosFlash(request, chofer);
            }

            response.sendRedirect(request.getContextPath() + "/administrador_sucursal/registrar_chofer");
        }
    }

    private Chofer construirChofer(HttpServletRequest request, Sucursal sucursal)
            throws IOException, ServletException {

        Usuario usuario = construirUsuario(request);
        Chofer chofer = new Chofer();
        chofer.setUsuario(usuario);
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
        usuario.setContrasenia(request.getParameter("contrasenia"));

        return usuario;
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
