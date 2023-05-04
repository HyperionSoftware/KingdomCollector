package cat.udl.hyperion.appmobils.kingdomcollector.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.models.Card;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionViewHolder> {
    private List<Card> cards;


    public CollectionAdapter(List<Card> cards) {
        this.cards = cards != null ? cards : new ArrayList<>();
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new CollectionViewHolder(view, cards);
    }


    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder holder, int position) {
        Card card = cards.get(position);
        holder.bind(card);
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

