package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.LinearLayout;

//import androidx.cardview.widget.CardView;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Board;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;

public class BoardView extends GridLayout {
    private CardView[][] cardViews;

    public BoardView(Context context) {
        super(context);
        init(context);
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setColumnCount(Board.getSize());
        setRowCount(Board.getSize());
        setPadding(10, 10, 10, 10);
        setBackgroundColor(Color.BLACK);
        cardViews = new CardView[Board.getSize()][Board.getSize()];
        createEmptyCardViews(context);
    }

    private void createEmptyCardViews(Context context) {
        for (int i = 0; i < Board.getSize(); i++) {
            for (int j = 0; j < Board.getSize(); j++) {
                CardView cardView = new CardView(context);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(5, 5, 5, 5);
                layoutParams.gravity = Gravity.CENTER;
                cardView.setLayoutParams(layoutParams);
                cardViews[i][j] = cardView;
                addView(cardView);
            }
        }
    }

    public void setBoard(Board board) {
        for (int i = 0; i < Board.getSize(); i++) {
            for (int j = 0; j < Board.getSize(); j++) {
                Card card = board.obtenerCarta(i, j);
                cardViews[i][j].setCard(card);
            }
        }
    }
}
