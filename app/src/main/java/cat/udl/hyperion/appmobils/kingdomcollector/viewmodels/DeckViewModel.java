package cat.udl.hyperion.appmobils.kingdomcollector.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.CardCollection;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Deck;
import cat.udl.hyperion.appmobils.kingdomcollector.views.OnCardClickListener;

/**
 * Esta clase ViewModel maneja el Deck de cartas y se encarga de la comunicación entre la vista y el modelo.
 * */
public class DeckViewModel extends ViewModel {
    private final MutableLiveData<Deck> deckLiveData;
    private OnCardClickListener onCardClickListener;

    /**
     * Constructor de la clase DeckViewModel. Inicializa el DeckLiveData.
     */
    public DeckViewModel() {
        deckLiveData = new MutableLiveData<>();
        deckLiveData.setValue(Deck.getInstance());
    }

    /**
     * Obtiene el DeckLiveData.
     * @return LiveData de Deck.
     */
    public LiveData<Deck> getDeckLiveData() {
        return deckLiveData;
    }

    /**
     * Agrega una carta al Deck.
     * @param card Carta a agregar.
     */
    public void addCard(Card card) {
        Deck deck = deckLiveData.getValue();
        if (deck != null) {
            deck.agregarCarta(card);
            deckLiveData.setValue(deck);
        }
    }

    /**
     * Elimina una carta del Deck.
     * @param card Carta a eliminar.
     */
    public void removeCard(Card card) {
        Deck deck = deckLiveData.getValue();
        if (deck != null) {
            deck.eliminarCarta(card);
            deckLiveData.setValue(deck);
        }
    }

    /**
     * Obtiene una carta del Deck según su índice.
     * @param index Índice de la carta en el Deck.
     * @return Carta en la posición especificada.
     */
    public Card getCard(int index) {
        Deck deck = deckLiveData.getValue();
        if (deck != null) {
            return deck.obtenerCarta(index);
        }
        return null;
    }

    /**
     * Baraja las cartas del Deck.
     */
    public void shuffle() {
        Deck deck = deckLiveData.getValue();
        if (deck != null) {
            deck.barajar();
            deckLiveData.setValue(deck);
        }
    }

    /**
     * Obtiene el tamaño del Deck.
     * @return Tamaño del Deck.
     */
    public int getDeckSize() {
        Deck deck = deckLiveData.getValue();
        if (deck != null) {
            return deck.getSize();
        }
        return 0;
    }

    /**
     * Obtiene la posición de la carta seleccionada.
     * @return Índice de la carta seleccionada.
     */
    public int getSelectedCard() {
        Deck deck = deckLiveData.getValue();
        if (deck != null) {
            return deck.getSelected();
        }
        return -1;
    }

    /**
     * Establece el OnCardSelectedListener.
     * @param listener Listener de selección de cartas.
     */
    public void setOnCardSelectedListener(OnCardClickListener listener) {
        this.onCardClickListener = listener;
    }

    /**
     * Método para llamar cuando se selecciona una carta.
     * @param cardCollection Carta seleccionada.
     */
    public void selectCard(CardCollection cardCollection) {
        if (onCardClickListener != null) {
            onCardClickListener.onCardSelected(cardCollection);
        }
    }
}
