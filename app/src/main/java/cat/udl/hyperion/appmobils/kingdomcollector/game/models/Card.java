package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.HashMap;
import java.util.Map;


import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.Player;

public class Card{
    private int imageUrl;
    private String name;
    private String type;
    private int powerArriba;
    private int powerIzquierda;
    private int powerAbajo;
    private int powerDerecha;
    private boolean isSelected;
    private ObservableField<Boolean> selected;
    private MutableLiveData<Player> owner;

    public Card(Map<String, Object> card) {

    }

    // Método para convertir los datos de la carta a un mapa que se puede guardar en Firebase
    public Map<String, Object> toMap() {
        // Crear un nuevo HashMap para almacenar los datos
        HashMap<String, Object> resultado = new HashMap<>();

        // Agregar cada campo de la carta al mapa con su respectivo nombre de clave
        resultado.put("imageUrl", imageUrl);
        resultado.put("name", name);
        resultado.put("type", type);
        resultado.put("powerArriba", powerArriba);
        resultado.put("powerIzquierda", powerIzquierda);
        resultado.put("powerAbajo", powerAbajo);
        resultado.put("powerDerecha", powerDerecha);
        resultado.put("isSelected", isSelected);

        // Si la carta tiene un propietario, guardar el nombre del propietario
        // Aquí estamos asumiendo que la clase Player tiene un método getName() que devuelve el nombre del jugador
        if (owner.getValue() != null) {
            resultado.put("owner", owner.getValue().getName());
        } else {
            // Si la carta no tiene propietario, guardar null
            resultado.put("owner", null);
        }

        // Devolver el mapa
        return resultado;
    }

    /**
     * Constructor de la clase Card. Crea una carta con los atributos indicados.
     * @param imageResource Identificador único de la imagen de la carta.
     * @param name Nombre de la carta.
     * @param type Tipo de la carta.
     * @param powerArriba Poder de la carta en la dirección de arriba.
     * @param powerIzquierda Poder de la carta en la dirección de la izquierda.
     * @param powerAbajo Poder de la carta en la dirección de abajo.
     * @param powerDerecha Poder de la carta en la dirección de la derecha.
     */
    public Card(String name,int imageResource,String type, int powerArriba, int powerIzquierda, int powerAbajo, int powerDerecha){
        this.imageUrl = imageResource;
        this.name = name;
        this.type = type;
        this.powerArriba = powerArriba;
        this.powerIzquierda = powerIzquierda;
        this.powerAbajo = powerAbajo;
        this.powerDerecha = powerDerecha;
        this.selected = new ObservableField<>(false);
        this.owner = new MutableLiveData<>(null);
    }
    public LiveData<Player> getOwnerField() {
        return owner;
    }
    public Player getOwner() {
        return owner.getValue();
    }

    public void setOwner(Player owner) {
        this.owner.setValue(owner);
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

    public ObservableField<Boolean> getSelectedField() {
        return selected;
    }

    public void notifyPropertyChanged(int fieldId) {
        selected.notifyChange();
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
