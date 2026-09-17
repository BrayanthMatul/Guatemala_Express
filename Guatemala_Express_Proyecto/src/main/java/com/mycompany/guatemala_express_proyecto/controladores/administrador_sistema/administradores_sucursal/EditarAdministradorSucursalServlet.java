/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.administrador_sistema.administradores_sucursal;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.daos.AdministradorSucursalDAO;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.AdministradorSucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;
import com.mycompany.guatemala_express_proyecto.servicios.administrador_sucursal.AdministradorSucursalServicio;
import com.mycompany.guatemala_express_proyecto.servicios.administrador_sucursal.DatosFlashAdministradorSucursalServicio;
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
@WebServlet(name = "EditarAdministradorSucursalServlet", urlPatterns = {
        "/administrador_sistema/editar_administrador_sucursal" })
public class EditarAdministradorSucursalServlet extends HttpServlet {

    private final AdministradorSucursalServicio administradorSucursalServicio = new AdministradorSucursalServicio();
    private final SucursalServicio sucursalServicio = new SucursalServicio();
    private final DatosFlashAdministradorSucursalServicio datosFlashAdministradorSucursalServicio = new DatosFlashAdministradorSucursalServicio();

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
        String nombreUsuario = request.getParameter("nombreUsuario");

        try {
            request.setAttribute("sucursales", sucursalServicio.obtenerSucursales());
        } catch (SQLException e) {
            session.setAttribute("tituloModal", "Error");
            session.setAttribute("mensajeModal", "No fue posible cargar las sucursales.");
            response.sendRedirect(request.getContextPath() + "/administrador_sistema/lista_administradores_sucursal");
            return;
        }

        if (nombreUsuario == null || nombreUsuario.isBlank()) {
            session.setAttribute("tituloModal", "Error");
            session.setAttribute("mensajeModal", "No se indicó el administrador de sucursal.");
            response.sendRedirect(request.getContextPath() + "/administrador_sistema/lista_administradores_sucursal");
            return;
        }

        if (session.getAttribute("edicion") != null) {
            datosFlashAdministradorSucursalServicio.colocarDatosFlash(request, session);
            request.setAttribute("nombreUsuario", nombreUsuario);
            session.removeAttribute("edicion");
        } else {
            AdministradorSucursalDAO administradorSucursalDAO = new AdministradorSucursalDAO();

            try {
                Optional<AdministradorSucursal> administradorOptional = administradorSucursalDAO
                        .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);
                if (administradorOptional.isPresent()) {
                    colocarDatosAdministradorSucursalEnRequest(request, administradorOptional.get());
                } else {
                    session.setAttribute("tituloModal", "Error");
                    session.setAttribute("mensajeModal", "No se encontró el administrador de sucursal.");
                    response.sendRedirect(
                            request.getContextPath() + "/administrador_sistema/lista_administradores_sucursal");
                    return;
                }

            } catch (SQLException e) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal",
                        "No fue posible cargar la información del administrador." + e.getMessage());
                response.sendRedirect(
                        request.getContextPath() + "/administrador_sistema/lista_administradores_sucursal");
                return;
            }
        }

        request.getRequestDispatcher(
                "/WEB-INF/views/administrador_sistema/administradores_sucursal/editar-administrador-sucursal.jsp")
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
        AdministradorSucursal administradorSucursal = construirAdministradorSucursal(request);
        HttpSession session = request.getSession();

        try {
            administradorSucursalServicio.editarAdministradorSucursal(administradorSucursal);
            session.setAttribute("tituloModal", "Éxito");
            session.setAttribute("mensajeModal", "Administrador de sucursal actualizado exitosamente.");
            response.sendRedirect(request.getContextPath() + "/administrador_sistema/lista_administradores_sucursal");
        } catch (DatosIncompletosException | EntidadYaRegistradaException | NoGuardadoEnBDException | SQLException e) {
            session.setAttribute("mensajeFlash", e.getMessage());
            datosFlashAdministradorSucursalServicio.guardarDatosFlash(request, administradorSucursal);
            session.setAttribute("edicion", true);
            response.sendRedirect(request.getContextPath()
                    + "/administrador_sistema/editar_administrador_sucursal?nombreUsuario="
                    + administradorSucursal.getUsuario().getNombreUsuario());
        }
    }

    private AdministradorSucursal construirAdministradorSucursal(HttpServletRequest request) {
        Usuario usuario = construirUsuario(request);
        Sucursal sucursal = new Sucursal();

        sucursal.setId(convertirEntero(request.getParameter("sucursalId")));

        AdministradorSucursal administradorSucursal = new AdministradorSucursal();
        administradorSucursal.setUsuario(usuario);
        administradorSucursal.setSucursal(sucursal);

        return administradorSucursal;
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

    private void colocarDatosAdministradorSucursalEnRequest(HttpServletRequest request,
            AdministradorSucursal administradorSucursal) {

        Usuario usuario = administradorSucursal.getUsuario();
        Sucursal sucursal = administradorSucursal.getSucursal();

        request.setAttribute("nombreUsuario", usuario.getNombreUsuario());
        request.setAttribute("nit", usuario.getNit());
        request.setAttribute("dpi", usuario.getDpi());
        request.setAttribute("nombreCompleto", usuario.getNombreCompleto());
        request.setAttribute("telefono", usuario.getTelefono());
        request.setAttribute("direccion", usuario.getDireccion());
        request.setAttribute("correoElectronico", usuario.getCorreoElectronico());
        request.setAttribute("sucursalId", sucursal.getId());
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
