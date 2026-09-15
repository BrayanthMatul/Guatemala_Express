<%-- 
    Document   : activar-desactivar-administradores-sucursal
    Created on : Sep 10, 2026, 4:00:42 AM
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

        <main class="contenedor">

            <section class="tarjeta">

                <div class="formulario-y">

                    <h2 class="titulo">
                        Activar o desactivar administradores de sucursal
                    </h2>

                    <p class="linea">
                        Presione la accion para cambiar el estado actual del administrador de sucursal.
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
                                            Estado
                                        </th>

                                        <th scope="col" class="p-4 text-center">
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

                                                <c:url var="urlCambiarEstado"
                                                    value="/administrador_sistema/activar_desactivar_administrador_sucursal"/>

                                                <form method="POST"
                                                    action="${urlCambiarEstado}">

                                                    <input
                                                        type="hidden"
                                                        name="nombreUsuario"
                                                        value="<c:out value='${administradorSucursal.usuario.nombreUsuario}'/>">

                                                    <input
                                                        type="hidden"
                                                        name="nuevoEstado"
                                                        value="${!administradorSucursal.usuario.estado}">

                                                    <button
                                                        type="submit"
                                                        class="linea-sky font-medium hover:underline">

                                                        <c:choose>

                                                            <c:when test="${administradorSucursal.usuario.estado}">
                                                                Desactivar
                                                            </c:when>

                                                            <c:otherwise>
                                                                Activar
                                                            </c:otherwise>

                                                        </c:choose>

                                                    </button>

                                                </form>

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
