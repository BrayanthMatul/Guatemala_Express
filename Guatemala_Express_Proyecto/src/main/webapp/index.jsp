<%-- 
    Document   : index
    Created on : Sep 5, 2026, 12:25:31 AM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link rel="stylesheet"
        <jsp:include page="/includes/recursos.jsp"/>
    </head>

    <body class="min-h-screen px-4 py-8">

        <main class="mx-auto flex min-h-[calc(100vh-4rem)] w-full max-w-md place-items-center">

            <div class="w-full">

                <!-- Identidad -->
                <header class="mb-7 text-center">


                    <h1 class="mt-4 text-3xl font-bold text-slate-900">
                        Guatemala Express
                    </h1>

                </header>

                <!-- Formulario -->
                <section class="rounded-2xl p-8 shadow-2xl">

                    <h2 class="text-center text-2xl font-bold text-gray-800">
                        Iniciar sesión
                    </h2>
                    <p class="mb-6 text-center text-sm text-gray-400">
                        Ingrese sus datos para iniciar sesión
                    </p>

                    <form method="POST" action="${pageContext.request.contextPath}/login" class="space-y-5">

                        <div>
                            <label for="correo"
                                class="mb-2 block text-sm font-semibold text-gray-700">
                                Correo electrónico
                            </label>

                            <div class="relative">
                                <i class="pi pi-envelope absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                <input
                                    id="correo"
                                    name="correo"
                                    type="email"
                                    value="${correo}"
                                    autocomplete="email"
                                    required
                                    placeholder="nombre.ejemplo@correo.com"
                                    class="w-full rounded-lg border border-blue-200
                                        py-3 pl-11 pr-4 text-blue-950 outline-none
                                        transition focus:border-amber-400
                                        focus:ring-2 focus:ring-amber-400/30">
                            </div>
                        </div>

                        <div>
                            <label for="contrasenia"
                                class="mb-2 block text-sm font-semibold text-gray-700">
                                Contraseña
                            </label>

                            <div class="relative">
                                <i class="pi pi-lock absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                <input
                                    id="contrasenia"
                                    name="contrasenia"
                                    type="password"
                                    autocomplete="current-password"
                                    required
                                    placeholder="Ingresá tu contraseña"
                                    class="w-full rounded-lg border border-blue-200
                                        py-3 pl-11 pr-4 text-blue-950 outline-none
                                        transition focus:border-amber-400
                                        focus:ring-2 focus:ring-amber-400/30">
                            </div>
                        </div>

                        <jsp:include page="/includes/informacion.jsp"/>
                        
                        <button
                            type="submit"
                            class="w-full rounded-lg bg-amber-400 px-5 py-3
                                font-bold text-slate-900 transition
                                hover:bg-amber-300 focus:outline-none
                                focus:ring-2 focus:ring-amber-400
                                focus:ring-offset-2">
                            Iniciar sesión
                        </button>
                    </form>

                    <p class="mt-6 text-center text-sm text-gray-700">
                        ¿Todavía no tenés una cuenta?

                        <a href="${pageContext.request.contextPath}/crear_cuenta"
                        class="font-bold text-slate-900 hover:underline">
                            Crear cuenta
                        </a>
                    </p>

                </section>
            </div>

        </main>

    </body>
</html>
