package cat.udl.hyperion.appmobils.kingdomcollector.game.fragments;

import android.os.Bundle;
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
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.HumanPlayer;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.PlayerViewModel;


public class PlayerFragment extends Fragment {

    private PlayerViewModel playerViewModel;
    private FragmentPlayerBinding binding;

    public static PlayerFragment newInstance(HumanPlayer humanPlayer) {
        PlayerFragment playerFragment = new PlayerFragment();
        Bundle args = new Bundle();
        args.putSerializable("humanPlayer", humanPlayer);
        playerFragment.setArguments(args);
        return playerFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false);

        // Recuperar el objeto HumanPlayer de los argumentos del Fragment
        HumanPlayer humanPlayer = null;
        if (getArguments() != null) {
            humanPlayer = (HumanPlayer) getArguments().getSerializable("humanPlayer");
        }

        // Pasar el objeto HumanPlayer al constructor de PlayerViewModel
        playerViewModel = new PlayerViewModel(humanPlayer);
        binding.setPlayerViewModel(playerViewModel);
        binding.setLifecycleOwner(this);

        playerViewModel.getPlayerName().observe(getViewLifecycleOwner(), name -> {
            binding.setPlayerViewModel(playerViewModel);
            binding.executePendingBindings();
        });

        playerViewModel.getPlayerPoints().observe(getViewLifecycleOwner(), points -> {
            binding.setPlayerViewModel(playerViewModel);
            binding.executePendingBindings();
        });

        return binding.getRoot();
    }


}
