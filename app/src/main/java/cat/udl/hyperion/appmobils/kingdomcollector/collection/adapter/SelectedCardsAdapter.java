package cat.udl.hyperion.appmobils.kingdomcollector.collection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;

public class SelectedCardsAdapter extends RecyclerView.Adapter<SelectedCardsAdapter.CardViewHolder> {
    private List<Card> selectedCards;

    public SelectedCardsAdapter(List<Card> selectedCards) {
        this.selectedCards = selectedCards;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_selected_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card card = selectedCards.get(position);
        holder.bind(card);
    }

    @Override
    public int getItemCount() {
        return selectedCards.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        private ImageView cardImage;

        public CardViewHolder(View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.selected_card_image);
        }

        public void bind(Card card) {
            cardImage.setImageResource(card.getImageResource());
        }
    }
}
