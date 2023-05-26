package cat.udl.hyperion.appmobils.kingdomcollector.game.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.game.adapters.MultiplayerMatchesAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.game.helpers.GlobalInfo;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.GameControllerOnline;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.MultiplayerMatch;
import cat.udl.hyperion.appmobils.kingdomcollector.game.provider.MultiplayerMatchesProvider;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.GameViewModel;

public class MultiplayerGameSelector extends AppCompatActivity {

    GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayer_game_selector);


        Button btnCreateMatch = findViewById(R.id.btn_create_match);
        btnCreateMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewMatch();
            }
        });

        initRecyclerViews();
    }

    private void initRecyclerViews() {

        MultiplayerMatchesProvider provider = new MultiplayerMatchesProvider();
        MultiplayerMatchesAdapter adapter =  new MultiplayerMatchesAdapter(provider.getLaMevaLlista());
        provider.setAdapter(adapter);
        provider.getFromFirebase();

        RecyclerView rv = findViewById(R.id.rv_matches);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }

    private void createNewMatch() {

        DatabaseReference myFirebaseDBGames = GlobalInfo.getIntance().getFirebaseGames();

        // Genera un ID único para la partida
        String matchId = myFirebaseDBGames.push().getKey();

        // Crea un nuevo objeto MultiplayerMatch con el nombre de usuario actual y el estado pendiente
        MultiplayerMatch newMatch = new MultiplayerMatch("Carlos"); // replace with actual user name
        newMatch.setStatus(GameControllerOnline.MULTIPLAYER_STATUS_PENDING);

        // Guarda la partida en Firebase
        myFirebaseDBGames.child(matchId).setValue(newMatch)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Refresh RecyclerView
                            Log.d("Provider", "PARTIDAAAAAAAA");
                            initRecyclerViews();
                        } else {
                            // Handle failure
                            Log.d("Provider", "Failed to create new match");
                        }
                    }
                });
    }
}
