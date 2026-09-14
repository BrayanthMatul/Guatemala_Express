<%-- 
    Document   : index
    Created on : Sep 5, 2026, 12:25:31 AM
    Author     : matul
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesión</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>

    <body>

        <main class="contenedor-sm">

            <header class="mb-7 text-center">
                <h1>
                    <span class="text-logo">Guatemala</span>
                    <span class="text-logo-acento">Express</span>
                </h1>
            </header>

            <section class="tarjeta">

                <h2 class="titulo">
                    Iniciar sesión
                </h2>

                <p class="linea-sm">
                    Ingrese sus datos para iniciar sesión
                </p>

                <form method="POST" action="${pageContext.request.contextPath}/login">

                    <div class="formulario-y">
                        <div>
                            <label for="Identificador" class="label-formulario">
                                Correo electrónico o nombre de usuario
                            </label>

                            <div class="relative">
                                <i class="pi pi-envelope icono-input"></i>
                                <input
                                    id="Identificador"
                                    name="identificador"
                                    type="text"
                                    value="<c:out value='${requestScope.identificador}'/>"
                                    autocomplete="email"
                                    required
                                    placeholder="nombre.ejemplo@correo.com"
                                    class="input-formulario"
                                >
                            </div>
                        </div>

                        <div>
                            <label for="contrasenia" class="label-formulario">
                                Contraseña
                            </label>

                            <div class="relative">
                                <i class="pi pi-lock icono-input"></i>
                                <input
                                    id="contrasenia"
                                    name="contrasenia"
                                    type="password"
                                    autocomplete="current-password"
                                    required
                                    placeholder="Ingresá tu contraseña"
                                    class="input-formulario"
                                >
                            </div>
                        </div>

                        <jsp:include page="/includes/informacion.jsp"/>

                        <button type="submit" class="boton-principal">
                            Iniciar sesión
                        </button>

                    </div>

                </form>

                <p class="linea-sm">
                    ¿Todavía no tenés una cuenta?

                    <a
                        href="${pageContext.request.contextPath}/crear_cuenta"
                        class="font-bold text-slate-900 hover:underline"
                    >
                        Crear cuenta
                    </a>
                </p>

            </section>

        </main>

    </body>
</html>
