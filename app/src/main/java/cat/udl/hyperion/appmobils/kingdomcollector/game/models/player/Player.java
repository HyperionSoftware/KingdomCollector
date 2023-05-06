package cat.udl.hyperion.appmobils.kingdomcollector.game.models.player;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;


import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Deck;
import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;

public abstract class Player {
    private String name;
    private ObservableField<Deck> deck;
    private MutableLiveData<Integer> points;

    public Player(String name){
        this.name = name;
        this.points = new MutableLiveData<>(0);
        deck = new ObservableField<>(new Deck());
        deck.get().initializeDeck();
    }
    public abstract void playTurn(GameController gameController);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getDeck() {
        return deck.get();
    }

    public void setDeck(Deck deck) {
        this.deck.set(deck);
    }

    public ObservableField<Deck> getDeckField() {
        return deck;
    }

    public MutableLiveData<Integer> getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points.setValue(points);
    }


    public void addCardToDeck(Card card) {
        Deck currentDeck = deck.get();
        currentDeck.agregarCarta(card);
        deck.set(currentDeck);
    }

    public void removeCardFromDeck(Card card) {
        Deck currentDeck = deck.get();
        currentDeck.eliminarCarta(card);
        deck.set(currentDeck);
    }

    public void shuffleDeck() {
        Deck currentDeck = deck.get();
        currentDeck.barajar();
        deck.set(currentDeck);
    }



}
