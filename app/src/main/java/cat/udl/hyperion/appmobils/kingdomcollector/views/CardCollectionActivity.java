package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.CardViewModel;





public class CardCollectionActivity extends AppCompatActivity {
    private CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_collection);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        adapter = new CardAdapter(null, null);
        recyclerView.setAdapter(adapter);



        CardViewModel viewModel = new ViewModelProvider(this).get(CardViewModel.class);
        viewModel.getCardsLiveData().observe(this, cards -> adapter.setCards(cards));
    }

    private static class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {
        private List<Card> cards;


        public CardAdapter(List<Card> cards, Object o) {
            this.cards = cards;

        }

        @NonNull
        @Override
        public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
            return new CardViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
            Card card = cards.get(position);
            holder.nameTextView.setText(card.getName());
            holder.imageView.setImageResource(card.getImage());
            holder.typeTextView.setText(card.getType());
        }

        @Override
        public int getItemCount() {
            return cards != null ? cards.size() : 0;
        }

        public void setCards(List<Card> cards) {
            this.cards = cards;
            notifyDataSetChanged();
        }
    }

    private static class CardViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final ImageView imageView;
        private final TextView typeTextView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            imageView = itemView.findViewById(R.id.image_view);
            typeTextView = itemView.findViewById(R.id.type_text_view);

        }
    }
}

