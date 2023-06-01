package cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.views;

import static android.content.ContentValues.TAG;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.adapters.MultiplayerMatchesAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.helpers.GlobalInfo;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.models.GameControllerOnline;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.models.MultiplayerMatch;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.provider.MultiplayerMatchesProvider;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.viewmodel.GameViewModel;

public class MultiplayerGameSelector extends AppCompatActivity {


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

    public void joinGamecarlos(String roomId, String userId) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference gameRef = rootRef.child("games").child(roomId);

        gameRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                MultiplayerMatch game = currentData.getValue(MultiplayerMatch.class);
                if (game == null) {
                    return Transaction.success(currentData);
                }

                if (game.getStatus() == 1 && game.getUserJoiner() == null) {
                    game.setUserJoiner(userId);
                    game.setStatus(2);
                    currentData.setValue(game);
                    return Transaction.success(currentData);
                } else {
                    return Transaction.success(currentData); // Puede que prefieras retornar Transaction.abort() aquí
                }
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                if (committed) {
                    Log.d(TAG, "Joined game successfully!");
                } else {
                    Log.w(TAG, "Join game failed.", databaseError.toException());
                }
            }
        });
    }
}
