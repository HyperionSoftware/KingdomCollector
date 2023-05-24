package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;

public class Game {

    public static final int MULTIPLAYER_STATUS_PENDING = 1;
    public static final int MULTIPLAYER_STATUS_MATCHED = 2;
    public static final int MULTIPLAYER_TYPE_CREATE = 1;
    public static final int MULTIPLAYER_TYPE_CONNECT = 2;

    private Integer currentPlayerMultiplayer;

    Board board;

    GameController gameController;

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
