package cat.udl.hyperion.appmobils.kingdomcollector.Models;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Player.Player;
import cat.udl.hyperion.appmobils.kingdomcollector.views.GameView;
import cat.udl.hyperion.appmobils.kingdomcollector.views.LoginActivity;

/**
 * Esta clase representa el juego Kingdom Collector.
 * */
public class Game {
    protected String myClassTag = this.getClass().getSimpleName();
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
    /**
     * Guarda la información de la partida cuando se termina.
     * @param userId
     * @param score
     */
    public void saveGameData(String userId, int score) {
        DatabaseReference gameDataRef = FirebaseDatabase.getInstance().getReference("game_data").child(userId);

        Map<String, Object> gameData = new HashMap<>();
        gameData.put("userId", userId);
        gameData.put("score", score);

        gameDataRef.push().setValue(gameData)
                .addOnSuccessListener(aVoid -> {
                    // Información del juego guardada exitosamente
                    Log.d(myClassTag, "Guardado el ganador exitosamente en firebase database.");
                })
                .addOnFailureListener(e -> {
                    // Error al guardar la información del juego
                    Log.d(myClassTag, "No se ha guardado el ganador en firsebase database");
                });
    }
}
