package cat.udl.hyperion.appmobils.kingdomcollector.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.CardCollection;
import cat.udl.hyperion.appmobils.kingdomcollector.R;

public class CardViewModel extends ViewModel {
    private final MutableLiveData<List<CardCollection>> cardsLiveData;

    public CardViewModel() {
        cardsLiveData = new MutableLiveData<>();
        List<CardCollection> cardCollections = new ArrayList<>();
        cardCollections.add(new CardCollection("Ibai", R.drawable.ibai_president, "President"));
        cardCollections.add(new CardCollection("Iker", R.drawable.iker_casillas_president, "President"));
        cardCollections.add(new CardCollection("River", R.drawable.rivers_president, "President"));
        cardCollections.add(new CardCollection("Juan", R.drawable.juan_guarnizo_president, "President"));
        cardCollections.add(new CardCollection("Kun", R.drawable.kun, "President"));
        cardCollections.add(new CardCollection("Gref", R.drawable.gref, "President"));
        cardsLiveData.setValue(cardCollections);
    }

    public LiveData<List<CardCollection>> getCardsLiveData() {
        return cardsLiveData;
    }
}
