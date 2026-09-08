/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.controladores.inicios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.guatemala_express_proyecto.modelos.MenuItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author matul
 */
@WebServlet(name = "InicioCliente", urlPatterns = { "/cliente/inicio" })
public class InicioCliente extends HttpServlet {

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
                List<MenuItem> menu = crearMenu();
                request.setAttribute("menuItems", menu);
        request.getRequestDispatcher("/WEB-INF/views/cliente/inicio.jsp")
                .forward(request, response);
    }

    private List<MenuItem> crearMenu() {
        List<MenuItem> menu = new ArrayList<>();

        MenuItem miPerfil = new MenuItem("Mi perfil", "/cliente/perfil/informacion");
        miPerfil.agregarSubOpcion("Consultar", "/cliente/perfil/informacion");
        miPerfil.agregarSubOpcion("Editar", "/cliente/perfil/editar");
        miPerfil.agregarSubOpcion("Cerrar sesión", "/login");
        menu.add(miPerfil);

        MenuItem viajes = new MenuItem("Viajes", "/cliente/viajes/comprar");
        viajes.agregarSubOpcion("Comprar boletos", "/cliente/viajes/comprar");
        viajes.agregarSubOpcion("Ver viajes regulares disponibles", "/cliente/viajes/disponibles");
        viajes.agregarSubOpcion("Boletos comprados", "/cliente/viajes/boletos");
        viajes.agregarSubOpcion("Consultar rutas", "/cliente/viajes/rutas");
        menu.add(viajes);

        MenuItem alquilarBus = new MenuItem("Alquilar bus", "/cliente/alquiler/solicitar");
        alquilarBus.agregarSubOpcion("Solicitar un alquiler", "/cliente/alquiler/solicitar");
        alquilarBus.agregarSubOpcion("Pagar monto de alquiler", "/cliente/alquiler/pagar");
        alquilarBus.agregarSubOpcion("Consultar solicitudes de alquiler", "/cliente/alquiler/consultar");
        menu.add(alquilarBus);

        MenuItem cartera = new MenuItem("Cartera", "/cliente/cartera/recargar"); 
        cartera.agregarSubOpcion("Recargar saldo a cuenta", "/cliente/cartera/recargar");
        cartera.agregarSubOpcion("Historia de recargas", "/cliente/cartera/historial");
        cartera.agregarSubOpcion("Consultar saldos y movimientos", "/cliente/cartera/consultar");
        menu.add(cartera);
        return menu;
    }

// Mi perfil

// Consultar perfil
// Editar perfil
// Cerrar sesion

// Viajes

// Comprar boletos
// Ver viajes regulares disponibles
// Boletos comprados
// Consultar rutas

// Alquilar bus

// Solicitar un alquiler
// Pagar monto de alquiler
// Consultar solicitudes de alquiler

// Cartera

// Recargar saldo a cuenta
// Historia de recargas
// Consultar saldos y movimientos


}
