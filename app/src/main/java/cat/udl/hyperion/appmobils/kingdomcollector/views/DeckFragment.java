package cat.udl.hyperion.appmobils.kingdomcollector.views;

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
import cat.udl.hyperion.appmobils.kingdomcollector.databinding.FragmentDeckBinding;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.DeckViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.views.DeckView;

public class DeckFragment extends Fragment {
    private DeckViewModel deckViewModel;
    private DeckView deckView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentDeckBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_deck, container, false);
        deckViewModel = new ViewModelProvider(this).get(DeckViewModel.class);
        binding.setDeckViewModel(deckViewModel);
        binding.setLifecycleOwner(this);
        View view = binding.getRoot();

        deckView = view.findViewById(R.id.deck_view_container);
        if (deckView instanceof DeckView) {
            ((DeckView) deckView).setDeckViewModel(deckViewModel);
        }
        return view;
    }
}

