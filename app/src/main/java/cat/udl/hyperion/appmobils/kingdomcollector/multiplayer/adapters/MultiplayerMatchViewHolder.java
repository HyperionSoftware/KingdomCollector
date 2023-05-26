package cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.models.MultiplayerMatch;

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



    }

    public void render(MultiplayerMatch mm) {
        username.setText(mm.getUserCreator());
    }

}
