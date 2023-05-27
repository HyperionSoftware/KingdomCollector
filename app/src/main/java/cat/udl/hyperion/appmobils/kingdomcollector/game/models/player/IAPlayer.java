package cat.udl.hyperion.appmobils.kingdomcollector.game.models.player;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import java.io.Serializable;
import java.util.Random;

import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Deck;

public class IAPlayer extends Player implements Serializable {
    public IAPlayer(String name) {
        super(name);
    }

    @Override
    public void playTurn(GameController gameController) {
        // Implementa la lógica para que la IA juegue un turno (colocar una carta en el tablero de forma aleatoria)
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!gameController.isCellEmpty(row, col));

        // Aquí también se debe seleccionar una carta al azar del mazo de la IA.
        int randomCardIndex = random.nextInt(gameController.getComputerDeckViewModel().getDeckSize());
        gameController.getComputerDeckViewModel().setSelectedCard(gameController.getComputerDeckViewModel().getCardAtIndex(randomCardIndex));

        gameController.playCard(this, row, col);
        Log.d("IAPlayer", "IA turn: Placed card at row " + row + ", col " + col);
    }

    @Override
    public void setDeck(ObservableField<Deck> deck) {
        super.setDeck(deck);
    }

    @Override
    public void setPoints(MutableLiveData<Integer> points) {
        super.setPoints(points);
    }

    @Override
    public void setDeck(Deck deck) {
        super.setDeck(deck);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setPoints(int points) {
        super.setPoints(points);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Deck getDeck() {
        return super.getDeck();
    }

    @Override
    public MutableLiveData<Integer> getPoints() {
        return super.getPoints();
    }

    @Override
    public ObservableField<Deck> getDeckField() {
        return super.getDeckField();
    }

}