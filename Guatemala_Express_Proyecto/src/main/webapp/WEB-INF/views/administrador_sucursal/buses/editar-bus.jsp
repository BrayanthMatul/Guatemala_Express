<%-- 
    Document   : editar-bus
    Created on : Sep 15, 2026, 9:38:13 PM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Bus</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor">

            <p class="linea-circular">
                <a href="${pageContext.request.contextPath}/administrador_sucursal/listar_buses"
                class="hover:underline">
                    <i class="pi pi-arrow-left mr-2"></i>
                    Lista de buses
                </a>
            </p>

            <section class="tarjeta">

                <div class="formulario-y">

                    <h2 class="titulo">
                        Editar bus
                    </h2>

                    <c:choose>

                        <c:when test="${empty requestScope.numeroPlaca || empty requestScope.sucursal}">

                            <h3 class="linea">
                                No fue posible cargar el bus
                            </h3>

                            <p class="linea-sm">
                                Regrese a la lista y seleccione nuevamente
                                el bus que desea editar.
                            </p>

                        </c:when>

                        <c:otherwise>

                            <p class="linea">
                                Bus asignado a
                                <strong>
                                    <c:out value="${requestScope.sucursal.nombre}"/>
                                </strong>.
                            </p>

                            <p class="linea-sm">
                                Placa:
                                <strong>
                                    <c:out value="${requestScope.numeroPlaca}"/>
                                </strong>
                            </p>

                            <form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/administrador_sucursal/editar_bus">

                                <div class="formulario-y">

                                    <div class="formulario-x">

                                        <div class="formulario-y">

                                            <input type="hidden" name="numeroPlaca" value="<c:out value='${requestScope.numeroPlaca}'/>">

                                            <div>
                                                <label for="marca"
                                                    class="label-formulario">
                                                    Marca
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-tag icono-input"></i>

                                                    <input
                                                        id="marca"
                                                        name="marca"
                                                        type="text"
                                                        maxlength="255"
                                                        value="<c:out value='${requestScope.marca}'/>"
                                                        required
                                                        placeholder="Ejemplo: Toyota"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                            <div>
                                                <label for="modelo"
                                                    class="label-formulario">
                                                    Modelo
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-car icono-input"></i>

                                                    <input
                                                        id="modelo"
                                                        name="modelo"
                                                        type="text"
                                                        maxlength="255"
                                                        value="<c:out value='${requestScope.modelo}'/>"
                                                        required
                                                        placeholder="Ejemplo: Coaster"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                        </div>

                                        <div class="formulario-y">

                                            <div>
                                                <label for="anioFabricacion"
                                                    class="label-formulario">
                                                    Año de fabricación
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-calendar icono-input"></i>

                                                    <input
                                                        id="anioFabricacion"
                                                        name="anioFabricacion"
                                                        type="number"
                                                        min="1900"
                                                        value="<c:out value='${requestScope.anioFabricacion}'/>"
                                                        required
                                                        placeholder="Ejemplo: 2024"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                            <div>
                                                <label for="capacidadPasajeros"
                                                    class="label-formulario">
                                                    Capacidad de pasajeros
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-users icono-input"></i>

                                                    <input
                                                        id="capacidadPasajeros"
                                                        name="capacidadPasajeros"
                                                        type="number"
                                                        min="1"
                                                        value="<c:out value='${requestScope.capacidadPasajeros}'/>"
                                                        required
                                                        placeholder="Ejemplo: 40"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                        </div>

                                    </div>

                                    <div>
                                        <label for="fotografia"
                                            class="label-formulario">
                                            Nueva fotografía
                                        </label>

                                        <input
                                            id="fotografia"
                                            name="fotografia"
                                            type="file"
                                            accept="image/jpeg"
                                            class="input-formulario">

                                        <p class="mt-2 text-sm text-slate-500">
                                            Este campo es opcional. Si no selecciona
                                            una fotografía, se conservará la actual.
                                        </p>
                                    </div>

                                    <jsp:include page="/includes/informacion.jsp"/>

                                    <button type="submit" class="boton-principal">
                                        Editar
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
