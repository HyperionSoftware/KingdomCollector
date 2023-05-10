package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

import androidx.databinding.ObservableArrayList;

import java.util.Collections;

import cat.udl.hyperion.appmobils.kingdomcollector.R;

public class Deck {
    private ObservableArrayList<Card> cards;
    private static Deck instance;

    public Deck(){
        initializeDeck();
    }

    public void initializeDeck(){
        //TODO: Fer que initializeDeck agafi únicament les 5 cartes seleccionades de Collection.
        cards = new ObservableArrayList<>();
        cards.add(new Card("Ibai Llanos", R.drawable.ibai,"President",9,8,9,8));
        cards.add(new Card("Iker Casillas", R.drawable.iker_casillas,"President",7,7,7,7));
        cards.add(new Card("The Gref", R.drawable.thegref,"President",6,5,4,7));
        cards.add(new Card("Kun Aguero", R.drawable.kun_aguero,"President",8,4,5,8));
        cards.add(new Card("Juan Guarnizo", R.drawable.juan_guarnizo,"President",9,6,3,2));
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
