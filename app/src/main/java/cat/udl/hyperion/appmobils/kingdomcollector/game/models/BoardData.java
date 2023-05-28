package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.game.GameController;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.BoardViewModel;
import cat.udl.hyperion.appmobils.kingdomcollector.game.viewmodels.CellViewModel;

public class BoardData {
    private List<Card> cards;
    private List<Cell> cells;

    public BoardData(){

    }
    // Constructor, getters y setters...
    public BoardData(List<Card> cards, List<Cell> cells){
        this.cards = cards;
        this.cells = cells;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public BoardViewModel toBoardViewModel(GameController gameController) {
        BoardViewModel boardViewModel = new BoardViewModel(gameController);
        // Asigna las tarjetas a las celdas correspondientes en el ViewModel
        if (cards.size() == cells.size()) { // asegúrate de que cada tarjeta tiene una celda correspondiente
            for (int i = 0; i < cards.size(); i++) {
                Card card = cards.get(i);
                Cell cell = cells.get(i);

                // Busca el CellViewModel correspondiente en el BoardViewModel
                CellViewModel cellViewModel = boardViewModel.getCellViewModelAt(cell.getRow(), cell.getCol());
                // Coloca la tarjeta en el CellViewModel
                cellViewModel.setCard(card);
            }
        } else {
            throw new IllegalStateException("El número de tarjetas y celdas no coincide.");
        }

        return boardViewModel;
    }




}
