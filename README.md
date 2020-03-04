# Alpha Project
## Proyecto para la materia de Sistemas Distribuidos

### Lineamientos
 - Multicast (UDP) y Sockets TCP
 - El cliente tiene una interfaz con una rejilla de objetos (pueden ser checkboxes)
 - Cada jugador tiene la misma interfaz con una rejilla de objetos
 - El servidor puede enviar a los clientes (vía mensaje multicast) un “monstruo” y la posición en la cual aparecerá. El servidor abre una ventana de tiempo para recibir respuestas. Las respuestas provienen de los clientes indicando que han golpeado al “monstruo”.
 - Los clientes reciben mensajes multicast (conteniendo monstruos) y lo despliegan en pantalla en la posición indicada.
 - El usuario golpea al monstruo (hace clic sobre el checkbox) y envía un mensaje vía Sockets al servidor.
 - El cliente que golpee N monstruos primero es el ganador. Esto es determinado por el Servidor.
 - Cada jugador, en su interfaz, debe mostrar su puntaje y el puntaje de los otros jugadores.
 - El servidor le avisa a los jugadores quien ganó el juego (vía un mensaje multicast) y se inicializa para empezar otro juego.
 - Los jugadores deben de poder entrar y salir del juego dinámicamente, sin que se afecte la partida actual. Si el jugador está a medio juego con cierto puntaje y se sale, al regresar deberá mantener su puntaje si y sólo si aun se encuentra en el mismo juego.
 - El servidor debe ofrecer un servicio de registro. Como respuesta al registro, el servidor le pasará al cliente la dirección IP y los puertos con los que se realiza el juego.
 - Sólo jugadores registrados podrán jugar.

### Entregables
 - Todo el código fuente.
 - Versión ejecutable.
 - Versión estresamiento.

### Evaluación experimental de desempeño

Estresar todo el sistema
 - Registro concurrente, juego concurrente, entrada concurrente y salida concurrente de jugadores.
 - Explicación detallada de la definición del experimento
 - Interpretación y análisis de resultados

> Ejemplo: 
> Tiempo de respuesta promedio y desviación estándar para 50, 100, 150, …, 500 clientes. 
> Realice al menos 10 repeticiones por configuración.
> Incluir gráficas y hoja de cálculo con los datos de los experimentos.

### Criterios evaluación:
 - Equipo de 3 personas.
 - Ejecución del proyecto con todos los requerimientos indicados en su descripción (10% de su calificación final)
 - Calidad y presentación de la evaluación experimental de desempeño (10% de su calificación final)
 - Fecha de entrega: Miércoles 18 de Marzo de 2020 a las 5 pm*
 - NOTA 1: 25% menos por cada día natural de retraso.
 - NOTA 2: Si se entrega después de la hora de entrega, en automático aplica un día menos.
