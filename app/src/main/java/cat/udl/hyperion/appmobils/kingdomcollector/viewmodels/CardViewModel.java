package cat.udl.hyperion.appmobils.kingdomcollector.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.models.Card;

public class CardViewModel extends ViewModel {
    private final MutableLiveData<List<Card>> cardsLiveData;


    public CardViewModel() {
        cardsLiveData = new MutableLiveData<>();
        List<Card> presidentCards = new ArrayList<>();


        presidentCards.add(new Card(1,"Ibai Llanos", R.drawable.ibai,"President"));
        presidentCards.add(new Card(2,"Iker Casillas", R.drawable.iker_casillas,"President"));
        presidentCards.add(new Card(3,"The Gref", R.drawable.thegref,"President"));
        presidentCards.add(new Card(4,"Kun Aguero", R.drawable.kun_aguero,"President"));
        presidentCards.add(new Card(5,"Juan Guarnizo", R.drawable.juan_guarnizo,"President"));
        presidentCards.add(new Card(6,"Rivers President", R.drawable.rivers_president,"President"));
        presidentCards.add(new Card(7,"Perxita", R.drawable.perxita,"President"));
        presidentCards.add(new Card(8,"Spursito", R.drawable.spursito,"President"));
        presidentCards.add(new Card(9,"Hermanos Buyer", R.drawable.hermanos_buyer,"President"));
        presidentCards.add(new Card(10,"DjMario", R.drawable.djmario,"President"));
        presidentCards.add(new Card(11,"Adri Contreras", R.drawable.adri_contreras,"President"));
        presidentCards.add(new Card(12,"Gerard Romero", R.drawable.gerard_romero,"President"));
        cardsLiveData.setValue(presidentCards);
    }

    public LiveData<List<Card>> getCardsLiveData() {
        return cardsLiveData;
    }


}
