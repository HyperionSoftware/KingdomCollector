package cat.udl.hyperion.appmobils.kingdomcollector.Models.Player;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.CardCollection;

/**
 * Esta clase representa un jugador humano en un juego de cartas.
 */
public class HumanPlayer extends Player {

    /**
     * Constructor de la clase HumanPlayer.
     *
     * @param name El nombre del jugador humano.
     */
    public HumanPlayer(String name) {
        super(name); // Llama al constructor de la clase padre (Player) para inicializar el nombre.
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
        return card; // Devuelve la carta seleccionada o null si no se ha seleccionado ninguna.
    }
}
