package cat.udl.hyperion.appmobils.kingdomcollector.game.adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.HumanPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.CellViewModel;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.databinding.CellLayoutBinding;
import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.CellViewModel;

public class CellAdapter extends RecyclerView.Adapter<CellAdapter.ViewHolder> {

    private final int numCells = 9;
    private final GameController gameController;

    private List<CellViewModel> cellViewModels;
    private final LifecycleOwner lifecycleOwner;
    private HumanPlayer humanPlayer;

    public CellAdapter(GameController gameController, LifecycleOwner lifecycleOwner, HumanPlayer humanPlayer) {
        this.gameController = gameController;
        this.lifecycleOwner = lifecycleOwner;
        this.cellViewModels = gameController.getBoardViewModel().getCellViewModels();
        this.humanPlayer = humanPlayer;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CellLayoutBinding binding;

        public ViewHolder(@NonNull CellLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CellLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.cell_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int row = position / 3;
        int col = position % 3;
        CellViewModel cellViewModel = cellViewModels.get(position);
        holder.binding.setCellViewModel(cellViewModel);
        holder.binding.executePendingBindings();

        // Agregar un observador para escuchar cambios en imageResource
        cellViewModel.getImageResource().observe(lifecycleOwner, integer -> {
            if (integer != null) {
                holder.binding.imageView.setImageResource(integer);
            }
        });

        View.OnClickListener cellClickListener = v -> {
            if (gameController.isCellEmpty(row, col)) {
                gameController.playCard(gameController.getHumanPlayer(),row, col);
            }
        };

        // Agregar un observador para escuchar cambios en Card
        cellViewModel.getCardOwner().observe(lifecycleOwner, owner ->  {
            if (owner != null) {
                Card card = cellViewModel.getCard().getValue();
                holder.binding.imageView.setImageResource(card.getImageResource());
                if (card.getOwner().getName().equals(humanPlayer.getName())) {
                    holder.binding.backgroundView.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    holder.binding.backgroundView.setBackgroundColor(Color.argb(128, 0, 0, 0));
                }
            } else {
                holder.binding.backgroundView.setBackgroundColor(Color.argb(128, 0, 0, 0));
            }
        });




        holder.binding.setCellClickListener(cellClickListener);
    }

    @Override
    public int getItemCount() {
        return numCells;
    }
    public CellViewModel getCellViewModelAt(int row, int col) {
        if (cellViewModels != null) {
            return cellViewModels.get(row * 3 + col);
        } else {
            throw new IllegalStateException("CellViewModels list is not initialized");
        }
    }
}
