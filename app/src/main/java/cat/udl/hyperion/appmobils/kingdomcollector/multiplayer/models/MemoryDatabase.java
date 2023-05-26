package cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.models;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {GameControllerOnline.class}, version = 1, exportSchema = false)
public abstract class MemoryDatabase extends RoomDatabase {
    public abstract GameControllerDAO gameDAO();
}

