package cat.udl.hyperion.appmobils.kingdomcollector.collection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;

public class CardCollectionAdapter extends RecyclerView.Adapter<CardCollectionViewHolder> {
    private List<Card> cards;


    public CardCollectionAdapter(List<Card> cards) {
        this.cards = cards != null ? cards : new ArrayList<>();
    }

    @NonNull
    @Override
    public CardCollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new CardCollectionViewHolder(view, cards);
    }


    // Actualiza la lista de cartas en el adaptador
    @Override
    public void onBindViewHolder(@NonNull CardCollectionViewHolder holder, int position) {
        Card card = cards.get(position);
        holder.bind(card);
    }

    // Devuelve el número de cartas en la lista.
    @Override
    public int getItemCount() {
        return cards != null ? cards.size() : 0;
    }


    public void setCards(List<Card> cards) {
        this.cards = cards;
        notifyDataSetChanged();
    }
}

