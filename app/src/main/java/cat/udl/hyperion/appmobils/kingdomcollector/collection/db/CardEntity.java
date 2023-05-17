package cat.udl.hyperion.appmobils.kingdomcollector.collection.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CardEntity {
    @PrimaryKey
    public String id;

    public int imageUrl;
    public String name;
    public String type;
    public int powerArriba;
    public int powerIzquierda;
    public int powerAbajo;
    public int powerDerecha;
    public boolean isSelected;
    // Considera cómo manejarás el propietario en la base de datos local.
    // Tal vez necesites una tabla separada para los jugadores y una clave externa aquí.
}