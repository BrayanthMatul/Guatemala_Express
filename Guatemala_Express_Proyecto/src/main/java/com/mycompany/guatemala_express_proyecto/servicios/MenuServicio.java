/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios;

import com.mycompany.guatemala_express_proyecto.enums.Rol;
import com.mycompany.guatemala_express_proyecto.modelos.MenuItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matul
 */
public class MenuServicio {

        public List<MenuItem> crearMenu(Rol rol) {
                List<MenuItem> menu = new ArrayList<>();

                agregarItemsGenerales(menu);

                switch (rol) {
                        case CLIENTE:
                                // No tiene items de menu adicionales
                                break;

                        case CHOFER:
                                agregarMenuChofer(menu);
                                break;

                        case ADMINISTRADOR_SISTEMA:
                                agregarMenuAdministradorSistema(menu);
                                break;

                        case ADMINISTRADOR_SUCURSAL:
                                agregarMenuAdministradorSucursal(menu);
                                break;
                }

                return menu;
        }

        private void agregarItemsGenerales(List<MenuItem> menu) {
                MenuItem miPerfil = new MenuItem("Mi perfil", "/perfil/informacion");
                miPerfil.agregarSubOpcion("Consultar", "/perfil/informacion");
                miPerfil.agregarSubOpcion("Editar", "/perfil/editar");
                miPerfil.agregarSubOpcion("Cerrar sesión", "/login");
                menu.add(miPerfil);

                MenuItem viajes = new MenuItem("Viajes", "/viajes/comprar_boletos");
                viajes.agregarSubOpcion("Comprar boletos", "/viajes/comprar_boletos");
                viajes.agregarSubOpcion("Ver viajes regulares disponibles", "/viajes/ver_viajes_regulares");
                viajes.agregarSubOpcion("Boletos comprados", "/viajes/boletos_comprados");
                viajes.agregarSubOpcion("Consultar rutas", "/viajes/consultar_rutas");
                menu.add(viajes);

                MenuItem alquilarBus = new MenuItem("Alquilar bus", "/alquiler/solicitar");
                alquilarBus.agregarSubOpcion("Solicitar un alquiler", "/alquiler/solicitar_alquiler");
                alquilarBus.agregarSubOpcion("Pagar monto de alquiler", "/alquiler/pagar_monto_alquiler");
                alquilarBus.agregarSubOpcion("Consultar solicitudes de alquiler", "/alquiler/consultar_solicitudes");
                menu.add(alquilarBus);

                MenuItem cartera = new MenuItem("Cartera", "/cartera/recargar_saldo");
                cartera.agregarSubOpcion("Recargar saldo a cuenta", "/cartera/recargar_saldo");
                cartera.agregarSubOpcion("Historia de recargas", "/cartera/historial_recarga");
                cartera.agregarSubOpcion("Consultar saldos y movimientos", "/cartera/consultar_movimientos");
                menu.add(cartera);
        }

        private void agregarMenuAdministradorSistema(List<MenuItem> menu) {
                MenuItem administradoresSistema = new MenuItem("Administradores de sistema",
                                "/administrador_sistema/registrar_administrador_sucursal");
                administradoresSistema.agregarSubOpcion("Registrar administrador de sistema",
                                "/administrador_sistema/registrar_administrador_sucursal");
                administradoresSistema.agregarSubOpcion("Lista de administradores de sistema",
                                "/administrador_sistema/listar_administradores_sistema");
                administradoresSistema.agregarSubOpcion("Activar/Desactivar",
                                "/administrador_sistema/activar_desactivar_administrador_sistema");
                menu.add(administradoresSistema);

                MenuItem sucursales = new MenuItem("Sucursales", "/administrador_sistema/registrar_sucursales");
                sucursales.agregarSubOpcion("Registrar sucursal", "/administrador_sistema/registrar_sucursales");
                sucursales.agregarSubOpcion("Editar sucursal", "/administrador_sistema/editar_sucursal");
                sucursales.agregarSubOpcion("Listar sucursales", "/administrador_sistema/listar_sucursales");
                menu.add(sucursales);

                MenuItem administradoresSucursal = new MenuItem("Administradores sucursal", "/alquiler/solicitar");
                administradoresSucursal.agregarSubOpcion("Registrar administrador de sucursal",
                                "/administrador_sistema/registrar_administrador_sucursal");
                administradoresSucursal.agregarSubOpcion("Editar administrador de sucursal",
                                "/administrador_sistema/editar_administrador_sucursal");
                administradoresSucursal.agregarSubOpcion("Listar administradores de sucursal",
                                "/administrador_sistema/listar_administradores_sucursal");
                administradoresSucursal.agregarSubOpcion("Activar/Desactivar administrador de sucursal",
                                "/administrador_sistema/activar_desactivar_administrador_sucursal");
                menu.add(administradoresSucursal);

                MenuItem configuraciones = new MenuItem("Configurar monto de depreciación",
                                "/configuraciones/depreciacion");
                menu.add(configuraciones);

                MenuItem reportes = new MenuItem("Reportes", "/reportes");
                reportes.agregarSubOpcion("Ganancias", "administrador_sistema/reportes/ganancias");
                reportes.agregarSubOpcion("Rutas mas demandas", "administrador_sistema/reportes/rutas_mas_demandadas");
                reportes.agregarSubOpcion("Costos operativos", "administrador_sistema/reportes/costos_operativos");
                reportes.agregarSubOpcion("Mapa de rutas", "administrador_sistema/reportes/mapa_rutas");
                menu.add(reportes);

        }

