package cat.udl.hyperion.appmobils.kingdomcollector.collection.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.adapter.CardCollectionAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.adapter.CardSelectedAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.viewmodels.CardSelectedViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.viewmodels.CardViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.views.GameActivity;


public class CardCollectionActivity extends AppCompatActivity {

    private CardCollectionAdapter cardCollectionAdapter;
    private CardSelectedAdapter selectedCardAdapter;

    private Button confirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_collection);

        confirmButton = findViewById(R.id.btn_confirm_selection);
        confirmButton.setOnClickListener(v -> confirmSelection());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        cardCollectionAdapter = new CardCollectionAdapter(null);
        recyclerView.setAdapter(cardCollectionAdapter);


        RecyclerView selectedRecyclerView = findViewById(R.id.selected_recycler_view);
        selectedRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        selectedCardAdapter = new CardSelectedAdapter(null);
        selectedRecyclerView.setAdapter(selectedCardAdapter);



        CardViewModel viewModel = new ViewModelProvider(this).get(CardViewModel.class);
        viewModel.getCardsLiveData().observe(this, cards -> cardCollectionAdapter.setCards(cards));

        CardSelectedViewModel selectedViewModel = new ViewModelProvider(this).get(CardSelectedViewModel.class);
        selectedViewModel.getSelectedCardsLiveData().observe(this, selectedCards -> selectedCardAdapter.setSelectedCards(selectedCards));

        // Cargar las cartas seleccionadas y actualizar el CardSelectedViewModel
        List<Card> selectedCards = loadSelectedCards();
        CardSelectedViewModel cardSelectedViewModel = new ViewModelProvider(this).get(CardSelectedViewModel.class);
        cardSelectedViewModel.setSelectedCardsLiveData(selectedCards);

    }

    @Override
    protected void onPause() {
        super.onPause();

        CardSelectedViewModel cardSelectedViewModel = new ViewModelProvider(this).get(CardSelectedViewModel.class);
        List<Card> selectedCards = cardSelectedViewModel.getSelectedCardsLiveData().getValue();
        saveSelectedCards(selectedCards);
    }


    @Override
    protected void onStop() {
        super.onStop();
        cardCollectionAdapter = null;
        selectedCardAdapter = null;
        confirmButton = null;
    }

    private void confirmSelection() {
        List<Card> selectedCards = selectedCardAdapter.getSelectedCards();
        if (selectedCards.size() != 5) {
            //TODO: DA1. Funcionar amb valors de strings.
            Toast.makeText(this, "Debe seleccionar exactamente 5 cartas", Toast.LENGTH_SHORT).show();
            return;
        }

        // Inicia el GameActivity y pasa las cartas seleccionadas como un Extra
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void saveSelectedCards(List<Card> selectedCards) {
        SharedPreferences sharedPreferences = getSharedPreferences("selected_cards", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(selectedCards);
        editor.putString("cards", json);
        editor.apply();
    }

    private List<Card> loadSelectedCards() {
        SharedPreferences sharedPreferences = getSharedPreferences("selected_cards", MODE_PRIVATE);
        String json = sharedPreferences.getString("cards", null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Card>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }
}




