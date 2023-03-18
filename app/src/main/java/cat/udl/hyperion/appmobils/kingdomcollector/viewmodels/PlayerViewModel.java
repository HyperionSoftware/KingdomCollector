package cat.udl.hyperion.appmobils.kingdomcollector.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Player.Player;

public class PlayerViewModel extends ViewModel {
    private final MutableLiveData<Player> player;

    public PlayerViewModel() {
        player = new MutableLiveData<>();
    }

    public void setPlayer(Player player) {
        this.player.setValue(player);
    }

    public LiveData<Player> getPlayer() {
        return player;
    }

    public String getName(){
        Player currentPlayer = player.getValue();
        if(currentPlayer!=null){
            String s = "Player name: " + currentPlayer.getName();
            return s;
        }
        return null;
    }

    public void updateScore(int newScore) {
        Player currentPlayer = player.getValue();
        if (currentPlayer != null) {
            currentPlayer.setScore(newScore);
            player.setValue(currentPlayer);
        }
    }

    public void updateNumberOfCards(int newNumberOfCards) {
        Player currentPlayer = player.getValue();
        if (currentPlayer != null) {
            currentPlayer.setDeck(newNumberOfCards);
            player.setValue(currentPlayer);
        }
    }

}
