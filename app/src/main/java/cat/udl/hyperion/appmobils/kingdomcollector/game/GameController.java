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
        // Aquí puedes implementar la lógica para actualizar los puntos de ambos jugadores
        // según las reglas del juego.
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
        return humanPlayer.getPoints();
    }

    public int getComputerPlayerPoints() {
        return computerPlayer.getPoints();
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
}

