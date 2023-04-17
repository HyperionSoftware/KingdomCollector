package cat.udl.hyperion.appmobils.kingdomcollector.Models;

/**
 * La clase Board representa el tablero del juego, con sus cartas y posiciones en el mismo.
 */
public class Board {
    private CardCollection[][] cells; // Matriz de cartas que representa las celdas del tablero.
    private static final int size = 3; // Tamaño del tablero, definido como una constante.

    /**
     * Constructor de la clase Board.
     * Inicializa la matriz de celdas del tablero.
     */
    public Board() {
        this.cells = new CardCollection[size][size];
    }

    /**
     * Getter para obtener el tamaño del tablero.
     *
     * @return El tamaño del tablero.
     */
    public static int getSize() {
        return size;
    }

    /**
     * Método para colocar una carta en una celda del tablero.
     *
     * @param x La posición horizontal de la celda.
     * @param y La posición vertical de la celda.
     * @param carta La carta que se quiere colocar.
     */
    public void colocarCarta(int x, int y, CardCollection carta) {
        if (cells[x][y] == null) { // Si la celda está vacía, se puede colocar la carta.
            cells[x][y] = carta; // Coloca la carta en la celda.
            verificarAdyacentes(x, y, carta); // Comprueba si la carta afecta a otras cartas adyacentes.
        }
    }

    /**
     * Método para obtener la carta que está en una celda del tablero.
     *
     * @param x La posición horizontal de la celda.
     * @param y La posición vertical de la celda.
     * @return La carta que está en la celda, o null si la celda está vacía.
     */
    public CardCollection obtenerCarta(int x, int y) {
        return cells[x][y]; // Devuelve la carta que está en la celda.
    }

    /**
     * Método para reiniciar el tablero, vaciando todas las celdas.
     */
    public void reiniciarTablero() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = null; // Vacía la celda i,j del tablero.
            }
        }
    }

    /**
     * Método para verificar si la carta colocada afecta a otras cartas adyacentes en el tablero.
     *
     * @param x La posición horizontal de la celda donde se ha colocado la carta.
     * @param y La posición vertical de la celda donde se ha colocado la carta.
     * @param carta La carta que se ha colocado en la celda.
     */
    private void verificarAdyacentes(int x, int y, CardCollection carta) {
        // Norte
        if (y > 0 && cells[x][y - 1] != null && cells[x][y - 1].getOwner() != carta.getOwner()) {
            if (carta.getPowerArriba() > cells[x][y - 1].getPowerAbajo()) {
                cells[x][y - 1].setOwner(carta.getOwner());
            }
        }

        // Este
        if (x < size - 1 && cells[x + 1][y]            != null && cells[x + 1][y].getOwner() != carta.getOwner()) {
            if (carta.getPowerDerecha() > cells[x + 1][y].getPowerIzquierda()) {
                cells[x + 1][y].setOwner(carta.getOwner());
            }
        }

        // Sur
        if (y < size - 1 && cells[x][y + 1] != null && cells[x][y + 1].getOwner() != carta.getOwner()) {
            if (carta.getPowerAbajo() > cells[x][y + 1].getPowerArriba()) {
                cells[x][y + 1].setOwner(carta.getOwner());
            }
        }

        // Oeste
        if (x > 0 && cells[x - 1][y] != null && cells[x - 1][y].getOwner() != carta.getOwner()) {
            if (carta.getPowerIzquierda() > cells[x - 1][y].getPowerDerecha()) {
                cells[x - 1][y].setOwner(carta.getOwner());
            }
        }
    }
}

