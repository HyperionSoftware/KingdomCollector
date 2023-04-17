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
    private final MutableLiveData<List<CardCollection>> selectedCards = new MutableLiveData<>();


    public CardViewModel() {
        cardsLiveData = new MutableLiveData<>();
        List<CardCollection> cardCollections = new ArrayList<>();
        cardCollections.add(new CardCollection(1,"Ibai Llanos", R.drawable.ibai_president, "Porcinos President",9,8,9,8));
        cardCollections.add(new CardCollection(2, "Iker Casillas", R.drawable.iker_casillas_president, "1K President",9,9,7,8));
        cardCollections.add(new CardCollection(3,"Samantha Treviño", R.drawable.rivers_president, "Rivers President",9,4,9,9));
        cardCollections.add(new CardCollection(4,"Juan Guarnizo", R.drawable.juan_guarnizo_president, "Aniquiladores President",9,9,9,9));
        cardCollections.add(new CardCollection(5,"Kun Agüero", R.drawable.kun, "Kunisports President",9,9,9,9));
        cardCollections.add(new CardCollection(6,"The Grefg", R.drawable.gref, "Saiyans FC President",9,8,8,9));
        cardsLiveData.setValue(cardCollections);
    }

    public LiveData<List<CardCollection>> getCardsLiveData() {
        return cardsLiveData;
    }

    /*public void selectCard(CardCollection card) {
        List<Card> cards = selectedCards.getValue();
        cards.add(card);
        selectedCards.setValue(cards);
    }*/

    /*public void deselectCard(CardCollection card) {
        List<Card> cards = selectedCards.getValue();
        cards.remove(card);
        selectedCards.setValue(cards);
    }*/

    /*public MutableLiveData<List<Card>> getSelectedCards() {
        return selectedCards;
    }*/
}
