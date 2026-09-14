/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores;

import java.io.IOException;
import java.sql.SQLException;

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
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {

                HttpSession session = request.getSession(false);

                if (session != null) {
                        Object mensaje = session.getAttribute("mensajeFlash");
                        Object identificador = session.getAttribute("identificadorFlash");

                        if (mensaje != null) {
                                request.setAttribute("mensaje", mensaje);
                                session.removeAttribute("mensajeFlash");
                        }

                        if (identificador != null) {
                                request.setAttribute("identificador", identificador);
                                session.removeAttribute("identificadorFlash");
                        } else {
                                session.invalidate(); // Cierra la sesión si no hay mensaje ni correo
                        }
                }

                request.getRequestDispatcher("/index.jsp").forward(request, response);
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

                String identificador = request.getParameter("identificador");
                String contrasenia = request.getParameter("contrasenia");

                try {
                        Usuario usuario = loginServicio.iniciarSesion(identificador, contrasenia);

                        HttpSession session = request.getSession();
                        session.setAttribute("nombreUsuario", usuario.getNombreUsuario());
                        session.setAttribute("rol", usuario.getRol());

                        response.sendRedirect(request.getContextPath() + "/perfil/informacion");

                } catch (UsuarioNoEncontradoException
                                | DatosIncompletosException
                                | CredencialesInvalidasException
                                | UsuarioDesactivadoException
                                | SQLException e) {

                        HttpSession session = request.getSession();
                        session.setAttribute("mensajeFlash", e.getMessage());
                        session.setAttribute("identificadorFlash", identificador);

                        response.sendRedirect(request.getContextPath() + "/login");
                }

        }

}
