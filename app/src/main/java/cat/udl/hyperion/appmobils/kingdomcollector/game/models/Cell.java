package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

import androidx.databinding.ObservableField;

public class Cell {
    private ObservableField<Card> card;
    private int row;
    private int col;

    public Cell(int row, int col) {
        this.card = new ObservableField<>();
        this.row = row;
        this.col = col;
    }

    public Card getCard() {
        return card.get();
    }

    public void setCard(Card card) {
        this.card.set(card);
    }

    public ObservableField<Card> getCardField() {
        return card;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void notifyPropertyChanged(int fieldId) {
        card.notifyChange();
    }
}
