<%-- 
    Document   : editar-admin-sistema.jsp
    Created on : Sep 14, 2026, 11:17:54 PM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar administrador del sistema</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>  
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor">

            <p class="linea-circular">
                <a href="${pageContext.request.contextPath}/administrador_sistema/lista_administradores_sistema" class="hover:underline">
                    <i class="pi pi-arrow-left mr-2"></i>
                    Lista de Administradores del sistema
                </a>
            </p>
            

            <section class="tarjeta">

                <h2 class="titulo">
                    Editar administrador del sistema
                </h2>

                <p class="linea">
                    Todos los datos son obligatorios, por favor ingrese sus datos.
                </p>

                <form method="POST" action="${pageContext.request.contextPath}/administrador_sistema/editar_admin_sistema">

                    <div class="formulario-y">

                        <div class="formulario-x">

                            <div class="formulario-y">

                                <input type="hidden" name="nombreUsuario" value="<c:out value='${nombreUsuario}'/>">

                                <div>
                                    <label for="Correo electrónico" class="label-formulario">
                                        Correo electrónico
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-envelope icono-input"></i>
                                        <input
                                            id="Correo electrónico"
                                            name="correoElectronico"
                                            type="email"
                                            title="Ingrese un formato de correo valido"
                                            value="<c:out value='${correoElectronico}'/>"
                                            required
                                            placeholder="1234567890123"
                                            class="input-formulario">
                                    </div>
                                </div>

                                <div>
                                    <label for="Nit" class="label-formulario">
                                        NIT (Número de Identificación Tributaria)
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-id-card icono-input"></i>
                                        <input
                                            id="Nit"
                                            name="nit"
                                            type="text"
                                            maxlength="20"
                                            pattern="[0-9]+-?[0-9Kk]"
                                            title="Ingrese un NIT valido, por ejemplo 1234567-8"
                                            value="<c:out value='${nit}'/>"
                                            required
                                            placeholder="1234567890123"
                                            class="input-formulario">
                                    </div>
                                </div>

                                <div>
                                    <label for="DPI" class="label-formulario">
                                        DPI (Documento de Identidad Personal)
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-id-card icono-input"></i>
                                        <input
                                            id="DPI"
                                            name="dpi"
                                            type="text"
                                            inputmode="numeric"
                                            pattern="[0-9]{13}"
                                            minlength="13"
                                            maxlength="13"
                                            value="<c:out value='${dpi}'/>"
                                            title="El DPI debe tener al menos 13 numeros"
                                            required
                                            placeholder="1234567890123"
                                            class="input-formulario">
                                    </div>
                                </div>

                            </div>

                            <div class="formulario-y">
                                <div>
                                    <label for="Nombre Completo" class="label-formulario">
                                        Nombre completo
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-user icono-input"></i>
                                        <input
                                            id="Nombre Completo"
                                            name="nombreCompleto"
                                            type="text"
                                            value="<c:out value='${nombreCompleto}'/>"
                                            required
                                            placeholder="1234567890123"
                                            class="input-formulario">
                                    </div>
                                </div>

                                <div>
                                    <label for="Telefono" class="label-formulario">
                                        Telefono
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-phone icono-input"></i>
                                        <input
                                            id="Telefono"
                                            name="telefono"
                                            type="tel"
                                            input="numeric"
                                            pattern="[0-9]{8}"
                                            minlength="8"
                                            maxlength="8"
                                            title="Debe ingresar un numero de 8 digitos"
                                            value="<c:out value='${telefono}'/>"
                                            required
                                            placeholder="1234567890123"
                                            class="input-formulario">
                                    </div>

                                </div>

                                <div>
                                    <label for="Direccion" class="label-formulario">
                                        Dirección
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-map-marker icono-input"></i>
                                        <input
                                            id="Direccion"
                                            name="direccion"
                                            type="text"
                                            value="<c:out value='${direccion}'/>"
                                            required
                                            placeholder="1234567890123"
                                            class="input-formulario">
                                    </div>
                                </div>

                            </div>

                        </div>

                        <jsp:include page="/includes/informacion.jsp"/>

                        <button type="submit" class="boton-principal">
                            Editar
                        </button>
                    
                    </div>

                </form>

            </section>

        </main>

    </body>
    
</html>
