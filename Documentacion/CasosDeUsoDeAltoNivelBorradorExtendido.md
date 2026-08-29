## Casos de uso

___

### Generales
___

| Número | CU001 |
| :--- | :---: |
| Caso de Uso: | Crear cuenta |
| Actores: | Usuario no registrado, Aplicación |
| Descripción: | Al entrar en la página web, el usuario presiona el botón para crear una cuenta nueva. Este lo llevará al formulario correspondiente para que ingrese sus datos; si todos los datos son correctos, la aplicación procederá a registrarlo. |
| Tipo: | Primario |

<br>

| Número | CU002 |
| :--- | :---: |
| Caso de Uso: | Iniciar sesión |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | Al entrar en la página web, el usuario ingresa sus credenciales para iniciar sesión. Si las credenciales son correctas, la aplicación se redirigirá a la página correspondiente según su rol. |
| Tipo: | Primario |

<br>

| Número | CU003 |
| :--- | :---: |
| Caso de Uso: | Consultar perfil |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario despliega el menú "Mi perfil", seleccionará "Consultar perfil" y la aplicación le mostrará su información guardada; no se mostrará la contraseña. |
| Tipo: | Secundario |

<br>

| Número | CU004 |
| :--- | :---: |
| Caso de Uso: | Editar perfil |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario despliega el menú "Mi perfil" y selecciona "Editar perfil". La aplicación se redirigirá a una página para que pueda editar su perfil; si todos los datos son correctos, se guardarán los cambios realizados. |
| Tipo: | Primario |


| Número | CU005 |
| :--- | :---: |
| Caso de Uso: | Cerrar sesión |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | Cuando un usuario haya iniciado sesión, podrá desplegar el menú de "Mi perfil" y presionará "Cerrar sesión". La aplicación lo llevará al inicio y borrará las credenciales ingresadas (Pendiente). |
| Tipo: | Primario |

<br>

| Número | CU006 |
| :--- | :---: |
| Caso de Uso: | Compra de boletos |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario despliega el menú "Viajes" y selecciona "Comprar boletos". La aplicación le mostrará la lista de sucursales; elegirá una sucursal, luego la aplicación le mostrará los viajes con asientos disponibles y el usuario podrá realizar la compra de los boletos que necesite, siempre y cuando no sobrepase el número de boletos disponibles. |
| Tipo: | Primario |

<br>

| Número | CU007 |
| :--- | :---: |
| Caso de Uso: | Ver viajes regulares disponibles |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | Un usuario registrado podrá ver la lista de viajes por sucursal con viajes disponibles; entrará al menú correspondiente y la aplicación le mostrará la información. |
| Tipo: | Primario |

<br>

| Número | CU008 |
| :--- | :---: |
| Caso de Uso: | Boletos comprados |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario podrá ver la lista de boletos que ya adquirió y toda la información relacionada; entrará al menú correspondiente y la aplicación le mostrará la información. |
| Tipo: | Primario |

<br>

| Número | CU009 |
| :--- | :---: |
| Caso de Uso: | Consultar rutas |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario podrá ver la lista de rutas que tiene una sucursal; entrará al menú correspondiente y la aplicación le mostrará la información. |
| Tipo: | Primario |

<br>

| Número | CU010 |
| :--- | :---: |
| Caso de Uso: | Alquilar bus |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario desplegará el menú de "Alquiler Bus" y seleccionará "Solicitar un alquiler"; se redirigirá a un formulario para que pueda llenar su solicitud. |
| Tipo: | Primario |

<br>

| Número | CU011 |
| :--- | :---: |
| Caso de Uso: | Pagar monto de alquiler |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario podrá ver las solicitudes confirmadas; si hay solicitudes aprobadas, el sistema le dará la opción para pagarlas. El usuario puede realizar el pago correspondiente y el monto se descontará de su saldo actual. |
| Tipo: | Primario |

<br>

| Número | CU012 |
| :--- | :---: |
| Caso de Uso: | Consultar solicitudes de alquiler |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario desplegará el menú "Alquilar bus" y seleccionará "Consultar solicitudes de alquiler"; si ya realizó solicitudes, se listarán con toda la información y el estado correspondiente. |
| Tipo: | Primario |

