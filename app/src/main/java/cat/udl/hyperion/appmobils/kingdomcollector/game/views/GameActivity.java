package cat.udl.hyperion.appmobils.kingdomcollector.game.views;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.List;
import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.admin.SharedPreferencesManager;
import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;
import cat.udl.hyperion.appmobils.kingdomcollector.game.fragments.BoardFragment;
import cat.udl.hyperion.appmobils.kingdomcollector.game.fragments.DeckFragment;
import cat.udl.hyperion.appmobils.kingdomcollector.game.fragments.PlayerFragment;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.HumanPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.IAPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.BoardViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.DeckViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.views.GameActivityInterface;

public class GameActivity extends AppCompatActivity implements GameActivityInterface {
    private GameController gameController;
    SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sharedPreferencesManager = new SharedPreferencesManager(this);
        List<Card> selectedCards = sharedPreferencesManager.getSelectedCards();

        gameController = new GameController(
                this,
                new BoardViewModel(gameController),
                new DeckViewModel(selectedCards),
                new DeckViewModel(),
                this,
                sharedPreferencesManager
        );

        updateUI();
    }

    @Override
    public void updateUI() {
        if (gameController == null) {
            return;
        }

        PlayerFragment playerFragment = PlayerFragment.newInstance(
                (HumanPlayer) gameController.getHumanPlayer(),
                (IAPlayer) gameController.getComputerPlayer()
        );

        DeckFragment deckFragment = DeckFragment.newInstance(gameController.getHumanDeckViewModel());

        BoardFragment boardFragment = BoardFragment.newInstance(gameController);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.playerFragment, playerFragment);
        fragmentTransaction.replace(R.id.deckFragment, deckFragment);
        fragmentTransaction.replace(R.id.boardFragment, boardFragment);

        fragmentTransaction.commit();
    }

    @Override
    public FragmentManager getSupportManager() {
        return getSupportFragmentManager();
    }


}
