<%-- 
    Document   : crear-cuenta
    Created on : Sep 6, 2026, 12:33:01 PM
    Author     : matul
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html lang="es">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear cuenta</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>

    <body>

        <main class="contenedor">

            <section class="tarjeta">

                <h1 class="titulo">
                    Crear cuenta
                </h1>

                <p class="linea">
                    Todos los datos son obligatorios. Por favor, ingrese sus datos.
                </p>

                <form method="POST" action="${pageContext.request.contextPath}/crear_cuenta">

                    <div class="formulario-y">

                        <div class="formulario-x">

                            <div class="formulario-y">

                                <div>
                                    <label for="correoElectronico" class="label-formulario">
                                        Correo electrónico
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-envelope icono-input"></i>
                                        <input
                                            id="correoElectronico"
                                            name="correoElectronico"
                                            type="email"
                                            value="<c:out value='${requestScope.correoElectronico}'/>"
                                            autocomplete="email"
                                            title="Ingrese un correo electrónico válido"
                                            required
                                            placeholder="nombre@correo.com"
                                            class="input-formulario"
                                        >
                                    </div>
                                </div>

                                <div>
                                    <label for="nombreUsuario" class="label-formulario">
                                        Nombre de usuario
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-user icono-input"></i>
                                        <input
                                            id="nombreUsuario"
                                            name="nombreUsuario"
                                            type="text"
                                            value="<c:out value='${requestScope.nombreUsuario}'/>"
                                            autocomplete="username"
                                            minlength="4"
                                            maxlength="30"
                                            pattern="[A-Za-z0-9._-]{4,30}"
                                            title="Utilice entre 4 y 30 caracteres: letras, números, punto, guion o guion bajo"
                                            required
                                            placeholder="Ejemplo: admin.gt"
                                            class="input-formulario"
                                        >
                                    </div>
                                </div>                            

                                <div>
                                    <label for="nit" class="label-formulario">
                                        NIT (Número de Identificación Tributaria)
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-id-card icono-input"></i>
                                        <input
                                            id="nit"
                                            name="nit"
                                            type="text"
                                            maxlength="20"
                                            pattern="[0-9]+-?[0-9Kk]"
                                            value="<c:out value='${requestScope.nit}'/>"
                                            title="Ingrese un NIT válido, por ejemplo 1234567-8"
                                            required
                                            placeholder="1234567-8"
                                            class="input-formulario"
                                        >
                                    </div>
                                </div>

                                <div>
                                    <label for="dpi" class="label-formulario">
                                        DPI (Documento Personal de Identificación)
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
                                            value="<c:out value='${requestScope.dpi}'/>"
                                            title="El DPI debe contener exactamente 13 números"
                                            required
                                            placeholder="1234567890101"
                                            class="input-formulario"
                                        >
                                    </div>
                                </div>

                            </div>

                            <div class="formulario-y">

                                <div>
                                    <label for="contrasenia" class="label-formulario">
                                        Contraseña
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-lock icono-input"></i>
                                        <input
                                            id="contrasenia"
                                            name="contrasenia"
                                            type="text"
                                            required
                                            placeholder="Contraseña segura"
                                            class="input-formulario"
                                        >
                                    </div>
                                </div>

                                <div>
                                    <label for="nombreCompleto" class="label-formulario">
                                        Nombre completo
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-user icono-input"></i>
                                        <input
                                            id="nombreCompleto"
                                            name="nombreCompleto"
                                            type="text"
                                            value="<c:out value='${requestScope.nombreCompleto}'/>"
                                            autocomplete="name"
                                            required
                                            placeholder="Nombre y apellidos"
                                            class="input-formulario"
                                        >
                                    </div>
                                </div>

                                <div>
                                    <label for="telefono" class="label-formulario">
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
                                            value="<c:out value='${requestScope.telefono}'/>"
                                            autocomplete="tel"
                                            title="Debe ingresar un número de 8 dígitos"
                                            required
                                            placeholder="55555555"
                                            class="input-formulario"
                                        >
                                    </div>
                                </div>

                                <div>
                                    <label
                                        for="direccion"
                                        class="label-formulario"
                                    >
                                        Dirección
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-map-marker icono-input"></i>

                                        <input
                                            id="direccion"
                                            name="direccion"
                                            type="text"
                                            value="<c:out value='${requestScope.direccion}'/>"
                                            autocomplete="street-address"
                                            required
                                            placeholder="Dirección completa"
                                            class="input-formulario"
                                        >
                                    </div>
                                </div>

                            </div>

                        </div>

                        <jsp:include page="/includes/informacion.jsp"/>

                        <button type="submit" class="boton-principal">
                            Crear cuenta
                        </button>
                        
                    </div>

                </form>

            </section>

        </main>

    </body>

</html>