package cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.models;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MultiplayerMatch {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    private String userCreator;

    {
        assert user != null;
        userCreator = user.getDisplayName();
    }

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
