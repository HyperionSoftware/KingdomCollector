package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

import androidx.databinding.ObservableArrayList;

import java.util.Collections;

/**
 * Cada jugador tiene su Deck, que es una lista de cartas.
 */
public class Deck {
    private ObservableArrayList<Card> cards;

    public Deck(){
        initializeDeck();
    }


    public void initializeDeck(){
        //TODO: Fer que initializeDeck agafi únicament les 5 cartes seleccionades de DBCollection.
        cards = new ObservableArrayList<>();
        //cards.add(collection);
    }

    public static Deck getInstance() {
        return new Deck();
    }

    public void agregarCarta(Card card) {
        cards.add(card);
    }

    public void eliminarCarta(Card card) {
        cards.remove(card);
    }

    public void barajar() {
        Collections.shuffle(cards);
    }

    public ObservableArrayList<Card> getCards() {
        return cards;
    }

    /*public void notifyPropertyChanged(int fieldId) {
        cards.notifyChange();
    }*/


}
