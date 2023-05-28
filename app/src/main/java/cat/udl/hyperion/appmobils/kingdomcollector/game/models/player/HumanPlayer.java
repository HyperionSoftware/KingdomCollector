package cat.udl.hyperion.appmobils.kingdomcollector.game.models.player;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import java.io.Serializable;

import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Deck;

public class HumanPlayer extends Player implements Serializable {
    @Override
    public void playTurn(GameController gameController) {
        // La lógica para jugar el turno de un jugador humano se manejará en la interfaz de usuario
        // No es necesario implementar nada aquí
        // NO TOCAR ESTO PORFAVOR PARA NADA. DEJAR VACÍO.
    }
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public MutableLiveData<Integer> getPoints() {
        return super.getPoints();
    }

    @Override
    public ObservableField<Deck> getDeckField() {
        return super.getDeckField();
    }

    @Override
    public Deck getDeck() {
        return super.getDeck();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setPoints(MutableLiveData<Integer> points) {
        super.setPoints(points);
    }

    @Override
    public void setDeck(ObservableField<Deck> deck) {
        super.setDeck(deck);
    }

    @Override
    public void setPoints(int points) {
        super.setPoints(points);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setDeck(Deck deck) {
        super.setDeck(deck);
    }
}