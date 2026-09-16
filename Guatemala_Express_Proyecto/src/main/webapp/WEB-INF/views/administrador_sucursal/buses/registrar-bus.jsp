<%-- 
    Document   : registrar-bus
    Created on : Sep 15, 2026, 9:38:05 PM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Bus</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor">

            <section class="tarjeta">

                <div class="formulario-y">

                    <h2 class="titulo">
                        Registrar bus
                    </h2>

                    <c:choose>

                        <c:when test="${empty requestScope.sucursal}">

                            <h3 class="linea">
                                No hay una sucursal asignada
                            </h3>

                            <p class="linea-sm">
                                No puede registrar buses porque no tiene una
                                sucursal asignada.
                            </p>

                        </c:when>

                        <c:otherwise>

                            <p class="linea">
                                El bus será registrado en
                                <strong>
                                    <c:out value="${requestScope.sucursal.nombre}"/>
                                </strong>.
                            </p>

                            <form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/administrador_sucursal/registrar_bus">

                                <div class="formulario-y">

                                    <div class="formulario-x">

                                        <div class="formulario-y">

                                            <div>
                                                <label for="numeroPlaca"
                                                    class="label-formulario">
                                                    Número de placa
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-id-card icono-input"></i>

                                                    <input
                                                        id="numeroPlaca"
                                                        name="numeroPlaca"
                                                        type="text"
                                                        maxlength="20"
                                                        pattern="[A-Za-z0-9-]{4,20}"
                                                        title="Utilice entre 4 y 20 caracteres: letras, números y guiones"
                                                        value="<c:out value='${requestScope.numeroPlaca}'/>"
                                                        required
                                                        placeholder="Ejemplo: C123BCD"
                                                        class="input-formulario">
                                                </div>
                                            </div>

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

                                            <div>
                                                <label for="kilometrajeActual"
                                                    class="label-formulario">
                                                    Kilometraje actual
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-gauge icono-input"></i>

                                                    <input
                                                        id="kilometrajeActual"
                                                        name="kilometrajeActual"
                                                        type="number"
                                                        min="0"
                                                        step="0.01"
                                                        value="<c:out value='${requestScope.kilometrajeActual}'/>"
                                                        required
                                                        placeholder="Ejemplo: 15000.50"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                        </div>

                                    </div>

                                    <div>
                                        <label for="fotografia"
                                            class="label-formulario">
                                            Fotografía del bus
                                        </label>

                                        <input
                                            id="fotografia"
                                            name="fotografia"
                                            type="file"
                                            accept="image/jpeg"
                                            required
                                            class="input-formulario">

                                        <p class="mt-2 text-sm text-slate-500">
                                            Seleccione una imagen JPG de hasta 5 MB.
                                        </p>
                                    </div>

                                    <jsp:include page="/includes/informacion.jsp"/>

                                    <button type="submit" class="boton-principal">
                                        Registrar
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
