// GameViewModel.java
package cat.udl.hyperion.appmobils.kingdomcollector.viewmodels;

import androidx.lifecycle.ViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Game;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Player.Player;

public class GameViewModel extends ViewModel {
    private Game game;

    public void initializeGame(Player player1, Player player2, int boardSize) {
        game = new Game(player1, player2, boardSize);
    }

    public void play(int x, int y, Card card) {
        game.jugar(x, y, card);
    }

    public void restartGame() {
        game.reiniciarJuego();
    }

    public Game getGame() {
        return game;
    }
}
