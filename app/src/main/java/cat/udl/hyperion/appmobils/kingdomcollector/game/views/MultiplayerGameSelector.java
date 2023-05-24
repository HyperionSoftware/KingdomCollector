package cat.udl.hyperion.appmobils.kingdomcollector.game.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.game.adapters.MultiplayerMatchesAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.game.provider.MultiplayerMatchesProvider;

public class MultiplayerGameSelector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayer_game_selector);

        initRecyclerViews();
    }

    private void initRecyclerViews() {

        MultiplayerMatchesProvider provider = new MultiplayerMatchesProvider();
        MultiplayerMatchesAdapter adapter =  new MultiplayerMatchesAdapter(provider.getLaMevaLlista());
        provider.setAdapter(adapter);
        provider.getFromFirebase();

        RecyclerView rv = findViewById(R.id.rv_matches);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }
}
