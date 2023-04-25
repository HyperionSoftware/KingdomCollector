package cat.udl.hyperion.appmobils.kingdomcollector.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.CardCollection;

public class CardViewModel extends ViewModel {
    private final MutableLiveData<List<CardCollection>> cardsLiveData;
    private final MutableLiveData<List<CardCollection>> selectedCards = new MutableLiveData<>();


    public CardViewModel() {
        cardsLiveData = new MutableLiveData<>();
        List<CardCollection> presidentCards = new ArrayList<>();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference ibaiRef = storageRef.child("https://firebasestorage.googleapis.com/v0/b/hyperion-843ad.appspot.com/o/ibai_president.png?alt=media&token=6ccad262-d734-4b1e-ad66-4ea5f1591f74");
        String ibaiImageUrl = ibaiRef.getDownloadUrl().toString();


        presidentCards.add(new CardCollection(1,"Ibai Llanos", ibaiImageUrl, "Porcinos President","President",9,8,9,8));
        presidentCards.add(new CardCollection(2, "Iker Casillas", "https://firebasestorage.googleapis.com/v0/b/my-app.appspot.com/o/images%2Fiker_casillas.png?alt=media&token=5678", "1K President","President",9,9,7,8));
        presidentCards.add(new CardCollection(3,"Samantha Treviño", "https://firebasestorage.googleapis.com/v0/b/my-app.appspot.com/o/images%2Frivers.png?alt=media&token=abcd", "Rivers President","President",9,4,9,9));
        presidentCards.add(new CardCollection(4,"Juan Guarnizo", "https://firebasestorage.googleapis.com/v0/b/my-app.appspot.com/o/images%2Fjuan_guarnizo.png?alt=media&token=efgh", "Aniquiladores President","President",9,9,9,9));
        presidentCards.add(new CardCollection(5,"Kun Agüero", "https://firebasestorage.googleapis.com/v0/b/my-app.appspot.com/o/images%2Fkun.png?alt=media&token=ijkl", "Kunisports President","President",9,9,9,9));
        presidentCards.add(new CardCollection(6,"The Grefg", "https://firebasestorage.googleapis.com/v0/b/my-app.appspot.com/o/images%2Fgref.png?alt=media&token=mnop", "Saiyans FC President","President",9,8,8,9));
        cardsLiveData.setValue(presidentCards);
    }

    public LiveData<List<CardCollection>> getCardsLiveData() {
        return cardsLiveData;
    }

    public void selectCard(CardCollection card) {
        List<CardCollection> cards = selectedCards.getValue();
        cards.add(card);
        selectedCards.setValue(cards);
    }

    public void deselectCard(CardCollection card) {
        List<CardCollection> cards = selectedCards.getValue();
        cards.remove(card);
        selectedCards.setValue(cards);
    }

    public MutableLiveData<List<CardCollection>> getSelectedCards() {
        return selectedCards;
    }
}
