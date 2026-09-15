<%-- 
    Document   : editar-administrador-sucursal
    Created on : Sep 10, 2026, 4:00:16 AM
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

        <main class="contenedor">

            <p class="linea-circular">
                <a href="${pageContext.request.contextPath}/administrador_sistema/lista_administradores_sucursal"
                class="hover:underline">

                    <i class="pi pi-arrow-left mr-2"></i>
                    Lista de administradores de sucursal
                </a>
            </p>

            <section class="tarjeta">

                <div class="formulario-y">

                    <h2 class="titulo">
                        Editar administrador de sucursal
                    </h2>

                    <c:choose>

                        <c:when test="${empty requestScope.sucursales}">

                            <h3 class="linea">
                                No hay sucursales disponibles
                            </h3>

                            <p class="linea-sm">
                                No es posible editar la sucursal asignada
                                porque no hay sucursales disponibles.
                            </p>

                        </c:when>

                        <c:otherwise>

                            <p class="linea">
                                Todos los datos son obligatorios, por favor ingrese los datos.
                            </p>

                            <form method="POST"
                                action="${pageContext.request.contextPath}/administrador_sistema/editar_administrador_sucursal">

                                <input
                                    type="hidden"
                                    name="nombreUsuario"
                                    value="<c:out value='${nombreUsuario}'/>">

                                <div class="formulario-y">

                                    <div class="formulario-x">

                                        <div class="formulario-y">

                                            <div>
                                                <label for="correoElectronico"
                                                    class="label-formulario">
                                                    Correo electrónico
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-envelope icono-input"></i>

                                                    <input
                                                        id="correoElectronico"
                                                        name="correoElectronico"
                                                        type="email"
                                                        maxlength="255"
                                                        title="Ingrese un formato de correo válido"
                                                        value="<c:out value='${correoElectronico}'/>"
                                                        required
                                                        placeholder="Ejemplo: usuario@correo.com"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                            <div>
                                                <label for="nit"
                                                    class="label-formulario">
                                                    NIT
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-id-card icono-input"></i>

                                                    <input
                                                        id="nit"
                                                        name="nit"
                                                        type="text"
                                                        maxlength="20"
                                                        pattern="[0-9]+-?[0-9Kk]"
                                                        title="Ingrese un NIT válido, por ejemplo 1234567-8"
                                                        value="<c:out value='${nit}'/>"
                                                        required
                                                        placeholder="Ejemplo: 1234567-8"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                            <div>
                                                <label for="dpi"
                                                    class="label-formulario">
                                                    DPI
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-id-card icono-input"></i>

                                                    <input
                                                        id="dpi"
                                                        name="dpi"
                                                        type="text"
                                                        inputmode="numeric"
                                                        pattern="[0-9]{13}"
                                                        minlength="13"
                                                        maxlength="13"
                                                        title="El DPI debe contener 13 números"
                                                        value="<c:out value='${dpi}'/>"
                                                        required
                                                        placeholder="Ejemplo: 1234567890101"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                        </div>

                                        <div class="formulario-y">

                                            <div>
                                                <label for="nombreCompleto"
                                                    class="label-formulario">
                                                    Nombre completo
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-user icono-input"></i>

                                                    <input
                                                        id="nombreCompleto"
                                                        name="nombreCompleto"
                                                        type="text"
                                                        maxlength="255"
                                                        value="<c:out value='${nombreCompleto}'/>"
                                                        required
                                                        placeholder="Nombre completo"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                            <div>
                                                <label for="telefono"
                                                    class="label-formulario">
                                                    Teléfono
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-phone icono-input"></i>

                                                    <input
                                                        id="telefono"
                                                        name="telefono"
                                                        type="tel"
                                                        inputmode="numeric"
                                                        pattern="[0-9]{8}"
                                                        minlength="8"
                                                        maxlength="8"
                                                        title="Debe ingresar un número de 8 dígitos"
                                                        value="<c:out value='${telefono}'/>"
                                                        required
                                                        placeholder="Ejemplo: 77612345"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                            <div>
                                                <label for="direccion"
                                                    class="label-formulario">
                                                    Dirección
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-map-marker icono-input"></i>

                                                    <input
                                                        id="direccion"
                                                        name="direccion"
                                                        type="text"
                                                        maxlength="255"
                                                        value="<c:out value='${direccion}'/>"
                                                        required
                                                        placeholder="Dirección"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                        </div>

                                    </div>

                                    <div>
                                        <label for="sucursalId"
                                            class="label-formulario">
                                            Sucursal asignada
                                        </label>

                                        <div class="relative">
                                            <i class="pi pi-building icono-input"></i>

                                            <select
                                                id="sucursalId"
                                                name="sucursalId"
                                                required
                                                class="input-formulario">

                                                <option value="">
                                                    Seleccione una sucursal
                                                </option>

                                                <c:forEach var="sucursal"
                                                        items="${requestScope.sucursales}">

                                                    <c:choose>

                                                        <c:when test="${sucursal.id == sucursalId}">
                                                            <option
                                                                value="${sucursal.id}"
                                                                selected>

                                                                <c:out value="${sucursal.nombre}"/>
                                                            </option>
                                                        </c:when>

                                                        <c:otherwise>
                                                            <option value="${sucursal.id}">
                                                                <c:out value="${sucursal.nombre}"/>
                                                            </option>
                                                        </c:otherwise>

                                                    </c:choose>

                                                </c:forEach>

                                            </select>
                                        </div>
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
