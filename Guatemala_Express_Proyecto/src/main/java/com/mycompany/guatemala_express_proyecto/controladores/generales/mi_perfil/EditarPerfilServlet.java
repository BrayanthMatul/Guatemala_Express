/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.generales.mi_perfil;

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
@WebServlet(name = "EditarPerfil", urlPatterns = { "/perfil/editar" })
public class EditarPerfilServlet extends HttpServlet {

    private final EditarPerfilServicio editorPerfilServicio = new EditarPerfilServicio();
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

        if (session.getAttribute("edicion") != null) {
            datosFlashPerfilServicio.colocarDatosFlash(request, session);
            session.removeAttribute("edicion");
        } else {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            String nombreUsuario = (String) request.getSession().getAttribute("nombreUsuario");

            try {
                Optional<Usuario> usuarioOptional = usuarioDAO.obtenerUsuarioPorNombreUsuario(nombreUsuario);

                if (usuarioOptional.isPresent()) {
                    colocarDatosUsuarioEnRequest(request, usuarioOptional.get());
                } else {
                    request.setAttribute("tituloModal", "Error");
                    request.setAttribute("mensajeModal", "No se encontró el usuario.");
                }
            } catch (SQLException e) {
                request.setAttribute("tituloModal", "Error");
                request.setAttribute("mensajeModal", "No fue posible cargar la información del perfil.");
            }
        }

        request.getRequestDispatcher("/WEB-INF/views/generales/mi_perfil/editar-pefil.jsp")
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
            session.setAttribute("tituloModalFlash", "Éxito");
            session.setAttribute("mensajeModalFlash", "Perfil actualizado exitosamente.");
            response.sendRedirect(request.getContextPath() + "/perfil/informacion");
        } catch (DatosIncompletosException | NoGuardadoEnBDException | SQLException | EntidadYaRegistradaException
                | UsuarioNoEncontradoException e) {
            session.setAttribute("mensajeFlash", e.getMessage());
            datosFlashPerfilServicio.guardarDatosFlash(request, usuario);
            session.setAttribute("edicion", true);
            response.sendRedirect(request.getContextPath() + "/perfil/editar");
        }

    }

    private Usuario construirUsuario(HttpServletRequest request) {
        Usuario usuario = new Usuario();
        String nombreUsuario = (String) request.getSession().getAttribute("nombreUsuario");
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setNit(request.getParameter("nit"));
        usuario.setDpi(request.getParameter("dpi"));
        usuario.setNombreCompleto(request.getParameter("nombreCompleto"));
        usuario.setTelefono(request.getParameter("telefono"));
        usuario.setDireccion(request.getParameter("direccion"));
        usuario.setCorreoElectronico(request.getParameter("correoElectronico"));
        return usuario;
    }

    private void colocarDatosUsuarioEnRequest(HttpServletRequest request, Usuario usuario) {
        request.setAttribute("nit", usuario.getNit());
        request.setAttribute("dpi", usuario.getDpi());
        request.setAttribute("nombreCompleto", usuario.getNombreCompleto());
        request.setAttribute("telefono", usuario.getTelefono());
        request.setAttribute("direccion", usuario.getDireccion());
        request.setAttribute("correoElectronico", usuario.getCorreoElectronico());
    }

}
