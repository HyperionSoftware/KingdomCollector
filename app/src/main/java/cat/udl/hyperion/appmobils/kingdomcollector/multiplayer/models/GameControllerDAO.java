package cat.udl.hyperion.appmobils.kingdomcollector.multiplayer.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GameControllerDAO {
    @Insert
    long[] insertAll(GameControllerOnline... games);

    @Insert
    long insert(GameControllerOnline gameControllerOnline);

    @Query("SELECT * FROM gameControllerOnline")
    List<GameControllerOnline> getAll();

    @Delete
    void deleteOne(GameControllerOnline gameControllerOnline);

    @Query("DELETE FROM gameControllerOnline")
    void deleteAll();


    @Update
    void update(GameControllerOnline game);
}