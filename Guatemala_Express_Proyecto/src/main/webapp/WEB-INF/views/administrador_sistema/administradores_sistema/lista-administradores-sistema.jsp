<%-- 
    Document   : lista-administradores-sistema
    Created on : Sep 10, 2026, 3:56:56 AM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administradores del sistema</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>

    <body>

        <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor-lg">

            <section class="tarjeta" >

                <div class="formulario-y">

                    <h2 class="titulo">
                        Administradores del sistema
                    </h2>

                    <p class="linea">
                        <a href="${pageContext.request.contextPath}/administrador_sistema/registrar_administrador_sistema" class="hover:underline">
                            <i class="pi pi-user-plus mr-2"></i>
                            Registrar nuevo administrador
                        </a>
                    </p>

                    <c:choose>

                        <c:when test="${empty requestScope.administradoresSistema}">
                            <h3 class="linea">
                                No hay administradores registrados
                            </h3>

                            <p class="linea-sm">
                                Registre un administrador para que aparezca en esta lista.
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

                                    <c:forEach var="administrador" items="${requestScope.administradoresSistema}" >

                                        <tr class="transition-colors hover:bg-gray-100">

                                            <td class="whitespace-nowrap p-4">

                                                <p class="font-semibold">
                                                    <c:out value="${administrador.nombreCompleto}"/>
                                                </p>

                                                <p class="mt-1 text-sm">
                                                    @<c:out value="${administrador.nombreUsuario}"/>
                                                </p>

                                            </td>

                                            <td class="whitespace-nowrap p-4">

                                                <p>
                                                    <c:out value="${administrador.correoElectronico}"/>
                                                </p>

                                                <p class="mt-1">
                                                    <c:out value="${administrador.telefono}"/>
                                                </p>

                                            </td>

                                            <td class="whitespace-nowrap p-4">
                                                <c:out value="${administrador.nit}"/>
                                            </td>

                                            <td class="whitespace-nowrap p-4">
                                                <c:out value="${administrador.dpi}"/>
                                            </td>

                                            <td class="max-w-64 p-4">
                                                <c:out value="${administrador.direccion}"/>
                                            </td>

                                            <td class="whitespace-nowrap p-4">

                                                <c:choose>

                                                    <c:when test="${administrador.estado}">
                                                        <span class="label-circular text-sky-700" >
                                                            Activo
                                                        </span>
                                                    </c:when>

                                                    <c:otherwise>
                                                        <span class="label-circular text-gray-700" >
                                                            Inactivo
                                                        </span>
                                                    </c:otherwise>

                                                </c:choose>

                                            </td>

                                            <td class="whitespace-nowrap p-4 text-center">

                                                <c:url var="urlEditarAdministrador" value="/administrador_sistema/editar_admin_sistema">
                                                    <c:param name="nombreUsuario" value="${administrador.nombreUsuario}" />
                                                </c:url>

                                                <p class="linea-sky font-medium hover:underline">
                                                    <a href="${urlEditarAdministrador}">
                                                    <i class="pi pi-pencil mr-2"></i>
                                                    Editar
                                                </a></p>

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