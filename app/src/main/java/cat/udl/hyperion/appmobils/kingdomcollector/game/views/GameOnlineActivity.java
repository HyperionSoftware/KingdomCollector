package cat.udl.hyperion.appmobils.kingdomcollector.game.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.admin.SharedPreferencesManager;
import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;
import cat.udl.hyperion.appmobils.kingdomcollector.game.fragments.BoardFragment;
import cat.udl.hyperion.appmobils.kingdomcollector.game.fragments.DeckFragment;
import cat.udl.hyperion.appmobils.kingdomcollector.game.fragments.PlayerFragment;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.BoardData;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.HumanPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.IAPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.BoardViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.DeckViewModel;

public class GameOnlineActivity extends AppCompatActivity implements GameActivityInterface {
    // Firebase
    private FirebaseDatabase database;
    private DatabaseReference boardRef;

    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference gameDataRef = database.getReference("winner_count");

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
                String gameId = "exampleGameId";
                joinGame(gameId);
            }
        });
    }

    private void createGame() {
        // Crea un nuevo GameController para esta partida
        List<Card> selectedCards = sharedPreferencesManager.getSelectedCards();
        Log.d("GameOnlineActivity", "selectedCards: " + selectedCards);
        gameController = new GameController(this, new BoardViewModel(gameController), new DeckViewModel(selectedCards), new DeckViewModel(), this, sharedPreferencesManager);
        Log.d("GameOnlineActivity", "GameController created");

        // Genera un identificador único para esta partida
        String gameId = database.getReference("games/").push().getKey();
        Log.d("GameOnlineActivity", "GameId: " + gameId);

        // Añade el estado del tablero a la base de datos
        // Añade el estado del tablero a la base de datos
        boardRef = database.getReference("games/" + gameId);
        BoardData boardData = new BoardData(gameController.getBoard().getCards(), gameController.getBoard().getCells()); // create BoardData
        Log.d("GameOnlineActivity", "boardData created: " + boardData);

        boardRef.setValue(boardData, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                Log.d("GameOnlineActivity", "Data could not be saved" + databaseError.getMessage());
            } else {
                Log.d("GameOnlineActivity", "Data saved successfully");
            }
        });

        // Actualiza la interfaz de usuario para reflejar el estado de la partida.
        updateUI();
    }

    private void joinGame(String gameId) {
        boardRef = database.getReference("games/" + gameId);

        // Descarga el estado del tablero
        boardRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BoardData boardData = snapshot.getValue(BoardData.class);
                if (boardData != null) {
                    Log.d("GameOnlineActivity", "Data read successfully: " + boardData);
                    BoardViewModel boardViewModel = boardData.toBoardViewModel(gameController);

                    // Comprobar si los datos se guardaron correctamente
                    // Esto implica comparar los datos leídos con los datos originales.
                    // Dado que esto es dependiente de la aplicación, te dejo un ejemplo genérico:
                    boolean dataSavedCorrectly = true;
                    for (int i = 0; i < boardData.getCards().size(); i++) {
                        if (!boardData.getCards().get(i).equals(gameController.getBoard().getCards().get(i))) {
                            dataSavedCorrectly = false;
                            break;
                        }
                    }

                    if (dataSavedCorrectly) {
                        Log.d("GameOnlineActivity", "Data saved correctly");
                    } else {
                        Log.d("GameOnlineActivity", "Data not saved correctly");
                    }

                } else {
                    Log.d("GameOnlineActivity", "No data found in database");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Log the error message for debugging purposes
                Log.d("GameOnlineActivity", "DatabaseError: " + error.getMessage());
            }
        });

    }

    @Override
    public void updateUI() {
        if (gameController != null) {
            createGameButton.setVisibility(View.GONE);
            joinGameButton.setVisibility(View.GONE);

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
        } else {
            createGameButton.setVisibility(View.VISIBLE);
            joinGameButton.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public FragmentManager getSupportManager() {
        return getSupportFragmentManager();
    }
}
