# Notas de requerimientos Guatemala Express

Idea principal: Gestion de flota de buses de transporte extraurbano en Guatemala 

## Entidades

- Usuario 
  - Id usuario
  - NIT
  - DPI
  - Nombre completo
  - Telefono
  - Direccion
  - Correo
  - Contrasenia
  - Rol
  - Saldo
  - Estado

- Sucursal
  - Id
  - Departamento
  - Municipio
  - Coordenadas
  - Telefono
  - Fecha y hora de apertura

- Administrador de sucursal
  - Id usuario
  - Sucursal asignada

- Chofer
  - Id usuario
  - Id sucursal
  - Fotografia
  - Numero de licencia
  - Tipo de licencia
  - Fecha de vencimiento de licencia
  - Salario base por viaje

- Bus
  - Numero de placa
  - Id sucursal
  - Foto
  - Marca
  - Modelo
  - Anio de fabricacion 
  - Capacidad de pasajeros
  - Estado operativo
  - Kilometraje actual del vehiculo
  - Estado
 
- Ruta Regular
  - Id 
  - Id sucursal origen
  - Id sucursal destino 
  - Distancia aproximada en kilometros
  - Precio del boleto
  - Estado

- Solicitud Alquiler
  - Id
  - Id usuario
  - Id sucursal
  - Coordenadas origen
  - Coordenadas destino
  - Distancia aproximada
  - Fecha y hora de salida
  - Fecha y hora de retorno
  - Viaje con retorno
  - Cantidad de pasajeros
  - Precio estimado
  - Precio confirmado
  - Estado solicitud
  - Estado pago
 
 - Pago Alquiler
   - Id
   - Id solicitud alquiler
   - Id usario
   - Fecha y hora pago (Manual)
   - Monto pagado
 
- Viaje 
  - Id
  - Tipo viaje
  - Placa bus
  - Id chofer
  - Fecha y hora salida programada
  - Fecha y hora estimada llegada
  - Salario aplicado
  - Estado viaje

- Viaje regular
  - Id viaje
  - Id ruta

- Viaje Privado
  - Id viaje
  - Id solicitud

- Registro salida
  - Id
  - Id usuario
  - Id chofer real
  - Placa bus real
  - Id viaje
  - Fecha y hora real de salida
  - Kilometraje inicial

- Registro llegada
  - Id
  - Id usuario
  - Id viaje
  - Fecha y hora real de llegada
  - Kilometraje final
  - Gasto combustible
  - Tarifa depreciacion aplicada
  - Monto depreciacion 

- Boleto
  - Id 
  - Id compra
  - Id viaje
  - Numero asiento
  - Precio

- Compra
  - Id 
  - Id usuario
  - Id viaje
  - Fecha de pago (Manual)
  - Monto total
 
- Recarga
  - Id
  - Id usuario
  - Fecha y hora recarga (Manual)
  - Monto

- Gasto taller
  - Id 
  - Placa bus
  - Monto mano de obra
  - Monto repuestos
  - Fecha de mantenimiento

- Configuracion 
  - Id
  - Descripcion 
  - Valor


## Casos de Uso General

- Crear cuenta
- Iniciar sesion

- Mi perfil 
  - Consultar perfil
  - Editar perfil
  - Cerrar sesion

- Viajes
  - Comprar boletos
  - Ver viajes regulares disponibles
  - Boletos comprados
  - Consultar rutas

- Alquilar bus
  - Solicitar un alquiler
  - Pagar monto de alquiler
  - Consultar solicitudes de alquiler

- Cartera
  - Recargar saldo a cuenta 
  - Historia de recargas
  - Consultar saldos y movimientos



## Casos de uso Administrador sistema

- Administradores de sistema
  - Registrar administrador de sistema
  - Lista de administradores de sistema
  - Activar / Desactivar administrador

- Sucursales
  - Registrar sucursal
  - Editar sucursal
  - Listar sucursal
 
- Administradores de sucursal
  - Registrar administrador sucursal
  - Editar administrador sucursal
  - Listar administradores de sucursales
  - Activar / Desactivar administrador de sucursal

- Configuraciones (Administrador sistema)
  - Configurar monto de depreciacion por Kilometro
 
- Reportes
  - Ganancias
  - Rutas mas demandadas
  - Costos operativos
  - Mapa de ruta

## Casos de uso Administrador Sucursal

- Buses
  - Registrar bus
  - Editar informacion bus
  - Listar buses sucursal
  - Activar / Desactivar bus (Restricciones)

- Choferes
  - Registrar chofer
  - Editar informacion chofer
  - Listar choferes
  - Activar / Desactivar chofer

- Rutas 
  - Registrar ruta
  - Modificar ruta
  - Eliminar ruta (Restricciones)

- Viajes Regulares 
  - Programar viaje
  - Editar datos de un viaje
  - Eliminar viaje (Restricciones)
  - Listar viajes
  - Consultar viaje
  - Registrar salida
  - Registrar llegada
 
- Viajes Privados
  - Asignar chofer y bus a viaje privado
  - Eliminar viaje (Restricciones)
  - Confirmar monto de viaje privado
  - Registrar Salida
  - Registrar llegada
  - Rechazar solicitud
  - Listar solicitudes

- Taller
  - Registrar gasto de taller

- Reportes
  - Ingreso por venta de boletos
  - Ingreso por alquiler de buses
  - Depreciacion por bus

## Chofer

- Viajes Regulares 
  - Ver viajes asigandos
  - Registrar salida
 
- Viajes privados 
  - Ver viajes asigandos
  - Registrar salida


## Restricciones 

- El asiento debe existir dentro de la capacidad del bus.

- Un asiento no puede venderse dos veces para el mismo viaje.

- El saldo debe ser suficiente.

- El descuento y la creación de boletos deben ejecutarse en una sola transacción.

- No se debe permitir comprar en viajes iniciados, finalizados o cancelados.

## Reglas de negocio

- Un bus pertenece a una única sucursal.
- Un chofer pertenece a una única sucursal.
- El bus y chofer asignados deben pertenecer a la sucursal correspondiente.
- No puede asignarse el mismo bus o chofer a - viajes con horarios cruzados.
- No puede asignarse un bus o chofer inactivo.
- No puede utilizarse una licencia vencida.
- El kilometraje final debe ser mayor o igual al inicial.
- El kilometraje registrado no puede ser menor al kilometraje actual del bus.
- Un bus no puede desactivarse si tiene viajes programados o en tránsito.
- Una ruta solo se elimina si no tiene viajes asociados.
- Un viaje solo se elimina si no ha iniciado ni tiene pagos.
- El tipo de viaje no puede modificarse.
- Los registros de salida y llegada son inmutables.
- Los usuarios no se eliminan; únicamente se desactivan.
- Siempre debe existir un administrador del sistema activo.