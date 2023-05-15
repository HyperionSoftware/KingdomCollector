package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

import androidx.databinding.ObservableArrayMap;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private ObservableArrayMap<String, ObservableArrayMap<String, Cell>> board;
    private static final int numRows = 3;
    private static final int numCols = 3;

    public void saveToFirebase() {
        // Obtén una instancia de la base de datos de Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Crea una referencia a la ubicación donde deseas almacenar el tablero
        DatabaseReference myRef = database.getReference("boards");

        // Convierte tu tablero a una estructura Map que Firebase puede entender
        Map<String, Map<String, Map<String, Object>>> firebaseBoard = new HashMap<>();
        for (String rowKey : board.keySet()) {
            Map<String, Map<String, Object>> row = new HashMap<>();
            for (String cellKey : board.get(rowKey).keySet()) {
                Cell cell = board.get(rowKey).get(cellKey);
                Map<String, Object> firebaseCell = new HashMap<>();
                // Aquí debes agregar los atributos de la celda que desees guardar
                firebaseCell.put("card", cell.getCard());
                row.put(cellKey, firebaseCell);
            }
            firebaseBoard.put(rowKey, row);
        }

        // Guarda el tablero en Firebase
        myRef.setValue(firebaseBoard);
    }


    public Board(){
        initializeBoard();
    }

    public ObservableArrayMap<String, ObservableArrayMap<String, Cell>> getBoard() {
        return board;
    }

    public void initializeBoard(){
        board = new ObservableArrayMap<>();
        for(int i = 0; i < numRows; i++){
            ObservableArrayMap<String, Cell> row = new ObservableArrayMap<>();
            for(int j = 0; j < numCols; j++){
                row.put("cell" + j, new Cell(i, j));
            }
            board.put("row" + i, row);
        }
    }

    public void placeCard(Card card, Cell cell){
        String row = "row" + cell.getRow();
        String col = "cell" + cell.getCol();
        board.get(row).get(col).setCard(card);
        board.get(row).get(col).notifyPropertyChanged(0);
    }

    //public ObservableArrayMap<String, ObservableArrayMap<String, Cell>> getBoard() {
    //    return board;
   // }

    public void notifyPropertyChanged(int fieldId) {
        for (ObservableArrayMap<String, Cell> row : board.values()) {
            for (Cell cell : row.values()) {
                cell.notifyPropertyChanged(0);
            }
        }
    }
    public Card getCardAt(Cell cell) {
        String rowKey = "row" + cell.getRow();
        String colKey = "cell" + cell.getCol();
        return board.get(rowKey).get(colKey).getCard();
    }

    public boolean isEmpty(int row, int col) {
        String rowKey = "row" + row;
        String colKey = "cell" + col;
        return board.get(rowKey).get(colKey).getCard() == null;
    }

    public void setCardAt(int row, int col, Card card) {
        String rowKey = "row" + row;
        String colKey = "cell" + col;
        board.get(rowKey).get(colKey).setCard(card);
    }

}
