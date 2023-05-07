package cat.udl.hyperion.appmobils.kingdomcollector.game.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import cat.udl.hyperion.appmobils.kingdomcollector.game.fragments.BoardFragment;
import cat.udl.hyperion.appmobils.kingdomcollector.game.fragments.DeckFragment;
import cat.udl.hyperion.appmobils.kingdomcollector.game.fragments.PlayerFragment;
import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.HumanPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.IAPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.BoardViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.DeckViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.PlayerViewModel;

public class GameActivity extends AppCompatActivity {
    private GameController gameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //TEST
        BoardViewModel boardViewModel = new BoardViewModel(gameController);
        DeckViewModel humanDeckViewModel = new DeckViewModel();
        DeckViewModel computerDeckViewModel = new DeckViewModel();

        gameController = new GameController(boardViewModel, humanDeckViewModel, computerDeckViewModel);

        PlayerFragment playerFragment = PlayerFragment.newInstance((HumanPlayer) gameController.getHumanPlayer(), (IAPlayer)gameController.getComputerPlayer());
        DeckFragment deckFragment = DeckFragment.newInstance(humanDeckViewModel);
        BoardFragment boardFragment = BoardFragment.newInstance(gameController);
        // Comienza una transacción de fragmentos
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Agrega los fragmentos a la actividad utilizando contenedores en el archivo de diseño activity_game.xml
        fragmentTransaction.add(R.id.playerFragment, playerFragment);
        fragmentTransaction.add(R.id.deckFragment, deckFragment);
        fragmentTransaction.add(R.id.boardFragment, boardFragment);

        // Realiza la transacción
        fragmentTransaction.commit();
    }
}
