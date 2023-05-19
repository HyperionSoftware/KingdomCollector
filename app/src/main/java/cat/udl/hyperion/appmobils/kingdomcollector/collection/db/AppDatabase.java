package cat.udl.hyperion.appmobils.kingdomcollector.collection.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.CardDao;

@Database(entities = {Card.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CardDao cardDao();
}
