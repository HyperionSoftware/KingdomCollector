package cat.udl.hyperion.appmobils.kingdomcollector.models;

/**
 * La clase CardCollection representa una carta del juego, con sus atributos y métodos y valores numéricos del elemento.
 */
public class Card {
    private int id;
    private String name;
    private int imageUrl;
    private String type;

    private boolean isSelected;



    public Card(int id, String name, int imageResource, String type) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageResource;
        this.type = type;


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

    public int setImageResource() {
        return imageUrl;
    }

    public String getType() {
        return type;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


}


