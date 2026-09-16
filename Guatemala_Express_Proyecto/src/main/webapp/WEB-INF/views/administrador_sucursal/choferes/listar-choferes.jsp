<%-- 
    Document   : listar-choferes
    Created on : Sep 16, 2026, 12:22:40 AM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Choferes</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor-xl">

            <section class="tarjeta w-full min-w-0">

                <div class="formulario-y">

                    <h2 class="titulo">
                        Choferes de
                        <c:out value="${requestScope.sucursal.nombre}"/>
                    </h2>


                    <p class="linea">
                        <a href="${pageContext.request.contextPath}/administrador_sucursal/registrar_chofer"
                        class="hover:underline">
                            <i class="pi pi-user-plus mr-2"></i>
                            Registrar nuevo chofer
                        </a>
                    </p>

                    <c:choose>

                        <c:when test="${empty requestScope.choferes}">

                            <h3 class="linea">
                                No hay choferes registrados
                            </h3>

                            <p class="linea-sm">
                                Registre un chofer para que aparezca en esta lista.
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
                                                Chofer
                                            </th>

                                            <th scope="col" class="p-4">
                                                Contacto
                                            </th>

                                            <th scope="col" class="p-4">
                                                Licencia
                                            </th>

                                            <th scope="col" class="p-4">
                                                Vencimiento
                                            </th>

                                            <th scope="col" class="p-4">
                                                Salario por viaje
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

                                        <c:forEach var="chofer"
                                                items="${requestScope.choferes}">

                                            <tr class="transition-colors hover:bg-gray-100">

                                                <td class="p-4">

                                                    <div class="h-[50px] w-[50px] overflow-hidden rounded-full bg-slate-100">

                                                        <c:choose>

                                                            <c:when test="${not empty chofer.fotografiaBase64}">
                                                                <img
                                                                    src="data:image/jpeg;base64,<c:out value='${chofer.fotografiaBase64}'/>"
                                                                    alt="Fotografía del chofer"
                                                                    class="h-full w-full object-cover">
                                                            </c:when>

                                                            <c:otherwise>
                                                                <div class="flex h-full w-full items-center justify-center text-slate-400">
                                                                    <i class="pi pi-user"></i>
                                                                </div>
                                                            </c:otherwise>

                                                        </c:choose>

                                                    </div>

                                                </td>

                                                <td class="whitespace-nowrap p-4">

                                                    <p class="font-semibold">
                                                        <c:out value="${chofer.usuario.nombreCompleto}"/>
                                                    </p>

                                                    <p class="mt-1 text-sm">
                                                        @<c:out value="${chofer.usuario.nombreUsuario}"/>
                                                    </p>

                                                </td>

                                                <td class="whitespace-nowrap p-4">

                                                    <p>
                                                        <c:out value="${chofer.usuario.correoElectronico}"/>
                                                    </p>

                                                    <p class="mt-1">
                                                        <c:out value="${chofer.usuario.telefono}"/>
                                                    </p>

                                                </td>

                                                <td class="whitespace-nowrap p-4">

                                                    <p class="font-semibold">
                                                        <c:out value="${chofer.numeroLicencia}"/>
                                                    </p>

                                                    <p class="mt-1 text-sm">
                                                        <c:out value="${chofer.tipoLicencia}"/>
                                                    </p>

                                                </td>

                                                <td class="whitespace-nowrap p-4">
                                                    <c:out value="${chofer.fechaVencimientoLicencia}"/>
                                                </td>

                                                <td class="whitespace-nowrap p-4">
                                                    Q
                                                    <c:out value="${chofer.salarioBasePorViaje}"/>
                                                </td>

                                                <td class="whitespace-nowrap p-4">

                                                    <c:choose>

                                                        <c:when test="${chofer.usuario.estado}">
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

                                                    <c:url var="urlEditarChofer"
                                                        value="/administrador_sucursal/editar_chofer">

                                                        <c:param
                                                            name="nombreUsuario"
                                                            value="${chofer.usuario.nombreUsuario}"/>

                                                    </c:url>

                                                    <a href="${urlEditarChofer}"
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
