package cat.udl.hyperion.appmobils.kingdomcollector.other;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.admin.SharedPreferencesManager;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.views.CollectionActivity;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.views.GameActivity;

public class PlayModeActivity extends AppCompatActivity {

    SharedPreferencesManager sharedPreferencesManager;
    private List<Card> selectedCardsList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_mode);

        // Botones de la pantalla de selección de modo de juego

        // Botón para regresar a Home.
        findViewById(R.id.back_to_main_menu).setOnClickListener(view-> goToHome());

        // Botón para jugar en modo online.
        findViewById(R.id.btn_play_online).setOnClickListener(view-> playOnline());

        // Botón para jugar en modo local.
        findViewById(R.id.btn_play_computer).setOnClickListener(view-> playLocal());

        // Botón para seleccionar cartas.
        findViewById(R.id.btn_select_your_cards).setOnClickListener(view-> selectYourCards());
    }

    public void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }

    public void goToHome(){startNewActivity(MainActivity.class);}

    public void playOnline(){
        selectedCardsList = sharedPreferencesManager.getSelectedCards();
        sharedPreferencesManager.storeSelectedCards(selectedCardsList);

        if(sharedPreferencesManager.getSelectedCards().size() == 5){
            startNewActivity(GameActivity.class);
        }else {
            Toast.makeText(this, "Debes tener cartas seleccionadas", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void playLocal(){startNewActivity(GameActivity.class);}

    public void selectYourCards(){
        Intent intent = new Intent(this, CollectionActivity.class);
        startActivity(intent);
    }
}