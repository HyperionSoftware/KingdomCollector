package cat.udl.hyperion.appmobils.kingdomcollector.Models.Player;


import cat.udl.hyperion.appmobils.kingdomcollector.Models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;

/**
 * Esta clase representa un jugador robot.
 * */
public class IAPlayer extends Player {
    /**
     * Constructor de IAPlayer.
     * @param name
     */
    public IAPlayer(String name) {
        super(name);
    }

    @Override
    public Card playCard(Board board) {
        // TODO: Implementar la lógica de selección de carta para el jugador IA
        return null;
    }
}
