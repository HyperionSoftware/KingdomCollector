package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

import androidx.databinding.ObservableField;
import java.util.HashMap;
import java.util.Map;

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

    // Método para convertir la celda a un mapa
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        // Almacenamos la fila y la columna de la celda
        result.put("row", this.row);
        result.put("col", this.col);
        // Si hay una carta en la celda, la almacenamos también
        if (this.getCard() != null) {
            result.put("card", this.getCard().toMap());
        }
        return result;
    }

    // Constructor para crear una celda a partir de un mapa
    public Cell(Map<String, Object> map) {
        // Recuperamos la fila y la columna de la celda
        this.row = ((Long) map.get("row")).intValue();
        this.col = ((Long) map.get("col")).intValue();
        this.card = new ObservableField<>();
        // Si hay una carta en el mapa, la recuperamos y la establecemos en la celda
        if (map.containsKey("card")) {
            this.card.set(new Card((Map<String, Object>) map.get("card")));
        }
    }

    public boolean hasCard() {
        if(this.getCard()!=null){
            return true;
        } else return false;
    }
}
