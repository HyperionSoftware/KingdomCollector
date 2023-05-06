package cat.udl.hyperion.appmobils.kingdomcollector.game;

import android.util.Log;

import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.BoardViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.CellViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.DeckViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.HumanPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.IAPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.Player;

public class GameController {
    private static final String TAG = "GameController";

    private BoardViewModel boardViewModel;
    private DeckViewModel humanDeckViewModel;
    private DeckViewModel computerDeckViewModel;
    private Player humanPlayer;
    private Player computerPlayer;
    private Player currentPlayer;

    public GameController(BoardViewModel boardViewModel, DeckViewModel humanDeckViewModel, DeckViewModel computerDeckViewModel) {
        this.boardViewModel = boardViewModel;
        this.humanDeckViewModel = humanDeckViewModel;
        this.computerDeckViewModel = computerDeckViewModel;
        this.humanPlayer = new HumanPlayer("Human");
        this.computerPlayer = new IAPlayer("Computer");
        this.currentPlayer = humanPlayer;

        // Inicializar el propietario de las cartas en cada mazo
        this.humanDeckViewModel.initializeOwnerForCards(humanPlayer);
        this.computerDeckViewModel.initializeOwnerForCards(computerPlayer);
    }

    public Player getHumanPlayer() {
        return humanPlayer;
    }

    public void startNewGame() {
        boardViewModel.clearBoard();
        humanDeckViewModel.resetDeck();
        computerDeckViewModel.resetDeck();
        humanPlayer.setPoints(0);
        computerPlayer.setPoints(0);
    }

    public void playCard(Player player, int row, int col) {
        Log.d(TAG,"Playing the card to position ("+ row+","+col+").");
        if (player == humanPlayer) {
            Card selectedCard = humanDeckViewModel.getSelectedCard().getValue();
            if (selectedCard != null) {
                boardViewModel.placeCard(row, col, selectedCard);
                checkAndUpdateAdjacentCards(row, col, selectedCard); // Añadir esta línea
                boardViewModel.setBoardDataChanged(true);
                humanDeckViewModel.removeCardFromDeck(selectedCard);
                humanDeckViewModel.setSelectedCard(null); // Assegurem que no es pugui tirar dos vegades la mateixa carta.
                updateGamePoints(); // Actualiza los puntos según las reglas del juego.
                switchTurn(humanPlayer, computerPlayer);
                if (!isGameOver()) {
                    computerPlayer.playTurn(this);
                }
            }
        } else if (player == computerPlayer) {
            Card selectedCard = computerDeckViewModel.getSelectedCard().getValue();
            if (selectedCard != null) {
                boardViewModel.placeCard(row, col, selectedCard);
                checkAndUpdateAdjacentCards(row, col, selectedCard);
                boardViewModel.setBoardDataChanged(true);
                computerDeckViewModel.removeCardFromDeck(selectedCard);
                computerDeckViewModel.setSelectedCard(null); // Assegurem que no es pugui tirar dos vegades la mateixa carta.
                updateGamePoints(); // Actualiza los puntos según las reglas del juego.
                switchTurn(computerPlayer, humanPlayer);
            }
        }
    }

    public boolean isGameOver() {
        return boardViewModel.isBoardFull() || humanDeckViewModel.isDeckEmpty() || computerDeckViewModel.isDeckEmpty();
    }

    private void updateGamePoints() {
        int humanPoints = 0;
        int computerPoints = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Card currentCard = boardViewModel.getCellViewModelAt(row, col).getCard().getValue();
                if (currentCard == null) {
                    continue;
                }

                Player owner = currentCard.getOwner();
                if (owner == humanPlayer) {
                    humanPoints++;
                } else if (owner == computerPlayer) {
                    computerPoints++;
                }
            }
        }

        humanPlayer.setPoints(humanPoints);
        computerPlayer.setPoints(computerPoints);
        Log.d(TAG, "HumanPlayerPoints: "+ humanPoints + " computerPlayerPoints: " + computerPoints);
    }

    public void switchTurn(Player player1, Player player2) {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
        Log.d(TAG,"switchTurn: it's " + currentPlayer.getName() + " turn.");
    }

    public boolean isCellEmpty(int row, int col) {
        CellViewModel cellViewModel = boardViewModel.getCellViewModelAt(row, col);
        return cellViewModel.getCard().getValue() == null;
    }

    public int getHumanPlayerPoints() {
        return humanPlayer.getPoints().getValue();
    }

    public int getComputerPlayerPoints() {
        return computerPlayer.getPoints().getValue();
    }

    public BoardViewModel getBoardViewModel() {
        return boardViewModel;
    }

    public DeckViewModel getHumanDeckViewModel() {
        return humanDeckViewModel;
    }

    public DeckViewModel getComputerDeckViewModel() {
        return computerDeckViewModel;
    }
    private void checkAndUpdateAdjacentCards(int row, int col, Card playedCard) {
        int[][] adjacentCoordinates = {{row - 1, col}, {row + 1, col}, {row, col - 1}, {row, col + 1}};

        for (int[] coordinates : adjacentCoordinates) {
            int newRow = coordinates[0];
            int newCol = coordinates[1];

            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                Card adjacentCard = boardViewModel.getCellViewModelAt(newRow, newCol).getCard().getValue();
                if (adjacentCard != null) {
                    int adjacentSide = getAdjacentSide(row, col, newRow, newCol);
                    if (hasGreaterPower(adjacentSide, playedCard, adjacentCard) && playedCard.getOwner() != adjacentCard.getOwner()) {
                        adjacentCard.setOwner(playedCard.getOwner());
                    }
                }
            }
        }
    }

    public boolean hasGreaterPower(int side, Card newCard, Card oldCard) {
        switch (side) {
            case 1:
                return newCard.getPowerAbajo() > oldCard.getPowerArriba();
            case 2:
                return newCard.getPowerDerecha() > oldCard.getPowerIzquierda();
            case 3:
                return newCard.getPowerArriba() > oldCard.getPowerAbajo();
            case 4:
                return newCard.getPowerIzquierda() > oldCard.getPowerDerecha();
            default:
                return false;
        }
    }


    private int getAdjacentSide(int row1, int col1, int row2, int col2) {
        if (row1 == row2 - 1) return 1; // top
        if (row1 == row2 + 1) return 3; // bottom
        if (col1 == col2 - 1) return 2; // left
        if (col1 == col2 + 1) return 4; // right
        return -1;
    }
}

