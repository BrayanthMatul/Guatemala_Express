<%-- 
    Document   : lista-sucursales
    Created on : Sep 10, 2026, 3:59:03 AM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor-lg">

            <section class="tarjeta">

                <div class="formulario-y">

                    <h2 class="titulo">
                        Sucursales
                    </h2>

                    <p class="linea">
                        <a href="${pageContext.request.contextPath}/administrador_sistema/registrar_sucursal"
                        class="hover:underline">

                            <i class="pi pi-plus-circle mr-2"></i>
                            Registrar nueva sucursal
                        </a>
                    </p>

                    <c:choose>

                        <c:when test="${empty requestScope.sucursales}">
                            <h3 class="linea">
                                No hay sucursales registradas
                            </h3>

                            <p class="linea-sm">
                                Registre una sucursal para que aparezca en esta lista.
                            </p>
                        </c:when>

                        <c:otherwise>

                            <table class="w-full text-left text-sm">

                                <thead class="bg-slate-900 text-white font-semibold">

                                    <tr>
                                        <th scope="col" class="p-4">
                                            Sucursal
                                        </th>

                                        <th scope="col" class="p-4">
                                            Ubicación
                                        </th>

                                        <th scope="col" class="p-4">
                                            Coordenadas
                                        </th>

                                        <th scope="col" class="p-4">
                                            Teléfono
                                        </th>

                                        <th scope="col" class="p-4">
                                            Fecha de apertura
                                        </th>

                                        <th scope="col" class="p-4">
                                            Acciones
                                        </th>
                                    </tr>

                                </thead>

                                <tbody class="divide-y divide-gray-200 text-slate-900">

                                    <c:forEach var="sucursal"
                                            items="${requestScope.sucursales}">

                                        <tr class="transition-colors hover:bg-gray-100">

                                            <td class="whitespace-nowrap p-4">

                                                <p class="font-semibold">
                                                    <c:out value="${sucursal.nombre}"/>
                                                </p>

                                                <p class="mt-1 text-sm">
                                                    Código: <c:out value="${sucursal.id}"/>
                                                </p>

                                            </td>

                                            <td class="p-4">

                                                <p class="font-medium">
                                                    <c:out value="${sucursal.municipio}"/>
                                                </p>

                                                <p class="mt-1 text-sm">
                                                    <c:out value="${sucursal.departamento}"/>
                                                </p>

                                            </td>

                                            <td class="whitespace-nowrap p-4">

                                                <p>
                                                    Latitud:
                                                    <c:out value="${sucursal.latitud}"/>
                                                </p>

                                                <p class="mt-1">
                                                    Longitud:
                                                    <c:out value="${sucursal.longitud}"/>
                                                </p>

                                            </td>

                                            <td class="whitespace-nowrap p-4">
                                                <c:out value="${sucursal.telefono}"/>
                                            </td>

                                            <td class="whitespace-nowrap p-4">
                                                <c:out value="${sucursal.fechaApertura}"/>
                                            </td>

                                            <td class="whitespace-nowrap p-4 text-center">

                                                <c:url var="urlEditarSucursal"
                                                    value="/administrador_sistema/editar_sucursal">

                                                    <c:param name="id"
                                                            value="${sucursal.id}"/>
                                                </c:url>

                                                <p class="linea-sky font-medium hover:underline">

                                                    <a href="${urlEditarSucursal}">
                                                        <i class="pi pi-pencil mr-2"></i>
                                                        Editar
                                                    </a>

                                                </p>

                                            </td>

                                        </tr>

                                    </c:forEach>

                                </tbody>

                            </table>

                        </c:otherwise>

                    </c:choose>

                </div>

            </section>

        </main>
    </body>
</html>
