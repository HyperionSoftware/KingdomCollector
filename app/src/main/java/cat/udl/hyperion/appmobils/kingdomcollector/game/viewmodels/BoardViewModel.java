package cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Cell;
import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;

public class BoardViewModel extends ViewModel {
    private MutableLiveData<Board> board;
    private DeckViewModel deckViewModel;
    private static final String TAG = "BoardViewModel";
    private MutableLiveData<Card> selectedCard = new MutableLiveData<>();

    private MutableLiveData<List<Cell>> cells;
    private List<CellViewModel> cellViewModels;
    private GameController gameController;
    private MutableLiveData<Boolean> boardDataChanged;



    public LiveData<Card> getSelectedCard(){
        return deckViewModel.getSelectedCard();
    }

    public void setSelectedCard(Card card) {
        selectedCard.setValue(card);
    }

    public BoardViewModel(GameController gameController) {
        this.gameController = gameController;
        this.boardDataChanged = new MutableLiveData<>();
        Log.d(TAG, "Creando el BoardViewModel...");

        cells = new MutableLiveData<>();
        List<Cell> initialCells = new ArrayList<>();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Cell cell = new Cell(row, col);
                initialCells.add(cell);
            }
        }

        cells.setValue(initialCells);

        cellViewModels = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            cellViewModels.add(new CellViewModel(initialCells.get(i)));
        }

        board = new MutableLiveData<>();
        board.setValue(new Board());
    }

    public LiveData<Board> getBoard() {
        return board;
    }

    public void placeCard(int row, int col, Card card) {
        if (board.getValue() != null) {
            board.getValue().placeCard(card, new Cell(row, col));
            CellViewModel cellViewModel = getCellViewModelAt(row, col);
            cellViewModel.setCard(card);
            board.postValue(board.getValue());
            logBoardState();
        }
    }



    public CellViewModel getCellViewModelAt(int row, int col) {
        if (cellViewModels != null) {
            return cellViewModels.get(row * 3 + col);
        } else {
            throw new IllegalStateException("CellViewModels list is not initialized");
        }
    }

    public void playSelectedCard(int row, int col) {
        Card cardToPlay = deckViewModel.getSelectedCard().getValue();
        deckViewModel.setSelectedCard(null);
        if (cardToPlay != null) {
            Log.d(TAG, "Colocando una carta en la posición " + row + " " + col + " la carta es " + cardToPlay.getName());
            placeCard(row, col, cardToPlay);

            deckViewModel.removeCardFromDeck(cardToPlay);

            logBoardState();
        } else {
            Log.d(TAG, "La carta seleccionada es null");
        }
    }

    public DeckViewModel getDeckViewModel() {
        return deckViewModel;
    }

    public void setDeckViewModel(DeckViewModel deckViewModel) {
        this.deckViewModel = deckViewModel;
    }

    public void logBoardState() {
        StringBuilder boardState = new StringBuilder("Estado del tablero:\n");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                CellViewModel cellViewModel = getCellViewModelAt(row, col);
                Card card = cellViewModel.getCard().getValue();
                if (card != null) {
                    boardState.append(String.format("Posición (%d, %d): %s : %s\n", row, col, card.getName(), card.getOwner().getName()));
                } else {
                    boardState.append(String.format("Posición (%d, %d): Vacío\n", row, col));
                }
            }
        }
        Log.d(TAG, boardState.toString());
    }



    public List<CellViewModel> getCellViewModels() {
        return cellViewModels;
    }

    public void clearBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                CellViewModel cellViewModel = getCellViewModelAt(row, col);
                cellViewModel.clearCard();
            }
        }
    }

    public boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                CellViewModel cellViewModel = getCellViewModelAt(row, col);
                if (cellViewModel.getCard().getValue() == null) {
                    return false;
                }
            }
        }
        return true;
    }
    public MutableLiveData<Boolean> getBoardDataChanged() {
        return boardDataChanged;
    }

    public void setBoardDataChanged(boolean changed) {
        this.boardDataChanged.setValue(changed);
    }
}

