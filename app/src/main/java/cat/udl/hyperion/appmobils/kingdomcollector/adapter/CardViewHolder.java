package cat.udl.hyperion.appmobils.kingdomcollector.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.CardSelectedViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.views.CardCollectionActivity;

public class CardViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameTextView;
    private final ImageView imageView;
    private final TextView typeTextView;
    private final List<Card> cards;

    public CardViewHolder(View itemView, List<Card> cards) {
        super(itemView);
        this.cards = cards;
        nameTextView = itemView.findViewById(R.id.name_text_view);
        imageView = itemView.findViewById(R.id.image_view);
        typeTextView = itemView.findViewById(R.id.type_text_view);

        // Agrega un listener de clic en la vista del elemento de la lista
        itemView.setOnClickListener(v -> {
            // Obtiene la posición de la carta en la lista
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                // Obtiene la carta correspondiente a la posición
                Card card = cards.get(position);
                // Actualiza el estado de selección de la carta
                card.setSelected(!card.isSelected());
                // Actualiza la vista para reflejar el cambio
                itemView.setBackgroundColor(card.isSelected() ? Color.LTGRAY : Color.WHITE);
                // Agrega o remueve la carta seleccionada del ViewModel
                CardSelectedViewModel selectedViewModel = new ViewModelProvider((CardCollectionActivity) itemView.getContext()).get(CardSelectedViewModel.class);
                if (card.isSelected()) {
                    selectedViewModel.addSelectedCard(card);
                } else {
                    selectedViewModel.removeSelectedCard(card);
                }
            }
        });
    }

    public void bind(Card card) {
        nameTextView.setText(card.getName());
        imageView.setImageResource(card.getImage());
        typeTextView.setText(card.getType());
        // Establece el fondo de la vista según el estado de selección de la carta
        itemView.setBackgroundColor(card.isSelected() ? Color.LTGRAY : Color.WHITE);
    }
}

