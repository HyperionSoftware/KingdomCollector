package cat.udl.hyperion.appmobils.kingdomcollector.game.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardDao {
    @Insert
    void insert(Card card);

    @Query("SELECT * FROM cards")
    LiveData<List<Card>> getAllCards();
}
