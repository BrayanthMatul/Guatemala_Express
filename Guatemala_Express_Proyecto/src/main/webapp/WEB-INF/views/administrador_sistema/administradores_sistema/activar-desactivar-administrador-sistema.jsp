<%-- 
    Document   : activar-desactivar-administrador-sistema
    Created on : Sep 10, 2026, 3:58:02 AM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estado administrador del sistema</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>

    <body>

        <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor">

            <section class="tarjeta" >

                <div class="formulario-y">

                    <h2 class="titulo">
                        Activar o desactivar administradores del sistema
                    </h2>

                    <p class="linea">
                        Debe haber al menos un administrador del sistema activo.
                    </p>


                    <table class="w-full text-left text-sm">

                        <thead class="bg-slate-900 text-white font-semibold">

                            <tr>
                                <th scope="col" class="p-4">
                                    Administrador
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

                                        <c:url var="urlCambiarEstado" value="/administrador_sistema/activar_desactivar_administrador_sistema"/>

                                        <form method="POST" action="${urlCambiarEstado}">
                                            <input type="hidden" name="nombreUsuario" value="<c:out value='${administrador.nombreUsuario}'/>">
                                            <input type="hidden" name="nuevoEstado" value="${!administrador.estado}">

                                            <button type="submit" class="linea-sky font-medium hover:underline">
                                                <c:choose>
                                                    <c:when test="${administrador.estado}">
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

                </div>

            </section>

        </main>

    </body>

</html>
