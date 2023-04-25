package cat.udl.hyperion.appmobils.kingdomcollector.Models;

/**
 * La clase CardCollection representa una carta del juego, con sus atributos y métodos y valores numéricos del elemento.
 */
public class CardCollection {
    private int id;
    private String name;
    private String imagePath;
    private String description;
    private String type;
    private int powerArriba;
    private int powerIzquierda;
    private int powerAbajo;
    private int powerDerecha;
    private boolean selected;
    private int owner;



    public CardCollection(int id, String name, String imagePath, String description, String type, int powerArriba, int powerIzquierda, int powerAbajo, int powerDerecha) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.type = type;
        this.powerArriba = powerArriba;
        this.powerIzquierda = powerIzquierda;
        this.powerAbajo = powerAbajo;
        this.powerDerecha = powerDerecha;
        this.selected = false;
        this.owner = -1;

    }

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

}


