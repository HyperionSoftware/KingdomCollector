package cat.udl.hyperion.appmobils.kingdomcollector.collection.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.adapter.CardAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.admin.AddingCardsManager;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.admin.SharedPreferencesManager;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.db.AppDatabase;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.views.GameActivity;



public class CollectionActivity extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView presidenteRecyclerView, delanteroRecyclerView, medioRecyclerView, defensaRecyclerView, selectedCardsRecyclerView;

    private List<Card> selectedCardsList = new ArrayList<>();
    private CardAdapter selectedCardsAdapter;

    //Para saber que cartas tiene el usuario en su colección:
    private FirebaseFirestore firestore;
    private FirebaseAuth firebaseAuth;
    private List<String> userCardIds;
    SharedPreferencesManager sharedPreferencesManager;
    private ProgressBar loadingIndicator;

    private AddingCardsManager addingCardsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        loadingIndicator = findViewById(R.id.loading_indicator);
        addingCardsManager = new AddingCardsManager(this);
        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        userCardIds = new ArrayList<>();
        sharedPreferencesManager = new SharedPreferencesManager(this);
        selectedCardsList = sharedPreferencesManager.getSelectedCards();

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "general-cards-local").build();
        //getUserCardIds();

        presidenteRecyclerView = findViewById(R.id.presidente_recycler_view);
        delanteroRecyclerView = findViewById(R.id.delantero_recycler_view);
        medioRecyclerView = findViewById(R.id.medio_recycler_view);
        defensaRecyclerView = findViewById(R.id.defensa_recycler_view);

        selectedCardsRecyclerView = findViewById(R.id.your_team_recycler_view);
        LinearLayoutManager selectedLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        selectedCardsRecyclerView.setLayoutManager(selectedLayoutManager);
        loadingIndicator.setVisibility(View.VISIBLE);

        getUserCardIds().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                loadingIndicator.setVisibility(View.GONE);

                selectedCardsAdapter = new CardAdapter(selectedCardsList, selectedCardsList, userCardIds);
                selectedCardsRecyclerView.setAdapter(selectedCardsAdapter);
                getCardsFromDb();
            }
        });

        // Botón confirmar equipo:
        findViewById(R.id.confirm_button_2).setOnClickListener(v-> sendCardsToGame());


        getCardsFromDb();
    }

    private Task<Void> getUserCardIds() {
        // Crear un objeto TaskCompletionSource
        TaskCompletionSource<Void> tcs = new TaskCompletionSource<>();

        firestore.collection("users")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("user_cards")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            userCardIds.add(document.getId());
                        }
                    } else {
                        Log.w("getUserCardIds", "Error getting documents.", task.getException());
                    }

                    // Indicar que la tarea se ha completado
                    tcs.setResult(null);
                });

        // Devolver la tarea
        return tcs.getTask();
    }



    private void getCardsFromDb() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<Card> allCards = db.cardDao().getAllCards();
                List<Card> presidenteCards = new ArrayList<>();
                List<Card> delanteroCards = new ArrayList<>();
                List<Card> medioCards = new ArrayList<>();
                List<Card> defensaCards = new ArrayList<>();

                for (Card card : allCards) {
                    if (card.getType().equals("Presidente")) {
                        presidenteCards.add(card);
                    } else if (card.getType().equals("Delantero")) {
                        delanteroCards.add(card);
                    } else if (card.getType().equals("Medio")) {
                        medioCards.add(card);
                    } else if (card.getType().equals("Defensa")) {
                        defensaCards.add(card);
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setupRecyclerView(presidenteRecyclerView, presidenteCards);
                        setupRecyclerView(delanteroRecyclerView, delanteroCards);
                        setupRecyclerView(medioRecyclerView, medioCards);
                        setupRecyclerView(defensaRecyclerView, defensaCards);
                    }
                });
            }
        });
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<Card> cards) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        CardAdapter cardAdapter = new CardAdapter(cards, selectedCardsList, userCardIds);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.setOnClickListener(new CardAdapter.OnClickListener() {
            @Override
            public void onClick() {
                selectedCardsAdapter.notifyDataSetChanged();
                Log.d("SelectedCards", "Selected cards: " + getSelectedCardNames(selectedCardsList));
            }
        });
    }

    private String getSelectedCardNames(List<Card> selectedCardsList) {
        String s = " ";
        for(int i = 0; i < selectedCardsList.size(); i++){
            s += selectedCardsList.get(i).getName() + " ";
        }
        return s;
    }

    private void sendCardsToGame(){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putParcelableArrayListExtra("selectedCards", (ArrayList<? extends Parcelable>) selectedCardsList);
        sharedPreferencesManager.storeSelectedCards(selectedCardsList);
        //startActivity(intent);
        finish();
    }
}
