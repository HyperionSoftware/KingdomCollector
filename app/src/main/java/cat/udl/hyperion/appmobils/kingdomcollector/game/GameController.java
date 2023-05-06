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

    private BoardViewModel boardViewModel;
    private DeckViewModel deckViewModel;
    private Player humanPlayer;
    private Player computerPlayer;
    private Player currentPlayer;

    public GameController(BoardViewModel boardViewModel, DeckViewModel humanDeckViewModel, DeckViewModel computerDeckViewModel) {
        this.boardViewModel = boardViewModel;
        this.deckViewModel = humanDeckViewModel;
        this.humanPlayer = new HumanPlayer("Human");
        this.computerPlayer = new IAPlayer("Computer");
        this.currentPlayer = humanPlayer;
    }

    public void startNewGame() {
        boardViewModel.clearBoard();
        deckViewModel.resetDeck();
        humanPlayer.setPoints(0);
        computerPlayer.setPoints(0);
    }

    public void playCard(int row, int col) {
        if (currentPlayer == humanPlayer) {
            Card selectedCard = deckViewModel.getSelectedCard().getValue();
            if (selectedCard != null) {
                boardViewModel.placeCard(row, col, selectedCard);
                boardViewModel.setBoardDataChanged(true);
                deckViewModel.removeCardFromDeck(selectedCard);
                updateGamePoints(); // Actualiza los puntos según las reglas del juego.
                switchTurn(humanPlayer, computerPlayer);
                Log.d("GameController", "It's " + currentPlayer +" turn.");
                if (!isGameOver()) {
                    computerPlayer.playTurn(this);
                }
                switchTurn(computerPlayer, humanPlayer);
            }
        }
    }

    public boolean isGameOver() {
        return boardViewModel.isBoardFull() || deckViewModel.isDeckEmpty();
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
}
