/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.filtros;

import com.mycompany.guatemala_express_proyecto.enums.Rol;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author matul
 */
@WebFilter(filterName = "AdministradorSistemaFiltro", urlPatterns = { "/administrador_sistema/*" })
public class AdministradorSistemaFiltro implements Filter {

    public AdministradorSistemaFiltro() {
    }

    /**
     *
     * @param request  The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain    The filter chain we are processing
     *
     * @exception IOException      if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("rol") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        Rol rol = (Rol) session.getAttribute("rol");

        if (rol != Rol.ADMINISTRADOR_SISTEMA) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/perfil/informacion");
            return;
        }

        chain.doFilter(request, response);
    }

}
