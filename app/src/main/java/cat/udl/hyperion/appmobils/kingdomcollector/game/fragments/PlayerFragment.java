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

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.databinding.FragmentPlayerBinding;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.PlayerViewModel;


public class PlayerFragment extends Fragment {

    private PlayerViewModel playerViewModel;
    private FragmentPlayerBinding binding;

    public static PlayerFragment newInstance() {
        PlayerFragment playerFragment = new PlayerFragment();
        return playerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false);

        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        binding.setPlayerViewModel(playerViewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }
}
