package cat.udl.hyperion.appmobils.kingdomcollector.Models.Player;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Deck;

/**
 * La clase Player representa un jugador en un juego de cartas, con sus cartas, puntos y jugadas.
 */
public abstract class Player {
    private String name; // El nombre del jugador.
    private Deck deck; // El mazo de cartas del jugador.
    private int score; // La puntuación del jugador.

    /**
     * Constructor de la clase Player.
     *
     * @param name El nombre del jugador.
     */
    public Player(String name) {
        setName(name); // Establece el nombre del jugador llamando al método setName().
        this.score = 0; // Inicializa la puntuación del jugador a cero.
    }

    /**
     * Método abstracto que debe ser implementado por las clases que extienden Player,
     * para que el jugador seleccione una carta para jugar.
     *
     * @param board El tablero actual del juego.
     * @return La carta seleccionada por el jugador, o null si no se ha seleccionado ninguna.
     */
    public abstract Card playCard(Board board);

    // Getters y setters

    /**
     * Getter para el mazo de cartas del jugador.
     *
     * @return El mazo de cartas del jugador.
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Setter para el mazo de cartas del jugador.
     *
     * @param deck El nuevo mazo de cartas del jugador.
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    /**
     * Getter para el nombre del jugador.
     *
     * @return El nombre del jugador.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter para el nombre del jugador.
     *
     * @param name El nuevo nombre del jugador.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método para comprobar si el jugador tiene una carta concreta.
     *
     * @param card La carta que se quiere comprobar.
     * @return true si el jugador tiene la carta, false en caso contrario.
     */
    public boolean hasCard(Card card) {
        for (Card c : this.deck.getCards()) { // Recorre las cartas del mazo del jugador.
            if (c.getId() == card.getId()) { // Compara los identificadores de las cartas.
                return true; // Si hay una carta con el mismo identificador, devuelve true.
            }
        }
        return false; // Si no se ha encontrado la carta, devuelve false.
    }

    /**
     * Método para eliminar una carta del mazo del jugador.
     *
     * @param card La carta que se quiere eliminar.
     */
    public void removeCard(Card card) {
        this.deck.eliminarCarta(card); // Elimina la carta del mazo del jugador.
    }

    /**
     * Getter para obtener el número de cartas en el mazo del jugador.
     *
     * @return El número de cartas en el mazo del jugador.
     */
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

    public void setDeck(int newNumberOfCards) {
        int indice = deck.getSelected();
        removeCard(deck.obtenerCarta(indice));
        if(deck.getSize() != newNumberOfCards){
            System.out.println("Problem deleting card of the player...");
        }
    }
}