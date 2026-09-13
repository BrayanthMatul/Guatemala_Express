<%-- 
    Document   : newjsp
    Created on : Sep 12, 2026, 2:02:59 AM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${not empty requestScope.mensajeModal}">

        <dialog
        id="modalMensaje"
        class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2  rounded-2xl shadow-2xl"
        >
        
        <div class="p-10">
            <h2
                id="tituloModal"
                class="mt-5 text-xl font-bold text-slate-900 text-center"
            >
                <c:out value="${empty requestScope.tituloModal ? 'Operación realizada' : requestScope.tituloModal}"/>
            </h2>

            <p
                id="contenidoModal"
                class="mt-2 text-sm leading-6 text-gray-700"
            >
                <c:out value="${requestScope.mensajeModal}"/>
            </p>

            <form method="dialog" class="mt-7">
                <button
                    type="submit"
                    autofocus
                    class="w-full rounded-lg bg-amber-400 px-5 py-3
                           font-bold text-slate-900 shadow-sm
                           transition-colors duration-200
                           hover:bg-amber-500
                           focus:outline-none focus:ring-4
                           focus:ring-amber-400/30"
                >
                    Aceptar
                </button>
            </form>

        </div>
    </dialog>

    <script>
        document.getElementById("modalMensaje").showModal();
    </script>

</c:if>