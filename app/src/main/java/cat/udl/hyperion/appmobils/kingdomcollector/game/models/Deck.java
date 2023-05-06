package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

import androidx.databinding.ObservableArrayList;

import java.util.Collections;
import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.DeckViewModel;

public class Deck {
    private ObservableArrayList<Card> cards;
    private static Deck instance;

    public Deck(){
        initializeDeck();
    }

    public void initializeDeck(){
        //TODO: Fer que initializeDeck agafi únicament les 5 cartes seleccionades de Collection.
        cards = new ObservableArrayList<>();
        cards.add(new Card(R.drawable.barrio_adri_contreras_presidente,"AdriContreras", 9, 7, 10,10));
        cards.add(new Card(R.drawable.barrio_barnera_portero, "Barnera", 3, 4, 4, 4));
        cards.add(new Card(R.drawable.barrio_boada_delantero,"Boada", 6,2,3,6));
        cards.add(new Card(R.drawable.barrio_capilla_medio,"Capilla",5,5,3,1));
        cards.add(new Card(R.drawable.barrio_jacob_delantero,"Jacob",2,3,6,7));
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
