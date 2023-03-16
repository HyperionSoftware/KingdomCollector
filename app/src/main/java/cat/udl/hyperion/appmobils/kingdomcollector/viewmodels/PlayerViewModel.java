package cat.udl.hyperion.appmobils.kingdomcollector.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Player.Player;

public class PlayerViewModel extends ViewModel {
    private MutableLiveData<Player> player;

    public PlayerViewModel() {
        player = new MutableLiveData<>();
    }

    public LiveData<Player> getPlayer() {
        return player;
    }

    public void setPlayer(Player newPlayer) {
        player.setValue(newPlayer);
    }

    public void updateScore(int newScore) {
        Player currentPlayer = player.getValue();
        if (currentPlayer != null) {
            currentPlayer.setScore(newScore);
            player.setValue(currentPlayer);
        }
    }
}
