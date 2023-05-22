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
    List<Card> getAllCards();

    @Query("SELECT * FROM cards ORDER BY RANDOM() LIMIT :limit")
    LiveData<List<Card>> getRandomCards(int limit);

    @Query("SELECT * FROM cards WHERE id = :id")
    Card getCardById(String id);
}
