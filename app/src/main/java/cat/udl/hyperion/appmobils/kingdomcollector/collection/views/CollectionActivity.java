package cat.udl.hyperion.appmobils.kingdomcollector.collection.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;
import java.util.concurrent.Executors;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.adapter.CardAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.db.AppDatabase;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;

public class CollectionActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "general-cards-local").build();

        getCardsFromDb();
    }

    private void getCardsFromDb() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<Card> cards = db.cardDao().getAllCards();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setupRecyclerView(cards);
                    }
                });
            }
        });
    }

    private void setupRecyclerView(List<Card> cards) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardAdapter cardAdapter = new CardAdapter(cards);
        recyclerView.setAdapter(cardAdapter);
    }
}
