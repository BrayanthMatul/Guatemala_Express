<%-- 
    Document   : lista-gastos-taller
    Created on : Sep 17, 2026, 11:23:53 PM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Gastos Taller</title>
        <jsp:include page="/includes/recursos.jsp" />
    </head>
    <body>
       <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor-xl">

            <section class="tarjeta">

                <div class="formulario-y">

                    <h2 class="titulo">
                        Gastos de taller de
                        <c:out value="${requestScope.sucursal.nombre}"/>
                    </h2>


                    <p class="linea">
                        <a
                            href="${pageContext.request.contextPath}/administrador_sucursal/gasto_taller"
                            class="hover:underline">
                            <i class="pi pi-plus mr-2"></i>
                            Registrar nuevo gasto
                        </a>
                    </p>


                    <c:choose>

                        <c:when test="${empty requestScope.gastosTaller}">
                            <h3 class="linea">
                                No hay gastos de taller registrados
                            </h3>

                            <p class="linea-sm">
                                Registre un gasto de taller para que aparezca en esta lista.
                            </p>
                        </c:when>

                        <c:otherwise>
                            <div class="w-full overflow-x-auto">

                                <table class="w-full text-left text-sm">

                                    <thead class="bg-slate-900 font-semibold text-white">

                                        <tr>
                                            <th scope="col" class="p-4">
                                                Fecha de mantenimiento
                                            </th>

                                            <th scope="col" class="p-4">
                                                Bus
                                            </th>

                                            <th scope="col" class="p-4">
                                                Mano de obra
                                            </th>

                                            <th scope="col" class="p-4">
                                                Repuestos
                                            </th>

                                            <th scope="col" class="p-4">
                                                Total
                                            </th>

                                        </tr>

                                    </thead>

                                    <tbody class="divide-y divide-gray-200 text-slate-900">

                                        <c:forEach var="gasto" items="${requestScope.gastosTaller}">

                                            <tr class="transition-colors hover:bg-gray-100">

                                                <td class="whitespace-nowrap p-4">
                                                    <i class="pi pi-calendar mr-2"></i>
                                                    <c:out value="${gasto.fechaMantenimiento}"/>
                                                </td>

                                                <td class="whitespace-nowrap p-4">
                                                    <p class="font-semibold">
                                                        <c:out value="${gasto.bus.marca}"/>
                                                        <c:out value="${gasto.bus.modelo}"/>
                                                    </p>

                                                    <p class="mt-1 text-sm">
                                                        Placa:
                                                        <c:out value="${gasto.bus.numeroPlaca}"/>
                                                    </p>
                                                </td>

                                                <td class="whitespace-nowrap p-4">
                                                    Q.
                                                    <c:out value="${gasto.montoManoDeObra}"/>
                                                </td>

                                                <td class="whitespace-nowrap p-4">
                                                    Q.
                                                    <c:out value="${gasto.montoRepuestos}"/>
                                                </td>

                                                <td class="whitespace-nowrap p-4 font-semibold">
                                                    Q.
                                                    <c:out value="${gasto.montoManoDeObra + gasto.montoRepuestos}"/>
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
