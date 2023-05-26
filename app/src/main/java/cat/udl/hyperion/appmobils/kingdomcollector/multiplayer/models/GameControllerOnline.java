package cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.HumanPlayer;

@Entity
public class GameControllerOnline {

    @Ignore
    protected String myClassTag = this.getClass().getSimpleName();

    @PrimaryKey(autoGenerate = true)
    public long id;

    public static final int MULTIPLAYER_STATUS_PENDING = 1;
    public static final int MULTIPLAYER_STATUS_MATCHED = 2;
    public static final int MULTIPLAYER_TYPE_CREATE = 1;
    public static final int MULTIPLAYER_TYPE_CONNECT = 2;

    public static final String MULTIPLAYER_KEY = "multiplayer-key";
    public static final String MULTIPLAYER_GAME_KEY = "multiplayer-game-key";

    @Ignore
    private HumanPlayer player1;

    @Ignore
    private HumanPlayer player2;

    @Ignore
    private HumanPlayer currentPlayer;

    @Ignore
    private Integer currentPlayerMultiplayer;

    @Ignore
    private HumanPlayer winner = null;

    @Ignore
    int card1SelectedRow = -1;
    @Ignore
    int card1SelectedColumn = -1;

    @Ignore
    Card card2Selected;

    @Ignore
    Board board;

    public void init(){
        board = new Board();

        player1 = new HumanPlayer("Player 1");
        player2 = new HumanPlayer("Player 2");
        currentPlayer = player1;

    }

    public void setBoard(Board b) {
        this.board = b;
    }

    public void setCurrentPlayerMultiplayer(Integer turn){
        this.currentPlayerMultiplayer = turn;
    }

    public Integer getCurrentPlayerMultiplayer() {
        return currentPlayerMultiplayer;
    }

    public Object getBoard() {
        return board;
    }
}
