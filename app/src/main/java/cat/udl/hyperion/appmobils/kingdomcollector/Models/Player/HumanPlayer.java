package cat.udl.hyperion.appmobils.kingdomcollector.Models.Player;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.CardCollection;
import java.util.HashMap;
import java.util.Map;
import android.util.Log;



/**
 * Esta clase representa un jugador humano en un juego de cartas.
 */
public class HumanPlayer extends Player {

    private static final String TAG = "HumanPlayer";

    /**
     * Constructor de la clase HumanPlayer.
     *
     * @param id El identificador único del jugador humano.
     * @param name El nombre del jugador humano.
     */
    public HumanPlayer(int id, String name) {
        super(id, name); // Llama al constructor de la clase padre (Player) para inicializar el identificador y el nombre.
        Log.d(TAG, "HumanPlayer creado con ID: " + id + " y nombre: " + name);
    }

    /**
     * Método para que el jugador humano seleccione una carta para jugar.
     *
     * @param board El tablero actual del juego.
     * @return La carta seleccionada por el jugador humano, o null si no se ha seleccionado ninguna.
     */
    @Override
    public CardCollection playCard(Board board) {
        // TODO: Implementar la lógica de selección de carta para el jugador humano.
        // En este método se debe programar la lógica que permita al jugador humano seleccionar
        // una carta para jugar. Una vez que se haya seleccionado la carta, se debe devolver
        // un objeto de tipo Card con la carta seleccionada. Si el jugador humano no selecciona
        // ninguna carta, se debe devolver null.
        CardCollection card = null;
        Log.d(TAG, "Jugador humano seleccionó una carta: " + (card != null ? card.toString() : "null"));
        return card; // Devuelve la carta seleccionada o null si no se ha seleccionado ninguna.
    }

    /**
     * Convierte el estado del jugador en un objeto Map<String, Object> que puede ser almacenado en Firebase.
     * @return un objeto Map<String, Object> que representa el estado del jugador.
     */
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        // Agrega aquí las propiedades del jugador que quieras almacenar, por ejemplo:
        result.put("name", getName());
        result.put("score", getScore());
        // ...
        return result;
    }
}
