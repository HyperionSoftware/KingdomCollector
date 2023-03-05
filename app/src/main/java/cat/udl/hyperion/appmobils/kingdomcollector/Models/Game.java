package cat.udl.hyperion.appmobils.kingdomcollector.Models;


import cat.udl.hyperion.appmobils.kingdomcollector.Models.Player.Player;


/**
 * Esta clase representa el juego.
 * */
public class Game {
    private static Game instance;

    private Board board;
    private Player player1;
    private Player player2;
    private boolean player1Turn;
    private boolean endGame;

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Este método se llama para inicializar el juego. Se encarga de inicializar el tablero y las cartas de los jugadores y establece el primer turno.
     * */
    public void initializeGame(){

        board = new Board();

        Deck deck = (Deck) Deck.getInstance();
        deck.shuffle();


      //  player1.setCards(deck.drawCards(6));
     //   player2.setCards(deck.drawCards(6));


        player1Turn = true;
    }


    /**
     * Este método se llama cuando acaba el turno de un jugador y se cambia al siguiente.
     * Se encarga de actualizar el turno, de capturar las cartas que correspondan y de verificar si el juego ha terminado.
     * */
    public void nextTurn(){

        player1Turn = !player1Turn;

      //  if (board.checkCapture()) {
      //      player1.addCapturedCards(board.getCapturedCards(player1));
       //     player2.addCapturedCards(board.getCapturedCards(player2));
       // }

        // if (board.isGameOver()) {
            //endGame = true;
            //calculatePuntuation();
       // }
    }


    /**
     * Este método se llama cuando el jugador selecciona una carta. Se encarga de verificar si la carta es jugable en ese turno y de actualizar los atributos correspondientes.
     * */
    public void selectCard(Card card){
        /*if (board.isPlayable(card)) {
            // Si la carta es jugable, se establece como la carta seleccionada del jugador
            Player currentPlayer = player1Turn ? player1 : player2;
            currentPlayer.setSelectedCard(card);
        } else {
            // Si la carta no es jugable, se muestra un mensaje de error
            System.out.println("La carta seleccionada no es jugable en este turno");
        }
        if (board.isPlayable(card)) {
            Player currentPlayer = player1Turn ? player1 : player2;
            currentPlayer.setSelectedCard(card);
            currentPlayer.setHasSelectedCard(true);
            currentPlayer.removeCardFromHand(card);
        } else {
            System.out.println("La carta seleccionada no es jugable en este turno");
        }*/
    }

    /**
     * Este método se llama cuando el jugador coloca una carta sobre el tablero. Se encarga de colocar la carta en el tablero, de actualizar los atributos
     * correspondientes y cambiar al siguiente turno.
     * */
    public void playCard(Card card){
        Player currentPlayer = player1Turn ? player1 : player2;
        //boolean success = board.playCard(card, currentPlayer);
       // if (success) {
            // Si se ha podido colocar la carta, se actualizan los atributos y se cambia el turno
           // currentPlayer.removeCard(card);
            //currentPlayer.setSelectedCard(null);
            player1Turn = !player1Turn;
        //} else {
            // Si no se ha podido colocar la carta, se muestra un mensaje de error
            System.out.println("No se ha podido colocar la carta en el tablero");
        //}
    }

    /**
     * Este método se llama cuando un jugador ha capturado una o más cartas del oponente. Se encarga de actualizar los atributos correspondientes y
     * de cambiar al siguiente turno.
     * */
    public void captureCards(){

        Player currentPlayer = player1Turn ? player1 : player2;
       // currentPlayer.addCapturedCards(board.getCapturedCards());
       // board.resetCapturedCards();

        player1Turn = !player1Turn;
    }


    /**
     * Este método se llama cuando se finaliza el juego. Se calcula las puntuaciones de cada jugador y determina el ganador.
     * */
    public void calculatePuntuation(){

        //int player1Points = player1.calculatePoints();
        //int player2Points = player2.calculatePoints();

        /*if (player1Points > player2Points) {
            System.out.println("El jugador 1 ha ganado");
        } else if (player2Points > player1Points) {
            System.out.println("El jugador 2 ha ganado");
        } else {
            System.out.println("Empate");
        }*/
    }


    /**
     * Este método se llama cuando se desea reiniciar el juego. Se encarga de volver a iniciar todos los atributos y objetos del juego.
     * */
    public void restartGame(){

       /* board.resetBoard();

        Deck deck = new Deck();
        deck.shuffle();

        player1.setCards(deck.dealCards());
        player2.setCards(deck.dealCards());

        player1Turn = true;

        player1.setSelectedCard(null);
        player2.setSelectedCard(null);
        player1.clearCapturedCards();
        player2.clearCapturedCards();
        endGame = false;*/
    }
}
