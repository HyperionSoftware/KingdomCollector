package cat.udl.hyperion.appmobils.kingdomcollector.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import cat.udl.hyperion.appmobils.kingdomcollector.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GameView gameFragment = new GameView();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.game_fragment_container, gameFragment);
        transaction.commit();
    }
}
