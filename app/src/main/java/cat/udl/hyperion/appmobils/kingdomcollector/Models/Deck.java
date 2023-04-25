package cat.udl.hyperion.appmobils.kingdomcollector.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * La clase Deck representa las cartas que quedan para poder escoger.
 */
public class Deck {
    private final List<CardCollection> cards;
    private static Deck instance;

    /**
     * Crea una nueva instancia de Deck.
     */
    public Deck() {
        this.cards = new ArrayList<CardCollection>();
    }

    /**
     * Devuelve la instancia única de Deck.
     *
     * @return la instancia única de Deck
     */
    public static Deck getInstance() {
        if(instance == null){
            instance = new Deck();
        }
        return instance;
    }

    /**
     * Agrega una carta al mazo.
     *
     * @param card la carta a agregar
     */
    public void agregarCarta(CardCollection card) {
        cards.add(card);
    }

    /**
     * Elimina una carta del mazo.
     *
     * @param card la carta a eliminar
     */
    public void eliminarCarta(CardCollection card) {
        cards.remove(card);
    }

    /**
     * Obtiene una carta del mazo según su índice.
     *
     * @param index el índice de la carta a obtener
     * @return la carta obtenida o null si el índice está fuera de rango
     */
    public CardCollection obtenerCarta(int index) {
        if (index >= 0 && index < cards.size()) {
            return cards.get(index);
        }
        return null;
    }

    /**
     * Obtiene una carta aleatoria del mazo y la elimina del mismo.
     *
     * @return la carta obtenida aleatoriamente
     */
    public CardCollection obtenerCartaAleatoria(){
        Random random = new Random();
        int randomIndex = random.nextInt(cards.size() - 0 + 1) + 0;
        CardCollection card = obtenerCarta(randomIndex);
        eliminarCarta(card);
        return card;
    }

    /**
     * Baraja el mazo.
     */
    public void barajar() {
        Collections.shuffle(cards);
    }

    /**
     * Obtiene el tamaño del mazo.
     *
     * @return el número de cartas en el mazo
     */
    public int getSize() {
        return cards.size();
    }

    /**
     * Obtiene la lista de cartas en el mazo.
     *
     * @return la lista de cartas en el mazo
     */
    public List<CardCollection> getCards() {
        return cards;
    }

    /**
     * Obtiene el índice de la carta seleccionada en el mazo.
     *
     * @return el índice de la carta seleccionada o -1 si no hay ninguna seleccionada
     */
    public int getSelected(){
        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i).isSelected()){
                return i;
            }
        }
        return -1;
    }

    /**
     * Inicializa el mazo con 15 cartas aleatorias.
     */
    public void initializeDeck(){
        CardCollection card = null;
        Random random = new Random();
        int valorMax = 10, valorMin = 1;
        for(int i = 0; i < 15; i++){ //Crearemos 15 cartas.
            int randomArriba = random.nextInt(valorMax - valorMin +1) + valorMin;
            int randomIzquierda = random.nextInt(valorMax - valorMin +1) + valorMin;
            int randomAbajo = random.nextInt(valorMax - valorMin +1) + valorMin;
            int randomDerecha = random.nextInt(valorMax - valorMin +1) + valorMin;
            assert false;
            card = new CardCollection(card.getId(), card.getName(),card.getImagePath(), card.getDescription(), card.getType(), randomArriba, randomIzquierda, randomAbajo, randomDerecha);
            cards.add(card);
        }
    }
}


