package cat.udl.hyperion.appmobils.kingdomcollector.game.models.player;

import java.io.Serializable;
import java.util.Random;

import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;

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
    }
}
