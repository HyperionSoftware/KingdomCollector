package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.adapter.CardAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.adapter.CardSelectedAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.other.MainActivity;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.CardSelectedViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.CardViewModel;



public class CardCollectionActivity extends AppCompatActivity {

    private CardAdapter cardAdapter;
    private CardSelectedAdapter selectedCardAdapter;

    private Button confirmButton;

    private SharedPreferences mSharedPreferences;
    private static final String SHARED_PREFS_FILE = "selected_cards_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_collection);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        cardAdapter = new CardAdapter(null);
        recyclerView.setAdapter(cardAdapter);

        RecyclerView selectedRecyclerView = findViewById(R.id.selected_recycler_view);
        selectedRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        selectedCardAdapter = new CardSelectedAdapter(null);
        selectedRecyclerView.setAdapter(selectedCardAdapter);

        confirmButton = findViewById(R.id.btn_confirm_selection);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmSelection();
            }
        });

        CardViewModel viewModel = new ViewModelProvider(this).get(CardViewModel.class);
        viewModel.getCardsLiveData().observe(this, cards -> cardAdapter.setCards(cards));

        CardSelectedViewModel selectedViewModel = new ViewModelProvider(this).get(CardSelectedViewModel.class);
        selectedViewModel.getSelectedCardsLiveData().observe(this, selectedCards -> selectedCardAdapter.setSelectedCards(selectedCards));

        // Inicializar SharedPreferences
        mSharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, MODE_PRIVATE);

        // Cargar las cartas seleccionadas guardadas anteriormente
        Set<String> selectedCardsSet = mSharedPreferences.getStringSet("selected_cards", new HashSet<String>());
        List<Card> selectedCards = new ArrayList<>();
        for (String cardJson : selectedCardsSet) {
            selectedCards.add(new Gson().fromJson(cardJson, Card.class));
        }
        selectedCardAdapter.setSelectedCards(selectedCards);
    }

    private void confirmSelection() {
        List<Card> selectedCards = selectedCardAdapter.getSelectedCards();
        if (selectedCards.size() != 5) {
            Toast.makeText(this, "Debe seleccionar exactamente 5 cartas", Toast.LENGTH_SHORT).show();
            return;
        }
        CardSelectedViewModel viewModel = new ViewModelProvider(this).get(CardSelectedViewModel.class);
        viewModel.setSelectedCardsLiveData(selectedCards);

        // Convertir el conjunto de cartas seleccionadas a un conjunto de strings
        Set<String> selectedCardsSet = new HashSet<>();
        for (Card card : viewModel.getSelectedCards()) {
            selectedCardsSet.add(new Gson().toJson(card));
        }

        // Guardar el conjunto de strings en las SharedPreferences
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putStringSet("selected_cards", selectedCardsSet);
        editor.apply();


        startActivity(new Intent(this, MainActivity.class));
    }
}




