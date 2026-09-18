<%-- 
    Document   : gasto-taller
    Created on : Sep 17, 2026, 10:14:22 PM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Gasto de Taller</title>
        <jsp:include page="/includes/recursos.jsp" />
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor-sm">

            <section class="tarjeta">

                <div class="formulario-y">

                    <h2 class="titulo">
                        Gastos de taller
                    </h2>

                    <p class="linea">
                        Sucursal:
                        <strong>
                            <c:out value="${requestScope.sucursal.nombre}"/>
                        </strong>
                    </p>

                    <p class="linea">
                        Registre los gastos de mantenimiento realizados a un bus de la sucursal.
                    </p>

                    <c:choose>

                        <c:when test="${empty requestScope.buses}">

                            <h3 class="linea">
                                No hay buses activos
                            </h3>

                            <p class="linea-sm">
                                Debe tener al menos un bus activo para registrar un gasto de taller.
                            </p>

                        </c:when>

                        <c:otherwise>

                            <form method="POST" action="${pageContext.request.contextPath}/administrador_sucursal/gasto_taller">

                                <div class="formulario-y">

                                    <div>
                                        <label for="numeroPlaca" class="label-formulario">
                                            Bus
                                        </label>

                                        <div class="relative">
                                            <i class="pi pi-car icono-input"></i>
                                            <select
                                                id="numeroPlaca"
                                                name="numeroPlaca"
                                                required
                                                class="input-formulario">

                                                <option value="">
                                                    Seleccione un bus
                                                </option>

                                                <c:forEach var="bus" items="${requestScope.buses}">

                                                    <c:choose>

                                                        <c:when test="${bus.numeroPlaca eq requestScope.numeroPlaca}">
                                                            <option value="${bus.numeroPlaca}" selected>

                                                                <c:out value="${bus.numeroPlaca}"/>
                                                                -
                                                                <c:out value="${bus.marca}"/>
                                                                <c:out value="${bus.modelo}"/>
                                                            </option>
                                                        </c:when>

                                                        <c:otherwise>
                                                            <option value="${bus.numeroPlaca}">
                                                                <c:out value="${bus.numeroPlaca}"/>
                                                                -
                                                                <c:out value="${bus.marca}"/>
                                                                <c:out value="${bus.modelo}"/>
                                                            </option>
                                                        </c:otherwise>

                                                    </c:choose>

                                                </c:forEach>

                                            </select>

                                        </div>

                                    </div>

                                    <div>
                                        <label for="montoManoDeObra" class="label-formulario">
                                            Monto de mano de obra (Q.)
                                        </label>

                                        <div class="relative">
                                            <i class="pi pi-wrench icono-input"></i>
                                            <input
                                                id="montoManoDeObra"
                                                name="montoManoDeObra"
                                                type="number"
                                                inputmode="decimal"
                                                min="0.00"
                                                max="99999999.99"
                                                step="0.01"
                                                value="<c:out value='${requestScope.montoManoDeObra}'/>"
                                                placeholder="0.00"
                                                required
                                                class="input-formulario">
                                        </div>

                                    </div>

                                    <div>
                                        <label for="montoRepuestos" class="label-formulario">
                                            Monto de repuestos (Q.)
                                        </label>

                                        <div class="relative">
                                            <i class="pi pi-cog icono-input"></i>
                                            <input
                                                id="montoRepuestos"
                                                name="montoRepuestos"
                                                type="number"
                                                inputmode="decimal"
                                                min="0.00"
                                                max="99999999.99"
                                                step="0.01"
                                                value="<c:out value='${requestScope.montoRepuestos}'/>"
                                                placeholder="0.00"
                                                required
                                                class="input-formulario">
                                        </div>
                                    </div>

                                    <jsp:include page="/includes/informacion.jsp"/>

                                    <button type="submit" class="boton-principal">
                                        Registrar gasto
                                    </button>

                                </div>

                            </form>

                        </c:otherwise>

                    </c:choose>

                </div>

            </section>

        </main>
    </body>
</html>
