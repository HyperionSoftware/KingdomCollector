package cat.udl.hyperion.appmobils.kingdomcollector.collection.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CardEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CardDao cardDao();
}