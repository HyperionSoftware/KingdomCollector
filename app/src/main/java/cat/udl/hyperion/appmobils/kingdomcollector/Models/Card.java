package cat.udl.hyperion.appmobils.kingdomcollector.Models;

//import android.sax.Element;


import cat.udl.hyperion.appmobils.kingdomcollector.Models.Player.Player;

/**
 * La clase Carta representaría una carta del juego, con sus atributos y métodos y valores numéricos de elemento.
 * */
public class Card {
    private int id;
    private String name;
    private int powerArriba;
    private int powerIzquierda;
    private int powerAbajo;
    private int powerDerecha;
    private boolean selected;
    private int owner;

    public Card(int id, String name, int powerArriba, int powerIzquierda, int powerAbajo, int powerDerecha) {
        this.id = id;
        this.name = name;
        this.powerArriba = powerArriba;
        this.powerIzquierda = powerIzquierda;
        this.powerAbajo = powerAbajo;
        this.powerDerecha = powerDerecha;
        this.owner = -1;
        this.selected = false;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
    // Getters y setters

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPowerAbajo() {
        return powerAbajo;
    }

    public int getPowerArriba() {
        return powerArriba;
    }

    public int getPowerDerecha() {
        return powerDerecha;
    }

    public int getPowerIzquierda() {
        return powerIzquierda;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPowerAbajo(int powerAbajo) {
        this.powerAbajo = powerAbajo;
    }

    public void setPowerArriba(int powerArriba) {
        this.powerArriba = powerArriba;
    }

    public void setPowerDerecha(int powerDerecha) {
        this.powerDerecha = powerDerecha;
    }

    public void setPowerIzquierda(int powerIzquierda) {
        this.powerIzquierda = powerIzquierda;
    }

    public boolean isSelected(){
        return selected;
    }
}

