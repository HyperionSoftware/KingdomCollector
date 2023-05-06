package cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels;



import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Deck;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.Player;

public class DeckViewModel extends ViewModel {
    private MutableLiveData<Deck> deck;
    private Player player;
    private MutableLiveData<Card> selectedCard;
    private static final String TAG = "DeckViewModel";

    public DeckViewModel() {
        Log.d("DeckViewModel", "Creando el DeckViewModel...");
        deck = new MutableLiveData<>();
        deck.setValue(new Deck());
        selectedCard = new MutableLiveData<>();
    }

    public LiveData<Deck> getDeck() {
        Log.d("DeckViewModel", "Obteniendo el DeckViewModel...");
        return deck;
    }

    public void addCardToDeck(Card card) {
        Deck currentDeck = deck.getValue();
        if (currentDeck != null) {
            currentDeck.agregarCarta(card);
            deck.postValue(currentDeck);
        }
    }

    public void removeCardFromDeck(Card card) {
        Deck currentDeck = deck.getValue();
        if (currentDeck != null) {
            currentDeck.eliminarCarta(card);
            deck.postValue(currentDeck);
        }
    }

    public void shuffleDeck() {
        Deck currentDeck = deck.getValue();
        if (currentDeck != null) {
            currentDeck.barajar();
            deck.postValue(currentDeck);
        }
    }

    public void setSelectedCard(Card card) {
        Log.d(TAG, "Estableciendo la carta seleccionada: " + (card != null ? card.getName() : "null"));
        selectedCard.setValue(card);
    }




    public LiveData<Card> getSelectedCard() {
        Log.d(TAG, "Obteniendo la carta seleccionada: " + (selectedCard.getValue() != null ? selectedCard.getValue().getName() : "null"));
        return selectedCard;
    }


    public void resetDeck() {
        Deck currentDeck = deck.getValue();
        if (currentDeck != null) {
            currentDeck.initializeDeck(); // Asumiendo que este método reinicia el mazo a su estado inicial.
            deck.postValue(currentDeck);
        }
    }

    public boolean isDeckEmpty() {
        Deck currentDeck = deck.getValue();
        if (currentDeck != null) {
            return currentDeck.getCards().isEmpty();
        }
        return true;
    }

    public int getDeckSize() {
        Deck currentDeck = deck.getValue();
        if(currentDeck!=null){
            return currentDeck.getCards().size();
        }
        return 0;
    }

    public Card getCardAtIndex(int index) {
        Deck currentDeck = deck.getValue();
        if (currentDeck != null && index >= 0 && index < currentDeck.getCards().size()) {
            return currentDeck.getCards().get(index);
        }
        return null;
    }
}
