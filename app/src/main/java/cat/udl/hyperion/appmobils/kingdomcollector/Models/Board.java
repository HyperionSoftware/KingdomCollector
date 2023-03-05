package cat.udl.hyperion.appmobils.kingdomcollector.Models;


/**
 * La clase tablero representa el tablero del juego, con sus cartas y posiciones en el mismo.
 * @author joanbonellruiz
 * @author carlosmg
 * @author claudiasarlle
 * @author ricardbp
 * */
public class Board {
    private Card[][] board;
    private int numRows;
    private int numColumns;
    private Card selectedCard;

    /**
     * Este método se llama para inicializar el tablero al comienzo del juego.
     * */
    public void initializeBoard(){
        // Crear el tablero con el tamaño adecuado
        numRows = 4;
        numColumns = 4;
        board = new Card[numRows][numColumns];
        // Colocar las cartas iniciales en el tablero
        // TODO: NO tenemos todavía las gráficas con BDD.

    }

    /**
     * Este método se llama cuando el jugador coloca una carta en el tablero.
     * @attributes Card, int, int
     * */
    public void placeCard(Card card, int posX, int posY){
        // Verificar si la posición es válida
        if (posX >= 0 && posX < numRows && posY >= 0 && posY < numColumns) {
            // Verificar si la posición está vacía
            if (board[posX][posY] == null) {
                // Colocar la carta en la posición indicada
                board[posX][posY] = card;
                // Establecer la carta seleccionada como null
                selectedCard = null;
                //TODO: actualizar los atributos correspondientes¿?¿?
                //TODO: cambiar al siguiente turno¿?¿?
            } else {
                System.out.println("La posición indicada ya está ocupada");
            }
        } else {
            System.out.println("La posición indicada no es válida");
        }
    }

    /**
     * Este método se llama cuando el jugador deselecciona la carta actual.
     * */
    public void unselectCard(Card card){
        // Se verifica si la carta actual es la que se quiere deseleccionar
        if (selectedCard != null && selectedCard.equals(card)) {
            // Se deselecciona la carta actual y se establece el valor a null
            selectedCard = null;
            //TODO: actualizar los atributos correspondientes bdd
        }
    }
}
