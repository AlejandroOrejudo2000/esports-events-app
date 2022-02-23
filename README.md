# Leaguesports

Aplicación web para gestionar elementos relacionados con __eventos de e-sports__. Se podrá acceder de forma pública a información de partidos, entradas, resultados, eventos, jugadores y equipos, noticias sobre eventos y merchandising. 

---
## Entidades:

- __Jugador:__ Miembro de un equipo definido por un nombre, ID de jugador, edad, etc.
- __Equipo:__ Conjunto de jugadores con un nombre, país e ID de equipo.
- __Partido:__ Evento con dos equipos, una fecha y un resultado.
- __Torneo:__ Conjunto de partidos con un nombre, fecha de inicio, número de edición y premio.
- __Producto:__ Elemento con un código, precio y comprador. Puede ser una entrada y tener un partido asignado.
- __Usuario:__ Permite crear un perfil para comprar productos y recibir notificaciones. Los usuarios administradores podrán hacer cambios en los equipos, jugadores, etc. e informar al resto de usuarios.
---
## Servicio interno:
Los usuarios administradores, al modificar información relativa a equipos, partidos, jugadores, etc. notificarán al resto de usuarios acerca del cambio por medio de un correo electrónico. 

Los usuarios reciben información mediante un correo electrónico tras realizar una compra de un producto. 

---
## Pantallas

A continuación se muestran las pantallas principales de la aplicación web: Las distintas páginas que muestran listas de elementos usan un sistema de paginación en el que muestran hasta 20 elementos por página. Además de enlaces a la página anterior y posterior si existen. Las opciones de añadir entidades serán privadas.

### Secciones principales (públicas)

- ___Pantalla principal:___ Pantalla inicial de la web. Desde ella se puede acceder al resto de secciones principales: ___torneos___, ___equipos___, ___jugadores___ y ___productos___, además del ___registro de usuarios___. 

![Principal](/documentation/pantallas/principal.PNG)

- ___Lista de torneos:___ Muestra la lista de torneos y añade un enlace a la pantalla de creación de torneo. Permite navegar a la página de detalles de un torneo pulsando sobre él.

![Torneos](/documentation/pantallas/torneos.PNG)

- ___Lista de equipos:___ Muestra la lista de equipos y añade un enlace a la pantalla de creación de equipo. Permite navegar a la página de detalles de un equipo pulsando sobre el nombre del mismo.

![Equipos](/documentation/pantallas/equipos.PNG)

- ___Lista de jugadores:___ Muestra la lista de jugadores y añade un enlace a la pantalla de creación de jugador. Permite navegar a la página de detalles de un jugador pulsando sobre el nickname del mismo.

![Jugadores](/documentation/pantallas/jugadores.PNG)

- ___Lista de productos:___ Muestra la lista de productos y añade un enlace a la pantalla de creación de producto. Permite navegar a la página de detalles de un producto pulsando sobre el nombre del mismo.

![Productos](/documentation/pantallas/productos.PNG)

### Secciones de detalles (públicas)

- ___Detalles de torneo:___ Muestra la fecha de inicio y fin, la lista de equipos participantes y una tabla con los partidos que incluye los dos equipos que participan y la fecha de celebración. También incluye opciones de añadir y borrar participantes, equipos y el propio torneo, que serán privadas.

![Torneo](/documentation/pantallas/torneo.PNG)

- ___Detalles de equipo:___ Muestra el nombre y años en activo del equipo y la lista de jugadores miembros del equipo seleccionado, pudiendo acceder a su información haciendo click sobre su nickname. También incluye opciones de borrar y editar el equipo, que serán privadas.

![Equipo](/documentation/pantallas/equipo.PNG)

- ___Detalles de jugador:___ Muestra el nickname, nombre, apellidos, edad, género y nacionalidad del jugador seleccionado, además del equipo al que pertenece si es que pertenece a alguno, pudiendo acceder a su información haciendo click sobre su nombre. También incluye opciones de borrar y editar el jugador y eliminar o seleccionar su equipo, que serán privadas.

![Jugador](/documentation/pantallas/jugador.PNG)

- ___Detalles de producto:___ Muestra el nombre y precio del producto seleccionado. También incluye la opción de borrar el producto, que será privada.

![Producto](/documentation/pantallas/producto.PNG)

### Secciones de creación y actualización (privadas)

- ___Creación de torneo:___ Muestra un formulario para crear un nuevo torneo con un nombre y unas fechas de inicio y fin. Al enviar el formulario se creará el torneo y se cargará una página que permite ver el torneo creado o volver a la lista de torneos.

![CrearTorneo](/documentation/pantallas/creartorneo.PNG) ![TorneoCreado](/documentation/pantallas/torneocreado.PNG)

- ___Creación/actualización de equipo:___ Muestra un formulario para crear un nuevo equipo con un nombre y unos años en activo. Al enviar el formulario se creará el equipo y se cargará una página que permite ver el equipo creado o volver a la lista de equipos. La pantalla de editar equipo es similar a la de creación pero incluye los valores actuales del equipo como valores iniciales del formulario, y al enviarlo te redirecciona automáticamente a la página de dicho equipo. 

![CrearEquipo](/documentation/pantallas/crearequipo.PNG) ![EditarEquipo](/documentation/pantallas/updequipo.PNG)![EquipoCreado](/documentation/pantallas/equipocreado.PNG)

- ___Creación/actualización de jugador:___ Muestra un formulario para crear un nuevo jugador con un nickname, nombre, apellidos, edad, género y nacionalidad. Al enviar el formulario se creará el jugador y se cargará una página que permite ver el jugador creado o volver a la lista de jugadores. La pantalla de editar jugador es similar a la de creación pero incluye los valores actuales del jugador como valores iniciales del formulario, y al enviarlo te redirecciona automáticamente a la página de dicho jugador. 

![CrearJugador](/documentation/pantallas/crearjugador.PNG) ![EditarJugador](/documentation/pantallas/updjugador.PNG)![JugadorCreado](/documentation/pantallas/jugadorcreado.PNG)

- ___Creación de productos:___ Muestra un formulario para crear un nuevo producto con un nombre y un precio. Al enviar el formulario se creará el producto y se cargará una página que permite ver el producto creado o volver a la lista de productos. 

![CrearProducto](/documentation/pantallas/crearproducto.PNG) ![ProductoCreado](/documentation/pantallas/productocreado.PNG)

- ___Registro de usuarios:___ Muestra un formulario para crear un nuevo usuario con un nombre y una contraseña. Al enviar el formulario se creará el usuario y se volverá a la pantalla principal. Esta sección será reemplazada por un sistema de registro y login de usuarios.

![CrearUsuario](/documentation/pantallas/crearusuario.PNG)
