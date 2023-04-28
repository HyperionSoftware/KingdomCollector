package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.adapter.CardAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.adapter.CardSelectedAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.CardSelectedViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.CardViewModel;



public class CardCollectionActivity extends AppCompatActivity {

    private CardAdapter cardAdapter;
    private CardSelectedAdapter selectedCardAdapter;

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

        CardViewModel viewModel = new ViewModelProvider(this).get(CardViewModel.class);
        viewModel.getCardsLiveData().observe(this, cards -> cardAdapter.setCards(cards));

        CardSelectedViewModel selectedViewModel = new ViewModelProvider(this).get(CardSelectedViewModel.class);
        selectedViewModel.getSelectedCardsLiveData().observe(this, selectedCards -> selectedCardAdapter.setSelectedCards(selectedCards));
    }
}




