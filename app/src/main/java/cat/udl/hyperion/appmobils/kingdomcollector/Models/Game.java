package cat.udl.hyperion.appmobils.kingdomcollector.Models;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Board;


import java.util.HashMap;
import java.util.Map;


import cat.udl.hyperion.appmobils.kingdomcollector.Models.Player.Player;

/**
 * Esta clase representa el juego Kingdom Collector.
 * */
public class Game {
    protected String myClassTag = this.getClass().getSimpleName();
    private final Player player1;
    private final Player player2;
    private final Board board;
    private Player currentPlayer;
    private boolean isGameOver;

    /**
     * Convierte el estado del juego en un objeto Map<String, Object> que puede ser almacenado
     * en Firebase.
     * Incluye información sobre los jugadores, el tablero, el jugador actual y si el juego ha
     * terminado.
     *
     * @return un objeto Map<String, Object> que representa el estado del juego.
     */
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("player1", player1.toMap());
        result.put("player2", player2.toMap());
        result.put("board", board.toMap());
        result.put("currentPlayer", currentPlayer.getId());
        result.put("isGameOver", isGameOver);
        return result;
    }


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
    public void jugar(int x, int y, CardCollection card) {
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
     * @param userId El identificador del usuario.
     * @param score La puntación del usuario.
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
