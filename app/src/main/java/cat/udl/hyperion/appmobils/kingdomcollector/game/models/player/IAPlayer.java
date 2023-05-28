package cat.udl.hyperion.appmobils.kingdomcollector.game.models.player;

import android.util.Log;

import java.io.Serializable;
import java.util.Random;

import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Deck;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.BoardViewModel;

public class IAPlayer extends Player implements Serializable {
    public IAPlayer(String name) {
        super(name);
    }

    @Override
    public void playTurn(GameController gameController) {
        // Implementa la lógica para que la IA juegue un turno (colocar una carta en el tablero de forma estratégica)
        int row = -1, col = -1;
        Card bestCard = null;
        int maxBenefit = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameController.isCellEmpty(i, j)) {
                    Deck deck = gameController.getComputerDeckViewModel().getDeck().getValue();
                    if(deck != null){
                        for (Card card : deck.getCards()) {
                            // Si la carta a jugar proporciona un beneficio mayor al actual, actualizamos nuestras variables
                            int cardBenefit = calculateCardBenefit(i, j, card, gameController.getBoardViewModel());
                            if (cardBenefit > maxBenefit) {
                                maxBenefit = cardBenefit;
                                bestCard = card;
                                row = i;
                                col = j;
                            }
                        }
                    }
                }
            }
        }

        // Si encontramos un movimiento estratégico, jugamos esa carta. Si no, caemos en el comportamiento aleatorio anterior
        if (row != -1 && col != -1 && bestCard != null) {
            gameController.getComputerDeckViewModel().setSelectedCard(bestCard);
            gameController.playCard(this, row, col);
            Log.d("IAPlayerLISTO", "IA turn: Placed card at row " + row + ", col " + col);
        } else {
            playTurnMenosListo(gameController);
        }
    }


    private int calculateCardBenefit(int row, int col, Card card, BoardViewModel boardViewModel) {
        int benefit = 0;

        // Check the 8 adjacent positions on the board for a given cell
        int[] rowAdj = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colAdj = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int newRow = row + rowAdj[i];
            int newCol = col + colAdj[i];

            // Check if the new position is valid and if there is a card at that position
            if(newRow >= 0 && newRow <=2 && newCol >=0 && newCol <=2){
                if (boardViewModel.isValid(newRow, newCol)) {
                    if(boardViewModel.getCell(newRow, newCol).hasCard()){
                        Card adjacentCard = boardViewModel.getCell(newRow, newCol).getCard();

                        // Check if the card we are considering to play can conquer the adjacent card
                        // Assume we have a 'canConquer' method that compares two cards and determines if the first can conquer the second
                        if (card.canConquer(adjacentCard)) {
                            // If so, add the points of the adjacent card to our benefit
                            benefit += 1;
                        }
                    }
                }
            }
        }

        return benefit;
    }



    public void playTurnMenosListo(GameController gameController) {
        // Implementa la lógica para que la IA juegue un turno (colocar una carta en el tablero de forma aleatoria)
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!gameController.isCellEmpty(row, col));

        // Aquí también se debe seleccionar una carta al azar del mazo de la IA.
        int randomCardIndex = random.nextInt(gameController.getComputerDeckViewModel().getDeckSize());
        gameController.getComputerDeckViewModel().setSelectedCard(gameController.getComputerDeckViewModel().getCardAtIndex(randomCardIndex));

        gameController.playCard(this, row, col);
        Log.d("IAPlayer", "IA turn: Placed card at row " + row + ", col " + col);
    }
}

