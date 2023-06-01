El juego implementa las siguientes mecánicas:

Inicio de un nuevo juego: La función startNewGame() se encarga de reiniciar el tablero, las barajas de cartas de los jugadores y los puntos de ambos jugadores.

Jugar una carta: La función playCard() permite a un jugador jugar una carta en una posición específica del tablero. Verifica si es el turno del jugador, coloca la carta en el tablero, actualiza las cartas adyacentes según las reglas del juego, elimina la carta del mazo del jugador, actualiza los puntos del juego y cambia el turno al siguiente jugador.

Verificar el ganador: La función getWinner() compara los puntos de los jugadores y devuelve al jugador ganador o null en caso de empate. La función isGameOver() verifica si el juego ha terminado, ya sea porque el tablero está lleno o porque una de las barajas de cartas se ha agotado. Si el juego ha terminado, muestra un mensaje de victoria o empate y realiza acciones adicionales según el resultado.

Actualizar los puntos del juego: La función updateGamePoints() calcula los puntos de cada jugador contando las cartas en el tablero que les pertenecen y actualiza los puntos de los jugadores.

Cambiar de turno: La función switchTurn() cambia el turno entre los jugadores, estableciendo al jugador actual como el siguiente jugador.

Verificar si una celda está vacía: La función isCellEmpty() verifica si una celda en el tablero no tiene una carta colocada.

Obtener los puntos del jugador humano y el jugador de la computadora: Las funciones getHumanPlayerPoints() y getComputerPlayerPoints() devuelven los puntos actuales del jugador humano y el jugador de la computadora, respectivamente.

Verificar si es el turno del jugador humano: La función isHumanPlayerTurn() verifica si es el turno del jugador humano.

Verificar y actualizar las cartas adyacentes: La función checkAndUpdateAdjacentCards() verifica y actualiza las cartas adyacentes a una posición específica del tablero después de colocar una nueva carta. Compara el poder de la nueva carta con las cartas adyacentes y cambia la propiedad de propiedad de las cartas según las reglas del juego.

Mostrar el fragmento del ganador: La función showWinnerFragment() muestra un fragmento de victoria en la interfaz de usuario con el nombre del ganador y los puntos finales del juego.

Incrementar el contador de victorias: La función incrementWinCount() aumenta el contador de victorias del jugador humano en la base de datos de Firebase.