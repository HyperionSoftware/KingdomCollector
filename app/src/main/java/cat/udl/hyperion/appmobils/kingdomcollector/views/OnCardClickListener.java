package cat.udl.hyperion.appmobils.kingdomcollector.views;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.Models.CardCollection;

public interface OnCardClickListener {
    void onCardSelected(CardCollection card);
    void onCardDeselected(CardCollection card);
}
