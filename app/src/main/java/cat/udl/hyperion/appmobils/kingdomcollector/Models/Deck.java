package cat.udl.hyperion.appmobils.kingdomcollector.Models;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Esta clase representa las cartas que quedan para poder escoger.
 * */
public class Deck {
    private ArrayList<Card> cards;
    private static Deck instance;

    /**
     * Constructor for the Deck class that initializes the card pool.
     */
    public Deck() {
        cards = new ArrayList<>();
    }

    public static Object getInstance() {

        if (instance == null) {
            instance = new Deck();
        }
        return instance;

    }

    /**
     * This method shuffles the cards in the deck.
     */
    public void shuffle(){
        /*The shuffle() method uses the Collections.shuffle() method to randomly shuffle the cards in the deck.
         This method uses the default random number generator to generate a random permutation of the list. */
        Collections.shuffle(cards);
    }

    /**
     * This method removes a card from the deck.
     * @param card The card to be removed.
     */
    public void remove(Card card){
        /*The remove() method simply removes the specified card from the deck using the ArrayList.remove() method.
         Since Card objects are compared by reference, this method will only remove the exact instance of the card
         that is passed as an argument.*/
        cards.remove(card);
    }
}

