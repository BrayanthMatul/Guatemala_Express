<%-- 
    Document   : informacion
    Created on : Sep 6, 2026, 7:44:26 AM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <%
        if (request.getAttribute("mensaje") != null) {
    %>
        <div class="text-center text-sm m-5 w-full">
            <p class="text-red-600">${mensaje}</p>
        </div>
    <%
        }
    %>
    <%
        if (request.getAttribute("exito") != null) {
    %>
        <div class="text-center text-sm m-5 w-full">
            <p class="text-green-950">${exito}</p>
        </div>
    <%
        }
%>