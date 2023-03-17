package cat.udl.hyperion.appmobils.kingdomcollector.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.DeckViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.GameViewModel;

public class GameView extends Fragment {
    private DeckViewModel deckViewModel;
    private DeckView deckView;
    private GameViewModel gameViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_juego, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BoardView boardView = BoardView.newInstance();
        fragmentTransaction.add(R.id.fragment_container, boardView);

        PlayerView playerFragment = new PlayerView();
        fragmentTransaction.replace(R.id.player_fragment_container, playerFragment);
        fragmentTransaction.commit();

        // Aquí puedes inicializar los elementos de la vista y establecer los listeners de los eventos
    }
}
