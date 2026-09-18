<%-- 
    Document   : listar-rutas
    Created on : Sep 16, 2026, 1:51:14 AM
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
                        Rutas regulares
                    </h2>

                    <p class="linea">
                        <a
                            href="${pageContext.request.contextPath}/administrador_sucursal/registrar_ruta_regular"
                            class="hover:underline">

                            <i class="pi pi-plus-circle mr-2"></i>
                            Registrar nueva ruta
                        </a>
                    </p>

                    <c:choose>

                        <c:when test="${empty requestScope.rutasRegulares}">

                            <h3 class="linea">
                                No hay rutas regulares registradas
                            </h3>

                            <p class="linea-sm">
                                Registre una ruta para que aparezca en esta lista.
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
                                                Distancia
                                            </th>

                                            <th scope="col" class="p-4">
                                                Duración estimada
                                            </th>

                                            <th scope="col" class="p-4">
                                                Precio del boleto
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
                                                    <c:out value="${ruta.distanciaAproximadaKm}"/>
                                                    km
                                                </td>

                                                <td class="whitespace-nowrap p-4">
                                                    <c:out value="${ruta.duracionEstimada}"/>
                                                </td>

                                                <td class="whitespace-nowrap p-4">
                                                    Q
                                                    <c:out value="${ruta.precioBoleto}"/>
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

                                                    <c:url
                                                        var="urlEditarRuta"
                                                        value="/administrador_sucursal/editar_ruta_regular">

                                                        <c:param name="idRuta" value="${ruta.id}"/>

                                                    </c:url>

                                                    <p class="linea-sky font-medium hover:underline">

                                                        <a href="${urlEditarRuta}">

                                                            <i class="pi pi-pencil mr-2"></i>
                                                            Editar

                                                        </a>

                                                    </p>

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
