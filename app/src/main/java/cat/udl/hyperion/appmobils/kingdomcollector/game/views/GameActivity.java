package cat.udl.hyperion.appmobils.kingdomcollector.game.views;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        BoardViewModel boardViewModel = new BoardViewModel(gameController);
        List<Card> selectedCards;
        // Recuperar las cartas seleccionadas de las SharedPreferences
        String selectedCardsJson = getSharedPreferences("APP_PREFS", MODE_PRIVATE)
                .getString("selectedCards", null);
        if (selectedCardsJson != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Card>>() {}.getType();
            selectedCards = new ArrayList<>();
            List<Card> parsedCards = gson.fromJson(selectedCardsJson, type);
            if (parsedCards != null) {
                for (Card card : parsedCards) {
                    selectedCards.add(card);
                }
            } else {
                Log.e("GameActivity", "Error parsing selectedCards JSON. Cannot proceed.");
            }
        } else {
            selectedCards = new ArrayList<>();
            Log.e("GameActivity", "selectedCards is null. Cannot proceed.");
        }

        DeckViewModel humanDeckViewModel = new DeckViewModel(selectedCards);
        DeckViewModel computerDeckViewModel = new DeckViewModel();

        gameController = new GameController(this, boardViewModel, humanDeckViewModel, computerDeckViewModel,this);

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
