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

public class GameActivity extends AppCompatActivity {
    private GameController gameController;
    // Crear una instancia de SharedPreferencesManager
    SharedPreferencesManager sharedPreferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        BoardViewModel boardViewModel = new BoardViewModel(gameController);
        sharedPreferencesManager = new SharedPreferencesManager(this);

        List<Card> selectedCards;
        selectedCards = sharedPreferencesManager.getSelectedCards();
        DeckViewModel humanDeckViewModel = new DeckViewModel(selectedCards);
        DeckViewModel computerDeckViewModel = new DeckViewModel();

        gameController = new GameController(this, boardViewModel, humanDeckViewModel, computerDeckViewModel,this,sharedPreferencesManager);

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