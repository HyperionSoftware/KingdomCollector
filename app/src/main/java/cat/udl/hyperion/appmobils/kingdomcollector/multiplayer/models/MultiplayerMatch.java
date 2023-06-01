package cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.models;



public class MultiplayerMatch {
    private String userCreator;
    private String userJoiner;
    private int status = 1;

    public MultiplayerMatch() {
        // Firebase necesita un constructor vacío
    }

    public MultiplayerMatch(String userCreator){
        this.userCreator = userCreator;
    }

    public String getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(String userCreator) {
        this.userCreator = userCreator;
    }

    public String getUserJoiner() {
        return userJoiner;
    }

    public void setUserJoiner(String userJoiner) {
        this.userJoiner = userJoiner;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
