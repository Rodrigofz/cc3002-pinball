# Pinball - Tarea CC3002

### Código

El código del proyecto se divide en 4 grandes categorías distintas:

###### Controller
Aquí se encuentra la clase game. Es el controlador del juego, se encarga de unir todos los componentes de este para que trabajen en conjunto.

La clase contiene una table, un score y 3 bonus. A score se le suma un valor cuando es necesario, y cada uno de los bonus puede ser activado cuando se necesita, desencadenando algun otro evento en game.

###### Facade
En la siguiente tarea se encargará de exponer la lógica del juego a una GUI.

###### Logic
Aquí se encuentran todos los elementos del juego. Estos son:
-Bonus: Bonuses que se pueden obtener al jugar
-Game elements: Hittables (bumpers y targets). Elementos golpeables que desencadenan distintos eventos al ser golpeados.
-Table: Elemento que contiene hittables. Tiene un score, que va cambiando constantemente y representa el score que se le debe sumar al de game cuando algún evento ocurre.

###### Visitor
Elementos usados para el patrón de diseño visitor, explicado más adelante.

### Diseño

El juego se diseñó de tal forma que un objeto game contiene y controla a un objeto table. Además la observa, utilizando el patrón de diseño observer. A su vez, este objeto table contiene hittables, que pueden ser bumpers o targets. Siendo estos últimos observados por table.

Como se utiliza el patrón de diseño observer, los hittables envían notificaciones a table y esta a game. Para enviar dichas notificaciones, se utiliza el patrón de diseño visitor. Cada visitor puede visitar a table o a game, y cada uno cumple un rol distinto, son 5 en total. En general, los hittables notifican a table cuando hacen alguna acción, y a su vez, table notifica a game cuando esta misma o sus hittables realizan alguna acción. 
 