package cat.udl.hyperion.appmobils.kingdomcollector.game.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.game.views.GameActivity;
import cat.udl.hyperion.appmobils.kingdomcollector.other.MainActivity;

public class WinnerFragment extends Fragment {
    private static final String ARG_WINNER_NAME = "winnerName";
    private static final String ARG_RESULT = "result";

    public static WinnerFragment newInstance(String winnerName, String result) {
        WinnerFragment fragment = new WinnerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_WINNER_NAME, winnerName);
        args.putString(ARG_RESULT, result);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_winner, container, false);

        // Recuperar el nombre del ganador y el resultado final
        String winnerName = getArguments().getString(ARG_WINNER_NAME);
        String result = getArguments().getString(ARG_RESULT);

        // Mostrar el nombre del ganador y el resultado final
        TextView tvResult = view.findViewById(R.id.tvResult);
        tvResult.setText("El ganador es " + winnerName + ". Resultado final: " + result);

        // Button para volver a la pantalla principal
        ImageButton btnHome = view.findViewById(R.id.btn_return_home);
        btnHome.setOnClickListener(v -> goToHome());

        // Button para volver a jugar "play again"
        ImageButton btnPlayAgain = view.findViewById(R.id.btn_play_again);
        btnPlayAgain.setOnClickListener(v-> playAgain());
        return view;
    }

    private void playAgain() {
        Intent intent = new Intent(getActivity(), GameActivity.class);
        getActivity().finish();  // Finaliza la actividad actual
        startActivity(intent);  // Inicia una nueva instancia de GameActivity
    }

    private void goToHome() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().finish();
        startActivity(intent);
    }
}


