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
import androidx.recyclerview.widget.LinearLayoutManager;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.game.adapters.CardAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.databinding.FragmentDeckBinding;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.DeckViewModel;

public class DeckFragment extends Fragment {

    private DeckViewModel deckViewModel;
    private FragmentDeckBinding binding;
    private CardAdapter cardAdapter;

    public static DeckFragment newInstance(DeckViewModel humanDeckViewModel) {
        DeckFragment deckFragment = new DeckFragment();
        deckFragment.setDeckViewModel(humanDeckViewModel);
        return deckFragment;
    }

    public void setDeckViewModel(DeckViewModel deckViewModel) {
        this.deckViewModel = deckViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_deck, container, false);

        binding.setDeckViewModel(deckViewModel);
        binding.setLifecycleOwner(this);

        cardAdapter = new CardAdapter(deckViewModel.getDeck().getValue().getCards(), card -> deckViewModel.setSelectedCard(card));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerView.setAdapter(cardAdapter);

        deckViewModel.getDeck().observe(getViewLifecycleOwner(), deck -> {
            cardAdapter.notifyDataSetChanged();
            Log.d("DeckFragment", "Deck data changed");
        });

        return binding.getRoot();
    }
}
