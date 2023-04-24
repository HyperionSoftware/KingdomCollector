package cat.udl.hyperion.appmobils.kingdomcollector.viewmodels;

import androidx.lifecycle.ViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.CardCollection;
import cat.udl.hyperion.appmobils.kingdomcollector.views.OnCardClickListener;

/**
 * ViewModel que se encarga de manejar los datos del tablero.
 * */
public class BoardViewModel extends ViewModel implements OnCardClickListener {
    private Board board;
    private CardCollection selectedCard;

    public BoardViewModel() {
        board = new Board();
    }

    /**
     * Coloca una carta en las coordenadas especificadas en el tablero.
     * @param x Coordenada X en la que se quiere colocar la carta.
     * @param y Coordenada Y en la que se quiere colocar la carta.
     * @param card La carta que se quiere colocar en el tablero.
     */
    public void placeCard(int x, int y, CardCollection card) {
        board.colocarCarta(x, y, card);
    }

    /**
     * Obtiene la carta ubicada en las coordenadas especificadas del tablero.
     * @param x Coordenada X de la carta que se quiere obtener.
     * @param y Coordenada Y de la carta que se quiere obtener.
     * @return La carta ubicada en las coordenadas especificadas del tablero.
     */
    public CardCollection getCard(int x, int y) {
        return board.obtenerCarta(x, y);
    }

    /**
     * Reinicia el tablero, eliminando todas las cartas colocadas en él.
     */
    public void resetBoard() {
        board.reiniciarTablero();
    }

    /**
     * Implementación del método de la interfaz `OnCardSelectedListener` que permite
     * establecer la carta seleccionada por el usuario.
     * @param card La carta seleccionada por el usuario.
     */
    @Override
    public void onCardSelected(CardCollection card){
        selectedCard = card;
    }

    @Override
    public void onCardDeselected(CardCollection card) {
        selectedCard = card;
    }

}
