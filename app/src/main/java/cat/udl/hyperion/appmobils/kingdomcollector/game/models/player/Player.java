package cat.udl.hyperion.appmobils.kingdomcollector.game.models.player;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;


import java.io.Serializable;

import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Deck;
import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;

public abstract class Player implements Serializable, Parcelable {
    private String name;
    private ObservableField<Deck> deck;
    private MutableLiveData<Integer> points;

    public Player(String name){
        this.name = name;
        this.points = new MutableLiveData<>(0);
        deck = new ObservableField<>(new Deck());
        deck.get().initializeDeck();
    }
    public abstract void playTurn(GameController gameController);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getDeck() {
        return deck.get();
    }

    public void setDeck(Deck deck) {
        this.deck.set(deck);
    }

    public ObservableField<Deck> getDeckField() {
        return deck;
    }

    public MutableLiveData<Integer> getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points.setValue(points);
    }


    public void addCardToDeck(Card card) {
        Deck currentDeck = deck.get();
        currentDeck.agregarCarta(card);
        deck.set(currentDeck);
    }

    public void removeCardFromDeck(Card card) {
        Deck currentDeck = deck.get();
        currentDeck.eliminarCarta(card);
        deck.set(currentDeck);
    }

    public void shuffleDeck() {
        Deck currentDeck = deck.get();
        currentDeck.barajar();
        deck.set(currentDeck);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(points.getValue());
        dest.writeParcelable(deck.get(), flags);
    }
    // Nuevo constructor para leer de un Parcel
    protected Player(Parcel in) {
        name = in.readString();
        points = new MutableLiveData<>(in.readInt());
        deck = new ObservableField<>(in.readParcelable(Deck.class.getClassLoader()));
    }
    // Creador Parcelable necesario
    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in) {
                @Override
                public void playTurn(GameController gameController) {
                    // Implementación de playTurn va aquí. Puedes dejarla vacía si Player es una clase abstracta.
                }
            };
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

}