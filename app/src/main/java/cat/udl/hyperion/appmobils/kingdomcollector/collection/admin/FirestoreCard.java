package cat.udl.hyperion.appmobils.kingdomcollector.collection.admin;

import android.util.Log;

public class FirestoreCard {
    private static final String TAG = "FirestoreCard";
    private String id;

    public FirestoreCard(String id){
        this.id = id;
        Log.d(TAG, "FirestoreCard iniciado con id: " + id + ".");

    }
    public FirestoreCard(){
        //Constructor buit per firebase.
        Log.d(TAG, "FirestoreCard iniciado con constructor vacío.");
    }

    public String getId() {
        Log.v(TAG, "getId llamado, devolviendo: " + id + ".");
        return id;
    }
    public void setId(String id) {
        Log.v(TAG, "setId llamado, estableciendo id: " + id + ".");
        this.id = id;
    }
}
