package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;

public class CardView extends LinearLayout {
    private TextView textView;
    private Card card;

    public CardView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        setBackgroundResource(android.R.drawable.btn_default);
        setPadding(10, 10, 10, 10);

        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        addView(textView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    public void setCard(Card card) {
        this.card = card;
        if (card != null) {
            String cardInfo = card.getName() + "\n"
                    + "N: " + card.getPowerArriba() + " E: " + card.getPowerDerecha() + "\n"
                    + "S: " + card.getPowerAbajo() + " W: " + card.getPowerIzquierda();
            textView.setText(cardInfo);
        } else {
            textView.setText("");
        }
    }

    public Card getCard() {
        return card;
    }
}
