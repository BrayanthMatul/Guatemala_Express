<%-- 
    Document   : activar-desactivar-ruta
    Created on : Sep 16, 2026, 1:51:04 AM
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
                        Activar o desactivar rutas regulares
                    </h2>

                    <p class="linea">
                        Sucursal de origen:
                        <strong>
                            <c:out value="${requestScope.sucursal.nombre}"/>
                        </strong>
                    </p>

                    <c:choose>

                        <c:when test="${empty requestScope.rutasRegulares}">

                            <h3 class="linea">
                                No hay rutas regulares registradas
                            </h3>

                            <p class="linea-sm">
                                Registre una ruta regular para poder administrar su estado.
                            </p>

                        </c:when>

                        <c:otherwise>

                            <div class="w-full overflow-x-auto">

                                <table class="w-full text-left text-sm">

                                    <thead class="bg-slate-900 font-semibold text-white">

                                        <tr>

                                            <th scope="col" class="p-4">
                                                Origen
                                            </th>

                                            <th scope="col" class="p-4">
                                                Destino
                                            </th>

                                            <th scope="col" class="p-4">
                                                Recorrido
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

                                        <c:forEach
                                            var="ruta"
                                            items="${requestScope.rutasRegulares}">

                                            <tr class="transition-colors hover:bg-gray-100">

                                                <td class="whitespace-nowrap p-4">

                                                    <p class="font-semibold">
                                                        <c:out value="${ruta.sucursalOrigen.nombre}"/>
                                                    </p>

                                                    <p class="mt-1 text-sm">
                                                        Código:
                                                        <c:out value="${ruta.sucursalOrigen.id}"/>
                                                    </p>

                                                </td>

                                                <td class="whitespace-nowrap p-4">

                                                    <p class="font-semibold">
                                                        <c:out value="${ruta.sucursalDestino.nombre}"/>
                                                    </p>

                                                    <p class="mt-1 text-sm">
                                                        Código:
                                                        <c:out value="${ruta.sucursalDestino.id}"/>
                                                    </p>

                                                </td>

                                                <td class="whitespace-nowrap p-4">

                                                    <p class="font-semibold">
                                                        <c:out value="${ruta.distanciaAproximadaKm}"/>
                                                        km
                                                    </p>

                                                    <p class="mt-1 text-sm">
                                                        <c:out value="${ruta.duracionEstimada}"/>
                                                    </p>

                                                </td>

                                                <td class="whitespace-nowrap p-4">

                                                    <c:choose>

                                                        <c:when test="${ruta.estado}">

                                                            <span class="label-circular text-sky-700">
                                                                Activa
                                                            </span>

                                                        </c:when>

                                                        <c:otherwise>

                                                            <span class="label-circular text-gray-700">
                                                                Inactiva
                                                            </span>

                                                        </c:otherwise>

                                                    </c:choose>

                                                </td>

                                                <td class="whitespace-nowrap p-4 text-center">

                                                    <form
                                                        method="POST"
                                                        action="${pageContext.request.contextPath}/administrador_sucursal/activar_desactivar_ruta_regular">

                                                        <input type="hidden" name="idRuta" value="<c:out value='${ruta.id}'/>">
                                                        <input type="hidden" name="nuevoEstado" value="${!ruta.estado}">

                                                        <button type="submit" class="linea-sky font-medium hover:underline">

                                                            <c:choose>

                                                                <c:when test="${ruta.estado}">
                                                                    <i class="pi pi-ban mr-2"></i>
                                                                    Desactivar
                                                                </c:when>

                                                                <c:otherwise>
                                                                    <i class="pi pi-check mr-2"></i>
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

                        </c:otherwise>

                    </c:choose>

                </div>

            </section>

        </main>
    </body>
</html>
