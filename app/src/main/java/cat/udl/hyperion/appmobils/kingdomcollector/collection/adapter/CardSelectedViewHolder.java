package cat.udl.hyperion.appmobils.kingdomcollector.collection.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.viewmodels.CardSelectedViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.views.CardCollectionActivity;

public class CardSelectedViewHolder extends RecyclerView.ViewHolder {
    private final ImageView imageView;
    private final List<Card> selectedCards;

    public CardSelectedViewHolder(@NonNull View itemView, List<Card> selectedCards) {
        super(itemView);
        this.selectedCards = selectedCards;
        imageView = itemView.findViewById(R.id.image_view);

        // Agrega un listener de clic en la vista del elemento de la lista
        itemView.setOnClickListener(v -> {
            // Obtiene la posición de la carta en la lista
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                // Obtiene la carta correspondiente a la posición
                Card card = selectedCards.get(position);

                // Actualiza el estado de selección de la carta
                card.setSelected(false);

                // Remueve la carta seleccionada del ViewModel
                CardSelectedViewModel selectedViewModel = new ViewModelProvider((CardCollectionActivity) itemView.getContext()).get(CardSelectedViewModel.class);
                selectedViewModel.removeSelectedCard(card);

                // Actualiza la lista de cartas seleccionadas en el adaptador
                CardSelectedAdapter adapter = (CardSelectedAdapter) ((RecyclerView) itemView.getParent()).getAdapter();
                adapter.setSelectedCards(selectedViewModel.getSelectedCardsLiveData().getValue());
                // Actualiza la vista para reflejar el cambio
                itemView.setBackgroundColor(card.isSelected() ? Color.LTGRAY : Color.WHITE);
            }
        });
    }

    public void bind(Card card) {
        imageView.setImageResource(card.getImage());
        // Establece el fondo de la vista según el estado de selección de la carta
        itemView.setBackgroundColor(card.isSelected() ? Color.LTGRAY : Color.WHITE);
    }
}