<br>

| Número | CU013 |
| :--- | :---: |
| Caso de Uso: | Recargar saldo a cuenta |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario desplegará el menú "Cartera" y seleccionará "Recargar saldo a cuenta"; se redirigirá a un formulario donde de forma manual podrá ingresar el monto que desee agregar a su cuenta. En el formulario también se presentará el saldo actual y se actualizará si se realiza una recarga. |
| Tipo: | Primario |

<br>

| Número | CU014 |
| :--- | :---: |
| Caso de Uso: | Ver historial de recargas |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario desplegará el menú "Cartera" y seleccionará "Historial de recargas"; se redirigirá a una página donde se mostrará todo su historial de recargas. |
| Tipo: | Primario |

<br>

| Número | CU015 |
| :--- | :---: |
| Caso de Uso: | Consultar saldo y movimientos |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario desplegará el menú "Cartera" y seleccionará "Consultar saldo y movimientos"; se redirigirá a una página donde se mostrará su saldo actual y los pagos que ha realizado. |
| Tipo: | Primario |

## Administrador de sistema
---

| Número | CU016 |
| :--- | :---: |
| Caso de Uso: | Registrar administrador de sistema |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Administradores de Sistema" y seleccionará "Registrar administrador de sistema"; se redirigirá a un formulario donde deberá ingresar los campos necesarios para el registro y después presionará guardar. Si la información es correcta, se guardará el administrador de sistema. |
| Tipo: | Primario |

<br>

| Número | CU017 |
| :--- | :---: |
| Caso de Uso: | Ver lista de administradores de sistema |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Administradores de Sistema" y seleccionará "Lista de administradores de sistema"; se redirigirá a una página donde la aplicación le mostrará la lista de administradores con toda su información. |
| Tipo: | Primario |

<br>

| Número | CU018 |
| :--- | :---: |
| Caso de Uso: | Desactivar administrador de sistema |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Administradores de Sistema" y seleccionará "Activar / Desactivar administrador"; se redirigirá a una página donde la aplicación le mostrará todos los administradores y elegirá uno para cambiar su estado. |
| Tipo: | Primario |

<br>

| Número | CU019 |
| :--- | :---: |
| Caso de Uso: | Registrar sucursal |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Sucursales" y seleccionará "Registrar sucursal"; la aplicación lo llevará a una página para que pueda registrar una sucursal nueva. Si todos los datos son correctos, la aplicación registrará la sucursal. |
| Tipo: | Primario |

<br>

| Número | CU020 |
| :--- | :---: |
| Caso de Uso: | Editar sucursal |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Sucursales" y seleccionará "Editar sucursal"; la aplicación lo llevará a una página para que elija una sucursal a editar y pueda ingresar los datos nuevos. Si todos los datos son correctos, la aplicación actualizará la información de la sucursal. |
| Tipo: | Primario |

<br>

| Número | CU021 |
| :--- | :---: |
| Caso de Uso: | Listar sucursales |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Sucursales" y seleccionará "Listar sucursales"; la aplicación lo llevará a una página donde podrá ver todas las sucursales e información relacionada. |
| Tipo: | Primario |

<br>

| Número | CU022 |
| :--- | :---: |
| Caso de Uso: | Registrar administrador de sucursal |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Administradores de sucursal" y seleccionará "Registrar administrador sucursal"; la aplicación lo llevará a una página con un formulario para que pueda ingresar toda la información de un nuevo administrador de sucursal. Si todos los campos son correctos, se registrará al nuevo administrador de sucursal. |
| Tipo: | Primario |

<br>

| Número | CU023 |
| :--- | :---: |
| Caso de Uso: | Editar administrador de sucursal |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Administradores de sucursal" y seleccionará "Editar administrador sucursal"; la aplicación lo llevará a una página con un formulario para que pueda ingresar toda la información actualizada del administrador de sucursal. Si todos los campos son correctos, se actualizará al administrador de sucursal. |
| Tipo: | Primario |

<br>

| Número | CU024 |
| :--- | :---: |
| Caso de Uso: | Listar administradores de sucursal |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Administradores de sucursal" y seleccionará "Listar administrador sucursal"; la aplicación lo llevará a una página donde aparecerán todos los administradores de sucursal e información relacionada. |
| Tipo: | Primario |

