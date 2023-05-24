package cat.udl.hyperion.appmobils.kingdomcollector.game.provider;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.game.adapters.MultiplayerMatchesAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.game.helpers.GlobalInfo;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Game;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.MultiplayerMatch;

public class MultiplayerMatchesProvider {

    MultiplayerMatchesAdapter adapter;
    public List<MultiplayerMatch> getLaMevaLlista() {
        return laMevaLlista;
    }

    List<MultiplayerMatch> laMevaLlista = new ArrayList<>();

    public MultiplayerMatchesProvider(){
        laMevaLlista.add(new MultiplayerMatch("Carlos"));
    }

    public void getFromFirebase(){
        DatabaseReference myFirebaseDBGames = GlobalInfo.getIntance().getFirebaseGames();
        Query q = myFirebaseDBGames.orderByChild("status").equalTo(Game.MULTIPLAYER_STATUS_PENDING);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                refreshData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Provider", "Problemes de connexio");
            }
        });
    }

    private void refreshData(DataSnapshot snapshot){
        laMevaLlista.clear();
        for (DataSnapshot gameSelected : snapshot.getChildren()){
            String key = gameSelected.getKey();
            MultiplayerMatch mm = new MultiplayerMatch(key);
            laMevaLlista.add(mm);
        }
        adapter.notifyItemRangeChanged(0, laMevaLlista.size());
    }

    public void setAdapter(MultiplayerMatchesAdapter adapter) {
        this.adapter = adapter;
    }
}
