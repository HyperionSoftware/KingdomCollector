package cat.udl.hyperion.appmobils.kingdomcollector.Models.Player;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.CardCollection;
import android.util.Log;


/**
 * Esta clase representa un jugador robot en un juego de cartas.
 */
public class IAPlayer extends Player {

    private static final String TAG = "IAPlayer";

    /**
     * Constructor de la clase IAPlayer.
     *
     * @param name El nombre del jugador robot.
     */
    public IAPlayer(int id, String name) {
        super(id, name); // Llama al constructor de la clase padre (Player) para inicializar el identificador y el nombre.
        Log.d(TAG, "IAPlayer creado con ID: " + id + " y nombre: " + name);
    }

    /**
     * Método para que el jugador robot seleccione una carta para jugar.
     *
     * @param board El tablero actual del juego.
     * @return La carta seleccionada por el jugador robot, o null si no se ha seleccionado ninguna.
     */
    @Override
    public CardCollection playCard(Board board) {
        // TODO: Implementar la lógica de selección de carta para el jugador IA.
        CardCollection card = null;
        Log.d(TAG, "Jugador IA seleccionó una carta: " + (card != null ? card.toString() : "null"));
        // En este método se debe programar la lógica que permita al jugador robot seleccionar
        // una carta para jugar. Una vez que se haya seleccionado la carta, se debe devolver
        // un objeto de tipo Card con la carta seleccionada. Si el jugador robot no selecciona
        // ninguna carta, se debe devolver null.
        return null; // Devuelve la carta seleccionada o null si no se ha seleccionado ninguna.
    }
}
