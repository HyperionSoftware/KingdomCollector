package cat.udl.hyperion.appmobils.kingdomcollector.game.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.io.Serializable;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.databinding.FragmentPlayerBinding;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.Player;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.PlayerViewModel;

public class PlayerFragment extends Fragment {

    private PlayerViewModel playerViewModel;
    private FragmentPlayerBinding binding;

    public static PlayerFragment newInstance(Player humanPlayer, Player robotPlayer) {
        PlayerFragment playerFragment = new PlayerFragment();
        Bundle args = new Bundle();
        args.putSerializable("humanPlayer", humanPlayer);
        args.putSerializable("robotPlayer", robotPlayer);
        playerFragment.setArguments(args);
        return playerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false);

        // Recuperar objetos de HumanPlayer y RobotPlayer de los argumentos del Fragment
        Player humanPlayer = null;
        Player robotPlayer = null;
        if (getArguments() != null) {
            humanPlayer = (Player) getArguments().getSerializable("humanPlayer");
            robotPlayer = (Player) getArguments().getSerializable("robotPlayer");
        }

        // Pasar los objetos HumanPlayer y RobotPlayer al constructor de PlayerViewModel
        playerViewModel = new PlayerViewModel(humanPlayer, robotPlayer);
        binding.setPlayerViewModel(playerViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        // Observar y actualizar la vista cuando los datos del ViewModel cambien
        playerViewModel.getHumanPlayerName().observe(getViewLifecycleOwner(), name -> {
            binding.setPlayerViewModel(playerViewModel);
            binding.executePendingBindings();
            Log.d("PlayerFragment", "Human player name changed: " + name);
        });

        playerViewModel.getHumanPlayerPoints().observe(getViewLifecycleOwner(), points -> {
            binding.setPlayerViewModel(playerViewModel);
            binding.executePendingBindings();
            Log.d("PlayerFragment", "Human player points changed: " + points);
        });

        playerViewModel.getRobotPlayerName().observe(getViewLifecycleOwner(), name -> {
            binding.setPlayerViewModel(playerViewModel);
            binding.executePendingBindings();
            Log.d("PlayerFragment", "Robot player name changed: " + name);
        });

        playerViewModel.getRobotPlayerPoints().observe(getViewLifecycleOwner(), points -> {
            binding.setPlayerViewModel(playerViewModel);
            binding.executePendingBindings();
            Log.d("PlayerFragment", "Robot player points changed: " + points);
        });

        return binding.getRoot();
    }
}
