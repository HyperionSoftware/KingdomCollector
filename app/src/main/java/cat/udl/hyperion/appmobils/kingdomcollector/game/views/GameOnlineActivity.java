package cat.udl.hyperion.appmobils.kingdomcollector.game.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.admin.SharedPreferencesManager;
import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.BoardViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.DeckViewModel;

public class GameOnlineActivity extends AppCompatActivity implements GameActivityInterface {
    // Firebase
    private FirebaseDatabase database;
    private DatabaseReference gameRef;

    // GameController
    private GameController gameController;

    // SharedPreferencesManager
    private SharedPreferencesManager sharedPreferencesManager;

    // Botones
    private Button createGameButton;
    private Button joinGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_online);

        // Inicia FirebaseDatabase
        database = FirebaseDatabase.getInstance();

        sharedPreferencesManager = new SharedPreferencesManager(this);

        // Configura los botones
        createGameButton = (Button) findViewById(R.id.createGameButton);
        joinGameButton = (Button) findViewById(R.id.joinGameButton);

        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createGame();
            }
        });

        joinGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Necesitarás proporcionar una forma para que los usuarios ingresen o seleccionen un gameId.
                // Por ahora, solo usaremos un gameId de ejemplo.
                String gameId = "exampleGameId";
                joinGame(gameId);
            }
        });
    }

    private void createGame() {
        // Crea un nuevo GameController para esta partida
        // Recupera las cartas seleccionadas
        List<Card> selectedCards = sharedPreferencesManager.getSelectedCards();
        gameController = new GameController(this, new BoardViewModel(gameController), new DeckViewModel(selectedCards), new DeckViewModel(), this, sharedPreferencesManager);

        // Genera un identificador único para esta partida
        String gameId = database.getReference("games").push().getKey();

        // Añade la partida a la base de datos
        gameRef = database.getReference("games/" + gameId);
        gameRef.setValue(gameController);

        // Actualiza la interfaz de usuario para reflejar el estado de la partida.
        updateUI();
    }

    private void joinGame(String gameId) {
        gameRef = database.getReference("games/" + gameId);

        // Descarga el estado del juego
        gameRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gameController = snapshot.getValue(GameController.class);
                updateUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar el error
            }
        });
    }

    @Override
    public void updateUI() {
        // Comprueba si el controlador del juego está inicializado.
        // Si es así, eso significa que el usuario ha creado o se ha unido a una partida.
        if (gameController != null) {
            // Oculta los botones para crear o unirse a un juego
            createGameButton.setVisibility(View.GONE);
            joinGameButton.setVisibility(View.GONE);

            // Muestra el estado del juego
            // Este es un lugar para añadir código que actualice la interfaz de usuario
            // Para este ejemplo, vamos a suponer que tienes una TextView llamada gameStatus.
           // TextView gameStatus = (TextView) findViewById(R.id.gameStatus);
          //  gameStatus.setText("¡Estás en el juego!");

            // Aquí puedes añadir más actualizaciones de UI, por ejemplo,
            // mostrar las cartas en la mano del jugador, actualizar el tablero, etc.
            // Todo dependerá de cómo quieras diseñar la interfaz de usuario de tu juego.
        } else {
            // Si el controlador del juego no está inicializado,
            // eso significa que el usuario todavía no ha creado ni se ha unido a ninguna partida.
            // Por lo tanto, muestra los botones para crear o unirse a un juego.
            createGameButton.setVisibility(View.VISIBLE);
            joinGameButton.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public FragmentManager getSupportManager() {
        return getSupportFragmentManager();
    }

}