        private void agregarMenuAdministradorSucursal(List<MenuItem> menu) {
                MenuItem buses = new MenuItem("Buses", "/administrador_sucursal/registrar_buses");
                buses.agregarSubOpcion("Registrar bus", "/administrador_sucursal/registrar_buses");
                buses.agregarSubOpcion("Lista de buses",
                                "/administrador_sucursal/listar_buses");
                buses.agregarSubOpcion("Activar/Desactivar",
                                "/administrador_sucursal/activar_desactivar_bus");
                menu.add(buses);

                MenuItem chofer = new MenuItem("Chofer", "/administrador_sucursal/registrar_chofer");
                chofer.agregarSubOpcion("Registrar chofer", "/administrador_sucursal/registrar_chofer");
                chofer.agregarSubOpcion("Editar chofer", "/administrador_sucursal/editar_chofer");
                chofer.agregarSubOpcion("Listar choferes", "/administrador_sucursal/listar_choferes");
                chofer.agregarSubOpcion("Activar/Desactivar chofer",
                                "/administrador_sucursal/activar_desactivar_chofer");
                menu.add(chofer);

                MenuItem rutas = new MenuItem("Rutas", "/administrador_sucursal/registrar_rutas");
                rutas.agregarSubOpcion("Registrar ruta", "/administrador_sucursal/registrar_rutas");
                rutas.agregarSubOpcion("Editar ruta", "/administrador_sucursal/editar_rutas");
                rutas.agregarSubOpcion("Listar rutas", "/administrador_sucursal/listar_rutas");
                rutas.agregarSubOpcion("Eliminar ruta", "/administrador_sucursal/eliminar_ruta");
                menu.add(rutas);

                MenuItem viajesRegulares = new MenuItem("Viajes regulares", "/administrador_sucursal/programar_viaje");
                viajesRegulares.agregarSubOpcion("Programar viaje", "/administrador_sucursal/programar_viaje");
                viajesRegulares.agregarSubOpcion("Editar datos de un viaje", "/administrador_sucursal/editar_viaje");
                viajesRegulares.agregarSubOpcion("Eliminar viaje", "/administrador_sucursal/eliminar_viaje");
                viajesRegulares.agregarSubOpcion("Listar viajes", "/administrador_sucursal/listar_viajes");
                viajesRegulares.agregarSubOpcion("Consultar viaje", "/administrador_sucursal/consultar_viaje");
                viajesRegulares.agregarSubOpcion("Registrar salida", "/administrador_sucursal/registrar_salida");
                viajesRegulares.agregarSubOpcion("Registrar llegada", "/administrador_sucursal/registrar_llegada");
                menu.add(viajesRegulares);

                MenuItem viajesPrivados = new MenuItem("Viajes privados", "/administrador_sucursal/asignar_chofer_bus");
                viajesPrivados.agregarSubOpcion("Asignar chofer y bus a viaje privado",
                                "/administrador_sucursal/asignar_chofer_bus");
                viajesPrivados.agregarSubOpcion("Eliminar viaje", "/administrador_sucursal/eliminar_viaje_privado");
                viajesPrivados.agregarSubOpcion("Confirmar monto de viaje privado",
                                "/administrador_sucursal/confirmar_monto_viaje_privado");
                viajesPrivados.agregarSubOpcion("Registrar Salida", "/administrador_sucursal/registrar_salida");
                viajesPrivados.agregarSubOpcion("Registrar llegada", "/administrador_sucursal/registrar_llegada");
                viajesPrivados.agregarSubOpcion("Rechazar solicitud", "/administrador_sucursal/rechazar_solicitud");
                viajesPrivados.agregarSubOpcion("Listar solicitudes", "/administrador_sucursal/listar_solicitudes");
                menu.add(viajesPrivados);

                MenuItem registraGastoTaller = new MenuItem("Registrar gasto de taller",
                                "/administrador_sucursal/registrar_gasto_taller");
                menu.add(registraGastoTaller);

                MenuItem reportes = new MenuItem("Reportes", "/administrador_sucursal/reportes");
                reportes.agregarSubOpcion("Ingresos por venta de boletos",
                                "/administrador_sucursal/reportes/ingresos_venta_boletos");
                reportes.agregarSubOpcion("Ingresos por alquiler de buses",
                                "/administrador_sucursal/reportes/ingresos_alquiler_buses");
                reportes.agregarSubOpcion("Depreciación por bus",
                                "/administrador_sucursal/reportes/depreciacion_por_bus");
                menu.add(reportes);

        }

        private void agregarMenuChofer(List<MenuItem> menu) {
                MenuItem viajesRegulares = new MenuItem("Viajes regulares", "/chofer/viajes_regulares");
                viajesRegulares.agregarSubOpcion("Ver viajes asignados", "/chofer/viajes_regulares");
                viajesRegulares.agregarSubOpcion("Registrar salida", "/chofer/registrar_salida");
                menu.add(viajesRegulares);

                MenuItem viajesPrivados = new MenuItem("Viajes privados", "/chofer/viajes_privados");
                viajesPrivados.agregarSubOpcion("Ver viajes asignados", "/chofer/viajes_privados");
                viajesPrivados.agregarSubOpcion("Registrar salida", "/chofer/registrar_salida");
                menu.add(viajesPrivados);

        }
}
