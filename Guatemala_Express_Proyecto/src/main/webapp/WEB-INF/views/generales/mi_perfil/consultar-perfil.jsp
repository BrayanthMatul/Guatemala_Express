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
        <title>JSP Page</title>
        <jsp:include page="/includes/recursos.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>

        <main class="mx-auto flex min-h-[calc(100vh-4rem)] w-full items-center justify-center">
            <section class="rounded-2xl p-8 shadow-2xl">

                <h2 class="text-center text-2xl font-bold text-gray-800">
                    Informacion del Perfil
                </h2>

                <p class="mb-6 text-center text-sm text-gray-400">
                    Rol: ${usuario.rol}
                </p>


                    <div class="flex flex-row gap-3">
                        <div class="flex flex-col gap-2">
                            <div>
                                <label 
                                    class="mb-2 block text-sm font-semibold text-gray-700">
                                    Correo electrónico
                                </label>

                                <div class="relative">
                                    <i class="pi pi-envelope absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                    <input
                                        type="email"
                                        value="${usuario.correoElectronico}"
                                        readonly
                                        class="w-full rounded-lg border border-blue-200
                                            py-3 pl-11 pr-4 text-blue-950 outline-none
                                            transition focus:border-amber-400
                                            focus:ring-2 focus:ring-amber-400/30">
                                </div>
                            </div>

                            <div>
                                <label 
                                    class="mb-2 block text-sm font-semibold text-gray-700">
                                    NIT (Número de Identificación Tributaria)
                                </label>

                                <div class="relative">
                                    <i class="pi pi-id-card absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                    <input
                                        type="text"
                                        value="${usuario.nit}"
                                        readonly
                                        class="w-full rounded-lg border border-blue-200
                                            py-3 pl-11 pr-4 text-blue-950 outline-none
                                            transition focus:border-amber-400
                                            focus:ring-2 focus:ring-amber-400/30">
                                </div>
                            </div>

                            <div>
                                <label
                                    class="mb-2 block text-sm font-semibold text-gray-700">
                                    DPI (Documento de Identidad Personal)
                                </label>

                                <div class="relative">
                                    <i class="pi pi-id-card absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                    <input
                                        type="text"
                                        readonly
                                        value="${usuario.dpi}"
                                        class="w-full rounded-lg border border-blue-200
                                            py-3 pl-11 pr-4 text-blue-950 outline-none
                                            transition focus:border-amber-400
                                            focus:ring-2 focus:ring-amber-400/30">
                                </div>
                            </div>
                        </div>

                        <div class="flex flex-col gap-2">
                            <div>
                                <label
                                    class="mb-2 block text-sm font-semibold text-gray-700">
                                    Nombre completo
                                </label>

                                <div class="relative">
                                    <i class="pi pi-user absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                    <input
                                        type="text"
                                        value="${usuario.nombreCompleto}"
                                        readonly
                                        class="w-full rounded-lg border border-blue-200
                                            py-3 pl-11 pr-4 text-blue-950 outline-none
                                            transition focus:border-amber-400
                                            focus:ring-2 focus:ring-amber-400/30">
                                </div>
                            </div>

                            <div>
                                <label
                                    class="mb-2 block text-sm font-semibold text-gray-700">
                                    Telefono
                                </label>

                                <div class="relative">
                                    <i class="pi pi-phone absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                    <input
                                        type="tel"
                                        value="${usuario.telefono}"
                                        readonly
                                        class="w-full rounded-lg border border-blue-200
                                            py-3 pl-11 pr-4 text-blue-950 outline-none
                                            transition focus:border-amber-400
                                            focus:ring-2 focus:ring-amber-400/30">
                                </div>
                            </div>

                            <div>
                                <label
                                    class="mb-2 block text-sm font-semibold text-gray-700">
                                    Dirección
                                </label>

                                <div class="relative">
                                    <i class="pi pi-map-marker absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                                    <input
                                        type="text"
                                        value="${usuario.direccion}"
                                        readonly
                                        class="w-full rounded-lg border border-blue-200
                                            py-3 pl-11 pr-4 text-blue-950 outline-none
                                            transition focus:border-amber-400
                                            focus:ring-2 focus:ring-amber-400/30">
                                </div>
                            </div>
                        </div>
                    </div>
            </section>
        </main>
        
    </body>
</html>

<!-- private int id;
    private String nit;
    private String dpi;
    private String nombreCompleto;
    private String telefono;
    private String direccion;
    private String correoElectronico;
    private String contrasenia;
    private Rol rol;
    private BigDecimal saldo;
    private boolean estado; -->
