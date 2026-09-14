<%-- 
    Document   : newjsp
    Created on : Sep 12, 2026, 2:02:59 AM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${not empty requestScope.mensajeModal}">

        <dialog id="modalMensaje" class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 tarjeta" >
        
            <div class="formulario-y">

                <h2 id="tituloModal" class="text-center text-xl font-bold text-slate-900" >
                    <c:out value="${empty requestScope.tituloModal ? 'Operación realizada' : requestScope.tituloModal}"/>
                </h2>

                <p id="contenidoModal" class="linea" >
                    <c:out value="${requestScope.mensajeModal}"/>
                </p>

                <form method="dialog">
                    <button type="submit" autofocus class="boton-principal" >
                        Aceptar
                    </button>
                </form>

            </div>

        </dialog>

    <script>
        document.getElementById("modalMensaje").showModal();
    </script>

</c:if>