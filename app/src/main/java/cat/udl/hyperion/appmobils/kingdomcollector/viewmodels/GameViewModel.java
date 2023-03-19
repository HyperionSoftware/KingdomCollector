// GameViewModel.java
package cat.udl.hyperion.appmobils.kingdomcollector.viewmodels;

import androidx.lifecycle.ViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Game;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Player.Player;

/**
 * Esta clase ViewModel representa la vista del juego, el estado de los jugadores y del tablero.
 * */
public class GameViewModel extends ViewModel {
    private Game game;

    /**
     * Inicializa el juego con los jugadores y el tamaño del tablero especificados.
     * @param player1 el jugador 1 del juego.
     * @param player2 el jugador 2 del juego.
     * @param boardSize el tamaño del tablero.
     * */
    public void initializeGame(Player player1, Player player2, int boardSize) {
        game = new Game(player1, player2, boardSize);
    }

    /**
     * Juega una carta en la posición especificada del tablero.
     * @param x la posición en el eje X donde se colocará la carta.
     * @param y la posición en el eje Y donde se colocará la carta.
     * @param card la carta a jugar.
     * */
    public void play(int x, int y, Card card) {
        game.jugar(x, y, card);
    }

    /**
     * Reinicia el juego.
     * */
    public void restartGame() {
        game.reiniciarJuego();
    }

    /**
     * Obtiene el estado actual del juego.
     * @return el objeto Game que representa el juego.
     * */
    public Game getGame() {
        return game;
    }
}
