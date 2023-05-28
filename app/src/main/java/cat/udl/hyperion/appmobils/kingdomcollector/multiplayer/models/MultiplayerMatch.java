package cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.models;

import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.PlayerViewModel;

public class MultiplayerMatch {

    private String userCreator;
    private PlayerViewModel userplayer;
    private int status = 1;

    public MultiplayerMatch() {
        // Firebase necesita un constructor vacío
    }

    public MultiplayerMatch(String user){
        this.userCreator = user;
    }

    public String getUserCreator() {
        //userCreator = userplayer.getHumanPlayerName().getValue();
        return userCreator;
    }

    public void setUserCreator(String userCreator) {
        this.userCreator = userCreator;
    }

    public PlayerViewModel getUserplayer() {
        return userplayer;
    }

    public void setUserplayer(PlayerViewModel userplayer) {
        this.userplayer = userplayer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