<br>

| Número | CU025 |
| :--- | :---: |
| Caso de Uso: | Activar / Desactivar administrador de sucursales |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Administradores de sucursal" y seleccionará "Activar / Desactivar administrador sucursal"; la aplicación lo llevará a una página donde deberá elegir a un administrador y cambiar su estado. |
| Tipo: | Primario |

<br>

| Número | CU026 |
| :--- | :---: |
| Caso de Uso: | Configurar monto de depreciación por kilómetro |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Configuraciones" y seleccionará "Configurar monto de depreciación por kilómetro"; la aplicación lo llevará a una página donde podrá configurar el monto. Si el dato es correcto, se actualizará el monto de depreciación. |
| Tipo: | Primario |

<br>

| Número | CU027 |
| :--- | :---: |
| Caso de Uso: | Ver reporte de ganancias |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Reporte" y seleccionará "Ganancias"; la aplicación lo llevará a una página donde podrá ver todos los ingresos que se han tenido por sucursal y el monto total. También podrá usar un filtro de intervalo de tiempo. |
| Tipo: | Primario |

<br>

| Número | CU028 |
| :--- | :---: |
| Caso de Uso: | Ver reporte de rutas más demandadas |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Reporte" y seleccionará "Rutas más demandadas"; la aplicación lo llevará a una página donde podrá ver las rutas más demandadas. |
| Tipo: | Primario |

<br>

| Número | CU029 |
| :--- | :---: |
| Caso de Uso: | Ver reporte de costos operativos |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Reporte" y seleccionará "Costos operativos"; la aplicación lo llevará a una página donde podrá ver los costos operativos que ha tenido cada sucursal. |
| Tipo: | Primario |

<br>

| Número | CU030 |
| :--- | :---: |
| Caso de Uso: | Ver mapa de rutas |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | El usuario desplegará el menú "Reporte" y seleccionará "Mapa de rutas"; la aplicación lo llevará a una página donde podrá ver las rutas de cada sucursal. |
| Tipo: | Primario |

### Administrador de sucursal
___

| Número | CU031 |
| :--- | :---: |
| Caso de Uso: | Registrar bus |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Buses" y seleccionará "Registrar bus"; la aplicación lo llevará a una página con un formulario para que pueda ingresar toda la información de un nuevo bus. Si todos los campos son correctos, se registrará el bus. |
| Tipo: | Primario |

<br>

| Número | CU032 |
| :--- | :---: |
| Caso de Uso: | Editar informacion bus |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Buses" y seleccionará "Editar informacion bus"; la aplicación lo llevará a una página con un formulario para que pueda ingresar toda la información actualizada del bus. Si todos los campos son correctos, se actualizará el bus. |
| Tipo: | Primario |

<br>

| Número | CU033 |
| :--- | :---: |
| Caso de Uso: | Listar buses sucursal |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Buses" y seleccionará "Listar buses sucursal"; la aplicación lo llevará a una página donde aparecerán todos los buses e información relacionada. |
| Tipo: | Primario |

<br>

| Número | CU034 |
| :--- | :---: |
| Caso de Uso: | Activar / Desactivar Bus |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Buses" y seleccionará "Activar / Desactivar buses"; la aplicación lo llevará a una página donde deberá elegir a un bus y cambiar su estado, siempre y cuando no este en ruta. |
| Tipo: | Primario |

<br>

| Número | CU035 |
| :--- | :---: |
| Caso de Uso: | Registrar chofer |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Choferes" y seleccionará "Registrar chofer"; la aplicación lo llevará a una página con un formulario para que pueda ingresar toda la información de un nuevo chofer. Si todos los campos son correctos, se registrará al chofer. |
| Tipo: | Primario |

<br>

| Número | CU036 |
| :--- | :---: |
| Caso de Uso: | Editar informacion chofer |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Choferes" y seleccionará "Editar informacion chofer"; la aplicación lo llevará a una página con un formulario para que pueda ingresar toda la información actualizada del chofer. Si todos los campos son correctos, se actualizará al chofer. |
| Tipo: | Primario |

