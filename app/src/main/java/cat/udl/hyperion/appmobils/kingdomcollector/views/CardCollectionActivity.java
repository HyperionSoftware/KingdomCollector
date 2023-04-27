package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.adapter.CardAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.CardViewModel;



public class CardCollectionActivity extends AppCompatActivity {

    private CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_collection);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        cardAdapter = new CardAdapter(null);
        recyclerView.setAdapter(cardAdapter);


        CardViewModel viewModel = new ViewModelProvider(this).get(CardViewModel.class);
        viewModel.getCardsLiveData().observe(this, cards -> cardAdapter.setCards(cards));

    }
}



