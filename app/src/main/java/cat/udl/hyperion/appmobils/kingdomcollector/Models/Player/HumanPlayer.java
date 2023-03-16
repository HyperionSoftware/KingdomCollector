package cat.udl.hyperion.appmobils.kingdomcollector.Models.Player;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;

/**
 * Esta clase representa un jugador humano.
 * */
public class HumanPlayer extends Player {
    /**
     * Constructor de HumanPlayer.
     * @param name
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * Jugar una carta.
     * @param board
     * @return
     */
    @Override
    public Card playCard(Board board) {
        // TODO: Implementar la lógica de selección de carta para el jugador humano
        Card card = null;
        return card;
    }
}
