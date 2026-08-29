## Casos de uso

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
| Caso de Uso: | Gestionar perfil |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario visualizara su informacion, tendra la opcion de editarla. |
| Tipo: | Primario |

<br>

| Número | CU004 |
| :--- | :---: |
| Caso de Uso: | Gestinar viajes regulares |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario tendra acceso a toda la informacion realacionada con viajes y podra comprar boletos.  |
| Tipo: | Primario |

<br>

| Número | CU005 |
| :--- | :---: |
| Caso de Uso: | Gestionar viajes privados  |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario solicitara alquilar un bus y tenedra a disposicion toda la informacion relacionada. |
| Tipo: | Primario |

<br>

| Número | CU006 |
| :--- | :---: |
| Caso de Uso: | Gestinar cartera |
| Actores: | Usuario registrado, Aplicación |
| Descripción: | El usuario realiza una recarga manual de saldo a su cuenta y tener a disposicion toda la informacion relacionada. |
| Tipo: | Primario |


## Administrador de sistema
---

| Número | CU007 |
| :--- | :---: |
| Caso de Uso: | Gestinar administradores del sistema |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | Un administrador del sistema realiza acciones como registrar, editar, ver y desactivar a administradores del sistema. |
| Tipo: | Primario |


<br>

| Número | CU008 |
| :--- | :---: |
| Caso de Uso: | Gestinar sucursales |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | Un administrador del sistema realiza acciones comoregistrar, editar y ver sucursales. |
| Tipo: | Primario |

<br>


| Número | CU009 |
| :--- | :---: |
| Caso de Uso: | Gestionar admistradores de sucursal |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: |  Un administrador del sistema realiza acciones como registrar, editar, ver y desactivar a administradores de sucursal. |
| Tipo: | Primario |

<br>

| Número | CU010 |
| :--- | :---: |
| Caso de Uso: | Configurar monto de depreciación por kilómetro |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: |  Un administrador del sistema configura el monto de depreciacion por kilometro para que se apliquen a cada viaje en todas las sucursales. |
| Tipo: | Primario |

<br>

| Número | CU011 |
| :--- | :---: |
| Caso de Uso: | Ver reportes del sistema |
| Actores: | Usuario Administrador de Sistema, Aplicación |
| Descripción: | Un administrador del sistema ve reportes pueden ser de ganancias, costos operativos y rutas de diferentes sucursales. |
| Tipo: | Primario |

### Administrador de sucursal
___

| Número | CU012 |
| :--- | :---: |
| Caso de Uso: | Gestinar buses |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | Un administrador de sucursal realiza acciones como registrar, editar, ver y desactivar a buses, la desactivacion tendra restricciones. |
| Tipo: | Primario |

<br>

| Número | CU013 |
| :--- | :---: |
| Caso de Uso: | Gestinar choferes |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | Un administrador de sucursal realiza acciones como registrar, editar, ver y desactivar a choferes, la desactivacion tendra restricciones. |
| Tipo: | Primario |

<br>

| Número | CU014 |
| :--- | :---: |
| Caso de Uso: | Gestionar ruta |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | Un administrador de sucursal realiza acciones como registrar, editar y ver rutas, para la eliminacion de rutas hay restricciones a tomar en cuenta.|
| Tipo: | Primario |

<br>


| Número | CU015 |
| :--- | :---: |
| Caso de Uso: | Gestionar viajes regulares |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | Un administrador de sucursal realiza acciones como registrar, editar, ver eliminar viajes, para la eliminacion de viajes regulares hay restricciones a tomar en cuenta. |
| Tipo: | Primario |

<br>

| Número | CU016 |
| :--- | :---: |
| Caso de Uso: | Registrar control viaje regular |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | Un administrador de sucursal rellena datos de control cuando un bus hace su salida y cuando hace su llegada |
| Tipo: | Primario |

<br

| Número | CU017 |
| :--- | :---: |
| Caso de Uso: | Gestionar viajes privados |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | Un administrador de sucursal realiza acciones como ver las solicutudes de alquiler que tiene en su sucursal, si hay alguna podra confirmar el monto que el sistema de defino o editarlo. Al confirmar el monto debera asigar chofer y bus al viaje privado. |
| Tipo: | Primario |

<br>

| Número | CU018 |
| :--- | :---: |
| Caso de Uso: | Registrar control viaje privado |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | Un administrador de sucursal rellena datos de control cuando un bus hace su salida y cuando hace su llegada |
| Tipo: | Primario |

<br>

| Número | CU019 |
| :--- | :---: |
| Caso de Uso: | Registrar gasto taller |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | Un administrador de sucursal registrara los gastos que tuvo un bus en el taller.  |
| Tipo: | Primario |

<br>

| Número | CU020 |
| :--- | :---: |
| Caso de Uso: | Ver reportes de la sucursal |
| Actores: | Usuario Administrador Sucursal, Aplicación |
| Descripción: | Un administrador ve reportes como ingreso de venta de boletos, ingreso por aquiler de buses y deprecion por bus. |
| Tipo: | Primario |


### Chofer
___

| Número | CU021 |
| :--- | :---: |
| Caso de Uso: | Ver viajes asignados |
| Actores: | Usuario Chofer, Aplicación |
| Descripción: | Un chofer visualiza los viajes que tiene asignado, tanto regulares como privados y toda la informacion relacionada. |
| Tipo: | Primario |

<br>

| Número | CU022 |
| :--- | :---: |
| Caso de Uso: | Registrar control salida |
| Actores: | Usuario Chofer, Aplicación |
| Descripción: | El chofer rellena los el control de salida de un viaje, ya sea regular o privado |
| Tipo: | Primario |































