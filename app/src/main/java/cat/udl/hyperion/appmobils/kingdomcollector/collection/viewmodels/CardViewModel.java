package cat.udl.hyperion.appmobils.kingdomcollector.collection.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;

public class CardViewModel extends ViewModel {
    private final MutableLiveData<List<Card>> cardsLiveData;


    public CardViewModel() {
        cardsLiveData = new MutableLiveData<>();
        List<Card> presidentCards = new ArrayList<>();
       //TODO: Llamar a db local
        cardsLiveData.setValue(presidentCards);
    }

    public LiveData<List<Card>> getCardsLiveData() {
        return cardsLiveData;
    }


}
