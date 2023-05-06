package cat.udl.hyperion.appmobils.kingdomcollector.game.models.player;

import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;

public class HumanPlayer extends Player{
    @Override
    public void playTurn(GameController gameController) {
        // La lógica para jugar el turno de un jugador humano se manejará en la interfaz de usuario
        // No es necesario implementar nada aquí
        // NO TOCAR ESTO PORFAVOR PARA NADA. DEJAR VACÍO.
    }
    public HumanPlayer(String name) {
        super(name);
    }
}
