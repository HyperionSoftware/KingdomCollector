package cat.udl.hyperion.appmobils.kingdomcollector.Models.Player;


import cat.udl.hyperion.appmobils.kingdomcollector.Models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Deck;

/**
 * La clase jugador representa el jugador del juego, con sus cartas, puntos y jugadas.
 * */
public abstract class Player {
        private String name;
        private Deck deck; //Deck of the player.
        private int score;

    public Player(String name) {
        setName(name);
        this.score = 0;
        //setDeck(deck);
    }

    public abstract Card playCard(Board board);

        // Getters y setters

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasCard(Card card) {
        for(Card c: this.deck.getCards()){
            if(c.getId() == card.getId()){
                return true;
            }
        }
        return false;
    }

    public void removeCard(Card card) {
        this.deck.eliminarCarta(card);
    }

    public int getNumberOfCards() {
        return this.deck.getSize();
    }

    public int getScore() {
        return this.score;
    }

    public void resetCards() {
        this.deck = new Deck();
    }

    public void setScore(int newScore) {
        this.score = newScore;
    }
}

