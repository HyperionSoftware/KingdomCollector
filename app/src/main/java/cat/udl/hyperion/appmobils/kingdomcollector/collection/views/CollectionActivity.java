package cat.udl.hyperion.appmobils.kingdomcollector.collection.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.os.Parcelable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.adapter.CardAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.db.AppDatabase;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.views.GameActivity;

public class CollectionActivity extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView presidenteRecyclerView, delanteroRecyclerView, medioRecyclerView, defensaRecyclerView, selectedCardsRecyclerView;

    private List<Card> selectedCardsList = new ArrayList<>();
    private CardAdapter selectedCardsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "general-cards-local").build();

        presidenteRecyclerView = findViewById(R.id.presidente_recycler_view);
        delanteroRecyclerView = findViewById(R.id.delantero_recycler_view);
        medioRecyclerView = findViewById(R.id.medio_recycler_view);
        defensaRecyclerView = findViewById(R.id.defensa_recycler_view);

        selectedCardsRecyclerView = findViewById(R.id.your_team_recycler_view);
        LinearLayoutManager selectedLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        selectedCardsRecyclerView.setLayoutManager(selectedLayoutManager);
        selectedCardsAdapter = new CardAdapter(selectedCardsList, selectedCardsList);
        selectedCardsRecyclerView.setAdapter(selectedCardsAdapter);

        // Botón confirmar equipo:
        findViewById(R.id.confirm_button_2).setOnClickListener(v-> sendCardsToGame());


        getCardsFromDb();
    }

    private void getCardsFromDb() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<Card> allCards;
                try {
                    allCards = db.cardDao().getAllCards();
                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(CollectionActivity.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }

                List<Card> presidenteCards = new ArrayList<>();
                List<Card> delanteroCards = new ArrayList<>();
                List<Card> medioCards = new ArrayList<>();
                List<Card> defensaCards = new ArrayList<>();

                for (Card card : allCards) {
                    if (card.getType().equals(getString(R.string.card_type_president))) {
                        presidenteCards.add(card);
                    } else if (card.getType().equals(getString(R.string.card_type_forward))) {
                        delanteroCards.add(card);
                    } else if (card.getType().equals(getString(R.string.card_type_midfielder))) {
                        medioCards.add(card);
                    } else if (card.getType().equals(getString(R.string.card_type_defender))) {
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
        CardAdapter cardAdapter = new CardAdapter(cards, selectedCardsList);  // Pasamos la lista de cartas seleccionadas como parámetro
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
        startActivity(intent);
    }
}

