package cat.udl.hyperion.appmobils.kingdomcollector.game.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import cat.udl.hyperion.appmobils.kingdomcollector.game.adapters.CellAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.databinding.FragmentBoardBinding;
import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Crear una instancia de BoardViewModel con el GameController
        boardViewModel = new BoardViewModel(gameController);

        // Agregar un observador para escuchar cambios en el BoardViewModel
        boardViewModel.getBoardDataChanged().observe(this, aBoolean -> {
            if (aBoolean != null && aBoolean) {
                // Si hay cambios en el tablero, actualizar el RecyclerView
                cellAdapter.notifyDataSetChanged();
                // Restablecer el valor de boardDataChanged en el ViewModel
                boardViewModel.setBoardDataChanged(false);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        binding.setBoardViewModel(boardViewModel);

        cellAdapter = new CellAdapter(gameController, getViewLifecycleOwner());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.recyclerView.setAdapter(cellAdapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
