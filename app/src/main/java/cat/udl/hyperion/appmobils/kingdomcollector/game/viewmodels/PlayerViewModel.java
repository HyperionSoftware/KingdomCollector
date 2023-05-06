package cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.HumanPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.Player;

public class PlayerViewModel extends ViewModel {
    private HumanPlayer player;

    public PlayerViewModel(HumanPlayer player) {
        this.player = player;
    }

    public LiveData<String> getPlayerName() {
        return new MutableLiveData<>(player.getName());
    }

    public LiveData<Integer> getPlayerPoints() {
        return player.getPoints();
    }
}
