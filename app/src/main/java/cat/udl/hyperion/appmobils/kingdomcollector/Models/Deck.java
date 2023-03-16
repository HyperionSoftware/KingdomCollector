package cat.udl.hyperion.appmobils.kingdomcollector.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Esta clase representa las cartas que quedan para poder escoger.
 * */
public class Deck {
    private List<Card> cards;
    private static Deck instance;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public static Deck getInstance() {
        if(instance == null){
            instance = new Deck();
        }
        return instance;
    }


    // Métodos para agregarCarta, eliminarCarta, obtenerCarta, barajar

    public void agregarCarta(Card card) {
        cards.add(card);
    }

    public void eliminarCarta(Card card) {
        cards.remove(card);
    }

    public Card obtenerCarta(int index) {
        if (index >= 0 && index < cards.size()) {
            return cards.get(index);
        }
        return null;
    }

    public void barajar() {
        Collections.shuffle(cards);
    }

        // Considera agregar un método para obtener el tamaño del mazo
    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}


