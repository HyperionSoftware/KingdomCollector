package cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.HumanPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.Player;

public class PlayerViewModel extends ViewModel {
    private Player humanPlayer;
    private Player robotPlayer;

    public PlayerViewModel(Player humanPlayer, Player robotPlayer) {
        this.humanPlayer = humanPlayer;
        this.robotPlayer = robotPlayer;
    }

    public LiveData<String> getHumanPlayerName() {
        return new MutableLiveData<>(humanPlayer.getName());
    }

    public LiveData<Integer> getHumanPlayerPoints() {
        return humanPlayer.getPoints();
    }

    public LiveData<String> getRobotPlayerName() {
        return new MutableLiveData<>(robotPlayer.getName());
    }

    public LiveData<Integer> getRobotPlayerPoints() {
        return robotPlayer.getPoints();
    }
}
