package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.CardCollection;
import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.databinding.FragmentBoardBinding;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.BoardViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.DeckViewModel;



public class BoardFragment extends Fragment {

    private Button[][] buttons;
    private BoardViewModel boardViewModel;
    private DeckViewModel deckViewModel;

    private CardCollection selectedCard;


    public BoardFragment() {
        // Required empty public constructor
    }

    public static BoardFragment newInstance() {
        BoardFragment fragment = new BoardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardViewModel = new ViewModelProvider(this).get(BoardViewModel.class);
        deckViewModel = new ViewModelProvider(this).get(DeckViewModel.class);
        deckViewModel.setOnCardSelectedListener(boardViewModel); // Establece BoardViewModel como el listener de selección de cartas en DeckViewModel
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentBoardBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board, container, false);
        binding.setBoardViewModel(boardViewModel);
        binding.setLifecycleOwner(this);
        View view = binding.getRoot();

        buttons = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getActivity().getPackageName());
                buttons[i][j] = view.findViewById(resID);
                int finalI = i;
                int finalJ = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selectedCard != null) { // Comprueba si hay una carta seleccionada
                            boardViewModel.placeCard(finalI, finalJ, selectedCard); // Coloca la carta seleccionada en la posición clicada
                            selectedCard = null; // Limpia la carta seleccionada para que no pueda ser colocada de nuevo
                        }
                    }
                });
            }
        }

        return view;
    }

}
/*public class BoardFragment extends Fragment {
    // ...
    private BoardViewModel boardViewModel;
    private DeckViewModel deckViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardViewModel = new ViewModelProvider(this).get(BoardViewModel.class);
        deckViewModel = new ViewModelProvider(this).get(DeckViewModel.class);
        deckViewModel.setOnCardSelectedListener(boardViewModel); // Establece BoardViewModel como el listener de selección de cartas en DeckViewModel
    }

    // ...
}
*/

