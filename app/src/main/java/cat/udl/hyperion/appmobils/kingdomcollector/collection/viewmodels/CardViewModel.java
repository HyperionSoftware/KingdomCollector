package cat.udl.hyperion.appmobils.kingdomcollector.collection.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;

public class CardViewModel extends ViewModel {
    private final MutableLiveData<List<Card>> cardsLiveData;


    public CardViewModel() {
        cardsLiveData = new MutableLiveData<>();
        List<Card> presidentCards = new ArrayList<>();


        presidentCards.add(new Card(1,"Ibai Llanos", R.drawable.ibai,"President",9,8,9,8));
        presidentCards.add(new Card(2,"Iker Casillas", R.drawable.iker_casillas,"President",7,7,7,7));
        presidentCards.add(new Card(3,"The Gref", R.drawable.thegref,"President",6,5,4,7));
        presidentCards.add(new Card(4,"Kun Aguero", R.drawable.kun_aguero,"President",8,4,5,8));
        presidentCards.add(new Card(5,"Juan Guarnizo", R.drawable.juan_guarnizo,"President",9,6,3,2));
        presidentCards.add(new Card(6,"Rivers President", R.drawable.rivers_president,"President",8,9,8,9));
        presidentCards.add(new Card(7,"Perxita", R.drawable.perxita,"President",5,6,5,6));
        presidentCards.add(new Card(8,"Spursito", R.drawable.spursito,"President",2,3,2,3));
        presidentCards.add(new Card(9,"Hermanos Buyer", R.drawable.hermanos_buyer,"President",4,4,5,3));
        presidentCards.add(new Card(10,"DjMario", R.drawable.djmario,"President",8,9,8,9));
        presidentCards.add(new Card(11,"Adri Contreras", R.drawable.adri_contreras,"President",4,5,6,3));
        presidentCards.add(new Card(12,"Gerard Romero", R.drawable.gerard_romero,"President",1,2,3,6));
        cardsLiveData.setValue(presidentCards);
    }

    public LiveData<List<Card>> getCardsLiveData() {
        return cardsLiveData;
    }


}
