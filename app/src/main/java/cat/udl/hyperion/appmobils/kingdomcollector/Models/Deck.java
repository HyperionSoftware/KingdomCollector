package cat.udl.hyperion.appmobils.kingdomcollector.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    public Card obtenerCartaAleatoria(){
        Random random = new Random();
        int randomIndex = random.nextInt(cards.size() - 0 + 1) + 0;
        Card card = obtenerCarta(randomIndex);
        eliminarCarta(card);
        return card;
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
    public int getSelected(){
       for(int i = 0; i < cards.size(); i++){
           if(cards.get(i).isSelected()){
               return i;
           }
       }
       return -1;
    }

    public void initializeDeck(){
        Card card;
        Random random = new Random();
        int valorMax = 10, valorMin = 1;
        for(int i = 0; i < 15; i++){ //Crearemos 15 cartas.
            int randomArriba = random.nextInt(valorMax - valorMin +1) + valorMin;
            int randomIzquierda = random.nextInt(valorMax - valorMin +1) + valorMin;
            int randomAbajo = random.nextInt(valorMax - valorMin +1) + valorMin;
            int randomDerecha = random.nextInt(valorMax - valorMin +1) + valorMin;
            card = new Card(i, "TestingCard",randomArriba, randomIzquierda, randomAbajo, randomDerecha);
            cards.add(card);
        }
    }
}


