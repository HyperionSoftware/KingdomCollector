package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.adapter.CardSelectedAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.CardSelectedViewModel;

public class GameActivity extends AppCompatActivity {

    private CardSelectedAdapter selectedCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        RecyclerView selectedRecyclerView = findViewById(R.id.selected_recycler_view);
        selectedRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        selectedCardAdapter = new CardSelectedAdapter(null);
        selectedRecyclerView.setAdapter(selectedCardAdapter);

        CardSelectedViewModel selectedViewModel = new ViewModelProvider(this).get(CardSelectedViewModel.class);
        selectedViewModel.getSelectedCardsLiveData().observe(this, selectedCards -> selectedCardAdapter.setSelectedCards(selectedCards));
    }
}