<br>

| Número | CU037 |
| :--- | :---: |
| Caso de Uso: | Listar choferes |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Choferes" y seleccionará "Listar chofers"; la aplicación lo llevará a una página donde aparecerán todos los choferes e información relacionada. |
| Tipo: | Primario |

<br>

| Número | CU038 |
| :--- | :---: |
| Caso de Uso: | Activar / Desactivar Chofer |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Choferes" y seleccionará "Activar / Desactivar chofer"; la aplicación lo llevará a una página donde deberá elegir a un bus y cambiar su estado, siempre y cuando no este en ruta. |
| Tipo: | Primario |

<br>

| Número | CU039 |
| :--- | :---: |
| Caso de Uso: | Registrar ruta |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Rutas" y seleccionará "Registrar Ruta"; la aplicación lo llevará a una página con un formulario para que pueda ingresar toda la información de un nueva ruta. Si todos los campos son correctos, se registrará la ruta. |
| Tipo: | Primario |

<br>

| Número | CU040 |
| :--- | :---: |
| Caso de Uso: | Editar informacion ruta |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Rutas" y seleccionará "Modificar ruta"; la aplicación lo llevará a una página con un formulario para que pueda ingresar toda la información actualizada de la ruta. Si todos los campos son correctos, se actualizará la ruta. |
| Tipo: | Primario |

<br>

| Número | CU041 |
| :--- | :---: |
| Caso de Uso: | Eliminar ruta |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Rutas" y seleccionará "Eliminar ruta"; la aplicación lo llevará un formulario donde mostrara las rutas y si se pueden eliminar. |
| Tipo: | Primario |

<br>

| Número | CU042 |
| :--- | :---: |
| Caso de Uso: | Programar viaje |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Regulares" y seleccionará "Programr viaje"; la aplicación lo llevará un formulario donde podra programar un nuevo viaje regular.  Si todos los campos son correctos, se guardara el viaje. |
| Tipo: | Primario |

<br>

| Número | CU043 |
| :--- | :---: |
| Caso de Uso: | Editar datos de un viaje |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes regulares" y seleccionará "Editar datos de un viaje"; la aplicación lo llevará a una página con un formulario para que pueda ingresar toda la información actualizada del viaje. Si todos los campos son correctos, se actualizará los datos del viaje. |
| Tipo: | Primario |

<br>

| Número | CU044 |
| :--- | :---: |
| Caso de Uso: | Eliminar viaje |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Regulares" y seleccionará "Eliminar viaje"; la aplicación lo llevará un formulario donde mostrara los viajes y si se pueden eliminar. |
| Tipo: | Primario |

<br>

| Número | CU045 |
| :--- | :---: |
| Caso de Uso: | Listar viajes |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Regulares" y seleccionará "Listar vijaes"; la aplicación lo llevará a una página donde aparecerán todos los viajes aun pendientes y finalizados e información relacionada. |
| Tipo: | Primario |

<br>

| Número | CU046 |
| :--- | :---: |
| Caso de Uso: | Consultar viaje |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Regulares" y seleccionará "Consultar vijaes"; la aplicación lo llevará a una página donde aparecerán los viajes, seleccionara uno y se dara informacion detallada de ese viaje. |
| Tipo: | Primario |

<br>

| Número | CU047 |
| :--- | :---: |
| Caso de Uso: | Registrar salida |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Regulares" y seleccionará "Registrar salida"; la aplicación lo llevará un formulario donde podra registrar un registro de salida.  Si todos los campos son correctos, se guardara el registro de salida. |
| Tipo: | Primario |

<br>

| Número | CU048 |
| :--- | :---: |
| Caso de Uso: | Registrar llegada |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Regulares" y seleccionará "Registrar llegada"; la aplicación lo llevará un formulario donde podra registrar un registro de llegada.  Si todos los campos son correctos, se guardara el registro de llegada. |
| Tipo: | Primario |

<br>

| Número | CU049 |
| :--- | :---: |
| Caso de Uso: | Asignar chofer y bus a viaje privado |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Privados" y seleccionará "Asignar chofer y bus a viaje privado"; la aplicación lo llevará un formulario donde podra asignar el bus y chofer al viaje.  Si todos los campos son correctos, se guardara espera a que se confirme el monto para aceptar la solitud. |
| Tipo: | Primario |

