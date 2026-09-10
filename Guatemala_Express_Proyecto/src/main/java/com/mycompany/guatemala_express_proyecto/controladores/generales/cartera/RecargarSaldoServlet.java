/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.generales.cartera;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.mycompany.guatemala_express_proyecto.daos.UsuarioDAO;
import com.mycompany.guatemala_express_proyecto.exceptions.DatoInvalidoException;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.modelos.Recarga;
import com.mycompany.guatemala_express_proyecto.servicios.generales.cartera.RecargaServicio;

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
@WebServlet(name = "RecargarSaldo", urlPatterns = { "/cartera/recargar_saldo" })
public class RecargarSaldoServlet extends HttpServlet {

        private final RecargaServicio recargaServicio = new RecargaServicio();

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

                if (session != null) {
                        colocarSaldoActual(request);
                        colocarDatosFlash(request);
                }

                request.getRequestDispatcher("/WEB-INF/views/generales/cartera/recargar-saldo.jsp")
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

                int idUsuario = (int) request.getSession().getAttribute("usuarioId");
                String fechaHoraString = request.getParameter("fechaHora");
                String montoRecargaString = request.getParameter("montoRecarga");
                HttpSession session = request.getSession();

                try {
                        Recarga recarga = construirRecarga(idUsuario, fechaHoraString, montoRecargaString);
                        recargaServicio.guardarRecarga(recarga);
                        session.setAttribute("recargaExitoFlash", "Recarga realizada exitosamente.");
                        response.sendRedirect(request.getContextPath() + "/cartera/recargar_saldo");
                } catch (DatosIncompletosException | DatoInvalidoException | SQLException e) {
                        session.setAttribute("mensajeFlash", e.getMessage());
                        session.setAttribute("fechaHoraFlash", fechaHoraString);
                        session.setAttribute("montoRecargaFlash", montoRecargaString);
                        response.sendRedirect(request.getContextPath() + "/cartera/recargar_saldo");
                }
        }

        private Recarga construirRecarga(int idUsuario, String fechaHoraString, String montoRecargaString)
                        throws DatosIncompletosException, DatoInvalidoException {
                verificarDatosVacios(fechaHoraString, montoRecargaString);

                LocalDateTime fechaHora;

                try {
                        fechaHora = LocalDateTime.parse(fechaHoraString);
                } catch (DateTimeParseException e) {
                        throw new DatoInvalidoException("El formato de la fecha y hora no es válido.");
                }

                BigDecimal montoRecarga;

                try {
                        montoRecarga = new BigDecimal(montoRecargaString.trim());
                } catch (NumberFormatException e) {
                        throw new DatoInvalidoException("El monto ingresado no es válido.");
                }
                return new Recarga(idUsuario, fechaHora, montoRecarga);
        }

        private void verificarDatosVacios(String fechaHoraString, String montoRecargaString)
                        throws DatosIncompletosException {

                if (fechaHoraString == null || fechaHoraString.isBlank()) {
                        throw new DatosIncompletosException("La fecha y hora de recarga no puede estar vacía.");
                }

                if (montoRecargaString == null || montoRecargaString.isBlank()) {
                        throw new DatosIncompletosException("El monto de recarga no puede estar vacío.");
                }
        }

        private void colocarDatosFlash(HttpServletRequest request) {
                HttpSession httpSession = request.getSession();
                Object mensajeFlash = httpSession.getAttribute("mensajeFlash");
                Object fechaHoraFlash = httpSession.getAttribute("fechaHoraFlash");
                Object montoRecargaFlash = httpSession.getAttribute("montoRecargaFlash");
                Object recargaExito = httpSession.getAttribute("recargaExitoFlash");

                if (mensajeFlash != null) {
                        request.setAttribute("mensaje", mensajeFlash);
                        httpSession.removeAttribute("mensajeFlash");
                }

                if (fechaHoraFlash != null) {
                        request.setAttribute("fechaRecarga", fechaHoraFlash);
                        httpSession.removeAttribute("fechaHoraFlash");
                }

                if (montoRecargaFlash != null) {
                        request.setAttribute("montoRecarga", montoRecargaFlash);
                        httpSession.removeAttribute("montoRecargaFlash");
                }

                if (recargaExito != null) {
                        request.setAttribute("recargaExito", recargaExito);
                        httpSession.removeAttribute("recargaExitoFlash");
                }
        }

        private void colocarSaldoActual(HttpServletRequest request) {
                HttpSession httpSession = request.getSession();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                int idUsuario = (int) httpSession.getAttribute("usuarioId");
                BigDecimal saldoActual;

                try {
                        saldoActual = usuarioDAO.obtenerSaldo(idUsuario);
                        request.setAttribute("saldo", saldoActual);
                } catch (SQLException e) {
                        request.setAttribute("saldo", "Error al obtener el saldo actual");
                }
        }

}
