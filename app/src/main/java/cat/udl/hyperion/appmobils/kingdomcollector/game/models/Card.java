package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

import androidx.databinding.ObservableField;

public class Card{
    private int imageUrl;
    private String name;
    private int powerArriba;
    private int powerIzquierda;
    private int powerAbajo;
    private int powerDerecha;
    private ObservableField<Boolean> selected;

    /**
     * Constructor de la clase Card. Crea una carta con los atributos indicados.
     * @param imageResource Identificador único de la imagen de la carta.
     * @param name Nombre de la carta.
     * @param powerArriba Poder de la carta en la dirección de arriba.
     * @param powerIzquierda Poder de la carta en la dirección de la izquierda.
     * @param powerAbajo Poder de la carta en la dirección de abajo.
     * @param powerDerecha Poder de la carta en la dirección de la derecha.
     */
    public Card(int imageResource, String name, int powerArriba, int powerIzquierda, int powerAbajo, int powerDerecha){
        this.imageUrl = imageResource;
        this.name = name;
        this.powerArriba = powerArriba;
        this.powerIzquierda = powerIzquierda;
        this.powerAbajo = powerAbajo;
        this.powerDerecha = powerDerecha;
        this.selected = new ObservableField<>(false);
    }

    public int getImageResource() {
        return imageUrl;
    }

    public void setImageResource(int imageResource) {
        this.imageUrl = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public ObservableField<Boolean> getSelectedField() {
        return selected;
    }

    public void notifyPropertyChanged(int fieldId) {
        selected.notifyChange();
    }

    /*public byte[] getImageUrl() {

    }*/
}
