package cat.udl.hyperion.appmobils.kingdomcollector.collection.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private static final String TAG = "CardAdapter";
    private List<Card> cards;

    public CardAdapter(List<Card> cards) {
        this.cards = cards;
        Log.d(TAG, "CardAdapter iniciado con " + cards.size() + " cartas.");
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v(TAG, "onCreateViewHolder llamado.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Log.v(TAG, "onBindViewHolder llamado para la posición " + position + ".");
        Card card = cards.get(position);
        holder.bind(card);
    }

    @Override
    public int getItemCount() {
        int size = (cards != null ? cards.size() : 0);
        Log.d(TAG, "getItemCount llamado, devolviendo " + size + "."); // registro de depuración
        return size;
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder {
        private ImageView cardImage;
        private TextView cardName;
        private TextView cardType;

        public CardViewHolder(View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.card_image); // reemplaza con tus ids
            cardName = itemView.findViewById(R.id.card_name);
            cardType = itemView.findViewById(R.id.card_type);
            Log.d(TAG, "CardViewHolder creado.");
        }

        public void bind(Card card) {
            Log.i(TAG, "Vinculando carta: " + card.getName() + " de tipo: " + card.getType() + ".");
            cardImage.setImageResource(card.getImageResource());
            cardName.setText(card.getName());
            cardType.setText(card.getType());
        }
    }
}
