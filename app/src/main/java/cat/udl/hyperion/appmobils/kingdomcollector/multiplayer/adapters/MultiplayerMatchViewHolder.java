package cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.adapters;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.helpers.GlobalInfo;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.models.MultiplayerMatch;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.views.GameOnlineActivity;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.views.MultiplayerGameSelector;

public class MultiplayerMatchViewHolder extends RecyclerView.ViewHolder{


    View itemView;
    TextView username;


    public MultiplayerMatchViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.username = itemView.findViewById(R.id.rv_item_username);
        itemView.findViewById(R.id.item_rv).setOnClickListener(v -> jumpToGame());
    }

    private void jumpToGame() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userName = user.getDisplayName();
        //GameOnlineActivity gameOnlineActivity = new GameOnlineActivity();
        //gameOnlineActivity.joinGame( username.getText().toString(), String.valueOf(itemView.getContext()));
        MultiplayerGameSelector multiplayerGameSelector = new MultiplayerGameSelector();
        multiplayerGameSelector.joinGamecarlos( username.getText().toString(), userName);

        Intent intent = new Intent(itemView.getContext(), GameOnlineActivity.class);
        intent.putExtra("username", username.getText().toString()); // el que crea la partida no puede hacer match con el mismo, se puede quitar.
        intent.putExtra( "userCreator", username.getText().toString());
        itemView.getContext().startActivity(intent);

    }

    public void render(MultiplayerMatch mm) {
        username.setText(mm.getUserCreator());
    }

}
