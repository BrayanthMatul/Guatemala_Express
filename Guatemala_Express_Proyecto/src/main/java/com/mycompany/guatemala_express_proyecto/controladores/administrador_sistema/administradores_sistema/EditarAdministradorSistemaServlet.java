/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.administrador_sistema.administradores_sistema;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.daos.UsuarioDAO;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.exceptions.UsuarioNoEncontradoException;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;
import com.mycompany.guatemala_express_proyecto.servicios.generales.mi_perfil.DatosFlashPerfilServicio;
import com.mycompany.guatemala_express_proyecto.servicios.generales.mi_perfil.EditarPerfilServicio;

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
@WebServlet(name = "EditarAdministradorSistemaServlet", urlPatterns = { "/administrador_sistema/editar_admin_sistema" })
public class EditarAdministradorSistemaServlet extends HttpServlet {

    private final EditarPerfilServicio editorPerfilServicio = new EditarPerfilServicio();
    private final DatosFlashPerfilServicio datosFlashPerfilServicio = new DatosFlashPerfilServicio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String nombreUsuario = request.getParameter("nombreUsuario");

        if (nombreUsuario == null || nombreUsuario.isBlank()) {
            session.setAttribute("tituloModal", "Error");
            session.setAttribute("mensajeModal", "No se indicó el administrador del sistema.");
            response.sendRedirect(request.getContextPath() + "/administrador_sistema/lista_administradores_sistema");
            return;
        }

        if (session.getAttribute("edicion") != null) {
            datosFlashPerfilServicio.colocarDatosFlash(request, session);
            session.removeAttribute("edicion");
        } else {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            try {
                Optional<Usuario> usuarioOptional = usuarioDAO.obtenerUsuarioPorNombreUsuario(nombreUsuario);

                if (usuarioOptional.isPresent()) {
                    colocarDatosUsuarioEnRequest(request, usuarioOptional.get());
                } else {
                    session.setAttribute("tituloModal", "Error");
                    session.setAttribute("mensajeModal", "No se encontró el administrador del sistema.");
                    response.sendRedirect(request.getContextPath()
                            + "/administrador_sistema/lista_administradores_sistema");
                    return;
                }

            } catch (SQLException e) {
                session.setAttribute("tituloModal", "Error");
                session.setAttribute("mensajeModal", "No fue posible cargar la información del administrador.");
                response.sendRedirect(
                        request.getContextPath() + "/administrador_sistema/lista_administradores_sistema");
                return;
            }
        }

        request.getRequestDispatcher(
                "/WEB-INF/views/administrador_sistema/administradores_sistema/editar-admin-sistema.jsp")
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
            editorPerfilServicio.actualizarPerfil(usuario);
            session.setAttribute("tituloModal", "Éxito");
            session.setAttribute("mensajeModal", "Administrador del sistema actualizado exitosamente.");
            response.sendRedirect(request.getContextPath() + "/administrador_sistema/lista_administradores_sistema");
        } catch (DatosIncompletosException | NoGuardadoEnBDException | SQLException | EntidadYaRegistradaException
                | UsuarioNoEncontradoException e) {
            session.setAttribute("mensajeFlash", e.getMessage());
            datosFlashPerfilServicio.guardarDatosFlash(request, usuario);
            session.setAttribute("edicion", true);
            response.sendRedirect(request.getContextPath()
                    + "/administrador_sistema/editar_admin_sistema?nombreUsuario=" + usuario.getNombreUsuario());
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

        return usuario;
    }

    private void colocarDatosUsuarioEnRequest(HttpServletRequest request, Usuario usuario) {
        request.setAttribute("nombreUsuario", usuario.getNombreUsuario());
        request.setAttribute("nit", usuario.getNit());
        request.setAttribute("dpi", usuario.getDpi());
        request.setAttribute("nombreCompleto", usuario.getNombreCompleto());
        request.setAttribute("telefono", usuario.getTelefono());
        request.setAttribute("direccion", usuario.getDireccion());
        request.setAttribute("correoElectronico", usuario.getCorreoElectronico());
    }

}
