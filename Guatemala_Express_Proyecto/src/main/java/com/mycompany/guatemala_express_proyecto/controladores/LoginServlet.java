/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores;

import java.io.IOException;

import com.mycompany.guatemala_express_proyecto.exceptions.CredencialesInvalidasException;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.UsuarioDesactivadoException;
import com.mycompany.guatemala_express_proyecto.exceptions.UsuarioNoEncontradoException;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;
import com.mycompany.guatemala_express_proyecto.servicios.LoginServicio;

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
@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

        private final LoginServicio loginServicio = new LoginServicio();

        @Override
        protected void doGet(
                        HttpServletRequest request,
                        HttpServletResponse response)
                        throws ServletException, IOException {

                HttpSession session = request.getSession(false);

                if (session != null) {
                        Object mensaje = session.getAttribute("mensajeFlash");
                        Object correo = session.getAttribute("correoFlash");

                        if (mensaje != null) {
                                request.setAttribute("mensaje", mensaje);
                                session.removeAttribute("mensajeFlash");
                        }

                        if (correo != null) {
                                request.setAttribute("correo", correo);
                                session.removeAttribute("correoFlash");
                        }
                }

                request.getRequestDispatcher("/index.jsp")
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

                String correo = request.getParameter("correo");
                String contrasenia = request.getParameter("contrasenia");

                try {
                        Usuario usuario = loginServicio.iniciarSesion(correo, contrasenia);

                        HttpSession session = request.getSession();
                        session.setAttribute("usuarioId", usuario.getId());
                        session.setAttribute("usuarioNombre", usuario.getNombreCompleto());
                        session.setAttribute("rol", usuario.getRol());

                        redireccionarSegunRol(request, response, usuario);

                } catch (UsuarioNoEncontradoException
                                | DatosIncompletosException
                                | CredencialesInvalidasException
                                | UsuarioDesactivadoException e) {

                        HttpSession session = request.getSession();
                        session.setAttribute("mensajeFlash", e.getMessage());
                        session.setAttribute("correoFlash", correo);

                        response.sendRedirect(request.getContextPath() + "/login");
                }

        }

        private void redireccionarSegunRol(HttpServletRequest request, HttpServletResponse response, Usuario usuario)
                        throws IOException, ServletException {
                switch (usuario.getRol()) {
                        case ADMINISTRADOR_SISTEMA:
                                response.sendRedirect(request.getContextPath() + "/administrador_sistema/inicio");
                                break;
                        case ADMINISTRADOR_SUCURSAL:
                                response.sendRedirect(request.getContextPath() + "/administrador_sucursal/inicio");
                                break;
                        case CHOFER:
                                response.sendRedirect(request.getContextPath() + "/chofer/inicio");
                                break;
                        case CLIENTE:
                                response.sendRedirect(request.getContextPath() + "/cliente/inicio");
                                break;
                        default:
                                request.getSession().invalidate();
                                request.setAttribute("mensaje", "Error al evaluar el rol del usuario.");
                                request.getRequestDispatcher("/index.jsp").forward(request, response);
                                break;
                }
        }

}
