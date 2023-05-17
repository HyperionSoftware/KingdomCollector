package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

import androidx.databinding.ObservableArrayList;

import java.util.Collections;

import cat.udl.hyperion.appmobils.kingdomcollector.R;

/**
 * Cada jugador tiene su Deck, que es una lista de cartas.
 */
public class Deck {
    private ObservableArrayList<Card> cards;

    public Deck(){
        initializeDeck();
    }


    public void initializeDeck(){
        //TODO: Fer que initializeDeck agafi únicament les 5 cartes seleccionades de Collection.
        cards = new ObservableArrayList<>();
        //public Card(String id,int imageResource, String name,String type, int powerArriba, int powerIzquierda, int powerAbajo, int powerDerecha){
        cards.add(new Card("barrio1",R.drawable.barrio_adri_contreras_presidente,"AdriContreras","Presidente", 9, 7, 10,10));
        cards.add(new Card("barrio2",R.drawable.barrio_barnera_portero ,"Barnera","Portero", 3, 4, 4, 4));
        cards.add(new Card("barrio3",R.drawable.barrio_boada_delantero,"Boada","Delantero", 6,2,3,6));
        cards.add(new Card("barrio4",R.drawable.barrio_capilla_medio,"Capilla","Medio",5,5,3,1));
        cards.add(new Card("barrio5",R.drawable.barrio_jacob_delantero,"Jacob","Delantero",2,3,6,7));
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