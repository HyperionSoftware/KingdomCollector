package cat.udl.hyperion.appmobils.kingdomcollector.collection.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<Card> cards;
    private List<Card> selectedCards;
    private OnClickListener onClickListener = null;

    // Cartas que tiene el usuario:
    private List<String> userCardIds;


    public CardAdapter(List<Card> cards, List<Card> selectedCards, List<String> userCardIds) {
        this.cards = cards;
        this.selectedCards = selectedCards;
        this.userCardIds = userCardIds;
    }


    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Card card = cards.get(position);
        holder.bind(card);

        boolean cardOwned = userCardIds.contains(card.getId()) || false;

        // Oscurecer la tarjeta si el usuario no la posee
        if (!cardOwned) {
            holder.itemView.setAlpha(0.5f);  // Ajusta este valor según el efecto de oscurecimiento deseado
        } else {
            // Si el usuario posee la tarjeta pero está seleccionada, oscurecerla aún más
            if (isSelected(card)) {
                holder.itemView.setAlpha(0.3f); // Establecer opacidad más reducida para indicar selección
            } else {
                holder.itemView.setAlpha(1.0f); // Establecer opacidad normal para tarjetas no seleccionadas que el usuario posee
            }
        }

        // Agregar OnClickListener para manejar el evento de selección de la tarjeta
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Comprobar si la carta pertenece al usuario
                if (cardOwned) {
                    // Verificar si la tarjeta ya está seleccionada
                    if (isSelected(card)) {
                        removeSelectedCard(card);
                        notifyItemRemoved(selectedCards.indexOf(card));
                        holder.itemView.setAlpha(1.0f); // Volver a la opacidad normal una vez deseleccionada
                    } else {
                        // Comprobar si el equipo ya tiene 5 cartas
                        if (selectedCards.size() >= 5) {
                            Toast.makeText(v.getContext(), "El equipo ya tiene 5 cartas. Desselecciona una carta para agregar otra.", Toast.LENGTH_LONG).show();
                        } else {
                            addSelectedCard(card);
                            notifyItemInserted(selectedCards.size() - 1);
                            holder.itemView.setAlpha(0.3f); // Oscurecer la tarjeta seleccionada
                        }
                    }
                    if (onClickListener != null) {
                        onClickListener.onClick();
                    }

                    notifyDataSetChanged();
                } else {
                    Toast.makeText(v.getContext(), "No posees esta carta.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return cards.size();
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder {
        private ImageView cardImage;

        public CardViewHolder(View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.card_image);
        }

        public void bind(Card card) {
            try {
                cardImage.setImageResource(card.getImageResource());
            } catch (Resources.NotFoundException e) {
                // Recurso de imagen no encontrado. Cargar una imagen predeterminada.
                cardImage.setImageResource(R.drawable.card_placeholder);  // suponiendo que 'ic_default_image' es tu imagen predeterminada.
            }
        }

    }

    private boolean isSelected(Card card) {
        return selectedCards != null && selectedCards.contains(card);
    }


    private void addSelectedCard(Card card) {
        selectedCards.add(card);
    }

    private void removeSelectedCard(Card card) {
        selectedCards.remove(card);
    }

    public List<Card> getSelectedCards() {
        return selectedCards;
    }
    public interface OnClickListener {
        void onClick();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
