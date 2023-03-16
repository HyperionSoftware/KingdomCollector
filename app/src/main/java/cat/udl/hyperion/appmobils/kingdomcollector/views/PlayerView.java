package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Player.Player;
import cat.udl.hyperion.appmobils.kingdomcollector.R;

public class PlayerView extends LinearLayout {
    private TextView playerNameTextView;
    private TextView playerScoreTextView;
    private TextView playerNumberOfCardsTextView;

    public PlayerView(Context context) {
        super(context);
        init(context);
    }

    public PlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.player_view, this);

        playerNameTextView = findViewById(R.id.player_name);
        playerScoreTextView = findViewById(R.id.player_score);
        playerNumberOfCardsTextView = findViewById(R.id.player_number_of_cards);
    }

    public void setPlayer(Player player) {
        playerNameTextView.setText(player.getName());
        playerScoreTextView.setText(String.valueOf(player.getScore()));
        playerNumberOfCardsTextView.setText(String.valueOf(player.getNumberOfCards()));
    }
}
