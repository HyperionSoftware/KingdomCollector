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

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.CardCollection;
import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.CardViewModel;





public class CardCollectionActivity extends AppCompatActivity {
    private CardAdapter adapter;
    private CardViewModel cardViewModel;
    private RecyclerView selectedCardsRecyclerView;
    private static FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_collection);

        // Inicializar FirebaseStorage
        storage = FirebaseStorage.getInstance();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        adapter = new CardAdapter(null, null);
        recyclerView.setAdapter(adapter);



        CardViewModel viewModel = new ViewModelProvider(this).get(CardViewModel.class);
        viewModel.getCardsLiveData().observe(this, cards -> adapter.setCards(cards));
    }

    private static class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {
        private List<CardCollection> cardCollections;
        private List<CardCollection> cards;
        private final OnCardClickListener onCardClickListener;

        public CardAdapter(List<CardCollection> cardCollections, OnCardClickListener onCardClickListener) {
            this.cardCollections = cardCollections;
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
            StorageReference storageRef = storage.getReference();
            StorageReference islandRef = storageRef.child("images/" + cardCollections.get(position).getName() + ".png");
            holder.nameTextView.setText(cardCollections.get(position).getName());
            holder.descriptionTextView.setText(cardCollections.get(position).getDescription());
            holder.cardCollection = cardCollections.get(position);
            holder.onCardClickListener = onCardClickListener;
            islandRef.getDownloadUrl().addOnSuccessListener(uri -> {
                Glide.with(holder.imageView.getContext())
                        .load(uri)
                        .into(holder.imageView);
            });


        }

        @Override
        public int getItemCount() {
            return cardCollections != null ? cardCollections.size() : 0;
        }

        public void setCards(List<CardCollection> cardCollections) {
            this.cardCollections = cardCollections;
            notifyDataSetChanged();
        }
    }

    private static class CardViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final ImageView imageView;
        private final TextView descriptionTextView;
        public OnCardClickListener onCardClickListener;
        private CardCollection cardCollection;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            imageView = itemView.findViewById(R.id.image_view);
            descriptionTextView = itemView.findViewById(R.id.description_text_view);

        }
    }
}

