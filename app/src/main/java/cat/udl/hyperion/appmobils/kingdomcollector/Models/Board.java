package cat.udl.hyperion.appmobils.kingdomcollector.Models;


/**
 * La clase tablero representa el tablero del juego, con sus cartas y posiciones en el mismo.
 * @author joanbonellruiz
 * @author carlosmg
 * @author claudiasarlle
 * @author ricardbp
 * */
public class Board {
    private Card[][] cells;
    private static final int size = 3;

    public Board() {
        this.cells = new Card[size][size];
    }

    public static int getSize() {
        return size;
    }


    // Métodos para colocarCarta, obtenerCarta, reiniciarTablero, verificarAdyacentes (similar a la respuesta anterior)
        public void colocarCarta(int x, int y, Card carta) {
            if (cells[x][y] == null) {
                cells[x][y] = carta;
                verificarAdyacentes(x, y, carta);
            }
        }

        public Card obtenerCarta(int x, int y) {
            return cells[x][y];
        }

        public void reiniciarTablero() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    cells[i][j] = null;
                }
            }
        }
        private void verificarAdyacentes(int x, int y, Card carta) {
            // Norte
            if (y > 0 && cells[x][y - 1] != null && cells[x][y - 1].getOwner() != carta.getOwner()) {
                if (carta.getPowerArriba() > cells[x][y - 1].getPowerAbajo()) {
                    cells[x][y - 1].setOwner(carta.getOwner());
                }
            }

            // Este
            if (x < size - 1 && cells[x + 1][y] != null && cells[x + 1][y].getOwner() != carta.getOwner()) {
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

