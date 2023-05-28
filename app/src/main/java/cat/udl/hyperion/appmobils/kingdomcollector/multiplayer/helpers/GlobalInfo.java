package cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.helpers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GlobalInfo {
    private static final GlobalInfo instance = new GlobalInfo();

    private final String FIREBASE_DB = "https://hyperion-843ad-default-rtdb.firebaseio.com/";

    private GlobalInfo(){
//        return new GlobalInfo();
    }

    public static GlobalInfo getIntance(){
        return instance;
    }


    public String getFIREBASE_DB() {
        return FIREBASE_DB;
    }

    public DatabaseReference getFirebaseGames(){
        String url = this.getFIREBASE_DB();
        FirebaseDatabase database = FirebaseDatabase.getInstance(url);
        return database.getReference("games");
    }

}
