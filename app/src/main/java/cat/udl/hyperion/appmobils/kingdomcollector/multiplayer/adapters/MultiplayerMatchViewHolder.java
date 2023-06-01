package cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.adapters;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.models.MultiplayerMatch;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.viewmodel.GameViewModel;
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
        //GameOnlineActivity gameOnlineActivity = new GameOnlineActivity();
        //gameOnlineActivity.joinGame( username.getText().toString(), String.valueOf(itemView.getContext()));
        MultiplayerGameSelector multiplayerGameSelector = new MultiplayerGameSelector();
        multiplayerGameSelector.joinGamecarlos( username.getText().toString(), String.valueOf(itemView.getContext()));

        Intent intent = new Intent(itemView.getContext(), GameOnlineActivity.class);
        intent.putExtra("username", username.getText().toString());
        itemView.getContext().startActivity(intent);

    }

    public void render(MultiplayerMatch mm) {
        username.setText(mm.getUserCreator());
    }

}
