package cat.udl.hyperion.appmobils.kingdomcollector.game;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.admin.SharedPreferencesManager;
import cat.udl.hyperion.appmobils.kingdomcollector.game.fragments.WinnerFragment;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.HumanPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.IAPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.Player;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.BoardViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.CellViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.DeckViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.game.views.GameActivityInterface;

public class GameController {
    private static final String TAG = "GameController";

    private BoardViewModel boardViewModel;
    private final DeckViewModel humanDeckViewModel;
    private final DeckViewModel computerDeckViewModel;
    private final Player humanPlayer;
    private final Player computerPlayer;
    private Player currentPlayer;

    private FirebaseAuth mAuth;
    private final Handler handler;
    private final Context context;
    private final SharedPreferencesManager sharedPreferencesManager;
    private GameActivityInterface gameActivityInterface;

    public GameController(Context context, BoardViewModel boardViewModel, DeckViewModel humanDeckViewModel, DeckViewModel computerDeckViewModel, GameActivityInterface gameActivityInterface, SharedPreferencesManager sharedPreferencesManager) {
        this.context = context;
        this.gameActivityInterface = gameActivityInterface;
        this.boardViewModel = boardViewModel;
        this.humanDeckViewModel = humanDeckViewModel;
        this.computerDeckViewModel = computerDeckViewModel;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        this.humanPlayer = new HumanPlayer(user.getDisplayName());
        this.computerPlayer = new IAPlayer("Computer");
        this.currentPlayer = humanPlayer;
        this.sharedPreferencesManager = sharedPreferencesManager;
        List<Card> selectedCards = sharedPreferencesManager.getSelectedCards();
        humanDeckViewModel.setCards(selectedCards);


        // Inicializar el propietario de las cartas en cada mazo
        this.humanDeckViewModel.initializeOwnerForCards(humanPlayer);
        this.computerDeckViewModel.initializeOwnerForCards(computerPlayer);

        handler = new Handler();

    }

    public Player getHumanPlayer() {
        return humanPlayer;
    }

    public Player getComputerPlayer() {
        return computerPlayer;
    }

    public void startNewGame() {
        boardViewModel.clearBoard();
        humanDeckViewModel.resetDeck();
        computerDeckViewModel.resetDeck();
        humanPlayer.setPoints(0);
        computerPlayer.setPoints(0);
    }

    public void playCard(Player player, int row, int col) {
        if (isGameOver()) {
            Player winner = getWinner();
            if (winner != null) {
                //TODO: DA1. Funcionar amb valors de strings.
                Toast.makeText(context, context.getString(R.string.the_winner_is) + winner.getName(), Toast.LENGTH_LONG).show();
            } else {
                //TODO: DA1. Funcionar amb valors de strings.
                Toast.makeText(context, context.getString(R.string.draw), Toast.LENGTH_LONG).show();

            }
            return;
        }
        Log.d(TAG,"Playing the card to position ("+ row+","+col+").");
        int randomTime = getRandomTimeToPlay();
        if (player == humanPlayer) {
            if (!isHumanPlayerTurn()) {
                //TODO: DA1. Funcionar amb valors de strings.
                Toast.makeText(context, context.getString(R.string.not_turn), Toast.LENGTH_SHORT).show();
                return;
            }
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
        }
        else if (player == computerPlayer) {
            handler.postDelayed(() -> {
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
            }, randomTime* 1000L); // Random time between 1 and 3 seconds to play the computer.
        }
    }

    private int getRandomTimeToPlay() {
        Random random = new Random();
        return random.nextInt(3)+1;
    }

    private Player getWinner() {
        int humanPoints = humanPlayer.getPoints().getValue();
        int computerPoints = computerPlayer.getPoints().getValue();

        if (humanPoints > computerPoints) {
            return humanPlayer;
        } else if (humanPoints < computerPoints) {
            return computerPlayer;
        } else {
            return null; // Empate
        }
    }


    public boolean isGameOver() {
        boolean gameOver = boardViewModel.isBoardFull() || humanDeckViewModel.isDeckEmpty() || computerDeckViewModel.isDeckEmpty();

        if (gameOver) {
            Player winner = getWinner();
            if (winner == humanPlayer) {
                showWinnerFragment(winner.getName());
                incrementWinCount();
            } else if(winner == computerPlayer){
                showWinnerFragment(winner.getName());
            }else {
                Toast.makeText(context, context.getString(R.string.draw), Toast.LENGTH_LONG).show();
            }
        }

        return gameOver;
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

    public boolean isHumanPlayerTurn() {
        return currentPlayer == humanPlayer;
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

    private void showWinnerFragment(String winnerName) {
        int humanPoints = getHumanPlayerPoints();
        int computerPoints = getComputerPlayerPoints();
        String result = humanPoints + "-" + computerPoints;

        WinnerFragment winnerFragment = WinnerFragment.newInstance(winnerName, result);
        gameActivityInterface.getSupportManager()
                .beginTransaction()
                .replace(R.id.boardFragment, winnerFragment)
                .commit();
    }
    public void incrementWinCount() {
        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        DatabaseReference userGameDataRef = FirebaseDatabase.getInstance().getReference("winner_count").child(userId);

        userGameDataRef.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                Long count = mutableData.getValue(Long.class);
                if (count == null) {
                    mutableData.setValue(1);
                } else {
                    mutableData.setValue(count + 1);
                }

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
                if (committed) {
                    Log.d(TAG, "Transacción completada con éxito + 1 win");
                } else {
                    Log.e(TAG, "Error en la transacción", error.toException());
                }
            }
        });
    }

    public BoardViewModel getBoard() {
        return boardViewModel;
    }

    public void setBoard(BoardViewModel board) {
        this.boardViewModel = board;
    }
}
