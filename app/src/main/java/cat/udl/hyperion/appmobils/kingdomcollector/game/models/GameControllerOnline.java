package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

public class GameControllerOnline {

    public static final int MULTIPLAYER_STATUS_PENDING = 1;
    public static final int MULTIPLAYER_STATUS_MATCHED = 2;
    public static final int MULTIPLAYER_TYPE_CREATE = 1;
    public static final int MULTIPLAYER_TYPE_CONNECT = 2;

    public static final String MULTIPLAYER_KEY = "multiplayer-key";
    public static final String MULTIPLAYER_GAME_KEY = "multiplayer-game-key";

    private Integer currentPlayerMultiplayer;

    Board board;

    public void init(){
        board = new Board();

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
