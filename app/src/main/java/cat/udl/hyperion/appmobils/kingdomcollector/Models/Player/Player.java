package cat.udl.hyperion.appmobils.kingdomcollector.Models.Player;

import java.util.ArrayList;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;

/**
 * La clase jugador representa el jugador del juego, con sus cartas, puntos y jugadas.
 * */
public class Player {
    private int points;
    private ArrayList<Card> cards;
    private boolean isComputer;


    /**
     * Este método se llama cuando el jugador coloca una carta en el tablero.
     * */
    public void playCard(Card card){
        // Se remueve la carta jugada de la mano del jugador
        cards.remove(card);
        // TODO: colocar la carta en el tablero
        // TODO: actualizar los atributos correspondientes
        // TODO: cambiar al siguiente turno
    }



    /**
     * Este método se llama para calcular la puntuación del jugador.
     * */
    public int calculatePuntuation(){
        int punt = this.points;
        return punt;
    }
}
