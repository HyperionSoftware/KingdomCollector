package cat.udl.hyperion.appmobils.kingdomcollector.Models;

/**
 * La clase CardCollection representa una carta del juego, con sus atributos y métodos y valores numéricos del elemento.
 */
public class CardCollection {
    private int id;
    private String name;
    private int imageResource;
    private String description;
    private String type;
    private int powerArriba;
    private int powerIzquierda;
    private int powerAbajo;
    private int powerDerecha;
    private boolean selected;
    private int owner;

    /**
     * Constructor de la clase Card. Crea una carta con los atributos indicados.
     * @param id Identificador único de la carta.
     * @param name Nombre de la carta.
     * @param powerArriba Poder de la carta en la dirección de arriba.
     * @param powerIzquierda Poder de la carta en la dirección de la izquierda.
     * @param powerAbajo Poder de la carta en la dirección de abajo.
     * @param powerDerecha Poder de la carta en la dirección de la derecha.
     */

    public CardCollection(int id, String name, int imageResource, String description,String type, int powerArriba, int powerIzquierda, int powerAbajo, int powerDerecha) {
        this.id = id;
        this.name = name;
        this.imageResource = imageResource;
        this.description = description;
        this.type = type;
        this.powerArriba = powerArriba;
        this.powerAbajo = powerAbajo;
        this.powerDerecha = powerDerecha;
        this.powerIzquierda = powerIzquierda;
        this.selected = false;
        this.owner = -1;

    }

    // Getters y setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public int getPowerArriba() {
        return powerArriba;
    }
    public void setPowerArriba(int powerArriba) {
        this.powerArriba = powerArriba;
    }
    public int getPowerIzquierda() {
        return powerIzquierda;
    }
    public void setPowerIzquierda(int powerIzquierda) {
        this.powerIzquierda = powerIzquierda;
    }
    public int getPowerAbajo() {
        return powerAbajo;
    }
    public void setPowerAbajo(int powerAbajo) {
        this.powerAbajo = powerAbajo;
    }
    public int getPowerDerecha() {
        return powerDerecha;
    }
    public void setPowerDerecha(int powerDerecha) {
        this.powerDerecha = powerDerecha;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}

