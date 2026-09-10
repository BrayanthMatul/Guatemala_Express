<%-- 
    Document   : recargar-saldo
    Created on : Sep 7, 2026, 10:03:58 PM
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
 

            <% if (request.getAttribute("recargaExito") != null) { %>
                <p class="mb-6 text-center text-sm text-sky-700">
                ${recargaExito}
                </p>
            <% } %>

                <h2 class="text-center text-2xl font-bold text-gray-800">
                    Recargar saldo
                </h2>

                <h2 class="mb-6 text-center text-sm text-sky-700">
                    Saldo actual: Q. ${saldo}
                </h2>
                <p class="mb-6 text-center text-sm text-gray-400">
                    Ingrese la cantidad que desea recargar a su saldo.
                </p>

                <form method="POST" action="${pageContext.request.contextPath}/cartera/recargar_saldo">
                    <input type="hidden" name="idUsuario" value="${idUsuario}"/>

                <div class="flex flex-col gap-2">
                    <div>
                        <label for="Fecha y hora de recarga"
                            class="mb-2 block text-sm font-semibold text-gray-700">
                            Fecha y hora de recarga
                        </label>

                        <div class="relative">
                            <i class="pi pi-calendar absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                            <input
                                id="Fecha y hora de recarga"
                                name="fechaHora"
                                type="datetime-local"
                                title="Ingrese una fecha valida"
                                value="${fechaRecarga}"
                                required
                                class="w-full rounded-lg border border-blue-200
                                    py-3 pl-11 pr-4 text-blue-950 outline-none
                                    transition focus:border-amber-400
                                    focus:ring-2 focus:ring-amber-400/30">
                        </div>
                    </div>

                    <div>
                        <label for="Monto de recarga"
                            class="mb-2 block text-sm font-semibold text-gray-700">
                            Monto de recarga (Q.)
                        </label>

                        <div class="relative">
                            <i class="pi pi-money-bill absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"></i>

                            <input
                                id="Monto de recarga"
                                name="montoRecarga"
                                type="number"
                                inputmode="decimal"
                                min="1.00"
                                max="50000.00"
                                step="0.01"
                                title="Ingrese un monto valido, por ejemplo 100.00"
                                value="${montoRecarga}"
                                placeholder="0.00"
                                required
                                class="w-full rounded-lg border border-blue-200
                                    py-3 pl-11 pr-4 text-blue-950 outline-none
                                    transition focus:border-amber-400
                                    focus:ring-2 focus:ring-amber-400/30">
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
                                Recargar
                            </button>
                    </div>
                </form>
            </section>
        </main>
</html>
