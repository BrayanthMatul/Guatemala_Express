<%-- 
    Document   : activar-desactivar-bus
    Created on : Sep 15, 2026, 9:38:23 PM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Activar/Desactivar Bus</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor">

            <section class="tarjeta">

                <div class="formulario-y">

                    <h2 class="titulo">
                        Activar o desactivar buses
                    </h2>

                    <p class="linea">
                        Sucursal:
                        <strong>
                            <c:out value="${requestScope.sucursal.nombre}"/>
                        </strong>
                    </p>

                    <c:choose>

                        <c:when test="${empty requestScope.buses}">

                            <h3 class="linea">
                                No hay buses registrados
                            </h3>

                            <p class="linea-sm">
                                Registre un bus para poder administrar su estado.
                            </p>

                        </c:when>

                        <c:otherwise>

                                <table class="w-full text-left text-sm">

                                    <thead class="bg-slate-900 font-semibold text-white">

                                        <tr>
                                            <th scope="col" class="p-4">
                                                Bus
                                            </th>

                                            <th scope="col" class="p-4">
                                                Estado operativo
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

                                        <c:forEach var="bus" items="${requestScope.buses}">

                                            <tr class="transition-colors hover:bg-gray-100">

                                                <td class="whitespace-nowrap p-4">

                                                    <p class="font-semibold">
                                                        <c:out value="${bus.marca}"/>
                                                        <c:out value="${bus.modelo}"/>
                                                    </p>

                                                    <p class="mt-1 text-sm">
                                                        Placa:
                                                        <c:out value="${bus.numeroPlaca}"/>
                                                    </p>

                                                </td>

                                                <td class="whitespace-nowrap p-4">

                                                    <span class="label-circular text-sky-700">
                                                        <c:out value="${bus.estadoOperativo}"/>
                                                    </span>

                                                </td>

                                                <td class="whitespace-nowrap p-4">

                                                    <c:choose>

                                                        <c:when test="${bus.estado}">
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

                                                    <form method="POST" action="${pageContext.request.contextPath}/administrador_sucursal/activar_desactivar_bus">

                                                        <input
                                                            type="hidden"
                                                            name="numeroPlaca"
                                                            value="<c:out value='${bus.numeroPlaca}'/>">

                                                        <input
                                                            type="hidden"
                                                            name="nuevoEstado"
                                                            value="${!bus.estado}">

                                                        <button
                                                            type="submit"
                                                            class="linea-sky font-medium hover:underline">

                                                            <c:choose>

                                                                <c:when test="${bus.estado}">
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

                        </c:otherwise>

                    </c:choose>

                </div>

            </section>

        </main>
    </body>
</html>
