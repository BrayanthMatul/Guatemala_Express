<%-- 
    Document   : lista-administradores-sucursal
    Created on : Sep 10, 2026, 4:00:27 AM
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

        <main class="contenedor-xl">

            <section class="tarjeta">

                <div class="formulario-y">

                    <h2 class="titulo">
                        Administradores de sucursal
                    </h2>

                    <p class="linea">
                        <a href="${pageContext.request.contextPath}/administrador_sistema/registrar_administrador_sucursal"
                        class="hover:underline">

                            <i class="pi pi-user-plus mr-2"></i>
                            Registrar nuevo administrador de sucursal
                        </a>
                    </p>

                    <c:choose>

                        <c:when test="${empty requestScope.administradoresSucursal}">

                            <h3 class="linea">
                                No hay administradores de sucursal registrados
                            </h3>

                            <p class="linea-sm">
                                Registre un administrador de sucursal para que aparezca en esta lista.
                            </p>

                        </c:when>

                        <c:otherwise>

                            <table class="w-full text-left text-sm">

                                <thead class="bg-slate-900 text-white font-semibold">

                                    <tr>
                                        <th scope="col" class="p-4">
                                            Administrador
                                        </th>

                                        <th scope="col" class="p-4">
                                            Sucursal
                                        </th>

                                        <th scope="col" class="p-4">
                                            Contacto
                                        </th>

                                        <th scope="col" class="p-4">
                                            NIT
                                        </th>

                                        <th scope="col" class="p-4">
                                            DPI
                                        </th>

                                        <th scope="col" class="p-4">
                                            Dirección
                                        </th>

                                        <th scope="col" class="p-4">
                                            Estado
                                        </th>

                                        <th scope="col" class="p-4">
                                            Acciones
                                        </th>
                                    </tr>

                                </thead>

                                <tbody class="divide-y divide-gray-200 text-slate-900">

                                    <c:forEach var="administradorSucursal"
                                            items="${requestScope.administradoresSucursal}">

                                        <tr class="transition-colors hover:bg-gray-100">

                                            <td class="whitespace-nowrap p-4">

                                                <p class="font-semibold">
                                                    <c:out value="${administradorSucursal.usuario.nombreCompleto}"/>
                                                </p>

                                                <p class="mt-1 text-sm">
                                                    @<c:out value="${administradorSucursal.usuario.nombreUsuario}"/>
                                                </p>

                                            </td>

                                            <td class="whitespace-nowrap p-4">

                                                <p class="font-semibold">
                                                    <c:out value="${administradorSucursal.sucursal.nombre}"/>
                                                </p>

                                                <p class="mt-1 text-sm">
                                                    Código:
                                                    <c:out value="${administradorSucursal.sucursal.id}"/>
                                                </p>

                                            </td>

                                            <td class="whitespace-nowrap p-4">

                                                <p>
                                                    <c:out value="${administradorSucursal.usuario.correoElectronico}"/>
                                                </p>

                                                <p class="mt-1">
                                                    <c:out value="${administradorSucursal.usuario.telefono}"/>
                                                </p>

                                            </td>

                                            <td class="whitespace-nowrap p-4">
                                                <c:out value="${administradorSucursal.usuario.nit}"/>
                                            </td>

                                            <td class="whitespace-nowrap p-4">
                                                <c:out value="${administradorSucursal.usuario.dpi}"/>
                                            </td>

                                            <td class="max-w-64 p-4">
                                                <c:out value="${administradorSucursal.usuario.direccion}"/>
                                            </td>

                                            <td class="whitespace-nowrap p-4">

                                                <c:choose>

                                                    <c:when test="${administradorSucursal.usuario.estado}">
                                                        <span class="label-circular text-sky-700">
                                                            Activo
                                                        </span>
                                                    </c:when>

                                                    <c:otherwise>
                                                        <span class="label-circular text-gray-700">
                                                            Inactivo
                                                        </span>
                                                    </c:otherwise>

                                                </c:choose>

                                            </td>

                                            <td class="whitespace-nowrap p-4 text-center">

                                                <c:url var="urlEditarAdministradorSucursal"
                                                    value="/administrador_sistema/editar_administrador_sucursal">

                                                    <c:param
                                                        name="nombreUsuario"
                                                        value="${administradorSucursal.usuario.nombreUsuario}"/>
                                                </c:url>

                                                <p class="linea-sky font-medium hover:underline">

                                                    <a href="${urlEditarAdministradorSucursal}">
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
