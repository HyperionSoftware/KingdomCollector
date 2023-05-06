package cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels;

import androidx.lifecycle.ViewModel;

import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.HumanPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.Player;

public class PlayerViewModel extends ViewModel {
    private HumanPlayer player;

    public PlayerViewModel() {
        // Asume que el nombre del jugador es "Jugador1" por defecto.
        player = new HumanPlayer("Joan");
        player.getDeck().initializeDeck();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(HumanPlayer player) {
        this.player = player;
    }

    // Puedes agregar más métodos si es necesario para actualizar y gestionar la información del jugador.
}
