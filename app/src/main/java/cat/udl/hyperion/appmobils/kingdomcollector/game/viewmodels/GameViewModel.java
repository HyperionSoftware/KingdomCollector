package cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cat.udl.hyperion.appmobils.kingdomcollector.game.helpers.GlobalInfo;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Game;

public class GameViewModel {

    protected String myClassTag = this.getClass().getSimpleName();


    private final MutableLiveData<Game> game = new MutableLiveData<>();

    private DatabaseReference myRef;
    private DatabaseReference myFirebaseDBReference;

    private Integer myMultiplayerPlayerType;



    public GameViewModel(){
        Game internalGame = new Game();
        internalGame.init();
        game.setValue(internalGame);
    }

    private void enableFirebaseDBv2() {
        myFirebaseDBReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Board b = dataSnapshot.child("board").getValue(Board.class);
                Integer multiplayerTurn = dataSnapshot.child("turn").getValue(Integer.class);
                Game g = game.getValue();
                g.setBoard(b);
                g.setCurrentPlayerMultiplayer(multiplayerTurn);
                game.setValue(g);
            }
            @Override
            public void onCancelled(@NotNull DatabaseError error) { // Failed to read value
                Log.w(myClassTag, "Failed to read value.", error.toException());
            }
        });
    }

    /**
     * Crea un game a firebase perquè es connecti algun altre player i l'inicialitza
     */
    public void multiplayerCreate() {
        DatabaseReference myFirebaseDBGames = GlobalInfo.getIntance().getFirebaseGames();
        String key = myFirebaseDBGames.push().getKey();
        myFirebaseDBReference = myFirebaseDBGames.child(key);
        this.myMultiplayerPlayerType = Game.MULTIPLAYER_TYPE_CREATE;

        Map<String, Object> data = new HashMap<>();
        data.put("status", Game.MULTIPLAYER_STATUS_PENDING);
        data.put("board", null);
        data.put("turn", myMultiplayerPlayerType);
        myFirebaseDBReference.setValue(data);

        game.getValue().setCurrentPlayerMultiplayer(myMultiplayerPlayerType);

        enableFirebaseDBv2();
        updateFirebaseDBv2();
    }

    /**
     * Connecta amb un game de firebase el bloqueja com a ja matchat i comença el joc
     */
    public void multiplayerConnect(String gameKey) {
        DatabaseReference games = GlobalInfo.getIntance().getFirebaseGames();

//        setting my config
        myMultiplayerPlayerType = Game.MULTIPLAYER_TYPE_CONNECT;
        myFirebaseDBReference = games.child(gameKey);

        myFirebaseDBReference.child("status").setValue(Game.MULTIPLAYER_STATUS_MATCHED);
        // TODO millora: idealment hauriem d'agafar el turn del Firebase
        game.getValue().setCurrentPlayerMultiplayer(Game.MULTIPLAYER_TYPE_CREATE);

        enableFirebaseDBv2();
    }

    /**
     * Si es un joc multiplayer, actualitza el board i el turn
     */
    private void updateFirebaseDBv2() {
        if (myFirebaseDBReference != null){
            Game g = game.getValue();
            myFirebaseDBReference.child("board").setValue(g.getBoard());
            myFirebaseDBReference.child("turn").setValue(g.getCurrentPlayerMultiplayer());
        }

    }
}
