package cat.udl.hyperion.appmobils.kingdomcollector.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.models.Card;

public class CardSelectedAdapter extends RecyclerView.Adapter<CardSelectedViewHolder> {
    private List<Card> selectedCards;

    public CardSelectedAdapter(List<Card> selectedCards) {
        this.selectedCards = selectedCards;
    }

    @NonNull
    @Override
    public CardSelectedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_selected_item, parent, false);
        return new CardSelectedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardSelectedViewHolder holder, int position) {
        Card card = selectedCards.get(position);
        holder.bind(card);
    }

    @Override
    public int getItemCount() {
        return selectedCards != null ? selectedCards.size() : 0;
    }

    public void setSelectedCards(List<Card> selectedCards) {
        this.selectedCards = selectedCards;
        notifyDataSetChanged();
    }

    public List<Card> getSelectedCards() {
        return selectedCards;
    }
}
