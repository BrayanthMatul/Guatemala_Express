<%-- 
    Document   : listar-buses
    Created on : Sep 15, 2026, 9:38:33 PM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de buses</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor-xl">

            <section class="tarjeta">

                <div class="formulario-y">

                    <h2 class="titulo">
                        Buses de
                        <c:out value="${requestScope.sucursal.nombre}"/>
                    </h2>

                    <div class="flex w-full justify-center gap-6">

                        <p class="linea">
                            <a href="${pageContext.request.contextPath}/administrador_sucursal/registrar_bus"
                            class="hover:underline">
                                <i class="pi pi-plus mr-2"></i>
                                Registrar nuevo bus
                            </a>
                        </p>

                    </div>

                    <c:choose>

                        <c:when test="${empty requestScope.buses}">

                            <h3 class="linea">
                                No hay buses registrados
                            </h3>

                            <p class="linea-sm">
                                Registre un bus para que aparezca en esta lista.
                            </p>

                        </c:when>

                        <c:otherwise>

                                <table class="w-full text-left text-sm">

                                    <thead class="bg-slate-900 font-semibold text-white">

                                        <tr>
                                            <th scope="col" class="p-4">
                                                Fotografía
                                            </th>

                                            <th scope="col" class="p-4">
                                                Bus
                                            </th>

                                            <th scope="col" class="p-4">
                                                Fabricación
                                            </th>

                                            <th scope="col" class="p-4">
                                                Capacidad
                                            </th>

                                            <th scope="col" class="p-4">
                                                Kilometraje
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

                                                <td class="p-4">

                                                    <div class="h-[50px] w-[80px] overflow-hidden rounded-md bg-slate-100">

                                                        <c:choose>

                                                            <c:when test="${not empty bus.fotografiaBase64}">
                                                                <img
                                                                    src="data:image/jpeg;base64,<c:out value='${bus.fotografiaBase64}'/>"
                                                                    alt="Fotografía del bus"
                                                                    class="h-full w-full object-cover">
                                                            </c:when>

                                                            <c:otherwise>
                                                                <div class="flex h-full w-full items-center justify-center text-slate-400">
                                                                    <i class="pi pi-image"></i>
                                                                </div>
                                                            </c:otherwise>

                                                        </c:choose>

                                                    </div>

                                                </td>

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
                                                    <c:out value="${bus.anioFabricacion}"/>
                                                </td>

                                                <td class="whitespace-nowrap p-4">
                                                    <c:out value="${bus.capacidadPasajeros}"/>
                                                    pasajeros
                                                </td>

                                                <td class="whitespace-nowrap p-4">
                                                    <c:out value="${bus.kilometrajeActual}"/>
                                                    km
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

                                                    <c:url var="urlEditarBus"
                                                        value="/administrador_sucursal/editar_bus">
                                                        <c:param
                                                            name="numeroPlaca"
                                                            value="${bus.numeroPlaca}"/>
                                                    </c:url>

                                                    <a href="${urlEditarBus}"
                                                    class="linea-sky font-medium hover:underline">
                                                        <i class="pi pi-pencil mr-2"></i>
                                                        Editar
                                                    </a>

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
