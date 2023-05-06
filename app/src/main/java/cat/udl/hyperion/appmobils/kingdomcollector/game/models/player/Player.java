package cat.udl.hyperion.appmobils.kingdomcollector.game.models.player;

import androidx.databinding.ObservableField;


import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Deck;
import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;

public abstract class Player {
    private String name;
    private ObservableField<Deck> deck;
    private ObservableField<Integer> points;

    public Player(String name){
        this.name = name;
        this.points = new ObservableField<>(0);
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

    public int getPoints() {
        return points.get();
    }

    public void setPoints(int points) {
        this.points.set(points);
    }

    public ObservableField<Integer> getPointsField() {
        return points;
    }

    public void notifyPropertyChanged(int fieldId) {
        deck.notifyChange();
        points.notifyChange();
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

    public void increasePoints(int points) {
        int currentPoints = this.points.get();
        currentPoints += points;
        this.points.set(currentPoints);
    }

}
