<%-- 
    Document   : registrar-chofer
    Created on : Sep 16, 2026, 12:22:12 AM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Chofer</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor-lg">

            <section class="tarjeta">

                <div class="formulario-y">

                    <h2 class="titulo">
                        Registrar chofer
                    </h2>

                    <c:choose>

                        <c:when test="${empty requestScope.sucursal}">

                            <h3 class="linea">
                                No hay una sucursal asignada
                            </h3>

                            <p class="linea-sm">
                                No puede registrar choferes porque no tiene
                                una sucursal asignada.
                            </p>

                        </c:when>

                        <c:otherwise>

                            <p class="linea">
                                El chofer será registrado en
                                <strong>
                                    <c:out value="${requestScope.sucursal.nombre}"/>
                                </strong>.
                            </p>

                            <form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/administrador_sucursal/registrar_chofer">

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
                                                        value="<c:out value='${requestScope.correoElectronico}'/>"
                                                        required
                                                        placeholder="Ejemplo: chofer@correo.com"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                            <div>
                                                <label for="nombreUsuario"
                                                    class="label-formulario">
                                                    Nombre de usuario
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-user icono-input"></i>

                                                    <input
                                                        id="nombreUsuario"
                                                        name="nombreUsuario"
                                                        type="text"
                                                        autocomplete="username"
                                                        minlength="4"
                                                        maxlength="30"
                                                        pattern="[A-Za-z0-9._-]{4,30}"
                                                        title="Utilice entre 4 y 30 caracteres: letras, números, puntos, guiones y guiones bajos"
                                                        value="<c:out value='${requestScope.nombreUsuario}'/>"
                                                        required
                                                        placeholder="Ejemplo: chofer1"
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
                                                        value="<c:out value='${requestScope.nit}'/>"
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
                                                        value="<c:out value='${requestScope.dpi}'/>"
                                                        required
                                                        placeholder="Ejemplo: 1234567890101"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                            <div>
                                                <label for="contrasenia"
                                                    class="label-formulario">
                                                    Contraseña
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-lock icono-input"></i>

                                                    <input
                                                        id="contrasenia"
                                                        name="contrasenia"
                                                        type="password"
                                                        autocomplete="new-password"
                                                        required
                                                        placeholder="Contraseña segura"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                            <div>
                                                <label for="numeroLicencia"
                                                    class="label-formulario">
                                                    Número de licencia
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-id-card icono-input"></i>

                                                    <input
                                                        id="numeroLicencia"
                                                        name="numeroLicencia"
                                                        type="text"
                                                        maxlength="50"
                                                        value="<c:out value='${requestScope.numeroLicencia}'/>"
                                                        required
                                                        placeholder="Número de licencia"
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
                                                        value="<c:out value='${requestScope.nombreCompleto}'/>"
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
                                                        title="Ingrese un número de 8 dígitos"
                                                        value="<c:out value='${requestScope.telefono}'/>"
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
                                                        value="<c:out value='${requestScope.direccion}'/>"
                                                        required
                                                        placeholder="Dirección"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                            <div>
                                                <label for="tipoLicencia"
                                                    class="label-formulario">
                                                    Tipo de licencia
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-car icono-input"></i>

                                                    <input
                                                        id="tipoLicencia"
                                                        name="tipoLicencia"
                                                        type="text"
                                                        maxlength="50"
                                                        value="<c:out value='${requestScope.tipoLicencia}'/>"
                                                        required
                                                        placeholder="Ejemplo: Tipo A"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                            <div>
                                                <label for="fechaVencimientoLicencia"
                                                    class="label-formulario">
                                                    Vencimiento de licencia
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-calendar icono-input"></i>

                                                    <input
                                                        id="fechaVencimientoLicencia"
                                                        name="fechaVencimientoLicencia"
                                                        type="date"
                                                        value="<c:out value='${requestScope.fechaVencimientoLicencia}'/>"
                                                        required
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                            <div>
                                                <label for="salarioBasePorViaje"
                                                    class="label-formulario">
                                                    Salario base por viaje
                                                </label>

                                                <div class="relative">
                                                    <i class="pi pi-money-bill icono-input"></i>

                                                    <input
                                                        id="salarioBasePorViaje"
                                                        name="salarioBasePorViaje"
                                                        type="number"
                                                        min="0.01"
                                                        step="0.01"
                                                        value="<c:out value='${requestScope.salarioBasePorViaje}'/>"
                                                        required
                                                        placeholder="Ejemplo: 250.00"
                                                        class="input-formulario">
                                                </div>
                                            </div>

                                        </div>

                                    </div>

                                    <div>
                                        <label for="fotografia"
                                            class="label-formulario">
                                            Fotografía del chofer
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

                                    <button type="submit"
                                            class="boton-principal">
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
