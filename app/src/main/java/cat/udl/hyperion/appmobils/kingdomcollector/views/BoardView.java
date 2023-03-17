package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.BoardViewModel;

public class BoardView extends Fragment {

    private Button[][] buttons;
    private BoardViewModel boardViewModel;

    public BoardView() {
        // Required empty public constructor
    }

    public static BoardView newInstance() {
        BoardView fragment = new BoardView();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardViewModel = new ViewModelProvider(this).get(BoardViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);

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
                        // Aquí puedes colocar una carta en el tablero usando el BoardViewModel
                        Card card = new Card(1,"Hola", 2,3,4,5); // Crea una instancia de Card con los valores apropiados
                        boardViewModel.placeCard(finalI, finalJ, card);


                    }
                });
            }
        }

        return view;
    }

}
