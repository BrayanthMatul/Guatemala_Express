/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores;

import java.io.IOException;
import java.sql.SQLException;

import com.mycompany.guatemala_express_proyecto.enums.Rol;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;
import com.mycompany.guatemala_express_proyecto.servicios.CrearUsuarioServicio;

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
@WebServlet(name = "CrearCuentaServlet", urlPatterns = { "/crear_cuenta" })
public class CrearCuentaServlet extends HttpServlet {

    private final CrearUsuarioServicio registrarUsuarioServicio = new CrearUsuarioServicio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            colocarDatosFlash(request, session);
        }

        request.getRequestDispatcher("/WEB-INF/views/crear-cuenta.jsp")
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

        Usuario usuario = construirUsuario(request);
        HttpSession session = request.getSession();

        try {
            int idGenerado = registrarUsuarioServicio.crearUsuario(usuario);
            request.getSession().setAttribute("usuarioId", idGenerado);
            session.setAttribute("usuarioNombre", usuario.getNombreCompleto());
            session.setAttribute("rol", usuario.getRol());
            response.sendRedirect(request.getContextPath() + "/cliente/inicio");
        } catch (DatosIncompletosException | NoGuardadoEnBDException | SQLException | EntidadYaRegistradaException e) {
            session.setAttribute("mensajeFlash", e.getMessage());
            guardarDatosFlash(request, usuario);

            response.sendRedirect(request.getContextPath() + "/crear_cuenta");
        }

    }

    private Usuario construirUsuario(HttpServletRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNit(request.getParameter("nit"));
        usuario.setDpi(request.getParameter("dpi"));
        usuario.setNombreCompleto(request.getParameter("nombreCompleto"));
        usuario.setTelefono(request.getParameter("telefono"));
        usuario.setDireccion(request.getParameter("direccion"));
        usuario.setCorreoElectronico(request.getParameter("correoElectronico"));
        usuario.setContrasenia(request.getParameter("contrasenia"));
        usuario.setRol(Rol.CLIENTE);
        return usuario;
    }

    private void guardarDatosFlash(HttpServletRequest request, Usuario usuario) {
        HttpSession session = request.getSession();
        session.setAttribute("nitFlash", usuario.getNit());
        session.setAttribute("dpiFlash", usuario.getDpi());
        session.setAttribute("nombreCompletoFlash", usuario.getNombreCompleto());
        session.setAttribute("telefonoFlash", usuario.getTelefono());
        session.setAttribute("direccionFlash", usuario.getDireccion());
        session.setAttribute("correoElectronicoFlash", usuario.getCorreoElectronico());
    }

    private void colocarDatosFlash(HttpServletRequest request, HttpSession session) {
        Object mensaje = session.getAttribute("mensajeFlash");
        Object nit = session.getAttribute("nitFlash");
        Object dpi = session.getAttribute("dpiFlash");
        Object nombreCompleto = session.getAttribute("nombreCompletoFlash");
        Object telefono = session.getAttribute("telefonoFlash");
        Object direccion = session.getAttribute("direccionFlash");
        Object correoElectronico = session.getAttribute("correoElectronicoFlash");

        if (mensaje != null) {
            request.setAttribute("mensaje", mensaje);
            session.removeAttribute("mensajeFlash");
        }

        if (nit != null) {
            request.setAttribute("nit", nit);
            session.removeAttribute("nitFlash");
        }

        if (dpi != null) {
            request.setAttribute("dpi", dpi);
            session.removeAttribute("dpiFlash");
        }

        if (nombreCompleto != null) {
            request.setAttribute("nombreCompleto", nombreCompleto);
            session.removeAttribute("nombreCompletoFlash");
        }

        if (telefono != null) {
            request.setAttribute("telefono", telefono);
            session.removeAttribute("telefonoFlash");
        }

        if (direccion != null) {
            request.setAttribute("direccion", direccion);
            session.removeAttribute("direccionFlash");
        }

        if (correoElectronico != null) {
            request.setAttribute("correoElectronico", correoElectronico);
            session.removeAttribute("correoElectronicoFlash");
        }

    }

}
