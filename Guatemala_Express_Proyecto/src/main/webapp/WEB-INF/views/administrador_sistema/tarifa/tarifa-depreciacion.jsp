<%-- 
    Document   : tarifa-depreciacion
    Created on : Sep 17, 2026, 10:13:48 PM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambiar Tarifa de Depreciación</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor-sm">

            <section class="tarjeta">

                <h2 class="titulo">
                    Tarifa de depreciación
                </h2>

                <h2 class="linea-sky">
                    Tarifa actual: Q.
                    <c:out value="${requestScope.tarifaDepreciacion.valor}"/>
                    por kilómetro
                </h2>

                <p class="linea">
                    Ingrese la nueva tarifa de depreciación que se aplicará por cada kilómetro recorrido.
                </p>

                <form method="POST" action="${pageContext.request.contextPath}/administrador_sistema/tarifa_depreciacion">

                    <div class="formulario-y">

                        <div>
                            <label for="valor" class="label-formulario">
                                Tarifa de depreciación por kilómetro (Q.)
                            </label>

                            <div class="relative">
                                <i class="pi pi-money-bill icono-input"></i>
                                <input
                                    id="valor"
                                    name="valor"
                                    type="number"
                                    inputmode="decimal"
                                    min="0.00"
                                    step="0.01"
                                    title="Ingrese una tarifa válida, por ejemplo 3.00"
                                    value="<c:out value='${requestScope.tarifaDepreciacion.valor}'/>"
                                    placeholder="0.00"
                                    required
                                    class="input-formulario">
                            </div>
                        </div>

                        <jsp:include page="/includes/informacion.jsp"/>

                        <button type="submit" class="boton-principal">
                            Actualizar tarifa
                        </button>

                    </div>

                </form>

            </section>

        </main>
    </body>
</html>
