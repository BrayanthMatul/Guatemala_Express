/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.generales.mi_perfil;

import java.io.IOException;
import java.sql.SQLException;

import com.mycompany.guatemala_express_proyecto.exceptions.UsuarioNoEncontradoException;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;
import com.mycompany.guatemala_express_proyecto.servicios.generales.mi_perfil.ConsultarPerfilServicio;

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
@WebServlet(name = "ConsultarPerfilServlet", urlPatterns = { "/perfil/informacion" })
public class ConsultarPerfilServlet extends HttpServlet {

    private final ConsultarPerfilServicio consultarPerfilServicio = new ConsultarPerfilServicio();

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

        String nombreUsuario = (String) request.getSession().getAttribute("nombreUsuario");
        HttpSession session = request.getSession();
        Object tituloModal = session.getAttribute("tituloModalFlash");
        Object mensajeModal = session.getAttribute("mensajeModalFlash");

        if (tituloModal != null) {
            request.setAttribute("tituloModal", tituloModal);
            session.removeAttribute("tituloModalFlash");
        }

        if (mensajeModal != null) {
            request.setAttribute("mensajeModal", mensajeModal);
            session.removeAttribute("mensajeModalFlash");

        }

        try {
            Usuario usuario = consultarPerfilServicio.obtenerUsuarioPorNombreUsuario(nombreUsuario);
            usuario.setContrasenia("");
            request.setAttribute("usuario", usuario);
        } catch (SQLException | UsuarioNoEncontradoException e) {
            request.setAttribute("error", e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/views/generales/mi_perfil/consultar-perfil.jsp")
                .forward(request, response);
    }
}
