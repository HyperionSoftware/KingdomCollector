package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Player.Player;

public class PlayerView extends Fragment {

    private TextView playerName;
    private TextView playerScore;
    private TextView playerNumberOfCards;

    private Player player;

    public PlayerView() {
        // Required empty public constructor
    }

    public static PlayerView newInstance() {
        PlayerView fragment = new PlayerView();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);

        playerName = view.findViewById(R.id.player_name);
        playerScore = view.findViewById(R.id.player_score);
        playerNumberOfCards = view.findViewById(R.id.player_number_of_cards);

        return view;
    }

    public void setPlayer(Player player) {
        this.player = player;
        updatePlayerView();
    }

    private void updatePlayerView() {
        if (player != null) {
            playerName.setText(player.getName());
            playerScore.setText("Score: " + player.getScore());
            playerNumberOfCards.setText("Cards: " + player.getNumberOfCards());
        }
    }
}
