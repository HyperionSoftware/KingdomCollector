package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

public class MultiplayerMatch {
    public String getUserCreator() {
        return userCreator;
    }

    String userCreator;
    int status = 1;

    public MultiplayerMatch(String user){
        this.userCreator = user;
    }
}
