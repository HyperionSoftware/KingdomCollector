package cat.udl.hyperion.appmobils.kingdomcollector.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cat.udl.hyperion.appmobils.kingdomcollector.models.Card;

public class CardSelectedViewModel extends ViewModel {
    private final MutableLiveData<List<Card>> selectedCardsLiveData;

    public CardSelectedViewModel() {
        selectedCardsLiveData = new MutableLiveData<>();
        selectedCardsLiveData.setValue(new ArrayList<>());
    }

    public LiveData<List<Card>> getSelectedCardsLiveData() {
        return selectedCardsLiveData;
    }

    public void setSelectedCardsLiveData(List<Card> cards) {
        selectedCardsLiveData.setValue(cards);
    }

    public void addSelectedCard(Card card) {
        List<Card> selectedCards = new ArrayList<>(Objects.requireNonNull(selectedCardsLiveData.getValue()));
        selectedCards.add(card);
        selectedCardsLiveData.setValue(selectedCards);
    }

    public void removeSelectedCard(Card card) {
        List<Card> selectedCards = new ArrayList<>(Objects.requireNonNull(selectedCardsLiveData.getValue()));
        selectedCards.remove(card);
        selectedCardsLiveData.setValue(selectedCards);
    }

}