<br>

| Número | CU050 |
| :--- | :---: |
| Caso de Uso: | Eliminar viaje privado |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Regulares" y seleccionará "Eliminar viaje"; la aplicación lo llevará un formulario donde mostrara los viajes y si se pueden eliminar. |
| Tipo: | Primario |

<br>

| Número | CU050 |
| :--- | :---: |
| Caso de Uso: | Confirmar monto de viaje privado |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Regulares" y seleccionará "Confirmar monto de viaje privado"; la aplicación lo llevará un formulario podra ver los solicitudes aceptadas pero sin monto confirmado. |
| Tipo: | Primario |

<br>

| Número | CU051 |
| :--- | :---: |
| Caso de Uso: | Registrar salida |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Privado" y seleccionará "Registrar salida"; la aplicación lo llevará un formulario donde podra registrar un registro de salida.  Si todos los campos son correctos, se guardara el registro de salida. |
| Tipo: | Primario |

<br>

| Número | CU052 |
| :--- | :---: |
| Caso de Uso: | Registrar llegada |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Privado" y seleccionará "Registrar llegada"; la aplicación lo llevará un formulario donde podra registrar un registro de llegada.  Si todos los campos son correctos, se guardara el registro de llegada. |
| Tipo: | Primario |

<br>

| Número | CU053 |
| :--- | :---: |
| Caso de Uso: | Registrar gasto taller |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Taller" y seleccionará "Registrar gasto taller"; la aplicación lo llevará un formulario donde podra registrar un gasto de taller.  Si todos los campos son correctos, se guardara el gasto de taller. |
| Tipo: | Primario |

<br>

| Número | CU054 |
| :--- | :---: |
| Caso de Uso: | Ver reporte de ingreso por venta de boletos |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Reporte" y seleccionará "Ingreso por venta de boletos"; la aplicación lo llevará a una página donde podrá ver todos los ingresos que se han tenido por venta de boletos. |
| Tipo: | Primario |

<br>

| Número | CU055 |
| :--- | :---: |
| Caso de Uso: | Ver reporte de rutas más demandadas |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Reporte" y seleccionará "Ingreso por alquiler de buses"; la aplicación lo llevará a una página donde podrá ver el ingreso por alquiler de buses. |
| Tipo: | Primario |

<br>

| Número | CU056 |
| :--- | :---: |
| Caso de Uso: | Ver reporte de costos operativos |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | El usuario desplegará el menú "Reporte" y seleccionará "Depreciacion por bus"; la aplicación lo llevará a una página donde podrá ver los costos de depreciacion por bus. |
| Tipo: | Primario |

### Chofer
___

| Número | CU057 |
| :--- | :---: |
| Caso de Uso: | Ver viajes asignados |
| Actores: | Usuario Chofer, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Regulares" y seleccionará "Ver viajes asignados"; la aplicación lo llevará a una página donde aparecerán los viajes que tiene asiganado. |
| Tipo: | Primario |

<br>

| Número | CU058 |
| :--- | :---: |
| Caso de Uso: | Registrar salida |
| Actores: | Usuario Chofer, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Regulares" y seleccionará "Registrar salida"; la aplicación lo llevará un formulario donde podra registrar un registro de salida.  Si todos los campos son correctos, se guardara el registro de salida. |
| Tipo: | Primario |

<br>

| Número | CU059 |
| :--- | :---: |
| Caso de Uso: | Ver viajes asignados |
| Actores: | Usuario Chofer, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Privados" y seleccionará "Ver viajes asignados"; la aplicación lo llevará a una página donde aparecerán los viajes privados que tiene asiganado. |
| Tipo: | Primario |

<br>

| Número | CU060 |
| :--- | :---: |
| Caso de Uso: | Registrar salida |
| Actores: | Usuario Chofer, Aplicación |
| Descripción: | El usuario desplegará el menú "Viajes Privados" y seleccionará "Registrar salida"; la aplicación lo llevará un formulario donde podra registrar un registro de salida.  Si todos los campos son correctos, se guardara el registro de salida. |
| Tipo: | Primario |































