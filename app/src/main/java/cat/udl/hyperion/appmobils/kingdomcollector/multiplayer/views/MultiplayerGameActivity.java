package cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.viewmodel.GameViewModel;

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
            int multiplayerType = extra.getInt(GameControllerOnline.MULTIPLAYER_KEY);
            if (multiplayerType == GameControllerOnline.MULTIPLAYER_TYPE_CREATE) game.multiplayerCreate();
            if (multiplayerType == GameControllerOnline.MULTIPLAYER_TYPE_CONNECT) game.multiplayerConnect(extra.getString(GameControllerOnline.MULTIPLAYER_GAME_KEY));
        }

        */

    }
}
