package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.DeckViewModel;

public class DeckView extends LinearLayout {
    private DeckViewModel deckViewModel;

    public DeckView(Context context) {
        super(context);
    }

    public DeckView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DeckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDeckViewModel(DeckViewModel deckViewModel) {
        this.deckViewModel = deckViewModel;
        // Configure the view with the ViewModel here
    }
}
