<%-- 
    Document   : consultar-perfil
    Created on : Sep 7, 2026, 9:53:20 PM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar perfil</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>

    <body>

        <jsp:include page="/includes/header.jsp"/>

        <main class="contenedor">

            <section class="tarjeta">

                <jsp:include page="/includes/informacion.jsp"/>

                <h2 class="titulo">
                    Informacion del Perfil
                </h2>

                <p class="linea">
                    Rol: <c:out value="${usuario.rol}"/>
                </p>

                <div class="formulario-y">

                    <div class="formulario-x">

                        <div class="formulario-y">

                            <div>
                                <label class="label-formulario">
                                    Correo electrónico
                                </label>

                                <div class="relative">
                                    <i class="pi pi-envelope icono-input"></i>
                                    <input
                                        type="email"
                                        value="<c:out value='${usuario.correoElectronico}'/>"
                                        readonly
                                        class="input-formulario">
                                </div>
                            </div>

                            <div>
                                <label class="label-formulario">
                                    Nombre de usuario
                                </label>

                                <div class="relative">
                                    <i class="pi pi-user icono-input"></i>
                                    <input
                                        type="text"
                                        value="<c:out value='${usuario.nombreUsuario}'/>"
                                        readonly
                                        class="input-formulario">
                                </div>
                            </div>

                            <div>
                                <label class="label-formulario">
                                    NIT (Número de Identificación Tributaria)
                                </label>

                                <div class="relative">
                                    <i class="pi pi-id-card icono-input"></i>
                                    <input
                                        type="text"
                                        value="<c:out value='${usuario.nit}'/>"
                                        readonly
                                        class="input-formulario">
                                </div>
                            </div>

                            <div>
                                <label class="label-formulario">
                                    DPI (Documento de Identidad Personal)
                                </label>
                                <div class="relative">
                                    <i class="pi pi-id-card icono-input"></i>
                                    <input
                                        type="text"
                                        readonly
                                        value="<c:out value='${usuario.dpi}'/>"
                                        class="input-formulario">
                                </div>
                            </div>

                        </div>

                        <div class="formulario-x">

                            <div>
                                <label class="label-formulario">
                                    Nombre completo
                                </label>

                                <div class="relative">
                                    <i class="pi pi-user icono-input"></i>
                                    <input
                                        type="text"
                                        value="<c:out value='${usuario.nombreCompleto}'/>"
                                        readonly
                                        class="input-formulario">
                                </div>
                            </div>

                            <div>
                                <label class="label-formulario">
                                    Telefono
                                </label>

                                <div class="relative">
                                    <i class="pi pi-phone icono-input"></i>
                                    <input
                                        type="tel"
                                        value="<c:out value='${usuario.telefono}'/>"
                                        readonly
                                        class="input-formulario">
                                </div>
                            </div>

                            <div>
                                <label class="label-formulario">
                                    Dirección
                                </label>

                                <div class="relative">
                                    <i class="pi pi-map-marker icono-input"></i>
                                    <input
                                        type="text"
                                        value="<c:out value='${usuario.direccion}'/>"
                                        readonly
                                        class="input-formulario">
                                </div>
                            </div>

                        </div>

                    </div>

                    <a href="${pageContext.request.contextPath}/perfil/editar">
                        <button
                            type="button"
                            class="boton-principal">
                            Ir a editar perfil
                        </button>
                    </a>

                </div>

            </section>

        </main>

    </body>

</html>

