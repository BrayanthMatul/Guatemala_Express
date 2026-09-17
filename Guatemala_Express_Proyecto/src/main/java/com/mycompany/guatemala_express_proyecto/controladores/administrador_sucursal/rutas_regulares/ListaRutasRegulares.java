/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.mycompany.guatemala_express_proyecto.controladores.administrador_sucursal.rutas_regulares;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.modelos.AdministradorSucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.servicios.administrador_sucursal.AdministradorSucursalServicio;
import com.mycompany.guatemala_express_proyecto.servicios.ruta_regular.RutaRegularServicio;

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
@WebServlet(name = "ListaRutasRegulares", urlPatterns = { "/administrador_sucursal/listar_rutas_regulares" })
public class ListaRutasRegulares extends HttpServlet {

    private final RutaRegularServicio rutaRegularServicio = new RutaRegularServicio();
    private final AdministradorSucursalServicio administradorSucursalServicio = new AdministradorSucursalServicio();

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
        Object tituloModal = session.getAttribute("tituloModal");
        Object mensajeModal = session.getAttribute("mensajeModal");

        if (tituloModal != null) {
            request.setAttribute("tituloModal", tituloModal);
            session.removeAttribute("tituloModal");
        }

        if (mensajeModal != null) {
            request.setAttribute("mensajeModal", mensajeModal);
            session.removeAttribute("mensajeModal");
        }

        try {
            String nombreUsuario = (String) session.getAttribute("nombreUsuario");

            Optional<AdministradorSucursal> administradorOptional = administradorSucursalServicio
                    .obtenerAdministradorSucursalPorNombreUsuario(nombreUsuario);

            if (administradorOptional.isPresent()) {
                Sucursal sucursal = administradorOptional.get().getSucursal();
                request.setAttribute("sucursal", sucursal);
                request.setAttribute("rutasRegulares", rutaRegularServicio.obtenerRutasPorSucursal(sucursal.getId()));
            } else {
                request.setAttribute("tituloModal", "Error");
                request.setAttribute("mensajeModal", "No se encontró la sucursal asignada al administrador.");
            }
        } catch (SQLException e) {
            request.setAttribute("tituloModal", "Error al obtener rutas regulares");
            request.setAttribute("mensajeModal", "No fue posible obtener las rutas regulares de la sucursal.");
        }

        request.getRequestDispatcher("/WEB-INF/views/administrador_sucursal/" + "rutas_regulares/listar-rutas.jsp")
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
    }

}
