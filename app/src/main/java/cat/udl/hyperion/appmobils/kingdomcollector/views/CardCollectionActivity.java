package cat.udl.hyperion.appmobils.kingdomcollector.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.CardCollection;
import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.CardViewModel;

public class CardCollectionActivity extends AppCompatActivity{
    private CardAdapter adapter;
    private CardViewModel cardViewModel;
    private RecyclerView selectedCardsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_collection);

        //cardViewModel = ViewModelProviders.of(this).get(CardViewModel.class);



        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CardAdapter(null, null); // Revisar.
        recyclerView.setAdapter(adapter);

        CardViewModel viewModel = new ViewModelProvider(this).get(CardViewModel.class);
        viewModel.getCardsLiveData().observe(this, cards -> adapter.setCards(cards));
    }

    /*@Override
    public void onCardSelected(CardCollection cardCollection) {
        cardViewModel.selectCard(cardCollection);

    }*/

    /*@Override
    public void onCardDeselected(CardCollection cardCollection) {
        cardViewModel.deselectCard(cardCollection);

    }*/

    private static class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {
        private List<CardCollection> cardCollections;
        private List<CardCollection> cards;
        private OnCardClickListener onCardClickListener;

        public CardAdapter(List<CardCollection> cards, OnCardClickListener onCardClickListener) {
            this.cards = cards;
            this.onCardClickListener = onCardClickListener;
        }


        @NonNull
        @Override
        public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
            return new CardViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
            CardCollection cardCollection = cardCollections.get(position);
            holder.nameTextView.setText(cardCollection.getName());
            holder.imageView.setImageResource(cardCollection.getImageResource());
            holder.descriptionTextView.setText(cardCollection.getDescription());
        }

        @Override
        public int getItemCount() {
            return cardCollections != null ? cardCollections.size() : 0;
        }

        @SuppressLint("NotifyDataSetChanged")
        public void setCards(List<CardCollection> cardCollections) {
            this.cardCollections = cardCollections;
            notifyDataSetChanged();
        }
    }

    private static class CardViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final ImageView imageView;
        private final TextView descriptionTextView;
        private CardCollection cardCollection;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            imageView = itemView.findViewById(R.id.image_view);
            descriptionTextView = itemView.findViewById(R.id.description_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    OnCardClickListener onCardClickListener = null;
                    if (cardCollection.isSelected()) {
                        cardCollection.setSelected(false);
                        assert false;
                        onCardClickListener.onCardDeselected(cardCollection);
                    } else {
                        cardCollection.setSelected(true);
                        assert false;
                        onCardClickListener.onCardSelected(cardCollection);
                    }
                }
            });
        }

        public void bind(CardCollection cardCollection) {
            this.cardCollection = cardCollection;
            imageView.setImageResource(cardCollection.getImageResource());
            nameTextView.setText(cardCollection.getName());
            descriptionTextView.setText(cardCollection.getDescription());

            if (cardCollection.isSelected()) {
                itemView.setBackgroundResource(R.drawable.reverse_card);
            } else {
                itemView.setBackgroundResource(R.drawable.reverse_card);
            }
        }
    }
}
