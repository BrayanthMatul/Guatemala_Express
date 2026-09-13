<%-- 
    Document   : registar-administrador-sistema
    Created on : Sep 10, 2026, 3:55:28 AM
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
        <main class="mx-auto flex min-h-[calc(100vh-4rem)] w-full items-center justify-center">
            <section class="rounded-2xl p-8 shadow-2xl">

                <h2 class="text-center text-2xl font-bold text-gray-800">
                    Registrar administrador del sistema
                </h2>
                <p class="mb-6 text-center text-sm text-gray-400">
                    Todos los datos son obligatorios, por favor ingrese los datos.
                </p>

                <form method="POST" action="${pageContext.request.contextPath}/administrador_sistema/registrar_administrador_sistema">

                    <div class="flex flex-row gap-3">
                        <div class="flex flex-col gap-2">
                            <div>
                                <label for="Correo electrónico"
                                    class="mb-2 block text-sm font-semibold text-gray-700">
                                    Correo electrónico
                                </label>

                                <div class="relative">
                                    <i class="pi pi-envelope absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                    <input
                                        id="Correo electrónico"
                                        name="correoElectronico"
                                        type="email"
                                        title="Ingrese un formato de correo valido"
                                        value="${correoElectronico}"
                                        required
                                        placeholder="1234567890123"
                                        class="w-full rounded-lg border border-blue-200
                                            py-3 pl-11 pr-4 text-blue-950 outline-none
                                            transition focus:border-amber-400
                                            focus:ring-2 focus:ring-amber-400/30">
                                </div>
                            </div>

                            <div>
                                <label for="Contrasenia"
                                    class="mb-2 block text-sm font-semibold text-gray-700">
                                    Contraseña
                                </label>

                                <div class="relative">
                                    <i class="pi pi-lock absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                    <input
                                        id="Contrasenia"
                                        name="contrasenia"
                                        type="text"
                                        value="${contrasenia}"
                                        required
                                        placeholder="Contraseña segura"
                                        class="w-full rounded-lg border border-blue-200
                                            py-3 pl-11 pr-4 text-blue-950 outline-none
                                            transition focus:border-amber-400
                                            focus:ring-2 focus:ring-amber-400/30">
                                </div>
                            </div>

                            <div>
                                <label for="Nit"
                                    class="mb-2 block text-sm font-semibold text-gray-700">
                                    NIT (Número de Identificación Tributaria)
                                </label>

                                <div class="relative">
                                    <i class="pi pi-id-card absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                    <input
                                        id="Nit"
                                        name="nit"
                                        type="text"
                                        maxlength="20"
                                        pattern="[0-9]+-?[0-9Kk]"
                                        title="Ingrese un NIT valido, por ejemplo 1234567-8"
                                        value="${nit}"
                                        required
                                        placeholder="1234567890123"
                                        class="w-full rounded-lg border border-blue-200
                                            py-3 pl-11 pr-4 text-blue-950 outline-none
                                            transition focus:border-amber-400
                                            focus:ring-2 focus:ring-amber-400/30">
                                </div>
                            </div>

                            <div>
                                <label for="DPI"
                                    class="mb-2 block text-sm font-semibold text-gray-700">
                                    DPI (Documento de Identidad Personal)
                                </label>

                                <div class="relative">
                                    <i class="pi pi-id-card absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                    <input
                                        id="DPI"
                                        name="dpi"
                                        type="text"
                                        inputmode="numeric"
                                        pattern="[0-9]{13}"
                                        minlength="13"
                                        maxlength="13"
                                        value="${dpi}"
                                        title="El DPI debe tener al menos 13 numeros"
                                        required
                                        placeholder="1234567890123"
                                        class="w-full rounded-lg border border-blue-200
                                            py-3 pl-11 pr-4 text-blue-950 outline-none
                                            transition focus:border-amber-400
                                            focus:ring-2 focus:ring-amber-400/30">
                                </div>
                            </div>
                        </div>

                        <div class="flex flex-col gap-2">
                            <div>
                                <label for="Nombre Completo"
                                    class="mb-2 block text-sm font-semibold text-gray-700">
                                    Nombre completo
                                </label>

                                <div class="relative">
                                    <i class="pi pi-user absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                    <input
                                        id="Nombre Completo"
                                        name="nombreCompleto"
                                        type="text"
                                        value="${nombreCompleto}"
                                        required
                                        placeholder="1234567890123"
                                        class="w-full rounded-lg border border-blue-200
                                            py-3 pl-11 pr-4 text-blue-950 outline-none
                                            transition focus:border-amber-400
                                            focus:ring-2 focus:ring-amber-400/30">
                                </div>
                            </div>

                            <div>
                                <label for="Telefono"
                                    class="mb-2 block text-sm font-semibold text-gray-700">
                                    Telefono
                                </label>

                                <div class="relative">
                                    <i class="pi pi-phone absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                    <input
                                        id="Telefono"
                                        name="telefono"
                                        type="tel"
                                        input="numeric"
                                        pattern="[0-9]{8}"
                                        minlength="8"
                                        maxlength="8"
                                        title="Debe ingresar un numero de 8 digitos"
                                        value="${telefono}"
                                        required
                                        placeholder="1234567890123"
                                        class="w-full rounded-lg border border-blue-200
                                            py-3 pl-11 pr-4 text-blue-950 outline-none
                                            transition focus:border-amber-400
                                            focus:ring-2 focus:ring-amber-400/30">
                                </div>
                            </div>

                            <div>
                                <label for="Direccion"
                                    class="mb-2 block text-sm font-semibold text-gray-700">
                                    Dirección
                                </label>

                                <div class="relative">
                                    <i class="pi pi-map-marker absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                    <input
                                        id="Direccion"
                                        name="direccion"
                                        type="text"
                                        value="${direccion}"
                                        required
                                        placeholder="1234567890123"
                                        class="w-full rounded-lg border border-blue-200
                                            py-3 pl-11 pr-4 text-blue-950 outline-none
                                            transition focus:border-amber-400
                                            focus:ring-2 focus:ring-amber-400/30">
                                </div>
                            </div>
                        </div>
                    </div>

                    <jsp:include page="/includes/informacion.jsp"/>
                    
                    <div class="mt-3 flex items-center justify-center w-full">
                            <button
                                type="submit"
                                class="w-xs rounded-lg bg-amber-400 px-5 py-3
                                    font-bold text-slate-900 transition
                                    hover:bg-amber-300 focus:outline-none
                                    focus:ring-2 focus:ring-amber-400
                                    focus:ring-offset-2">
                                Registrar
                            </button>
                    </div>
                </form>
            </section>
        </main>
    </body>
</html>
