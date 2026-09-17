/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.administrador_sistema.administradores_sistema;

import java.io.IOException;
import java.sql.SQLException;

import com.mycompany.guatemala_express_proyecto.enums.Rol;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;
import com.mycompany.guatemala_express_proyecto.servicios.usuarios.CrearUsuarioServicio;
import com.mycompany.guatemala_express_proyecto.servicios.generales.mi_perfil.DatosFlashPerfilServicio;

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
@WebServlet(name = "RegistrarAdminstradorSistemaServlet", urlPatterns = {
        "/administrador_sistema/registrar_administrador_sistema" })
public class RegistrarAdminstradorSistemaServlet extends HttpServlet {

    private final CrearUsuarioServicio registrarUsuarioServicio = new CrearUsuarioServicio();
    private final DatosFlashPerfilServicio datosFlashPerfilServicio = new DatosFlashPerfilServicio();

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

        HttpSession session = request.getSession();
        datosFlashPerfilServicio.colocarDatosFlash(request, session);

        request.getRequestDispatcher(
                "/WEB-INF/views/administrador_sistema/administradores_sistema/registrar-administrador-sistema.jsp")
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
        String nombreUsuario = request.getParameter("nombreCompleto");

        try {
            registrarUsuarioServicio.crearUsuario(usuario);
            session.setAttribute("tituloModal", "Administrador guardado");
            session.setAttribute("mensajeModal", "Administrador " + nombreUsuario + " registrado correctamente.");
            response.sendRedirect(request.getContextPath() + "/administrador_sistema/lista_administradores_sistema");
        } catch (DatosIncompletosException | NoGuardadoEnBDException | SQLException | EntidadYaRegistradaException e) {
            session.setAttribute("mensajeFlash", e.getMessage());
            datosFlashPerfilServicio.guardarDatosFlash(request, usuario);
            response.sendRedirect(request.getContextPath() + "/administrador_sistema/registrar_administrador_sistema");
        }
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
        usuario.setRol(Rol.ADMINISTRADOR_SISTEMA);
        return usuario;
    }

}
