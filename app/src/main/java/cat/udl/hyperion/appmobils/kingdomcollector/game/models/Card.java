package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.HashMap;
import java.util.Map;

import cat.udl.hyperion.appmobils.kingdomcollector.game.models.player.Player;

@Entity(tableName = "cards")
public class Card implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;
    private int imageUrl;
    private String name;
    private String type;
    private int powerArriba;
    private int powerIzquierda;
    private int powerAbajo;
    private int powerDerecha;
    private boolean isSelected;
    @Ignore
    private ObservableField<Boolean> selected;

    @Ignore
    private MutableLiveData<Player> owner;


    public Card(Map<String, Object> card) {

    }

    public Card(){

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
    public Card(String id,int imageResource, String name,String type, int powerArriba, int powerIzquierda, int powerAbajo, int powerDerecha){
        this.id = id;
        this.imageUrl = imageResource;
        this.name = name;
        this.type = type;
        this.powerArriba = powerArriba;
        this.powerIzquierda = powerIzquierda;
        this.powerAbajo = powerAbajo;
        this.powerDerecha = powerDerecha;
        this.selected = new ObservableField<>(false);
        if(owner == null){
            this.owner = new MutableLiveData<>();
        }
        else{
            this.owner.setValue(owner.getValue());
        }
    }

    public String getId() {
        return id;
    }

    public LiveData<Player> getOwnerField() {
        return owner;
    }
    public Player getOwner() {
        if (owner != null) {
            return owner.getValue();
        } else {
            return null; // O devuelve un objeto Player predeterminado o maneja el caso nulo según tus necesidades
        }
    }


    public void setOwner(Player owner) {
        Log.d("setOwner", "owner is: " + (owner != null ? owner.getName() : "null"));
        if (this.owner != null) {
            this.owner.setValue(owner);
        }
    }



    public int getImageResource() {
        return imageUrl;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setOwner(MutableLiveData<Player> owner) {
        Log.d("setOwner", "owner is: " + (owner != null ? owner.getValue().getName() : "null"));
        this.owner.setValue(owner.getValue());
    }

    public void setSelected(ObservableField<Boolean> selected) {
        this.selected = selected;
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

    public int getMinValue() {
        return Math.min(Math.min(powerArriba, powerAbajo), Math.min(powerIzquierda, powerDerecha));
    }


    public void setSelected(boolean selected) {
        isSelected = selected;
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

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(imageUrl);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeInt(powerArriba);
        dest.writeInt(powerIzquierda);
        dest.writeInt(powerAbajo);
        dest.writeInt(powerDerecha);
        dest.writeByte((byte) (isSelected ? 1 : 0));  // If isSelected is true, write 1, otherwise write 0
        // Check if owner is null before calling getValue()
        if (owner != null && owner.getValue() != null) {
            dest.writeParcelable(owner.getValue(), flags);
        } else {
            dest.writeParcelable(null, flags);
        }
    }
    protected Card(Parcel in) {
        id = in.readString();
        imageUrl = in.readInt();
        name = in.readString();
        type = in.readString();
        powerArriba = in.readInt();
        powerIzquierda = in.readInt();
        powerAbajo = in.readInt();
        powerDerecha = in.readInt();
        isSelected = in.readByte() != 0;  // isSelected is boolean; if it is true, then it is 1, otherwise it is 0
        selected = new ObservableField<>(isSelected);

        // Read owner as Parcelable and set it to owner LiveData
        Player ownerPlayer = in.readParcelable(Player.class.getClassLoader());
        if (ownerPlayer == null) {
            owner = new MutableLiveData<>(null);
        } else {
            owner = new MutableLiveData<>(ownerPlayer);
        }
    }
    // Creador Parcelable necesario
    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}
