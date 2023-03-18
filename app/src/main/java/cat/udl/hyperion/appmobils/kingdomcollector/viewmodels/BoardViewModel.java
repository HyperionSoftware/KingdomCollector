package cat.udl.hyperion.appmobils.kingdomcollector.viewmodels;

import androidx.lifecycle.ViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;

public class BoardViewModel extends ViewModel {
    private Board board;

    public BoardViewModel() {
        board = new Board();
    }

    public void placeCard(int x, int y, Card card) {
        board.colocarCarta(x, y, card);
    }

    public Card getCard(int x, int y) {
        return board.obtenerCarta(x, y);
    }

    public void resetBoard() {
        board.reiniciarTablero();
    }
}
