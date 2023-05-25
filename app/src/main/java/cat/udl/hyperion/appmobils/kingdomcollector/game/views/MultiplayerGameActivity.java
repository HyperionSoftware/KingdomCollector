package cat.udl.hyperion.appmobils.kingdomcollector.game.views;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Game;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.GameViewModel;

public class MultiplayerGameActivity extends AppCompatActivity {

    private GameViewModel game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /*

        // collecting data from Intent - Bundle from previous Activity
        Bundle extra = getIntent().getExtras();
        //protected if no data received in Extra
        if (extra != null) {
            String nomJugador1 = extra.getString("nomDelJugador1");
            if (nomJugador1 != null) {
                Toast.makeText(this, nomJugador1, Toast.LENGTH_LONG).show();
            }
        }

        game = new ViewModelProvider(this).get(GameViewModel.class);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        binding.setGameViewModel(game);
        binding.setLifecycleOwner(this);

//        creating Multiplayer game if sent
        if (extra != null){
            int multiplayerType = extra.getInt(Game.MULTIPLAYER_KEY);
            if (multiplayerType == Game.MULTIPLAYER_TYPE_CREATE) game.multiplayerCreate();
            if (multiplayerType == Game.MULTIPLAYER_TYPE_CONNECT) game.multiplayerConnect(extra.getString(Game.MULTIPLAYER_GAME_KEY));
        }

        */

    }
}
