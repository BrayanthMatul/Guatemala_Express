<%-- 
    Document   : editar-sucursal
    Created on : Sep 10, 2026, 3:58:51 AM
    Author     : matul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/includes/recursos.jsp"/>
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css">
        <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"> </script>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/modal.jsp"/>

        <main class="contenedor">

            <p class="linea-circular">
                <a href="${pageContext.request.contextPath}/administrador_sistema/lista_sucursales"
                class="hover:underline">

                    <i class="pi pi-arrow-left mr-2"></i>
                    Lista de sucursales
                </a>
            </p>

            <section class="tarjeta">

                <h2 class="titulo">
                    Editar sucursal
                </h2>

                <p class="linea">
                    Todos los datos son obligatorios, por favor ingrese los datos.
                </p>

                <form method="POST"
                    action="${pageContext.request.contextPath}/administrador_sistema/editar_sucursal">

                    <input
                        type="hidden"
                        name="id"
                        value="<c:out value='${id}'/>">

                    <input
                        id="longitud"
                        type="hidden"
                        name="longitud"
                        value="<c:out value='${longitud}'/>">

                    <input
                        id="latitud"
                        type="hidden"
                        name="latitud"
                        value="<c:out value='${latitud}'/>">

                    <div class="formulario-y">

                        <div class="formulario-x">

                            <div class="formulario-y">

                                <div>
                                    <label for="nombre" class="label-formulario">
                                        Nombre de la sucursal
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-building icono-input"></i>

                                        <input
                                            id="nombre"
                                            name="nombre"
                                            type="text"
                                            maxlength="255"
                                            value="<c:out value='${nombre}'/>"
                                            required
                                            placeholder="Ejemplo: Sucursal Quetzaltenango"
                                            class="input-formulario">
                                    </div>
                                </div>

                                <div>
                                    <label for="departamento" class="label-formulario">
                                        Departamento
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-map icono-input"></i>

                                        <input
                                            id="departamento"
                                            name="departamento"
                                            type="text"
                                            maxlength="255"
                                            value="<c:out value='${departamento}'/>"
                                            required
                                            placeholder="Ejemplo: Quetzaltenango"
                                            class="input-formulario">
                                    </div>
                                </div>

                            </div>

                            <div class="formulario-y">

                                <div>
                                    <label for="municipio" class="label-formulario">
                                        Municipio
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-map-marker icono-input"></i>

                                        <input
                                            id="municipio"
                                            name="municipio"
                                            type="text"
                                            maxlength="255"
                                            value="<c:out value='${municipio}'/>"
                                            required
                                            placeholder="Ejemplo: Quetzaltenango"
                                            class="input-formulario">
                                    </div>
                                </div>

                                <div>
                                    <label for="telefono" class="label-formulario">
                                        Teléfono
                                    </label>

                                    <div class="relative">
                                        <i class="pi pi-phone icono-input"></i>

                                        <input
                                            id="telefono"
                                            name="telefono"
                                            type="tel"
                                            inputmode="tel"
                                            minlength="8"
                                            maxlength="15"
                                            pattern="\+?[0-9]{8,15}"
                                            title="Ingrese un numero de teléfono válido (8 dígitos)."
                                            value="<c:out value='${telefono}'/>"
                                            required
                                            placeholder="Ejemplo: 77612345"
                                            class="input-formulario">
                                    </div>
                                </div>

                            </div>

                        </div>

                        <div>
                            <label class="label-formulario text-center">
                                Ubicación de la sucursal
                            </label>

                            <p class="linea">
                                Seleccione en el mapa la ubicación exacta de la sucursal.
                            </p>

                            <div id="mapaSucursal" class="w-full rounded-lg h-80"> </div>
                        </div>

                        <jsp:include page="/includes/informacion.jsp"/>

                        <button type="submit" class="boton-principal">
                            Editar
                        </button>

                    </div>

                </form>

            </section>

        </main>

        <script>
            const mapa = L.map("mapaSucursal") .setView([15.7835, -90.2308], 7);
            L.tileLayer( "https://tile.openstreetmap.org/{z}/{x}/{y}.png", { maxZoom: 19, attribution: "&copy; OpenStreetMap" } ).addTo(mapa);

            const inputLatitud = document.getElementById("latitud");
            const inputLongitud = document.getElementById("longitud");

            let marcador;

            function seleccionarUbicacion(latitud, longitud) {
                inputLatitud.value = latitud.toFixed(6);
                inputLongitud.value = longitud.toFixed(6);

                if (marcador) {
                    marcador.setLatLng([latitud, longitud]);
                } else {
                    marcador = L.marker([latitud, longitud]) .addTo(mapa);
                }
            }

            mapa.on("click", function (evento) {
                seleccionarUbicacion( evento.latlng.lat, evento.latlng.lng );
            });

            const latitudGuardada = parseFloat(inputLatitud.value);
            const longitudGuardada = parseFloat(inputLongitud.value);

            if (!Number.isNaN(latitudGuardada) && !Number.isNaN(longitudGuardada)) {
                seleccionarUbicacion( latitudGuardada, longitudGuardada );
                mapa.setView( [latitudGuardada, longitudGuardada], 15 );
            }
        </script>
    </body>
</html>
