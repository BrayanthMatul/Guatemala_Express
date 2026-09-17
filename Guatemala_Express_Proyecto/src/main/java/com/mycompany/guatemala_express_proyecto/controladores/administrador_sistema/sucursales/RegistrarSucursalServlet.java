/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.administrador_sistema.sucursales;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

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
@WebServlet(name = "RegistrarSucursalServlet", urlPatterns = { "/administrador_sistema/registrar_sucursal" })
public class RegistrarSucursalServlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        datosFlashSucursalServicio.colocarDatosFlash(request, session);
        request.getRequestDispatcher("/WEB-INF/views/administrador_sistema/sucursales/registrar-sucursal.jsp")
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
            boolean exito = sucursalServicio.crearSucursal(sucursal);

            if (exito) {
                session.setAttribute("tituloModal", "Éxito");
                session.setAttribute("mensajeModal", "Sucursal registrada exitosamente.");
                response.sendRedirect(request.getContextPath() + "/administrador_sistema/lista_sucursales");
            }

        } catch (DatosIncompletosException | EntidadYaRegistradaException | NoGuardadoEnBDException | SQLException e) {
            session.setAttribute("mensajeFlash", e.getMessage());
            datosFlashSucursalServicio.guardarDatosFlash(request, sucursal);
            response.sendRedirect(request.getContextPath() + "/administrador_sistema/registrar_sucursal");
        }
    }

    private Sucursal construirSucursal(HttpServletRequest request) {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(request.getParameter("nombre"));
        sucursal.setDepartamento(request.getParameter("departamento"));
        sucursal.setMunicipio(request.getParameter("municipio"));
        sucursal.setLongitud(convertirBigDecimal(request.getParameter("longitud")));
        sucursal.setLatitud(convertirBigDecimal(request.getParameter("latitud")));
        sucursal.setTelefono(request.getParameter("telefono"));

        return sucursal;
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
