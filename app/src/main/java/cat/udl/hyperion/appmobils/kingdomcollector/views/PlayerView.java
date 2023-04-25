package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Player.Player;
import cat.udl.hyperion.appmobils.kingdomcollector.databinding.FragmentPlayerBinding;

public class PlayerView extends Fragment {

    private FragmentPlayerBinding binding;
    private Player player;
    private static FirebaseAuth mAuth;
    protected String myClassTag = this.getClass().getSimpleName();


    public PlayerView() {
        // Required empty public constructor
        updatePlayerView();
    }

    public static PlayerView newInstance() {
        PlayerView fragment = new PlayerView();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlayerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        // ViewModel y el LifecycleOwner para un futuro por si acaso lo dejo aquí.
        // binding.setViewModel(viewModel);
        // binding.setLifecycleOwner(getViewLifecycleOwner());

        return view;
    }

    public void setPlayer(Player player) {
        this.player = player;
        updatePlayerView();
    }

    private void updatePlayerView() {
        if (player != null) {
            binding.playerName.setText(player.getName());
            binding.playerScore.setText("Score: " + player.getScore());
            binding.playerNumberOfCards.setText("Cards: " + player.getNumberOfCards());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
