<%-- 
    Document   : editar-ruta
    Created on : Sep 16, 2026, 1:50:49 AM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar ruta regular</title>
        <jsp:include page="/includes/recursos.jsp"/>
        <jsp:include page="/includes/leaflet.jsp"/>
    </head>

    <body>

        <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor">

            <p class="linea-circular">

                <a
                    href="${pageContext.request.contextPath}/administrador_sucursal/listar_rutas_regulares"
                    class="hover:underline">

                    <i class="pi pi-arrow-left mr-2"></i>

                    Lista de rutas regulares

                </a>

            </p>

            <section class="tarjeta">

                <h2 class="titulo">
                    Editar ruta regular
                </h2>

                <p class="linea">
                    Todos los datos son obligatorios, por favor ingrese los datos.
                </p>

                <form
                    method="POST"
                    action="${pageContext.request.contextPath}/administrador_sucursal/editar_ruta_regular">

                    <input
                        type="hidden"
                        name="idRuta"
                        value="<c:out value='${requestScope.idRuta}'/>">

                    <div class="formulario-y">

                        <div class="formulario-x">

                            <div class="formulario-y">

                                <div>

                                    <label for="sucursalOrigen" class="label-formulario">
                                        Sucursal de origen
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-building icono-input"></i>
                                        <input
                                            id="sucursalOrigen"
                                            type="text"
                                            readonly
                                            value="<c:out value='${requestScope.sucursalOrigen.nombre}'/>"
                                            class="input-formulario bg-gray-100">
                                    </div>

                                </div>

                            </div>

                            <div class="formulario-y">

                                <div>
                                    <label for="precioBoleto" class="label-formulario">
                                        Precio del boleto
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-money-bill icono-input"></i>
                                        <input
                                            id="precioBoleto"
                                            name="precioBoleto"
                                            type="number"
                                            min="0"
                                            step="0.01"
                                            value="<c:out value='${requestScope.precioBoleto}'/>"
                                            required
                                            placeholder="Ejemplo: 75.00"
                                            class="input-formulario">
                                    </div>
                                </div>

                            </div>

                        </div>

                        <div>

                            <label for="idSucursalDestino" class="label-formulario">
                                Sucursal de destino
                            </label>

                            <div class="relative">
                                <i class="pi pi-map-marker icono-input"></i>
                                <select
                                    id="idSucursalDestino"
                                    name="idSucursalDestino"
                                    required
                                    class="input-formulario">

                                    <option value="">
                                        Seleccione una sucursal
                                    </option>

                                    <c:set var="haySucursalesDestino" value="false"/>

                                    <c:forEach
                                        var="sucursal"
                                        items="${requestScope.sucursales}">

                                        <c:if test="${sucursal.id ne requestScope.sucursalOrigen.id}">

                                            <c:set var="haySucursalesDestino" value="true"/>

                                            <c:choose>

                                                <c:when test="${sucursal.id eq requestScope.idSucursalDestino}">
                                                    <option
                                                        value="${sucursal.id}"
                                                        data-latitud="${sucursal.latitud}"
                                                        data-longitud="${sucursal.longitud}"
                                                        selected>

                                                        <c:out value="${sucursal.nombre}"/>
                                                        -
                                                        <c:out value="${sucursal.municipio}"/>

                                                    </option>
                                                </c:when>

                                                <c:otherwise>
                                                    <option
                                                        value="${sucursal.id}"
                                                        data-latitud="${sucursal.latitud}"
                                                        data-longitud="${sucursal.longitud}">

                                                        <c:out value="${sucursal.nombre}"/>
                                                        -
                                                        <c:out value="${sucursal.municipio}"/>

                                                    </option>
                                                </c:otherwise>

                                            </c:choose>

                                        </c:if>

                                    </c:forEach>

                                </select>

                            </div>

                        </div>

                        <input
                            id="distanciaAproximadaKm"
                            name="distanciaAproximadaKm"
                            type="hidden"
                            value="<c:out value='${requestScope.distanciaAproximadaKm}'/>">

                        <input
                            id="duracionEstimada"
                            name="duracionEstimada"
                            type="hidden"
                            value="<c:out value='${requestScope.duracionEstimada}'/>">

                        <div>
                            <label class="label-formulario text-center">
                                Recorrido de la ruta
                            </label>

                            <p class="linea">
                                La distancia y duración estimada se calcularán automáticamente.
                            </p>

                            <div
                                id="mapaRuta"
                                data-latitud-origen="${requestScope.sucursalOrigen.latitud}"
                                data-longitud-origen="${requestScope.sucursalOrigen.longitud}"
                                class="h-96 w-full rounded-lg">
                            </div>

                        </div>

                        <div class="formulario-x">

                            <p class="linea">
                                <i class="pi pi-arrows-h mr-2"></i>
                                Distancia aproximada:
                                <span id="distanciaMostrada" class="font-semibold">
                                    <c:out value="${requestScope.distanciaAproximadaKm}"/> km
                                </span>
                            </p>

                            <p class="linea">
                                <i class="pi pi-clock mr-2"></i>
                                Duración estimada:
                                <span id="duracionMostrada" class="font-semibold">
                                    <c:out value="${requestScope.duracionEstimada}"/>
                                </span>
                            </p>

                        </div>

                        <c:if test="${not haySucursalesDestino}">
                            <p class="linea">
                                No hay otra sucursal disponible para utilizar como destino.
                            </p>
                        </c:if>

                        <jsp:include page="/includes/informacion.jsp"/>

                        <button
                            id="botonActualizar"
                            type="submit"
                            disabled
                            class="boton-principal disabled:cursor-not-allowed disabled:opacity-50">

                            Editar

                        </button>

                    </div>

                </form>

            </section>

        </main>

        <script>

            const mapaElemento = document.getElementById("mapaRuta");

            const selectDestino = document.getElementById(
                    "idSucursalDestino"
            );

            const inputDistancia = document.getElementById(
                    "distanciaAproximadaKm"
            );

            const inputDuracion = document.getElementById(
                    "duracionEstimada"
            );

            const distanciaMostrada = document.getElementById(
                    "distanciaMostrada"
            );

            const duracionMostrada = document.getElementById(
                    "duracionMostrada"
            );

            const botonActualizar = document.getElementById(
                    "botonActualizar"
            );

            const latitudOrigen = parseFloat(
                    mapaElemento.dataset.latitudOrigen
            );

            const longitudOrigen = parseFloat(
                    mapaElemento.dataset.longitudOrigen
            );

            const mapa = L.map("mapaRuta").setView(
                    [latitudOrigen, longitudOrigen],
                    8
            );

            L.tileLayer(
                    "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
                    {
                        maxZoom: 19,
                        attribution: "&copy; OpenStreetMap"
                    }
            ).addTo(mapa);

            const marcadorOrigen = L.marker(
                    [latitudOrigen, longitudOrigen]
            ).addTo(mapa);

            marcadorOrigen.bindPopup("Sucursal de origen");

            let marcadorDestino = null;

            let capaRuta = null;

            let controladorSolicitud = null;

            selectDestino.addEventListener(
                    "change",
                    calcularRuta
            );

            if (selectDestino.value) {

                calcularRuta();

            }

            async function calcularRuta() {

                if (controladorSolicitud !== null) {

                    controladorSolicitud.abort();

                }

                limpiarRuta();

                const opcionSeleccionada =
                        selectDestino.options[selectDestino.selectedIndex];

                if (!opcionSeleccionada || !opcionSeleccionada.value) {

                    mapa.setView(
                            [latitudOrigen, longitudOrigen],
                            8
                    );

                    return;

                }

                const latitudDestino = parseFloat(
                        opcionSeleccionada.dataset.latitud
                );

                const longitudDestino = parseFloat(
                        opcionSeleccionada.dataset.longitud
                );

                if (
                        Number.isNaN(latitudDestino)
                        || Number.isNaN(longitudDestino)
                ) {

                    return;

                }

                marcadorDestino = L.marker(
                        [latitudDestino, longitudDestino]
                ).addTo(mapa);

                marcadorDestino.bindPopup(
                        "Sucursal de destino"
                );

                mapa.fitBounds(
                        [
                            [latitudOrigen, longitudOrigen],
                            [latitudDestino, longitudDestino]
                        ],
                        {
                            padding: [30, 30]
                        }
                );

                const controladorActual = new AbortController();

                controladorSolicitud = controladorActual;

                const url =
                        "https://router.project-osrm.org/route/v1/driving/"
                        + longitudOrigen + "," + latitudOrigen + ";"
                        + longitudDestino + "," + latitudDestino
                        + "?overview=full&geometries=geojson";

                try {

                    const respuesta = await fetch(
                            url,
                            {
                                signal: controladorActual.signal
                            }
                    );

                    if (!respuesta.ok) {

                        throw new Error(
                                "No fue posible consultar el recorrido."
                        );

                    }

                    const datos = await respuesta.json();

                    if (
                            datos.code !== "Ok"
                            || !datos.routes
                            || datos.routes.length === 0
                    ) {

                        throw new Error(
                                "No se encontró un recorrido entre las sucursales."
                        );

                    }

                    const ruta = datos.routes[0];

                    const distanciaKilometros =
                            (ruta.distance / 1000).toFixed(2);

                    const duracionSegundos =
                            Math.round(ruta.duration);

                    inputDistancia.value =
                            distanciaKilometros;

                    inputDuracion.value =
                            convertirSegundosATiempo(
                                    duracionSegundos
                            );

                    distanciaMostrada.textContent =
                            distanciaKilometros + " km";

                    duracionMostrada.textContent =
                            mostrarDuracion(
                                    duracionSegundos
                            );

                    capaRuta = L.geoJSON(
                            ruta.geometry,
                            {
                                style: {
                                    color: "#0284c7",
                                    weight: 5,
                                    opacity: 0.8
                                }
                            }
                    ).addTo(mapa);

                    mapa.fitBounds(
                            capaRuta.getBounds(),
                            {
                                padding: [20, 20]
                            }
                    );

                    botonActualizar.disabled = false;

                } catch (error) {

                    if (error.name === "AbortError") {

                        return;

                    }

                    inputDistancia.value = "";

                    inputDuracion.value = "";

                    distanciaMostrada.textContent =
                            "Sin calcular";

                    duracionMostrada.textContent =
                            "Sin calcular";

                    botonActualizar.disabled = true;

                } finally {

                    if (
                            controladorSolicitud
                            === controladorActual
                    ) {

                        controladorSolicitud = null;

                    }

                }

            }

            function limpiarRuta() {

                if (marcadorDestino !== null) {

                    mapa.removeLayer(marcadorDestino);

                    marcadorDestino = null;

                }

                if (capaRuta !== null) {

                    mapa.removeLayer(capaRuta);

                    capaRuta = null;

                }

                inputDistancia.value = "";

                inputDuracion.value = "";

                distanciaMostrada.textContent =
                        "Sin calcular";

                duracionMostrada.textContent =
                        "Sin calcular";

                botonActualizar.disabled = true;

            }

            function convertirSegundosATiempo(segundosTotales) {

                const horas = Math.floor(
                        segundosTotales / 3600
                );

                const minutos = Math.floor(
                        (segundosTotales % 3600) / 60
                );

                const segundos = Math.floor(
                        segundosTotales % 60
                );

                const valores = [
                    horas,
                    minutos,
                    segundos
                ];

                const valoresArreglados = [];

                for (
                    let i = 0;
                    i < valores.length;
                    i++
                ) {

                    valoresArreglados.push(
                            String(valores[i]).padStart(
                                    2,
                                    "0"
                            )
                    );

                }

                return valoresArreglados.join(":");

            }

            function mostrarDuracion(segundosTotales) {

                const horas = Math.floor(
                        segundosTotales / 3600
                );

                const minutos = Math.floor(
                        (segundosTotales % 3600) / 60
                );

                if (horas > 0) {

                    return horas + " h "
                            + minutos + " min";

                }

                return minutos + " min";

            }

        </script>

    </body>

</html>
</html>
