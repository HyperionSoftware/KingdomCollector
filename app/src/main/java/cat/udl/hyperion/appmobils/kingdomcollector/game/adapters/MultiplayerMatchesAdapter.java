package cat.udl.hyperion.appmobils.kingdomcollector.game.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.MultiplayerMatch;

public class MultiplayerMatchesAdapter extends RecyclerView.Adapter<MultiplayerMatchViewHolder>{

    private List<MultiplayerMatch> listRooms;

    public MultiplayerMatchesAdapter(List<MultiplayerMatch> listRooms){
        this.listRooms = listRooms;
    }

    @NonNull
    @Override
    public MultiplayerMatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View v = li.inflate(R.layout.multiplayer_rv_item, parent, false);
        return new MultiplayerMatchViewHolder(v);
    }

    // para cada item de la lista, se llama a este método.
    @Override
    public void onBindViewHolder(@NonNull MultiplayerMatchViewHolder holder, int position) {
        MultiplayerMatch mm = listRooms.get(position);
        holder.render(mm);
    }

    @Override
    public int getItemCount() {
        return listRooms.size();
    }

}
