/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.administrador_sistema.sucursales;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.daos.SucursalDAO;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.servicios.sucursales.DatosFlashSucursalServicio;
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
@WebServlet(name = "EditarSucursalServlet", urlPatterns = { "/administrador_sistema/editar_sucursal" })
public class EditarSucursalServlet extends HttpServlet {

        private final SucursalServicio sucursalServicio = new SucursalServicio();
        private final DatosFlashSucursalServicio datosFlashSucursalServicio = new DatosFlashSucursalServicio();

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
                int id = convertirEntero(request.getParameter("id"));

                if (id <= 0) {
                        request.setAttribute("tituloModal", "Error");
                        request.setAttribute("mensajeModal", "No se indicó la sucursal que se desea editar.");
                } else if (session != null && session.getAttribute("edicion") != null) {
                        datosFlashSucursalServicio.colocarDatosFlash(request, session);
                        request.setAttribute("id", id);
                        session.removeAttribute("edicion");

                } else {
                        SucursalDAO sucursalDAO = new SucursalDAO();

                        try {
                                Optional<Sucursal> sucursalOptional = sucursalDAO.obtenerSucursalPorId(id);

                                if (sucursalOptional.isPresent()) {
                                        colocarDatosSucursalEnRequest(request, sucursalOptional.get());

                                } else {
                                        request.setAttribute("tituloModal", "Error");
                                        request.setAttribute("mensajeModal", "No se encontró la sucursal.");
                                }

                        } catch (SQLException e) {
                                request.setAttribute("tituloModal", "Error");
                                request.setAttribute("mensajeModal",
                                                "No fue posible cargar la información de la sucursal.");
                        }
                }

                request.getRequestDispatcher(
                                "/WEB-INF/views/administrador_sistema/sucursales/editar-sucursal.jsp")
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
                Sucursal sucursal = construirSucursal(request);
                HttpSession session = request.getSession();

                try {
                        sucursalServicio.editarSucursal(sucursal);

                        session.setAttribute("tituloModal", "Éxito");
                        session.setAttribute("mensajeModal", "Sucursal actualizada exitosamente.");
                        response.sendRedirect(request.getContextPath() + "/administrador_sistema/lista_sucursales");

                } catch (DatosIncompletosException | EntidadYaRegistradaException
                                | NoGuardadoEnBDException | SQLException e) {

                        session.setAttribute("mensajeFlash", e.getMessage());
                        datosFlashSucursalServicio.guardarDatosFlash(request, sucursal);
                        session.setAttribute("edicion", true);
                        response.sendRedirect(request.getContextPath() + "/administrador_sistema/editar_sucursal?id="
                                        + sucursal.getId());
                }

        }

        private Sucursal construirSucursal(HttpServletRequest request) {
                Sucursal sucursal = new Sucursal();

                sucursal.setId(convertirEntero(request.getParameter("id")));
                sucursal.setNombre(request.getParameter("nombre"));
                sucursal.setDepartamento(request.getParameter("departamento"));
                sucursal.setMunicipio(request.getParameter("municipio"));
                sucursal.setLongitud(convertirBigDecimal(request.getParameter("longitud")));
                sucursal.setLatitud(convertirBigDecimal(request.getParameter("latitud")));
                sucursal.setTelefono(request.getParameter("telefono"));

                return sucursal;
        }

        private void colocarDatosSucursalEnRequest(
                        HttpServletRequest request, Sucursal sucursal) {

                request.setAttribute("id", sucursal.getId());
                request.setAttribute("nombre", sucursal.getNombre());
                request.setAttribute("departamento", sucursal.getDepartamento());
                request.setAttribute("municipio", sucursal.getMunicipio());
                request.setAttribute("longitud", sucursal.getLongitud());
                request.setAttribute("latitud", sucursal.getLatitud());
                request.setAttribute("telefono", sucursal.getTelefono());
                request.setAttribute("fechaApertura", sucursal.getFechaApertura());
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

        private BigDecimal convertirBigDecimal(String valor) {
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
