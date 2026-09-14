<%-- 
    Document   : informacion
    Created on : Sep 6, 2026, 7:44:26 AM
    Author     : matul
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<c:if test="${not empty requestScope.mensaje}">
    <p class="mb-6 text-center text-sm text-red-700">
        <c:out value="${requestScope.mensaje}"/>
    </p>
</c:if>
