# Leaguesports

Aplicación web para gestionar elementos relacionados con __eventos de e-sports__. Se podrá acceder de forma pública a información de partidos, entradas, resultados, eventos, jugadores y equipos, noticias sobre eventos y merchandising. 

### Entidades:

- __Jugador:__ Miembro de un equipo definido por un nombre, ID de jugador, edad, etc.
- __Equipo:__ Conjunto de jugadores con un nombre, país e ID de equipo.
- __Partido:__ Evento con dos equipos, una fecha y un resultado.
- __Torneo:__ Conjunto de partidos con un nombre, fecha de inicio, número de edición y premio.
- __Producto:__ Elemento con un código, precio y comprador. Puede ser una entrada y tener un partido asignado.
- __Usuario:__ Permite crear un perfil para comprar productos y recibir notificaciones. Los usuarios administradores podrán hacer cambios en los equipos, jugadores, etc. e informar al resto de usuarios.

### Servicio interno:

Los usuarios administradores, al modificar información relativa a equipos, partidos, jugadores, etc. notificarán al resto de usuarios acerca del cambio por medio de un correo electrónico. 

Los usuarios reciben información mediante un correo electrónico tras realizar una compra de un producto. 

