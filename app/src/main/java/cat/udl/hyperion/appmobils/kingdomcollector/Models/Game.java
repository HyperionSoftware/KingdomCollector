package cat.udl.hyperion.appmobils.kingdomcollector.Models;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Player.Player;

/**
 * Esta clase representa el juego Kingdom Collector.
 * */
public class Game {
    private Player player1;
    private Player player2;
    private Board board;
    private Player currentPlayer;
    private boolean isGameOver;

    /**
     * Crea un objeto Game con dos jugadores y un tamaño de tablero.
     *
     * @param player1 el primer jugador del juego.
     * @param player2 el segundo jugador del juego.
     * @param boardSize el tamaño del tablero.
     */
    public Game(Player player1, Player player2, int boardSize) {
        this.player1 = player1;
        player1.setName("JOAN");
        this.player2 = player2;
        this.board = new Board();
        this.currentPlayer = player1;
        this.isGameOver = false;
    }

    /**
     * Realiza una jugada en el tablero.
     *
     * @param x la posición x donde colocar la carta.
     * @param y la posición y donde colocar la carta.
     * @param card la carta que se jugará.
     */
    public void jugar(int x, int y, Card card) {
        if (!isGameOver) {
            if (currentPlayer.hasCard(card) && board.obtenerCarta(x, y) == null) {
                board.colocarCarta(x, y, card);
                currentPlayer.removeCard(card);
                verificarGanador();
                if (!isGameOver) {
                    cambiarTurno();
                }
            }
        }
    }

    /**
     * Cambia el turno de los jugadores.
     */
    private void cambiarTurno() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    /**
     * Verifica si hay un ganador en el juego.
     */
    private void verificarGanador() {
        if (player1.getNumberOfCards() == 0 || player2.getNumberOfCards() == 0) {
            isGameOver = true;
            Player ganador = (player1.getScore() > player2.getScore()) ? player1 : player2;
            System.out.println("El ganador es: " + ganador.getName());
        }
    }

    /**
     * Reinicia el juego.
     */
    public void reiniciarJuego() {
        board.reiniciarTablero();
        player1.resetCards();
        player2.resetCards();
        currentPlayer = player1;
        isGameOver = false;
    }
}
