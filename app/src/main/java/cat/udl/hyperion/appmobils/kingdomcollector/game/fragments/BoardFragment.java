package cat.udl.hyperion.appmobils.kingdomcollector.game.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import cat.udl.hyperion.appmobils.kingdomcollector.game.adapters.CellAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.databinding.FragmentBoardBinding;
import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.HumanPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.BoardViewModel;

public class BoardFragment extends Fragment {

    private BoardViewModel boardViewModel;
    private GameController gameController;
    private FragmentBoardBinding binding;
    private CellAdapter cellAdapter;

    public static BoardFragment newInstance(GameController gameController) {
        BoardFragment fragment = new BoardFragment();
        fragment.setGameController(gameController);
        return fragment;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        HumanPlayer humanPlayer = (HumanPlayer) gameController.getHumanPlayer();
        cellAdapter = new CellAdapter(gameController, getViewLifecycleOwner(), humanPlayer);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.recyclerView.setAdapter(cellAdapter);

        // Initialize BoardViewModel here
        boardViewModel = new BoardViewModel(gameController);
        binding.setBoardViewModel(boardViewModel);

        // Observe LiveData here
        boardViewModel.getBoardDataChanged().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean != null && aBoolean) {
                // If there are changes on the board, update the RecyclerView
                cellAdapter.notifyDataSetChanged();
                // Reset the boardDataChanged value in the ViewModel
                boardViewModel.setBoardDataChanged(false);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

