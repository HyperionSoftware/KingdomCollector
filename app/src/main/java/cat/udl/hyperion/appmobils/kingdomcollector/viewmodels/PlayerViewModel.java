package cat.udl.hyperion.appmobils.kingdomcollector.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Player.Player;

/**
 * Esta clase es responsable de almacenar y manipular la información del jugador.
 */
public class PlayerViewModel extends ViewModel {

    private final MutableLiveData<Player> player;

    /**
     * Constructor de la clase PlayerViewModel.
     */
    public PlayerViewModel() {
        player = new MutableLiveData<>();
    }

    /**
     * Establece el jugador.
     * @param player Objeto Player que representa al jugador.
     */
    public void setPlayer(Player player) {
        this.player.setValue(player);
    }

    /**
     * Obtiene el objeto LiveData de Player.
     * @return LiveData de Player.
     */
    public LiveData<Player> getPlayer() {
        return player;
    }

    /**
     * Obtiene el nombre del jugador actual.
     * @return String que representa el nombre del jugador actual.
     */
    public String getName(){
        Player currentPlayer = player.getValue();
        if(currentPlayer!=null){
            String s = "Player name: " + currentPlayer.getName();
            return s;
        }
        return null;
    }

    /**
     * Actualiza la puntuación del jugador actual.
     * @param newScore Nueva puntuación del jugador.
     */
    public void updateScore(int newScore) {
        Player currentPlayer = player.getValue();
        if (currentPlayer != null) {
            currentPlayer.setScore(newScore);
            player.setValue(currentPlayer);
        }
    }

    /**
     * Actualiza el número de cartas del jugador actual.
     * @param newNumberOfCards Nuevo número de cartas del jugador.
     */
    public void updateNumberOfCards(int newNumberOfCards) {
        Player currentPlayer = player.getValue();
        if (currentPlayer != null) {
            currentPlayer.setDeck(newNumberOfCards);
            player.setValue(currentPlayer);
        }
    }
}
