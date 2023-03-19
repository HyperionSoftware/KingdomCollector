package cat.udl.hyperion.appmobils.kingdomcollector.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Deck;
import cat.udl.hyperion.appmobils.kingdomcollector.views.OnCardSelectedListener;


public class DeckViewModel extends ViewModel {
    private MutableLiveData<Deck> deckLiveData;
    private OnCardSelectedListener onCardSelectedListener;
    public DeckViewModel() {
        deckLiveData = new MutableLiveData<>();
        deckLiveData.setValue(Deck.getInstance());
    }


    public LiveData<Deck> getDeckLiveData() {
        return deckLiveData;
    }

    public void addCard(Card card) {
        Deck deck = deckLiveData.getValue();
        if (deck != null) {
            deck.agregarCarta(card);
            deckLiveData.setValue(deck);
        }
    }

    public void removeCard(Card card) {
        Deck deck = deckLiveData.getValue();
        if (deck != null) {
            deck.eliminarCarta(card);
            deckLiveData.setValue(deck);
        }
    }

    public Card getCard(int index) {
        Deck deck = deckLiveData.getValue();
        if (deck != null) {
            return deck.obtenerCarta(index);
        }
        return null;
    }

    public void shuffle() {
        Deck deck = deckLiveData.getValue();
        if (deck != null) {
            deck.barajar();
            deckLiveData.setValue(deck);
        }
    }

    public int getDeckSize() {
        Deck deck = deckLiveData.getValue();
        if (deck != null) {
            return deck.getSize();
        }
        return 0;
    }

    public int getSelectedCard() {
        Deck deck = deckLiveData.getValue();
        if (deck != null) {
            return deck.getSelected();
        }
        return -1;
    }
    public void setOnCardSelectedListener(OnCardSelectedListener listener) {
        this.onCardSelectedListener = listener;
    }
    // Método para llamar cuando una carta es seleccionada
    public void selectCard(Card card) {
        if (onCardSelectedListener != null) {
            onCardSelectedListener.onCardSelected(card);
        }
    }
}

