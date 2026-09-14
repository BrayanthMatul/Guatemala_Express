<%-- 
    Document   : recargar-saldo
    Created on : Sep 7, 2026, 10:03:58 PM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recargar saldo</title>
        <jsp:include page="/includes/recursos.jsp"/>  
    </head>

    <body>

        <jsp:include page="/includes/header.jsp"/>  
       
        <main class="contenedor">
           
            <section class="tarjeta">

                <c:if test="${not empty requestScope.recargaExito}">
                    <p class="mb-6 text-center text-sm text-sky-700">
                        <c:out value="${requestScope.recargaExito}"/>
                    </p>
                </c:if>

                <h2 class="titulo">
                    Recargar saldo
                </h2>

                <h2 class="linea-sky">
                    Saldo actual: Q. <c:out value="${requestScope.saldo}"/>
                </h2>

                <p class="linea">
                    Ingrese la cantidad que desea recargar a su saldo.
                </p>

                <form method="POST" action="${pageContext.request.contextPath}/cartera/recargar_saldo">

                    <div class="formulario-y">

                        <div>
                            <label for="Fecha y hora de recarga" class="label-formulario">
                                Fecha y hora de recarga
                            </label>

                            <div class="relative">
                                <i class="pi pi-calendar icono-input"></i>
                                <input
                                    id="Fecha y hora de recarga"
                                    name="fechaHora"
                                    type="datetime-local"
                                    title="Ingrese una fecha valida"
                                    value="<c:out value='${requestScope.fechaRecarga}'/>"
                                    required
                                    class="input-formulario">
                            </div>
                        </div>

                        <div>
                            <label for="Monto de recarga" class="label-formulario">
                                Monto de recarga (Q.)
                            </label>

                            <div class="relative">
                                <i class="pi pi-money-bill icono-input"></i>
                                <input
                                    id="Monto de recarga"
                                    name="montoRecarga"
                                    type="number"
                                    inputmode="decimal"
                                    min="1.00"
                                    max="50000.00"
                                    step="0.01"
                                    title="Ingrese un monto valido, por ejemplo 100.00"
                                    value="<c:out value='${requestScope.montoRecarga}'/>"
                                    placeholder="0.00"
                                    required
                                    class="input-formulario">
                            </div>
                        </div>

                        <jsp:include page="/includes/informacion.jsp"/>

                        <button type="submit" class="boton-principal">
                            Recargar
                        </button>

                    </div>

                </form>

            </section>

        </main>

    </body>

</html>